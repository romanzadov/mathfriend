package display;

public class Point implements Cloneable {

		public float x = 0;
		public float y = 0;
		
		public Point(){
			
		}
		
		@Override
		public String toString(){
			return "("+x+","+y+")";
		}
		
		public Point(int X, int Y){
			x = X;
			y = Y;
		}
		public Point(float X, float Y){
			x = X;
			y = Y;
			
		}
		
		public Point(Point a, Point b, String s){
			if (s == "add"){
			x = a.x+b.x;
			y = a.y + b.y;}
		}
		
		
		public double distance(Point a, Point b){
			double dist;
			dist = Math.pow((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y), .5);
			return dist;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
}
