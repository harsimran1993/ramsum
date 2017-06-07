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
	public boolean isTrigger = false;
	
	public SceneObj(int type,int x,int y,int width,int height,float speedMUL,boolean isTrigger)
	{
		setRect(new Rectangle(x, y, width, height));
		this.type = type;
		this.speedMUL=speedMUL;
		this.isTrigger=isTrigger;
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
		if(isCSTrigger()){
			batcher.draw(Assetloader.ui[17],rect.x+rect.width * 0.5f - 20,rect.y,40,40);
		}
		else if(isLVLTrigger()){
			batcher.draw(Assetloader.ui[17],rect.x+rect.width * 0.5f - 20,rect.y,40,40);
		}
		else if(isPickUP()){}
		else
		batcher.draw(Assetloader.objs[type],rect.x,rect.y,rect.width,rect.height);
	}

	public void render(ShapeRenderer shaper) {
		// TODO Auto-generated method stub
		shaper.rect(rect.x,rect.y,rect.width,rect.height);
	}

	public boolean isPickUP(){
		return type >100;
	}
	public boolean isCSTrigger(){
		return type == -1;
	}
	public boolean isLVLTrigger(){
		return type < -1;
	}
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
