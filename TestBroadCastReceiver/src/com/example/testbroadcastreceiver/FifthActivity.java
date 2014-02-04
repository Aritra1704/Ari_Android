package com.example.testbroadcastreceiver;

import com.example.testbroadcastreceiver.receiver.MyReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FifthActivity extends Activity
{
	IntentFilter filter;
	MyReceiver receiver;
	
	private static boolean fifth = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		
		Button btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
//				startActivity(new Intent(FifthActivity.this, SecondActivity.class));
				Intent intent = new Intent();
				intent.setAction("com.example.ACTION_TERMINATE");
				sendBroadcast(intent);
			}
		});
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		if(!fifth)
		{
			filter = new IntentFilter("com.example.ACTION_TERMINATE");
			receiver = new MyReceiver();
			registerReceiver(receiver, filter);
			
			fifth = true;
		}
	}
	
	@Override
    protected void onDestroy() 
	{
        super.onDestroy();
        unregisterReceiver(receiver);
        
        fifth = false;
    }
	
}
