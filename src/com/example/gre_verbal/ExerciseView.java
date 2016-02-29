package com.example.gre_verbal;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExerciseView extends Fragment {

	private int contentHeight;
	private int screenWidth;
	private int type;
	private Context context;
	private RelativeLayout exerciseViewLayout;
	private int numberOfBooks;
	private int[] numberOfExercisesInBooks;
	private String[] namesOfBooks;
	
	public ExerciseView(Context context, int type, int screenWidth, int contentHeight){
		this.type = type;
		this.screenWidth = screenWidth;
		this.contentHeight = contentHeight;
		this.context = context;
		
	}
	
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.exerciseview, container, false);  
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState); 
        this.init();
    }  
    
	
	private void init() {

		this.exerciseViewLayout = (RelativeLayout)this.getView().findViewById(R.id.ExerciseViewLayout);
		
		this.initData();
		this.initView();
	}
	
	private void initData(){
		switch(this.type){
			case 0:
				this.numberOfBooks = 3;
				this.numberOfExercisesInBooks = new int[3];
				this.numberOfExercisesInBooks[0] = 4;
				this.numberOfExercisesInBooks[1] = 4;
				this.numberOfExercisesInBooks[2] = 14;
				this.namesOfBooks = new String[3];
				this.namesOfBooks[0] = "Official Guide";
				this.namesOfBooks[1] = "Cracking the GRE";
				this.namesOfBooks[2] = "1014 Practice Questions";
				break;
			case 1:
				this.numberOfBooks = 3;
				this.numberOfExercisesInBooks = new int[3];
				this.numberOfExercisesInBooks[0] = 3;
				this.numberOfExercisesInBooks[1] = 2;
				this.numberOfExercisesInBooks[2] = 6;
				this.namesOfBooks = new String[3];
				this.namesOfBooks[0] = "Official Guide";
				this.namesOfBooks[1] = "Cracking the GRE";
				this.namesOfBooks[2] = "1014 Practice Questions";
				break;
			case 2:
				this.numberOfBooks = 4;
				this.numberOfExercisesInBooks = new int[4];
				this.numberOfExercisesInBooks[0] = 4;
				this.numberOfExercisesInBooks[1] = 8;
				this.numberOfExercisesInBooks[2] = 4;
				this.numberOfExercisesInBooks[3] = 4;
				this.namesOfBooks = new String[4];
				this.namesOfBooks[0] = "Official Guide";
				this.namesOfBooks[1] = "Official Practice Questions";
				this.namesOfBooks[2] = "Cracking the GRE";
				this.namesOfBooks[3] = "GRE Practice Book";
				break;
			default:
				break;
		}
	}
	
	private int getNumberOfTotalQuestions(int exerciseIndex){
		final String DB_NAME = "countries.db";
		final String PACKAGE_NAME = "com.example.gre_verbal";
		final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"
	            + PACKAGE_NAME;
		int numberOfTotalQuestions = 0;
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, 1);  
		Cursor cursor = null;
		switch(this.type){
		case 0:
			cursor = db.rawQuery("SELECT COUNT(*) FROM DISCRETE WHERE exerciseIndex = ?", new String[]{String.valueOf(exerciseIndex)});
			break;
		case 1:
			cursor = db.rawQuery("SELECT COUNT(*) FROM READING WHERE exerciseIndex = ?", new String[]{String.valueOf(exerciseIndex)});
			break;
		case 2:
			cursor = db.rawQuery("SELECT COUNT(*) FROM MIXED WHERE exerciseIndex = ?", new String[]{String.valueOf(exerciseIndex)});
			break;
		default:
			break;
		}
		if(cursor.moveToNext()){
			numberOfTotalQuestions = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return numberOfTotalQuestions;
	}
	
	private int getNumberOfRightAnswers(int exerciseIndex){
		final String DB_NAME = "countries.db";
		final String PACKAGE_NAME = "com.example.gre_verbal";
		final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"
	            + PACKAGE_NAME;
		int numberOfRightAnswers = 0;
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, 0);
		db.execSQL("CREATE TABLE IF NOT EXISTS EXERCISEHISTORY(exerciseIndex SMALLINT, questionIndex SMALLINT, tableIndex SMALLINT, isRight SMALLINT, userAnswer VARCHAR(10))");
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM EXERCISEHISTORY WHERE exerciseIndex = ? AND tableIndex = ? AND isRight = 1", new String[]{String.valueOf(exerciseIndex),
		String.valueOf(this.type)});
		if(cursor.moveToNext()){
			numberOfRightAnswers = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return numberOfRightAnswers;
	}
	
	private int getNumberOfCompletedQuestions(int exerciseIndex){
		final String DB_NAME = "countries.db";
		final String PACKAGE_NAME = "com.example.gre_verbal";
		final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"
	            + PACKAGE_NAME;
		int numberOfcompletedQuestions = 0;
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, 0);
		db.execSQL("CREATE TABLE IF NOT EXISTS EXERCISEHISTORY(exerciseIndex SMALLINT, questionIndex SMALLINT, tableIndex SMALLINT, isRight SMALLINT, userAnswer VARCHAR(10))");
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM EXERCISEHISTORY WHERE exerciseIndex = ? AND tableIndex = ? AND userAnswer != '-'", new String[]{String.valueOf(exerciseIndex),
		String.valueOf(this.type)});
		if(cursor.moveToNext()){
			numberOfcompletedQuestions = cursor.getInt(0);
		}
		cursor.close();
		cursor = db.rawQuery("SELECT * FROM EXERCISEHISTORY WHERE exerciseIndex = ? AND tableIndex = ? AND isRight != 2", new String[]{String.valueOf(exerciseIndex),
					String.valueOf(this.type)});
		if(cursor.moveToNext()){
				numberOfcompletedQuestions += 100;
		}			
		cursor.close();
		db.close();
		return numberOfcompletedQuestions;
	}
	
	private String getNameOfExercise(int exerciseIndex){
		final String DB_NAME = "countries.db";
		final String PACKAGE_NAME = "com.example.gre_verbal";
		final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"
	            + PACKAGE_NAME;
		String nameOfExercise = "";
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, 0);
		Cursor cursor = db.rawQuery("SELECT exerciseName FROM EXERCISENAME WHERE exerciseIndex = ? AND tableIndex = ?", new String[]{String.valueOf(exerciseIndex),
		String.valueOf(this.type)});
		if(cursor.moveToNext()){
			nameOfExercise = cursor.getString(0);
		}
		cursor.close();
		db.close();
		return nameOfExercise;
	}
	
	private int getExerciseIndex(int section, int row){
		int exerciseIndex = row;
		for(int i = 0; i < section; i++){
			exerciseIndex += this.numberOfExercisesInBooks[i];
		}
		return exerciseIndex;
	}
	private void initView(){
		
		ScrollView scrollView = new ScrollView(this.context);
		scrollView.setVerticalScrollBarEnabled(true);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(this.screenWidth, this.contentHeight);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		this.exerciseViewLayout.addView(scrollView, lp);
		
		int width = (int)(this.screenWidth * Constants.tableViewWidthRatio);
		int cellHeight = (int)(this.screenWidth * Constants.cellViewHeightRatio);
		
		CustomTableView customTableView = new CustomTableView(this.context);
		customTableView.setWidth(width);
		customTableView.setNumberOfSections(this.numberOfBooks);
		for(int i = 0; i < this.numberOfBooks; i++){
			customTableView.setNumberOfRowsInSection(this.numberOfExercisesInBooks[i], i);
		}
		int lastId = 1300;
		for(int i = 0; i < this.numberOfBooks; i++){
			
			customTableView.setHeightForSectionHeader(cellHeight / 2, i);
			RelativeLayout sectionHeaderView = new RelativeLayout(this.context);
			sectionHeaderView.setBackgroundColor(Color.LTGRAY);
			customTableView.setViewForSectionHeader(sectionHeaderView, i);
			
			for(int j = 0; j < this.numberOfExercisesInBooks[i]; j++){
				
				customTableView.setHeightForCellInSection(cellHeight, j, i);
				CustomCellView cell = new CustomCellView(this.context);
				if (j == 0){
					RelativeLayout cellContentView = new RelativeLayout(this.context);
					TextView textView = new TextView(this.context);
					textView.setText(this.namesOfBooks[i]);
					textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
					textView.setGravity(Gravity.CENTER_VERTICAL);
					lp = new RelativeLayout.LayoutParams(width, cellHeight);
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
					lp.setMargins(0, 0, 0, 0);
					cellContentView.addView(textView, lp);
					cell.init(cellContentView, width, cellHeight, lastId + 1, false, false);
				}
				else{
					int exerciseIndex = this.getExerciseIndex(i, j);
					CellContentView cellContentView = new CellContentView(this.context, this.getNameOfExercise(exerciseIndex),
							width - (int)(width * Constants.indicatorViewWidthRatio), cellHeight, this.getNumberOfCompletedQuestions(exerciseIndex),
							this.getNumberOfRightAnswers(exerciseIndex), this.getNumberOfTotalQuestions(exerciseIndex));
					cell.init(cellContentView, width, cellHeight, lastId + 1, true, true);
				}
				customTableView.setCellOfRowInSection(cell, j, i);
				lastId ++;
			}
			
		}
		customTableView.buildView();
		
		ScrollView.LayoutParams lp2 = new ScrollView.LayoutParams(width, LayoutParams.WRAP_CONTENT);
		lp2.setMargins((this.screenWidth - width) / 2, 0, (this.screenWidth - width) / 2, cellHeight / 2);
		scrollView.addView(customTableView, lp2);
	}
}
