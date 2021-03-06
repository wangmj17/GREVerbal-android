package com.example.gre_verbal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.gre_verbal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

	final int BUFFER_SIZE = 10000;
	private int screenWidth, screenHeight;
	private RelativeLayout mainActivityLayout;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		this.mainActivityLayout = (RelativeLayout)this.findViewById(R.id.MainActivityLayout);
		
		WindowManager wm = this.getWindowManager();
		this.screenWidth = wm.getDefaultDisplay().getWidth();
		this.screenHeight = wm.getDefaultDisplay().getHeight();
		
		this.checkDatabase();
		this.initView();
	}
	
	private void checkDatabase(){
		final String DB_NAME = "countries.db";
		final String PACKAGE_NAME = "com.example.gre_verbal";
		final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"
	            + PACKAGE_NAME;
		try {
			if (!(new File(DB_PATH + "/" + DB_NAME).exists())){
				InputStream is = this.getResources().openRawResource(
	                    R.raw.gre);
				FileOutputStream fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
	            while ((count = is.read(buffer)) > 0) {
	                fos.write(buffer, 0, count);
	            }
	            fos.close();
	            is.close(); 
			}
		}catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        } 
	}
	
	@SuppressWarnings("deprecation")
	private void initView(){
		
		RelativeLayout mainViewBackgroundLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(this.screenWidth, this.screenHeight - this.screenWidth);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		this.mainActivityLayout.addView(mainViewBackgroundLayout, lp);
		
		ImageView mainViewBackgroundImageView = new ImageView(this);
		mainViewBackgroundImageView.setBackgroundResource(R.drawable.mainviewbackground);
		lp =  new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		mainViewBackgroundLayout.addView(mainViewBackgroundImageView, lp);
		
		ImageView beatGREIcon = new ImageView(this);
		beatGREIcon.setBackgroundResource(R.drawable.beatgre);
		lp = new RelativeLayout.LayoutParams(this.screenWidth / 2, (int) (this.screenWidth / 2 * 0.66));
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		mainViewBackgroundLayout.addView(beatGREIcon, lp);
		
		RelativeLayout buttonsLayout = new RelativeLayout(this);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth, this.screenWidth);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.setMargins(0, 0, 0, 0);
		this.mainActivityLayout.addView(buttonsLayout, lp);
		
		
		int buttonImageWidth = this.screenWidth / 4;
		int buttonImageHeight = (int) (buttonImageWidth / 0.72);
		
		ImageView exerciseButton = new ImageView(this);
		exerciseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(MainActivity.this,VerbalExerciseActivity.class),0);
			}
		});
		exerciseButton.setImageResource(R.drawable.verbalexercise);
		exerciseButton.setPadding(this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2, this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth / 2, this.screenWidth / 2);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth / 2, this.screenWidth / 2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		buttonsLayout.addView(exerciseButton, lp);
		
		ImageView categoryButton = new ImageView(this);
		categoryButton.setImageResource(R.drawable.verbalcategory);
		categoryButton.setPadding(this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2, this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth / 2, this.screenWidth / 2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		buttonsLayout.addView(categoryButton, lp);
		
		ImageView notebookButton = new ImageView(this);
		notebookButton.setImageResource(R.drawable.notebook);
		notebookButton.setPadding(this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2, this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth / 2, this.screenWidth / 2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.setMargins(0, 0, 0, 0);
		buttonsLayout.addView(notebookButton, lp);
		
		ImageView wordbookButton = new ImageView(this);
		wordbookButton.setImageResource(R.drawable.wordbook);
		wordbookButton.setPadding(this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2, this.screenWidth / 4 - buttonImageWidth / 2, this.screenWidth / 4 - buttonImageHeight / 2);
		lp =  new RelativeLayout.LayoutParams(this.screenWidth / 2, this.screenWidth / 2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.setMargins(0, 0, 0, 0);
		buttonsLayout.addView(wordbookButton, lp);
	}
	
}
