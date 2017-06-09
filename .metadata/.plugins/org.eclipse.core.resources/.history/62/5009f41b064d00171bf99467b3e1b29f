package GameObjects;

import Helper.Assetloader;

import box2dLight.ConeLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platforms{
	private Rectangle rect;
	boolean dynamic=false,onscreen=false;
	ConeLight c1,c2;
	int direction=0;
	float dist=0,maxdist=24;
	float vely=4;
	private int type = 0;

	public Platforms(float x, float y, int width, int height,boolean move,int type,float maxdist) {
		rect= new Rectangle(x,y,width,height);
		this.setType(type);
		this.dynamic=move;
		this.maxdist=maxdist;
		vely=maxdist/6;
	}
	public float getX()
	{
		return rect.x;
	}
	
	public float getY()
	{
		return rect.y;
	}

	public void update(float delta)
	{
		
		if(dynamic)
		{
			//System.out.println(dist);
			rect.y+=vely*delta;
			dist+=(vely>0?vely:-vely)*delta;
			if(dist>maxdist)
			{
				vely*=-1;
				dist=0;
			}
		}
	}
	
	public void render(SpriteBatch batcher,float runTime)
	{
		if(type==0 && vely<0){
			batcher.draw(Assetloader.specialeffA[1].getKeyFrame(runTime),rect.x,rect.y+15,30,30,50,50,1f, 1f,200.0f);
		batcher.draw(Assetloader.specialeffA[1].getKeyFrame(runTime),rect.x+rect.width-60,rect.y+15,30,30,50,50,1f, 1f,200.0f);
		}
		if(type == 4)
			batcher.setColor(Color.OLIVE);
		batcher.draw(Assetloader.platform[getType()],rect.x,rect.y,rect.width,rect.height);

		if(type == 4)
			batcher.setColor(Color.WHITE);
	}
	
	/*public void render(SpriteBatch batcher,float Runtime)
	{
		batcher.draw(new TextureRegion(),rect.x,rect.y,rect.width,rect.height);
	}*/

	public float getwidth() {
		// TODO Auto-generated method stub
		return rect.width;
	}

	public float getheight() {
		// TODO Auto-generated method stub
		return rect.height;
	}

	public float getground(Player p)
	{
		switch(getType())
		{
		case 0:return rect.y-getheight();
		case 1:return (float) (-((p.centreX-getX())*0.36)+rect.y+50);
		case 2:return rect.y-getheight();
		case 3:return rect.y - p.getheight() + 7;
		case 4:return rect.y - p.getheight() + 12;
		case 5:return rect.y + rect.height * 0.33f;
		default:return p.getGround();
		}
	}
	public void setX(float f) {
		// TODO Auto-generated method stub
		this.rect.x+=f;
		if(rect.x+rect.width<0 || rect.x>480)
			onscreen=false;
		else
			onscreen=true;
	}
	public boolean onScreen(int gamewidth)
	{
		return ((rect.x>-rect.width)&& rect.x<gamewidth);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float boardingHeight(){
		switch(type){
		case 5: return (rect.y + rect.height * 0.75f);
			default:return (rect.y + 10);
		}
	}
	
}
