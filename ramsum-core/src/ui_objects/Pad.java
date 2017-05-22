package ui_objects;

import java.util.ArrayList;

import Helper.Assetloader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Pad {
Circle c;
int radius,x,y;
boolean visible=false;
public Pad(int radius)
{
 this.radius=radius;
}

public void view(int x,int y)
{
	this.x=x;
	this.y=y;
	visible=true;
}

public void hide()
{
	visible=false;
}

public void render(SpriteBatch batcher)
{
	if(visible)
	batcher.draw(Assetloader.getUI(0),x,y,radius,radius);
}
}
