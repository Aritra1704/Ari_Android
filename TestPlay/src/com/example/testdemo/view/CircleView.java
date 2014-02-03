package com.example.testdemo.view;

import java.util.Vector;

import com.example.testplay.Object.Coordinate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CircleView extends View
{

	Vector<Coordinate> vecCoordinate;
	
	public CircleView(Context context, Vector<Coordinate> vecCoordinate) 
	{
		super(context);
		this.vecCoordinate = vecCoordinate;
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		int x = getWidth();
	    int y = getHeight();
	    int radius;
	    radius = 40;
	    Paint paint = new Paint();
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.WHITE);
	    canvas.drawPaint(paint);
	    
	    for (int i = 0; i < vecCoordinate.size(); i++) 
	    {
	    	// Use Color.parseColor to define HTML colors
	    	paint.setColor(Color.parseColor("#CD5C5C"));
	    	canvas.drawCircle(vecCoordinate.get(i).coordiX, vecCoordinate.get(i).coordiY, radius, paint);
	    	
	    	paint.setColor(Color.BLACK);
	    	paint.setStyle(Paint.Style.FILL);
	    	paint.setTextSize(40.0f);
	    	if(i<9)
	    		canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-10, vecCoordinate.get(i).coordiY+15, paint);
	    	else
	    		canvas.drawText(""+(i+1), vecCoordinate.get(i).coordiX-20, vecCoordinate.get(i).coordiY+15, paint);
		}
	}
}
