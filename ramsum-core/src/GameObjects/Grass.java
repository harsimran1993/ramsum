package GameObjects;

public class Grass extends Scrollable{

	float flowvel;
	public Grass(float x, float y, int width, int height, float scrollSpeed,int gamewidth,float mul) {
		super(x, y, width, height, scrollSpeed,gamewidth);
		// TODO Auto-generated constructor stub
		this.flowvel=scrollSpeed * mul;
	}
	public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
	public void updatewater(float delta)
	{
		position.x+=flowvel*delta;
	}
}
