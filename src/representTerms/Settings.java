package representTerms;

public class Settings {

	public static final int SELECTION_TIME = 150;

	public static final float MIN_FONT = 1;
	public static final float MAX_FONT = 50;
	public static final float PREFFERED_FONT = 25;

	
	public static enum ScreenType {
		MAIN (700, 300),
		GHOST   (300, 100),
		OPERATOR (100, 100),
		HISTORY   (300, 500);

		public final int width;  
		public final int height; 
		ScreenType(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}

}
