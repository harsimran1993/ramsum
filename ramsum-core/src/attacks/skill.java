package attacks;

import java.util.ArrayList;

import GameObjects.Enemy;
import GameObjects.Player;
import Helper.Assetloader;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class skill {
private int id,width,height;
String name;
public float dmgMul=1,itm[],delay=0.5f,timer=0,maxtime=0;
public int damage,stage=0,maxstage;
public int LextX,LextY,RextX,RextY;
public Rectangle rect;
public skill()
{
	
}
public skill(int id)
{
	this.setId(id);
	rect=new Rectangle();
	switch(id)
	{
	case 1:	width=60;
			height=45;
			name="Basic";
			maxstage=5;
			itm=new float[]{0.21f,0.21f,0.30f,0.30f,0.54f};
			dmgMul=1;
			LextX=-40-width;
			LextY=10;
			RextX=40;
			RextY=10;
			break;
	case 2: width=120;
			height=80;
			name="hurricane";
			maxstage=1;
			itm=new float[]{0.70f};
			dmgMul=1.5f;
			LextX=20-width;
			LextY=0;
			RextX=-20;
			RextY=0;
			break;
	case 3:  width=200;
			height=70;
			name="Slide";
			maxstage=1;
			itm=new float[]{.50f};
			dmgMul=2.8f;
			LextX=35-width;
			LextY=0;
			RextX=-35;
			RextY=0;
			delay=0.0f;
			break;
	case 4: width=80;
			height=160;
			name="airpunch";
			maxstage=2;
			itm=new float[]{0.42f,0.36f};
			dmgMul=2.5f;
			LextX=-width;
			LextY=-90;
			RextX=0;
			RextY=-90;
			break;
	case 5: width=60;
			height=45;
			name="mage";
			maxstage=1;
			itm=new float[]{0.21f};
			LextX=-40-width;
			LextY=10;
			RextX=40;
			RextY=10;
			break;
	default: width=40;
			 height=60;
			 name="Basic";
			 dmgMul=1;
			 break;
	}
}

public void hit(ArrayList<Enemy> en,float x,float y,boolean left,int attack)
{
	if(delay<0)
		reset();
	stage++;
	if(stage>maxstage)
		stage=1;
	maxtime+=itm[stage-1];
	if(left)
		rect=new Rectangle(x+LextX, y+LextY, width, height);
	else
		rect=new Rectangle(x+RextX, y+RextY, width, height);
	for (Enemy enemy : en) {
		if(enemy.isAlive && Intersector.overlaps(enemy.rect, rect) )
		{
			damage=(int) (attack*(dmgMul+(0.2*stage))+(Assetloader.getrandom(5)*0.1f)*(attack-(Assetloader.getrandom(5)*0.1f)*enemy.def));
			enemy.hurt(damage,(enemy.getX()-x)>0?20:-20);
			
			if(enemy.ishit())
				switch(id)
				{
					case 3: if(left)
								enemy.fallto(-80);
							else
								enemy.fallto(80);
							break;
					case 2:if(enemy.getX()-x>0)
								enemy.fallto(40);
							else
								enemy.fallto(-40);
				}
		}	
		
	}
}

//for bullets
public void hit(){
	if(delay<0)
	reset();
stage++;
if(stage>maxstage){
	stage=1;
	timer=0;
}
maxtime=itm[stage-1];
}

public void hit(Player p,float x,float y,boolean left,int attack)
{
	if(delay<0)
		reset();
	stage++;
	if(stage>maxstage)
		stage=1;
	maxtime+=itm[stage-1];
	if(left)
		rect=new Rectangle(x+LextX, y+LextY, width, height);
	else
		rect=new Rectangle(x+RextX, y+RextY, width, height);
	if(p.isAlive && Intersector.overlaps(p.rect, rect) && timer>(maxtime-0.5f))
	{
		damage=(int) (attack*(dmgMul+(0.2*stage))+(Assetloader.getrandom(5)*0.1f)*(attack-(Assetloader.getrandom(5)*0.1f)*p.getDef()));
		p.hurt(damage,!left);
	}
}
public void reset()
{

	timer=0;
	stage=0;
	maxtime=0;
	if(id==3)
		delay=0.0f;
	else
		delay=0.5f;
}

public TextureRegion returnFrame(float timer)
{
	switch(getId())
	{
		case 1:return Assetloader.attAnim.getKeyFrame(timer);
		case 2:return Assetloader.hurricaneA.getKeyFrame(timer);
		case 3:return Assetloader.slideA.getKeyFrame(timer);
		case 4:return Assetloader.ramupA.getKeyFrame(timer);
		default:return Assetloader.attAnim.getKeyFrame(timer);
	}
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
