package com.example.testbroadcastreceiver.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Log.d("context", context.getClass().toString());
		((Activity) context).finish();
	}

}
