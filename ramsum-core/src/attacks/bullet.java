package attacks;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class bullet {
float vel;
public Rectangle rect;
private boolean isleft=false;
private static int rw=15,rh=15,vels=450;
private int damage=300;

public bullet(float x,float y,int damage,boolean isleft)
{
	rect=new Rectangle(x , y - rh, isleft?-(rw * 2) : (rw * 2), rh * 2);
	this.vel=isleft?-vels:vels;
	this.damage=damage;
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

public int getDMG(){
	return damage;
}
}
