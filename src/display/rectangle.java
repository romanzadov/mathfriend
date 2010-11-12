package display;


public class rectangle implements Cloneable {
	
//	public Color color;
	public float width;
	public float height;
	public point bl = new point();
	public point topleft(point bl, float height){
		point tl=new point();
		tl.x = bl.x;
		tl.y = bl.y-height;
		return tl;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		rectangle clone = (rectangle)super.clone();
		clone.bl = (point)this.bl.clone();
		return clone;
	}
	
	@Override
	public String toString(){
		String st="";
		st+=bl.toString();
		st+=" h:"+height+" w:"+width;
		return st;
	}
	
}
