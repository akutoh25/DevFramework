package com.engine.framework.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;

public class DialogUtil {

	public enum DialogType {
		ALERT,
		CONFIRMATION,
		CUSTOM
	}
	
	AlertDialog.Builder dialogBuilder;
	
	public static void showAlertDialog(Context context, String title, String message) {
		
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
			dialogBuilder.setTitle(title);
			dialogBuilder.setMessage(message);
			dialogBuilder.setCancelable(true);
			dialogBuilder.setPositiveButton("OK", new OnClickListener() {
	
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
									   
								   });
			
			AlertDialog dialog = dialogBuilder.create();
			dialog.show();				   	
			
	}
	
	public static void showAlertDialog(Context context, String title, String message, OnDismissListener listener) {
		
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		dialogBuilder.setTitle(title);
		dialogBuilder.setMessage(message);
		dialogBuilder.setCancelable(false);
		dialogBuilder.setOnDismissListener(listener);
		dialogBuilder.setPositiveButton("OK", new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
								   
							   });
		
		AlertDialog dialog = dialogBuilder.create();
		dialog.show();				   	
		
}
	
}
