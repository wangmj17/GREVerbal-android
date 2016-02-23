package com.example.gre_verbal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VerbalExerciseActivity extends Activity {
	private RelativeLayout verbalExerciseActivityLayout, contentLayout, discreteLayout, readingLayout, mixedLayout;
	private int selectedIndex = 1;
	private FragmentManager fragmentManager;
	private Fragment discreteFragment, readingFragment, mixedFragment;
	private int screenWidth, screenHeight;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_verbalexercise);

		this.verbalExerciseActivityLayout = (RelativeLayout) this.findViewById(R.id.VerbalExerciseActivityLayout);
		
		WindowManager wm = this.getWindowManager();
		this.screenWidth = wm.getDefaultDisplay().getWidth();
		this.screenHeight = wm.getDefaultDisplay().getHeight();

		this.initTopView();
		this.initContentView();
	}
	
	private void initTopView(){
		
		int topViewHeight = (int)(this.screenWidth * Constants.topViewHeightRatio);
		RelativeLayout tabBarLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(this.screenWidth, topViewHeight);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		this.verbalExerciseActivityLayout.addView(tabBarLayout, lp);
		
		int btnBackImageWidth = (int)(this.screenWidth * Constants.btnBackImageWidthRatio);
		int btnBackImageH = (int)(this.screenWidth * Constants.btnBackImageHRatio);
		int btnBackImageV = (int)(this.screenWidth * Constants.btnBackImageVRatio);
		ImageView backButton = new ImageView(this);
		backButton.setImageResource(R.drawable.backbutton);
		backButton.setPadding(btnBackImageH, btnBackImageV, btnBackImageH, btnBackImageV);
		backButton.setId(400);
		lp =  new RelativeLayout.LayoutParams(btnBackImageWidth, topViewHeight);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(backButton, lp);
		
		int buttonWidth = (this.screenWidth - btnBackImageWidth) / 3;
		float fontSize = this.screenWidth / Constants.topViewTitleFontSizeRatio;
		
		TextView discreteButton = new TextView(this);
		discreteButton.setText("填空");
		discreteButton.setTextColor(Color.rgb(0, 0, 0));
		discreteButton.setTextSize(fontSize);
		discreteButton.setGravity(Gravity.CENTER);
		discreteButton.setId(401);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 400);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(discreteButton, lp);
		
		TextView readingButton = new TextView(this);
		readingButton.setText("阅读");
		readingButton.setTextColor(Color.rgb(0, 0, 0));
		readingButton.setTextSize(fontSize);
		readingButton.setGravity(Gravity.CENTER);
		readingButton.setId(402);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 401);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(readingButton, lp);
		
		TextView mixedButton = new TextView(this);
		mixedButton.setText("混合");
		mixedButton.setTextColor(Color.rgb(0, 0, 0));
		mixedButton.setTextSize(fontSize);
		mixedButton.setGravity(Gravity.CENTER);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 402);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(mixedButton, lp);
		
		ImageView selectView = new ImageView(this);
		selectView.setBackgroundResource(R.drawable.selectview);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 400);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(selectView, lp);
	}
	
	private void initContentView(){
		
	}
}
