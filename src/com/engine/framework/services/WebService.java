/**
 * Author: Ronald Phillip C. Cui
 * Reference Author: Napolean A. Patague
 * Date: Oct 13, 2013
 */
package com.engine.framework.services;

import java.io.InputStream;

import com.engine.framework.enumerations.ResponseStatus;
import com.engine.framework.helper.FileHelper;
import com.engine.framework.helper.WebServiceHelper;
import com.engine.framework.services.interfaces.ServiceListener;
import com.engine.framework.services.response.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class WebService extends AsyncTask<WebServiceInfo,Integer,Response> {

	private ServiceListener mListener;
	private ProgressDialog mDialog;
	protected int mRequestId;

	public WebService(int requestId) {
		mRequestId  = requestId;
	}
	
	public WebService setProgressDialog(ProgressDialog dialog) {
		mDialog = dialog;
		return this;
	}
	
	public WebService setProgressDialog(Context context, String title, String message) {
		mDialog = new ProgressDialog(context);
		mDialog.setTitle(title);
		mDialog.setMessage(message);
		mDialog.setCancelable(false);
		mDialog.setIndeterminate(true);
		return this;
	}
	
	public ProgressDialog getProgressDialog() {
		return mDialog;
	}
	
	public WebService setServiceListener(ServiceListener listener) {
		mListener = listener;
		return this;
	}
	
	public ServiceListener getServiceListener() {
		return mListener;
	}
	
	@Override
	protected void onPreExecute() {
		if(mDialog != null) mDialog.show();
	}
	
	@Override
	protected Response doInBackground(WebServiceInfo... params) {
		
		WebServiceInfo wsInfo = params[0];
		
		Response response = new Response();
		
		try {
			
			InputStream source = WebServiceHelper.getWebServiceResponse(wsInfo);
			
			if(source != null) {
				
				response.setResult( FileHelper.getResponseString( source ) );
				response.setStatus( ResponseStatus.SUCCESS );
				source.close();
				
				return response;
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setStatus(ResponseStatus.FAILED);
		
		return response;
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {	
		if( mListener != null ) mListener.onProgressUpdate(progress);
	}

	@Override 
	protected void onPostExecute(Response response) {
		
		if(mDialog != null) mDialog.dismiss();
		
		if( mListener != null ) mListener.serviceCallback(response, mRequestId);
		
	}
	
}