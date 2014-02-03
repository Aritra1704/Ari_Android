package com.example.testdemo.view;

import java.util.Vector;

import com.example.testplay.Object.Coordinate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PlayView extends View
{
	private static final float MINP = 0.25f;
	private static final float MAXP = 0.75f;
	private Bitmap  mBitmap;
	private Canvas  mCanvas;
	private Path    mPath;
	private Paint   mBitmapPaint;
	private Context context;
	private Paint mPaint;
	private Vector<Coordinate> vecCoordinate;
	private float density;
	
	public PlayView(Context context, Paint mPaint, float density, Vector<Coordinate> vecCoordinate) 
	{
		super(context);
		
		this.context = context;
		mPath = new Path();
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		
		this.mPaint= mPaint;
		
		this.density = density;
		this.vecCoordinate = vecCoordinate;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);
		
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);

	}
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		int radius = 10;
		
		Log.d("density", ""+density);
		if(density>=2.0)
			radius = 40;
		else if(density>=1.5 && density<2.0)
			radius = 20;
		else if(density>=1.0 && density<1.5)
			radius = 15;
	    Paint paint = new Paint();
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.CYAN);
	    canvas.drawPaint(paint);
	    
	    for (int i = 0; i < vecCoordinate.size(); i++) 
	    {
	    	// Use Color.parseColor to define HTML colors
	    	paint.setColor(Color.parseColor("#CD5C5C"));
	    	canvas.drawCircle(vecCoordinate.get(i).coordiX, vecCoordinate.get(i).coordiY, radius, paint);
	    	
	    	paint.setColor(Color.BLACK);
	    	paint.setStyle(Paint.Style.FILL);
	    	if(density>=2.0)
	    	{
	    		paint.setTextSize(40.0f);
	    		if(i<9)
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-10, vecCoordinate.get(i).coordiY+15, paint);
	    		else
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-20, vecCoordinate.get(i).coordiY+15, paint);
	    	}
			else if(density>=1.5 && density<2.0)
			{
				paint.setTextSize(25.0f);
				if(i<9)
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-8, vecCoordinate.get(i).coordiY+12, paint);
	    		else
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-17, vecCoordinate.get(i).coordiY+12, paint);
			}
			else if(density>=1.0 && density<1.5)
			{
				paint.setTextSize(20.0f);
				if(i<9)
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-5, vecCoordinate.get(i).coordiY+10, paint);
	    		else
	    			canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-15, vecCoordinate.get(i).coordiY+10, paint);
			}
		}
		
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
		canvas.drawPath(mPath, mPaint);
	}
	
	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) 
	{
		//showDialog(); 
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;

	}
	private void touch_move(float x, float y) 
	{
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) 
		{
		    mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
		    mX = x;
		    mY = y;
		}
	}
	private void touch_up() 
	{
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath.reset();
//		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
//		mPaint.setMaskFilter(null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		float x = event.getX();
		float y = event.getY();
	
		switch (event.getAction()) 
		{
		    case MotionEvent.ACTION_DOWN:
		        touch_start(x, y);
		        invalidate();
		        break;
		    case MotionEvent.ACTION_MOVE:
	
		        touch_move(x, y);
		        invalidate();
		        break;
		    case MotionEvent.ACTION_UP:
		        touch_up();
		        invalidate();
		        break;
		}
		return true;
	}  
}
