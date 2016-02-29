package com.example.gre_verbal;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CustomCellView extends RelativeLayout {

	private Context context;
	private RelativeLayout cellViewLayout;
	private boolean editing;
	public boolean editable;
	private ImageView selectView;
	private RelativeLayout cellContentView;
	private RelativeLayout.LayoutParams selectViewLayout;
	public boolean selected;
	
	public CustomCellView(Context context) {
		this(context, null);
	}

	public CustomCellView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.customcellview, this, true);
		this.cellViewLayout = (RelativeLayout)this.findViewById(R.id.CellViewLayout);
		this.editing = false;
		this.selected = false;
	}
	
	public void init(RelativeLayout cellContentView, int width, int height, int Id, boolean hasIndicator, boolean editable){
		
		this.editable = editable;
		this.cellContentView = cellContentView;
		this.cellContentView.setId(Id);
		RelativeLayout.LayoutParams lp;
		
		if(hasIndicator){
			
			int indicatorViewWidth = (int)(width * Constants.indicatorViewWidthRatio);
			int indicatorWidth = (int)(indicatorViewWidth * Constants.indicatorWidthRatio);
			int indicatorHeight = indicatorWidth * 2;

			ImageView indicator = new ImageView(this.context);
			indicator.setImageResource(R.drawable.indicator);
			indicator.setPadding((indicatorViewWidth - indicatorWidth) / 4, (height - indicatorHeight) / 2, (indicatorViewWidth - indicatorWidth) * 3 / 4, (height - indicatorHeight) / 2);
			
			lp =  new RelativeLayout.LayoutParams(40, height); 
			lp.addRule(RelativeLayout.RIGHT_OF, Id);
			lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			lp.setMargins(0, 0, 0, 0);
			this.cellViewLayout.addView(indicator, lp);			
			
		}
		
		if (!hasIndicator){
			lp =  new RelativeLayout.LayoutParams(width, height);
		}
		else{
			lp =  new RelativeLayout.LayoutParams(width - (int)(width * Constants.indicatorViewWidthRatio), height);
		}
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp.setMargins(0, 0, 0, 0);
		this.cellViewLayout.addView(this.cellContentView, lp);

		int selectViewWidth = (int)(width * Constants.cellSelectViewWidthRatio);
		int selectCircleRadius = (int)(selectViewWidth * Constants.cellSelectCircleRadiusRatio);
		this.selectView = new ImageView(this.context);
		this.selectView.setId(Id + 1000);
		if (this.editable){
			this.selectView.setImageResource(R.drawable.selectcircle);
			this.selectView.setPadding((selectViewWidth - selectCircleRadius) / 2, (height - selectCircleRadius) / 2, (selectViewWidth - selectCircleRadius) / 2, (height - selectCircleRadius) / 2);
		}
		else{
			this.selectView.setBackgroundColor(Color.WHITE);
		}
		this.selectViewLayout =  new RelativeLayout.LayoutParams(selectViewWidth, height); 
		this.selectViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		this.selectViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.selectViewLayout.setMargins(0, 0, 0, 0);
		
		View segmentationLine = new View(this.context);
		segmentationLine.setBackgroundColor(Color.GRAY);
		lp = new RelativeLayout.LayoutParams((int)(width * Constants.tableViewSegmentationLineWidthRatio), 1);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.setMargins(0, 0, 0, 0);
		this.cellViewLayout.addView(segmentationLine, lp);
	}
	
	public void changeEditingStatus(){
		
		this.editing = !this.editing;
		
		if (this.editing){
			
			this.cellViewLayout.addView(this.selectView, this.selectViewLayout);
			
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)this.cellContentView.getLayoutParams();
			lp.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.addRule(RelativeLayout.RIGHT_OF, this.cellContentView.getId() + 1000);
			this.cellContentView.setLayoutParams(lp);
		}
		else{
			this.cellViewLayout.removeView(this.selectView);
			
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)this.cellContentView.getLayoutParams();
			lp.removeRule(RelativeLayout.RIGHT_OF);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			this.cellContentView.setLayoutParams(lp);
		}

	}
	
	public void changeSelectedStatus(){
		
		if (this.editing){
			this.selected = !this.selected;
			
			if (this.selected){
				this.selectView.setImageResource(R.drawable.selectcircleselected);
			}
			else{
				this.selectView.setImageResource(R.drawable.selectcircle);
			}
		}
	}
	
}
