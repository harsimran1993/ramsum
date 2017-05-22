package GameObjects;

public class Background extends Scrollable {
	
	public Background(float x, float y, int width, int height, float scrollSpeed,int gamewidth) {
		super(x, y, width, height, scrollSpeed,gamewidth);
		// TODO Auto-generated constructor stub
	}
	public void onRestart(float x, float scrollSpeed) {
		position.x = x;
        velocity.x = scrollSpeed;
    }
}
