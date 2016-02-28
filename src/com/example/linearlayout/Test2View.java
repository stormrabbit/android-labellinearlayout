package com.example.linearlayout;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Test2View extends LinearLayout {

	private int contentWidth;

	public Test2View(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public Test2View(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Test2View(Context context) {
		this(context, null, 0);
	}

	private void initView() {
		View.inflate(getContext(), R.layout.ll_content, this);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		contentWidth = MeasureSpec.getSize(widthMeasureSpec);
		int rh = MeasureSpec.getSize(heightMeasureSpec);
	}

	public void addLabel(final String[] names) {
		ArrayList<TextView> tViews = new ArrayList<TextView>();
		// 1 首先,测量出content的宽度
		int margin = 15;
		int lineWidth = 0;
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);

		if (contentWidth == 0) {
				this.measure(widthMeasureSpec, heightMeasureSpec);
				contentWidth = getWidth();
		}
		// 2 创建一个横排linearlayout,宽度设置为matchparent,高度为0dp,weight1,
		LinearLayout llLine = (LinearLayout) View.inflate(getContext(),
				R.layout.ll_line, null);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, 0, 1.0f);

		// 2.1开始循环
		for (String name : names) {
			// 3 创建一个textview,设置内容
			TextView tView = (TextView) View.inflate(getContext(),
					R.layout.holder_text_view, null);
			tView.setText(name);
			// 4 测量textView的宽度
			tView.measure(widthMeasureSpec, heightMeasureSpec);
			int tViewWidth = tView.getMeasuredWidth();
			// 5 将宽度和margin值累加
			// 6 如果宽度值小于content的宽度,将textView保存在集合中,继续下一步
			if ((lineWidth + margin * 2 + tViewWidth) < contentWidth) {
				tViews.add(tView);
				lineWidth = margin * 2 + tViewWidth + lineWidth;
			}
			// 7 如果宽度之大于等于content的高度,则减去累加值
			else {
				// 8 用content的宽度-目前累加的宽度,得出剩余宽度
				int leftWidth = contentWidth - lineWidth;
				// 9 用剩余宽度除以集合的个数,获得panding值
				if (tViews.size() == 0) {
					System.out.println();
				}
				int padding = leftWidth / tViews.size();
				// 10 从集合中拿出textview,按照padding值,marging值设置lieanrylayout
				for (int i = 0; i < tViews.size(); i++) {
					TextView textView = tViews.get(i);
					LinearLayout.LayoutParams layoutParams2 = new LayoutParams(
							layoutParams.WRAP_CONTENT,
							layoutParams.WRAP_CONTENT);
					layoutParams2.rightMargin = margin;
					layoutParams2.leftMargin = margin;
					int paddingLeft = textView.getPaddingLeft();
					int paddingRight = textView.getPaddingRight();
					int paddingBottom = textView.getPaddingBottom();
					int paddingTop = textView.getPaddingTop();
					textView.setPadding(paddingLeft + (padding / 2),
							paddingTop, paddingRight + (padding / 2),
							paddingBottom);
					llLine.addView(textView, layoutParams2);
				}
				// 11 将linearlayout添加至content上
				this.addView(llLine, layoutParams);
				// 12 清除集合数据 累加宽度清零 新建linearlayout
				tViews.clear();
				tViews.add(tView);
				lineWidth = margin * 2 + tViewWidth;
				llLine = (LinearLayout) View.inflate(getContext(),
						R.layout.ll_line, null);
				// 13 集合数据中添加新的textview,宽度新的累加
				// 14 继续下一轮循环
			}

		}
		// 循环结束后,检查tView里面的值,如果还有剩余,则继续添加

		if (tViews.size() != 0) {
			// 8 用content的宽度-目前累加的宽度,得出剩余宽度
			int leftWidth = contentWidth - lineWidth;
			// 9 用剩余宽度除以集合的个数,获得panding值
			int padding = leftWidth / tViews.size();
			for (int i = 0; i < tViews.size(); i++) {
				TextView textView = tViews.get(i);
				LinearLayout.LayoutParams layoutParams2 = new LayoutParams(
						layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
				layoutParams2.rightMargin = margin;
				layoutParams2.leftMargin = margin;
				int paddingLeft = textView.getPaddingLeft();
				int paddingRight = textView.getPaddingRight();
				int paddingBottom = textView.getPaddingBottom();
				int paddingTop = textView.getPaddingTop();
				textView.setPadding(paddingLeft + (padding / 2), paddingTop,
						paddingRight + (padding / 2), paddingBottom);
				llLine.addView(textView, layoutParams2);
			}
			// 11 将linearlayout添加至content上
			this.addView(llLine, layoutParams);
			// 13 集合数据中添加新的textview,宽度新的累加
			// 14 继续下一轮循环

		}
	
	}

	public void setWidth(int mHeaderViewHeight) {
			this.contentWidth = mHeaderViewHeight;
	}
}
