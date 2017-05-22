package GameObjects;

import Helper.Assetloader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class SceneObj {
	//type 0-fog,1-
	int type = 0;
	float speedMUL=1.0f;
	private Rectangle rect;
	
	public SceneObj(int type,int x,int y,int width,int height,float speedMUL)
	{
		setRect(new Rectangle(x, y, width, height));
		this.type = type;
		this.speedMUL=speedMUL;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public float getMUL(){
		return speedMUL;
	}
	
	public void setMUL(float MUL){
		this.speedMUL=MUL;
	}
	
	public void render(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		batcher.draw(Assetloader.objs[type],rect.x,rect.y,rect.width,rect.height);
	}

	public void render(ShapeRenderer shaper) {
		// TODO Auto-generated method stub
		shaper.rect(rect.x,rect.y,rect.width,rect.height);
	}

}
