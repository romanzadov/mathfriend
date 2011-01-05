package display;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;


public class XYLayout extends ViewGroup {

	Context con;

	public XYLayout(Context context) {
		super(context);
		con = context;
	
		// TODO Auto-generated constructor stu
	}

	public static class LayoutParams extends
			android.view.ViewGroup.LayoutParams {

		
		public LayoutParams(Context c, AttributeSet attrs) {
			super(c,attrs);
		}
	}

	public XYLayout(Context context, AttributeSet attrs){
		super(context,attrs);
	}
	/*
	public XYLayout(Context context, AttributeSet attrs, Map inflateParams) {
		super(context, attrs, inflateParams);
	//	readAttr(context, attrs);
	}

	public XYLayout(Context context, AttributeSet attrs, Map inflateParams,
			int defStyle) {
		super(context, attrs, inflateParams, defStyle);
	//	readAttr(context, attrs);
	}*/

	/*
	private void readAttr(Context c, AttributeSet attrs) {
		android.content.Resources.StyledAttributes a = c
				.obtainStyledAttributes(attrs, R.styleable.MyGridLayout);
		this.rows = a.getInt(R.styleable.MyGridLayout_rows, 1);
		this.columns = a.getInt(R.styleable.MyGridLayout_columns, 1);
		this.preferredCellWidth = a.getDimension(
				R.styleable.MyGridLayout_preferredCellWidth, 1);
		this.preferredCellHeight = a.getDimension(
				R.styleable.MyGridLayout_preferredCellHeight, 1);
		a.recycle();
	}
	*/

	private int rows;
	private int columns;
	private float preferredCellWidth;
	private float preferredCellHeight;

/*
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		float cellWidth = preferredCellWidth(), cellHeight = preferredCellHeight();
		if (widthMode == MeasureSpec.EXACTLY
				|| widthMode == MeasureSpec.AT_MOST) {
			float width = widthSize;
			width = width - mPaddingLeft - mPaddingRight;
			width /= columns();
			if (widthMode == MeasureSpec.EXACTLY)
				cellWidth = width;
			else
				cellWidth = Math.min(cellWidth, width);
		}
		if (heightMode == MeasureSpec.EXACTLY
				|| heightMode == MeasureSpec.AT_MOST) {
			float height = heightSize;
			height = height - mPaddingTop - mPaddingBottom;
			height /= rows();
			if (heightMode == MeasureSpec.EXACTLY)
				cellHeight = height;
			else
				cellHeight = Math.min(cellHeight, height);
		}

		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				LayoutParams lp = (LayoutParams) child.getLayoutParams();
				int width = (int) Math.round(cellWidth * lp.width());
				int height = (int) Math.round(cellHeight * lp.height());
				child.measure(MeasureSpec.makeMeasureSpec(width,
						MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
						height, MeasureSpec.EXACTLY));
			}
		}

		setMeasuredDimension((int) Math.round(cellWidth * columns()
				+ mPaddingLeft + mPaddingRight), (int) Math.round(cellHeight
				* rows() + mPaddingTop + mPaddingBottom));
		
		
		
	}

	*/
	
	private int lastWidth, lastHeight;

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	
		/*
		int width = r - l;
		int height = b - t;
		if (lastWidth == width && lastHeight == height)
			return;
		float cellWidth = width, cellHeight = height;
		cellWidth = cellWidth - mPaddingLeft - mPaddingRight;
		cellHeight = cellHeight - mPaddingTop - mPaddingBottom;
		cellWidth /= columns();
		cellHeight /= rows();
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				LayoutParams lp = (LayoutParams) child.getLayoutParams();
				int cl = (int) Math.round(mPaddingLeft + lp.columnStart
						* cellWidth);
				int ct = (int) Math.round(mPaddingTop + lp.rowStart
						* cellHeight);
				int cr = (int) Math.round(mPaddingLeft + lp.columnEnd
						* cellWidth);
				int cb = (int) Math.round(mPaddingTop + lp.rowEnd * cellHeight);
				child.layout(cl, ct, cr, cb);
			}
		}
		lastWidth = width;
		lastHeight = height;
		*/
		
	}
/*
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		// let the MyGridLayout.LayoutParams to read attributes of layout instead of ViewGroup.LayoutParams
		return new LayoutParams(getContext(), attrs);
	}

	protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
		// if the layout params is invalid, the android will throw a runtime exception.
		if (p instanceof LayoutParams) {
			int columns = columns(), rows = rows();
			LayoutParams lp = (LayoutParams) p;
			if (lp.columnEnd > columns || lp.columnStart < 0)
				return false;
			if (lp.rowEnd > rows || lp.rowStart < 0)
				return false;
			return lp.columnEnd > lp.columnStart && lp.rowEnd > lp.rowStart;
		} else
			return false;
	}
	*/

}