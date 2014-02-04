package com.example.testbroadcastreceiver;

import com.example.testbroadcastreceiver.receiver.MyReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity
{
	
	IntentFilter filter;
	MyReceiver receiver;
	
	private static boolean third = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		Button btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(ThirdActivity.this, FourthActiviy.class));
			}
		});
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		if(!third)
		{
			filter = new IntentFilter("com.example.ACTION_TERMINATE");
			receiver = new MyReceiver();
			registerReceiver(receiver, filter);
			
			third = true;
		}
	}

	@Override
    protected void onDestroy() 
	{
        super.onDestroy();
        unregisterReceiver(receiver);
        
        third = false;
    }
	
}
