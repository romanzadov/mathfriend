package display;

public class Points implements Cloneable {

		public float x = 0;
		public float y = 0;
		
		public Points(){
			
		}
		
		@Override
		public String toString(){
			return "("+x+","+y+")";
		}
		
		public Points(int X, int Y){
			x = X;
			y = Y;
		}
		public Points(float X, float Y){
			x = X;
			y = Y;
			
		}
		
		public Points(Points a, Points b, String s){
			if (s == "add"){
			x = a.x+b.x;
			y = a.y + b.y;}
		}
		
		
		public double distance(Points a, Points b){
			double dist;
			dist = Math.pow((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y), .5);
			return dist;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
}
