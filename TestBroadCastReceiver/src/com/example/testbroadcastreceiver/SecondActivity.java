package com.example.testbroadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testbroadcastreceiver.receiver.MyReceiver;

public class SecondActivity extends Activity
{
	IntentFilter filter;
	MyReceiver receiver;
	
	private static boolean second = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Button btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
			}
		});
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		if(!second)
		{
			filter = new IntentFilter("com.example.ACTION_TERMINATE");
			receiver = new MyReceiver();
			registerReceiver(receiver, filter);	
			
			second = true;
		}
	}
	
	@Override
    protected void onDestroy() 
	{
        super.onDestroy();
        unregisterReceiver(receiver);
        
        second = false;
    }
}
