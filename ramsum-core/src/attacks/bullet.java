package attacks;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class bullet {
float vel;
public Rectangle rect;
private boolean isleft=false;
private static int rw=15,rh=15,vels=450;

public bullet(float x,float y,boolean isleft)
{
	rect=new Rectangle(x , y - rh, isleft?-(rw * 2) : (rw * 2), rh * 2);
	this.vel=isleft?-vels:vels;
}
public void update(float delta)
{
	rect.x+=vel*delta;
}
public boolean collision(Rectangle r)
{
	if(rect.overlaps(r))
		return true;
	return false;
}
}
