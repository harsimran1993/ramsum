package ui_objects;

import com.badlogic.gdx.math.Rectangle;

public class Bar {
public Rectangle bar,innerbar;
float fixval,val;
public Bar()
{}
public Bar(float fixval,int x,int y,int width,int height)
{
	this.fixval=fixval;
	this.val=fixval;
	bar=new Rectangle(x,y,width,height);
	innerbar=bar;
}
public void updateleft(float val)
{	//like this [###___]
	this.val=val;
	int newwidth=(int) (bar.getWidth()*(val/fixval));
	innerbar=new Rectangle(bar.x,bar.y,newwidth,bar.height);
}
public void updateright(float val)
{
	//like this [___###]
	this.val=val;
	int newx=(int) (bar.x*(val/fixval));
	innerbar=new Rectangle(newx,bar.y,bar.width-(bar.x-newx),bar.height);
}
public void update(float val)
{
	this.val=val;
	int newx=(int) (bar.x*(val/fixval))+1;
	innerbar=new Rectangle(newx,bar.y,bar.width*(val/fixval)-2,bar.height);
}
}
