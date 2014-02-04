package com.example.testbroadcastreceiver;

import com.example.testbroadcastreceiver.receiver.MyReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FourthActiviy extends Activity
{
	
	IntentFilter filter;
	MyReceiver receiver;
	
	private static boolean fourth = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		
		Button btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(FourthActiviy.this, FifthActivity.class));
			}
		});
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		if(!fourth)
		{
			filter = new IntentFilter("com.example.ACTION_TERMINATE");
			receiver = new MyReceiver();
			registerReceiver(receiver, filter);
			
			fourth = true;
		}
	}
	
	@Override
    protected void onDestroy() 
	{
        super.onDestroy();
        unregisterReceiver(receiver);
        fourth = false;
    }
	
}
