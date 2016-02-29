package com.example.gre_verbal;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class CustomTableView extends RelativeLayout {

	private int[] numberOfCellsInSections;
	private int numberOfSections;
	private int width;
	public boolean editing;
	private ArrayList<ArrayList<CustomCellView>> sectionsInTable; 
	private Context context;
	private ArrayList<RelativeLayout> sectionHeaders;
	private ArrayList<CustomCellView> tableViewCells;
	private RelativeLayout tableViewLayout;
	private int[] heightsForSectionHeaders;
	private ArrayList<ArrayList<Integer>> heightsInTable;
	
	public CustomTableView(Context context) {
		this(context, null);
	}

	public CustomTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.customtableview, this, true);
		this.tableViewLayout = (RelativeLayout)this.findViewById(R.id.TableViewLayout);
		
		this.sectionsInTable = new ArrayList<ArrayList<CustomCellView>>();
		this.sectionHeaders = new ArrayList<RelativeLayout>();
		this.heightsInTable = new ArrayList<ArrayList<Integer>>();
		this.init();
	}
	
	private void init(){
		this.editing = false;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setNumberOfSections(int numberOfSections){
		this.numberOfSections = numberOfSections;
		this.numberOfCellsInSections = new int[numberOfSections];
		this.heightsForSectionHeaders = new int[numberOfSections];
		
		for(int i = 0; i < numberOfSections; i++){
			ArrayList<CustomCellView> cellsInSection = new ArrayList<CustomCellView>();
			this.sectionsInTable.add(cellsInSection);
			RelativeLayout sectionHeader = new RelativeLayout(this.context);
			this.sectionHeaders.add(sectionHeader);
			ArrayList<Integer> heightsInSection = new ArrayList<Integer>();
			this.heightsInTable.add(heightsInSection);
		}
	}
	
	public void setNumberOfRowsInSection(int numberOfRows, int section){
		this.numberOfCellsInSections[section] = numberOfRows;
		ArrayList<CustomCellView> cellsInSection = this.sectionsInTable.get(section);
		ArrayList<Integer> heightsInSection = this.heightsInTable.get(section);
		for(int i = 0; i < numberOfRows; i++){
			CustomCellView cell = new CustomCellView(this.context);
			cellsInSection.add(cell);
			heightsInSection.add(Integer.valueOf(0));
		}
	}
	
	public void setCellOfRowInSection(CustomCellView cell, int row, int section){
		ArrayList<CustomCellView> cellsInSection = this.sectionsInTable.get(section);
		cellsInSection.set(row, cell);
	}
	
	public void setViewForSectionHeader(RelativeLayout header, int section){
		this.sectionHeaders.set(section, header);
	}
	
	public void setHeightForSectionHeader(int height, int section){
		this.heightsForSectionHeaders[section] = height;
	}
	
	public void setHeightForCellInSection(int height, int row, int section){
		ArrayList<Integer> heightsInSection = this.heightsInTable.get(section);
		heightsInSection.set(row, Integer.valueOf(height));
	}
	
	public void buildView(){
		int lastID = 3600;
		for (int i = 0; i < this.numberOfSections; i++){
			
			RelativeLayout sectionHeader = this.sectionHeaders.get(i);
			sectionHeader.setId(lastID + 1);
			RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(this.width, this.heightsForSectionHeaders[i]);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			if (i == 0){
				lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			}
			else{
				lp.addRule(RelativeLayout.BELOW, lastID);
			}
			lp.setMargins(0, 0, 0, 0);
			this.tableViewLayout.addView(sectionHeader, lp);
			lastID ++;
			
			ArrayList<CustomCellView> cellsInSection = this.sectionsInTable.get(i);
			ArrayList<Integer> heightsInSection = this.heightsInTable.get(i);
			for (int j = 0; j < this.numberOfCellsInSections[i]; j++){
				CustomCellView cell = (CustomCellView)cellsInSection.get(j);
				cell.setId(lastID + 1);
				lp =  new RelativeLayout.LayoutParams(this.width, heightsInSection.get(j));
				lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				lp.addRule(RelativeLayout.BELOW, lastID);
				lp.setMargins(0, 0, 0, 0);
				this.tableViewLayout.addView(cell, lp);
				lastID ++;
			}
		}
	}

	public void changeEditingStatus(){
		
		this.editing = !this.editing;
		for (int i = 0; i <= this.numberOfSections; i++){
			
			ArrayList<CustomCellView> cellsInSection = this.sectionsInTable.get(i);
			for (int j = 0; j <= this.numberOfCellsInSections[i]; i++){
				CustomCellView cell = (CustomCellView)cellsInSection.get(j);
				cell.changeEditingStatus();
			}
		}
	}
	
	public void deleteCellsAtIndexes(){
		
	}
}
