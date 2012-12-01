package display;


public class Rectangle implements Cloneable {
	
//	public Color color;
	public float width;
	public float height;
	public Point bl = new Point();
	
	public Point getTopLeft(){
		Point tl=new Point();
		tl.x = bl.x;
		tl.y = bl.y+height;
		return tl;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rectangle clone = (Rectangle)super.clone();
		clone.bl = (Point)this.bl.clone();
		return clone;
	}
	
	@Override
	public String toString(){
		String st="";
		st+=bl.toString();
		st+=" h:"+height+" w:"+width;
		return st;
	}
	
	public void scaleAndTranslate(float f){
		width  *= f;
		height *= f;
		bl.x   *= f;
		bl.y   *= f;
	}
	
	public void translate(float x, float y){
		bl.x += x;
		bl.y += y;
	}
	
	public void flipY(float screenHeight){
		bl.y = screenHeight-bl.y-height;
	}
	
	public boolean isPointInsideRectangle(Point a){
		
		boolean inside = true;
		
		if(a.x<bl.x){inside = false;}
		if(a.x>bl.x+width){inside = false;}
		if(a.y<bl.y){inside = false;}
		if(a.y>bl.y+height){inside = false;}
		
		return inside;
	}
}