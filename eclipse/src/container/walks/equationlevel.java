package container.walks;

import display.Point;

public class equationlevel {

	Point bl = new Point();

	public Point level(int width, int height){

		bl.x=(int)(300/2-width/2);
		bl.y=(int)(440/2-height/2);
		//System.out.println("bl.y  "+bl.y+ " height  "+height);
		return bl;
	}

	


}
