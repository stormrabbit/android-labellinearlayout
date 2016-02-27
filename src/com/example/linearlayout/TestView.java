package com.example.linearlayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {


	public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}



	public TestView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TestView(Context context) {
		this(context, null, 0);
	}
	
	private void initView() {
		
	}

}
