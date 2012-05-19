package representTerms;

public class Settings {

	public static final int SELECTION_TIME = 150;

	public static final float MIN_FONT = 1;
	public static final float MAX_FONT = 50;
	public static final float PREFFERED_FONT = 25;
    public static final String DECIMAL = "\\.";
	
	public static enum ScreenType {
		MAIN (700, 300,10896163),
		GHOST   (300, 100, 7831377),
		OPERATOR (100, 100,16774570),
		HISTORY   (300, 500, 0);

		public final int width;  
		public final int height; 
		public final int color;
		ScreenType(int width, int height, int color) {
			this.width = width;
			this.height = height;
			this.color = color;
		}

	}

}
