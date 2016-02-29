package com.example.gre_verbal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VerbalExerciseActivity extends FragmentActivity implements View.OnClickListener{
	private RelativeLayout verbalExerciseActivityLayout, contentLayout;
	private ExerciseView discreteLayout, readingLayout, mixedLayout;
	private FragmentManager fragmentManager;
	private ExerciseView discreteFragment, readingFragment, mixedFragment;
	private FragmentTransaction fragmentTransaction;
	private int screenWidth, screenHeight;
	private ImageView selectView;
	
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
		this.initFragments();
	}
	
	
	private void initTopView(){
		
		int topViewHeight = (int)(this.screenWidth * Constants.topViewHeightRatio);
		RelativeLayout tabBarLayout = new RelativeLayout(this);
		tabBarLayout.setId(800);
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
		float fontSize = this.screenWidth * Constants.topViewTitleFontSizeRatio;
		
		TextView discreteButton = new TextView(this);
		discreteButton.setText("填空");
		discreteButton.setTextColor(Color.rgb(0, 0, 0));
		discreteButton.setTextSize(fontSize);
		discreteButton.setGravity(Gravity.CENTER);
		discreteButton.setId(401);
		discreteButton.setOnClickListener(this);
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
		readingButton.setOnClickListener(this);
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
		mixedButton.setId(403);
		mixedButton.setOnClickListener(this);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 402);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(mixedButton, lp);
		
		this.selectView = new ImageView(this);
		this.selectView.setBackgroundResource(R.drawable.selectview);
		lp =  new RelativeLayout.LayoutParams(buttonWidth, topViewHeight);
		lp.addRule(RelativeLayout.RIGHT_OF, 400);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		tabBarLayout.addView(this.selectView, lp);
		
	}
	
	private void initContentView(){
		
		int topViewHeight = (int)(this.screenWidth * Constants.topViewHeightRatio);
		
		this.contentLayout = new RelativeLayout(this);
		this.contentLayout.setId(2001);
		RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(this.screenWidth, this.screenHeight - topViewHeight);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.BELOW, 800);
		lp.setMargins(0, 0, 0, 0);
		this.verbalExerciseActivityLayout.addView(contentLayout, lp);
		
	}

	private void initFragments(){
		this.fragmentManager = this.getFragmentManager();
		this.fragmentTransaction = this.fragmentManager.beginTransaction();
		this.discreteFragment = new ExerciseView(this, 0, this.screenWidth, this.screenHeight - (int)(this.screenWidth * Constants.topViewHeightRatio));
	    this.fragmentTransaction.replace(2001, this.discreteFragment);
	    this.fragmentTransaction.commit();
	}
	
	private void replaceFragments(Fragment fragment){
		this.fragmentTransaction = this.fragmentManager.beginTransaction();
		if (!fragment.isAdded()) {  
			this.fragmentTransaction.replace(2001, fragment);  
			this.fragmentTransaction.commit();  
        } else {  
        	this.fragmentTransaction.show(fragment);  
        }
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		RelativeLayout.LayoutParams lp;
		switch(v.getId()){
		case 401:
			if(this.discreteFragment == null){
				this.discreteFragment = new ExerciseView(this, 0, this.screenWidth, this.screenHeight - (int)(this.screenWidth * Constants.topViewHeightRatio));
			}
			this.replaceFragments(this.discreteFragment);
			lp = (RelativeLayout.LayoutParams)this.selectView.getLayoutParams();
			lp.removeRule(RelativeLayout.RIGHT_OF);
			lp.addRule(RelativeLayout.RIGHT_OF, 400);
			this.selectView.setLayoutParams(lp);
			break;
		case 402:
			if(this.readingFragment == null){
				this.readingFragment = new ExerciseView(this, 1, this.screenWidth, this.screenHeight - (int)(this.screenWidth * Constants.topViewHeightRatio));
			}
			this.replaceFragments(this.readingFragment);
			lp = (RelativeLayout.LayoutParams)this.selectView.getLayoutParams();
			lp.removeRule(RelativeLayout.RIGHT_OF);
			lp.addRule(RelativeLayout.RIGHT_OF, 401);
			this.selectView.setLayoutParams(lp);
			break;
		case 403:
			if(this.mixedFragment == null){
				this.mixedFragment = new ExerciseView(this, 2, this.screenWidth, this.screenHeight - (int)(this.screenWidth * Constants.topViewHeightRatio));
			}
			this.replaceFragments(this.mixedFragment);
			lp = (RelativeLayout.LayoutParams)this.selectView.getLayoutParams();
			lp.removeRule(RelativeLayout.RIGHT_OF);
			lp.addRule(RelativeLayout.RIGHT_OF, 402);
			this.selectView.setLayoutParams(lp);
			break;
		}
	}
	

}
