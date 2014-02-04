package com.example.testbroadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		Button btnnext = (Button) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(FirstActivity.this, SecondActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

}
