package ui_objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class movepad {
	
public Circle base,stick;
int rad1=25,rad2=20;
boolean visible=false;
public enum direct{up,down,left,right,none};
public direct dir;
public movepad(){dir=dir.none;}

public void set(int x, int y)
{
	if(x>(200-50)) x=200-50;
	base=new Circle(x,y,rad1);
	stick= new Circle(x,y,rad2);
	visible=true;
}
public void drag(int x,int y)
{
	if(x>(200-20)) x=200-20;
	stick= new Circle(x,y,rad2);
}
public void hide()
{
	visible=false;
	dir=direct.none;
}
public void render(ShapeRenderer shaper)
{
	if(visible)
	{
	shaper.circle(base.x,base.y,base.radius);
	shaper.circle(stick.x, stick.y, stick.radius);
	}
}
public boolean collides() throws Exception
{
	return Intersector.overlaps(base, stick);
}
}
