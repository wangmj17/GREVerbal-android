package com.example.gre_verbal;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class CustomTableView extends RelativeLayout {

	private int[] numberOfCellsInSections;
	private int numberOfSections;
	public boolean editing;
	private ArrayList<ArrayList> sectionsInTable; 
	private Context context;
	public CustomTableView(Context context) {
		this(context, null);
	}

	public CustomTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.customtableview, this, true);
		
		this.init();
	}
	
	private void init(){
		this.editing = false;
	}
	
	public void setNumberOfSections(int numberOfSections){
		this.numberOfSections = numberOfSections;
		this.numberOfCellsInSections = new int[numberOfSections];
		this.sectionsInTable = new ArrayList<ArrayList>();
		for(int i = 0; i < numberOfSections; i++){
			ArrayList<RelativeLayout> cellsInSection = new ArrayList<RelativeLayout>();
			this.sectionsInTable.add(cellsInSection);
		}
	}
	
	public void setNumberOfRowsInSection(int numberOfRows, int section){
		this.numberOfCellsInSections[section] = numberOfRows;
		ArrayList<RelativeLayout> cellsInSection = this.sectionsInTable.get(section);
		for(int i = 0; i < numberOfRows; i++){
			RelativeLayout cell = new RelativeLayout(this.context);
			cellsInSection.add(cell);
		}
	}
	
	public void setCellOfRowInSection(RelativeLayout cell, int row, int section){
		ArrayList<RelativeLayout> cellsInSection = this.sectionsInTable.get(section);
		cellsInSection.set(row, cell);
	}

}
