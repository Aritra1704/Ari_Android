package com.example.testplay;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdemo.dialog.ColorPickerDialog;
import com.example.testdemo.view.CircleView;
import com.example.testdemo.view.MyView;
import com.example.testdemo.view.PlayView;
import com.example.testplay.Object.Coordinate;

public class StartActivity extends Activity implements ColorPickerDialog.OnColorChangedListener
{

	private ImageView imgvDot;
	private TextView txtvDot;
	private Context context;
	private RelativeLayout rlview;
	private RelativeLayout.LayoutParams params;
	private Vector<Coordinate> vecCoordinate;
	private Paint mPaint;
	private MaskFilter  mEmboss;
	private MaskFilter  mBlur;
	private MyView mv;
	private PlayView pv;
	private CircleView circleview;
	private int coorX,coorY;
	private int COUNT = 0;
	private static boolean incorrect = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFFFF0000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(20);
		mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
		mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
//		
//		mv= new MyView(this,mPaint);
//		mv.setDrawingCacheEnabled(true);
////		mv.setBackgroundResource(R.layout.start);//set the back ground if you wish to
//		mv.setBackgroundResource(R.drawable.round_layout_bg_xml);//set the back ground if you wish to
//		setContentView(mv);
		
		if(getResources().getDisplayMetrics().density >= 2.0)
		{
			coorX = 70;
			coorY = 100;
		}
		else if(getResources().getDisplayMetrics().density >= 1.5 && getResources().getDisplayMetrics().density < 2.0)
		{
			coorX = 40;
			coorY = 60;
		}
		else if(getResources().getDisplayMetrics().density >= 1.0 && getResources().getDisplayMetrics().density < 1.5)
		{
			coorX = 25;
			coorY = 40;
		}
		initializecontrols();
//		initialize();
		setCoordinates();
//		setview();

		pv= new PlayView(this,mPaint,getResources().getDisplayMetrics().density,vecCoordinate);
		pv.setDrawingCacheEnabled(true);
		pv.setBackgroundResource(R.drawable.round_layout_bg_xml);//set the back ground if you wish to
		setContentView(pv);

		
		setTouchListener(0);

//		setContentView(new CircleView(getBaseContext(),vecCoordinate));
	}

	private void initializecontrols() 
	{
		context = StartActivity.this;
		
		rlview = (RelativeLayout) findViewById(R.id.rlview);
		
		vecCoordinate = new Vector<Coordinate>();
	}
	
	private void initialize() 
	{
	}
	
	private void setview() 
	{
		rlview.removeAllViews();
		for (int i = 0; i < vecCoordinate.size(); i++) 
		{
			imgvDot = new ImageView(context);
			imgvDot.setBackgroundResource(R.drawable.dot);
			
			txtvDot = new TextView(context);
			txtvDot.setBackgroundResource(R.drawable.dot);
			txtvDot.setHeight(30);
			txtvDot.setWidth(30);
			txtvDot.setGravity(Gravity.CENTER);
			txtvDot.setText(""+(i+1));
			
			params = new RelativeLayout.LayoutParams(80, 80);
			params.topMargin = vecCoordinate.get(i).coordiX;
			params.leftMargin = vecCoordinate.get(i).coordiY;
			params.rightMargin = 10;
			params.bottomMargin = 10;
			rlview.addView(txtvDot, params);
		}
	}
	
	private void setCoordinates() 
	{
//		Random rand = new Random();
//		for (int i = 0; i < 10; i++) 
//		{
////			Coordinate objCoordinate = new Coordinate();
////			objCoordinate.coordiX = rand.nextInt(500);
////			objCoordinate.coordiY = rand.nextInt(800);
//			Coordinate objCoordinate = new Coordinate();
//			objCoordinate.coordiX = coorX*(i+1);
//			objCoordinate.coordiY = coorY*(i+1);
//	        vecCoordinate.add(objCoordinate);
//		}
		
		//house
		Coordinate objCoordinate;
		
		objCoordinate = new Coordinate();//1
		objCoordinate.coordiX = coorX*11;
		objCoordinate.coordiY = coorY*3;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//2
		objCoordinate.coordiX = coorX*5;
		objCoordinate.coordiY = coorY/3;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//3
		objCoordinate.coordiX = coorX/2;
		objCoordinate.coordiY = coorY*3;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//4
		objCoordinate.coordiX = coorX*9;
		objCoordinate.coordiY = coorY*3;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//5
		objCoordinate.coordiX = coorX*9;
		objCoordinate.coordiY = coorY*10;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//6
		objCoordinate.coordiX = coorX*2;
		objCoordinate.coordiY = coorY*10;
        vecCoordinate.add(objCoordinate);
        
        objCoordinate = new Coordinate();//7
		objCoordinate.coordiX = coorX*2;
		objCoordinate.coordiY = (int) (coorY*3.5);
        vecCoordinate.add(objCoordinate);
	}

	private void setTouchListener(int count) 
	{
		COUNT = count;
		
		pv.setOnTouchListener(new OnTouchListener() 
		{
			@Override
			public boolean onTouch(View v, MotionEvent event) 
			{
				if(COUNT < vecCoordinate.size())
				{
					int minX = vecCoordinate.get(COUNT).coordiX-10;
					int maxX = vecCoordinate.get(COUNT).coordiX+10;
					int minY = vecCoordinate.get(COUNT).coordiY-10;
					int maxY = vecCoordinate.get(COUNT).coordiY+10;
					
					int eventX = (int)event.getX();
					int eventY = (int)event.getY();
//					Log.d("touched", "X: "+event.getX()+"Y: "+event.getY());
					if((eventX >= minX) && (eventX <= maxX))
					{
		//					Log.d("touched", "MinX: "+minX+" MaxX: "+maxX+" getX: "+event.getX());
		//					Log.d("touched", "MinY: "+minY+" MaxY: "+maxY+" getY: "+event.getY());
						if((eventY >= minY) && (eventY <= maxY))
						{
							Log.d("touched", "dot: "+COUNT);
							COUNT++;
							incorrect = false;
						}
					}
					else
					{
						for (int i = COUNT; i < vecCoordinate.size(); i++) 
						{
							if((eventX >= vecCoordinate.get(i).coordiX-10) && (eventX <= vecCoordinate.get(i).coordiX+10))
							{
								if((eventY >= vecCoordinate.get(i).coordiY-10) && (eventY <= vecCoordinate.get(i).coordiY+10))
								{
									if(!incorrect || (COUNT == vecCoordinate.size()))
									{
										Log.d("touched", "X: "+eventX+" Y: "+eventY+" i: "+i);
										Toast.makeText(StartActivity.this, "Incorrect Sequence", Toast.LENGTH_SHORT).show();
										incorrect = true;
//									Log.d("touched", "dot: "+i);
									}
								}
							}
						}
					}
					
				}
				return false;
			}
		});
	}

	@Override
	public void colorChanged(int color) 
	{
		mPaint.setColor(color);		
	}
}
