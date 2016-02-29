package com.example.gre_verbal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CellContentView extends RelativeLayout {
	private Context context;
	private RelativeLayout cellContentLayout;
	private int cellWidth;
	private int cellHeight;
	private int numberOfCompletedQuestions;
	private int numberOfRightAnswers;
	private int numberOfTotalQuestions;
	private TextView circleLabel;
	private boolean isDrawn;
	private Paint p;
	private RectF circle;
	private RelativeLayout.LayoutParams lp;
	
	public CellContentView(Context context, String exerciseName, int width, int cellHeight, int numberOfCompletedQuestions, int numberOfRightAnswers, int numberOfTotalQuestions) {
		this(context, null, exerciseName, width, cellHeight, numberOfCompletedQuestions, numberOfRightAnswers, numberOfTotalQuestions);
	}

	public CellContentView(Context context, AttributeSet attrs, String exerciseName, int cellWidth, int cellHeight, int numberOfCompletedQuestions, int numberOfRightAnswers, int numberOfTotalQuestions) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.numberOfCompletedQuestions = numberOfCompletedQuestions;
		this.numberOfRightAnswers = numberOfRightAnswers;
		this.numberOfTotalQuestions = numberOfTotalQuestions;
		this.isDrawn = false;
		this.p = new Paint();
		this.circle = new RectF(this.cellWidth - (int)(this.cellHeight * Constants.cellCircleRadiusRatio) - this.cellHeight / 2, this.cellHeight / 2 - (int)(this.cellHeight
				 * Constants.cellCircleRadiusRatio), this.cellWidth - this.cellHeight / 2 + (int)(this.cellHeight * Constants.cellCircleRadiusRatio), 
						 this.cellHeight / 2 + (int)(this.cellHeight * Constants.cellCircleRadiusRatio));
		this.circleLabel = new TextView(this.context);
		this.circleLabel.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.lp = new RelativeLayout.LayoutParams(this.cellHeight, this.cellHeight);
		this.lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		this.lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.lp.setMargins(0, 0, 0, 0);
		
		LayoutInflater.from(context).inflate(R.layout.customcellview, this, true);
		this.cellContentLayout = (RelativeLayout)this.findViewById(R.id.CellViewLayout);

		TextView textView = new TextView(this.context);
		textView.setText(exerciseName);
		textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams((int)(cellWidth * Constants.exerciseNameWidthRatio), cellHeight);
		lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp2.setMargins(0, 0, 0, 0);
		this.cellContentLayout.addView(textView, lp2);
		
		this.setWillNotDraw(false);
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);

		if (this.numberOfCompletedQuestions <= this.numberOfTotalQuestions){
			if(this.numberOfCompletedQuestions >= 0){
				this.p.setColor(Color.argb(255, 206, 206, 206));
				this.p.setStyle(Paint.Style.STROKE); 
				canvas.drawArc(circle, 0, 360, false, p);
				
				this.p.setColor(Color.argb(255, 60, 130, 255));
				this.p.setStyle(Paint.Style.STROKE);
				canvas.drawArc(circle, 270, (int)(360 * this.numberOfCompletedQuestions / this.numberOfTotalQuestions), false, p);
			}
			if(!this.isDrawn){
				this.cellContentLayout.addView(this.circleLabel, this.lp);
			}
			if(this.numberOfCompletedQuestions >= 0){
				this.circleLabel.setText(String.format("%d/%d", this.numberOfCompletedQuestions, this.numberOfTotalQuestions));
			}
			else{
				this.circleLabel.setText("");
			}
			this.circleLabel.setTextColor(Color.argb(255, 40, 100, 255));
		}
		else{
			float percentage = (float)(this.numberOfRightAnswers) * 100.0f / (float)(this.numberOfTotalQuestions);
			this.p.setColor(Color.argb(255, 255 - (int)(percentage * 2.55), (int)(percentage * 2.55), 255));
			canvas.drawArc(circle, 0, 360, false, p);
			if (!this.isDrawn){
				this.cellContentLayout.addView(this.circleLabel, this.lp);
			}
			this.circleLabel.setText(String.format("%d%%", (int)Math.floor(percentage)));
			this.circleLabel.setTextColor(Color.argb(255, 255 - (int)(percentage * 2.55), (int)(percentage * 2.55), 0));
		}
		this.isDrawn = true;
	}
}
