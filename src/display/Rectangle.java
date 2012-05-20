package display;


public class Rectangle implements Cloneable {
	
//	public Color color;
    private float width;
	private float height;
	public Point bl = new Point();

    public Rectangle(float width, float height, Point bl) {
        this.setWidth(width);
        this.setHeight(height);
        this.bl = bl;
    }

    public Rectangle(float width, float height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public Point getTopLeft(){
		Point tl=new Point();
		tl.x = bl.x;
		tl.y = bl.y+ getHeight();
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
		st+=" h:"+ getHeight() +" w:"+ getWidth();
		return st;
	}
	
	public void scaleAndTranslate(float f){
		setWidth(getWidth() * f);
		setHeight(getHeight() * f);
		bl.x   *= f;
		bl.y   *= f;
	}
	
	public void translate(float x, float y){
		bl.x += x;
		bl.y += y;
	}
	
	public void flipY(float screenHeight){
		bl.y = screenHeight-bl.y- getHeight();
	}
	
	public boolean isPointInsideRectangle(Point a){
		
		boolean inside = true;
		
		if(a.x<bl.x){inside = false;}
		if(a.x>bl.x+ getWidth()){inside = false;}
		if(a.y<bl.y){inside = false;}
		if(a.y>bl.y+ getHeight()){inside = false;}
		
		return inside;
	}

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
