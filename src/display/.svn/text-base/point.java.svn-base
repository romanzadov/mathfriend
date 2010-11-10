package display;

public class point implements Cloneable {

		public float x = 0;
		public float y = 0;
		
		public point(){
			
		}
		
		@Override
		public String toString(){
			return "("+x+","+y+")";
		}
		
		public point(int X, int Y){
			x = X;
			y = Y;
		}
		public point(float X, float Y ){
			x = (int)X;
			y = (int)Y;
			
		}
		
		public point(point a, point b, String s){
			if (s == "add"){
			x = a.x+b.x;
			y = a.y + b.y;}
		}
		
		
		public double distance(point a, point b){
			double dist;
			dist = Math.pow((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y), .5);
			return dist;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
}
