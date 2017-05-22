package GameObjects;

import com.badlogic.gdx.math.Rectangle;

public class Door {
	
private Rectangle rect;
int nextlevel;

public Door(float x,float y,int width,int height)
{
	setRect(new Rectangle(x,y,width,height));
}

public void setnextlevel(int lvl)
{
	nextlevel=lvl;
}

public int getlevel()
{
	return nextlevel;
}

public boolean collides(Rectangle r)
{
	return getRect().overlaps(r);
}

public Rectangle getRect() {
	return rect;
}

public void setRect(Rectangle rect) {
	this.rect = rect;
}

}
