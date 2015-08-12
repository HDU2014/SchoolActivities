package com.lugeek.schoolevents.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.lugeek.schoolevents.R;

public class SearchBar extends EditText
{
	public SearchBar(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		setup(context);
	}
	
	public SearchBar(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		setup(context);
	}
	
	public SearchBar(Context context) 
	{
		super(context);
		setup(context);
	}
	
	public void setup(Context context)
	{
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.search_image);
		paint = new Paint();
		this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() 
		{
			@Override
			public void onGlobalLayout() 
			{
				padding = 10;
				size = getHeight() - (padding * 2);
				scaled = Bitmap.createScaledBitmap(bitmap, size, size, true);
			}
		});
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		if(scaled != null)
			canvas.drawBitmap(scaled, (this.getWidth() - padding) - size, getHeight() - padding - size, paint);
	}
	
	private int padding;
	private int size;
	
	
	private Bitmap bitmap;
	private Bitmap scaled;
	private Paint paint;
}
