package GameObjects;

import ui_objects.Bar;

import GameWorld.GameWorld;
import Helper.Assetloader;

import attacks.barrage;
import attacks.bullet;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	private Vector2 pos;
	private Vector2 vel;
	private Vector2 acc;
	public float height,width,timer=0,hurttime=0,hittime=0,spawnTime=0.60f,tempwidth,fixwidth,hurtmax;
	public int hp;
	public int mp,att,def,hurtdamage,hurtratio,damage;
	public Rectangle rect;
	private float ground,atttime,distance,idlewalkdist=-1,attackrange,alertrange,movevel,attackdelay;
	public boolean isAlive,isscored=false,isfly=false,shot[],isleft=false,isboss=false;
	private boolean hurt;
	private enum state{idle,attack,hit,special};
	private state State;
	private boolean walk=true,isranger=false,isOnScreen,specfired=false;
	public int type,minionoffset;
	public Bar hpb,mpb;
	private int health[]={5000,1200,1500,750,1200,20000};
	int attack[]={300,30,60,125,100,250};
	int defense[]={500,20,40,30,120,200};
	int attrange[]={200,150,150,300,50,200};
	int alrtrange[]={660,600,600,650,200,2000};
	int hurtrto[]={10,2,4,5,3,10};
	int minoff[]={0,0,2,1,1,1};
	int bossoff[]={0,0,0,0,0,1};
	float attackTime[]={2.4f,1.0f,2.4f,1.4f,1.2f,2.4f};//hit the player after atttime duration
	public float hitTime[][]={{2.4f},{0.6f},{2.4f},{0.4f},{0.3f,0.6f},{0.8f,1.7f,2.3f}};
	float Velv[]={10,120,11,20,100,400};//movement vel
	float Awmul[]={1,1,1,2,1,1};//temp width when attacking
	float hurttm[]={0.36f,0.54f,0.36f,0.30f,0.24f,0.72f};//hurt animation time
	float attackdely[]={3f,2f,2f,2f,1f,2f};//delay between consecutive hits
	public float dialogtime=0;
	String dialogs[]={"smash!!","Face me Coward!!","Ha Ha Ha Ha","Face My Wrath!!!"};
	int specseq[]={1,0,0,0,0,2};
	int currentspec=0;
	public int level=1;
	//public skill basic;
	public String dialog="";
	
	public Enemy()
	{
		
	}
	public Enemy(int type,int x,int y,float width,float height,float g,boolean fly,int lvl,boolean isboss)
	{
		this.type=type;
		level=lvl;
		/*if(type==1)   //non moving boss
			walk=false;*/
		spawnTime=0.60f;
		hp=health[type]+(health[type]*(level-1))/2;
		att=attack[type]+(attack[type]*(level-1))/2;
		def=defense[type]+(defense[type]*(level-1))/2;
		atttime=attackTime[type];
		attackrange=attrange[type];
		alertrange=alrtrange[type];
		hurtratio=hurtrto[type];
		minionoffset=minoff[type];
		hurtmax=hurttm[type];
		movevel=(float) (Velv[type]+Assetloader.getrandom((int) (Velv[type] * 0.5f)));
		attackdelay=0;
		State=state.idle;
		isAlive=true;
		this.width=width;
		this.fixwidth=width;
		tempwidth=width*Awmul[type];
		this.height=height;
		this.ground=g;
		pos = new Vector2(x, y);
		vel = new Vector2(0, 0);
		acc = new Vector2(0, 460);
		rect= new Rectangle(x, y, width, height);
		this.isfly=fly;
		hpb=new Bar((float)hp,(int) this.pos.x,(int) this.pos.y,(int) width, 5);
		shot=new boolean[hitTime.length];
		if(type == 3 || type == 5)
			this.isranger=true;
		this.isboss=isboss;
		if(isboss){
			mp=0;
			mpb=new Bar(100,(int) this.pos.x,(int) this.pos.y-7,(int) width, 5);
			mpb.update(mp);
		}
	}
	public void update(float delta, Player p1,GameWorld world)
	{
		isOnScreen = true;
		if(hittime<hurtmax){
			hittime +=delta;
		}
		else if(ishit()){
			hurtnomore();
		}
		if(dialogtime > 0) {
			dialogtime -= delta;
		}
		else if(specfired)
		{
			specfired = false;
			switch(currentspec){
			
			case 0:
				break;
			case 1:world.DropBarrage(new barrage(p1.getX(), 10, (int) p1.getwidth(), 60, 1));
				break;
			
			default:world.DropBarrage(new barrage(p1.getX(), 10, (int) p1.getwidth(), 60, 1));
			}
		}
		distance=(float) Math.sqrt((p1.centreX-pos.x)*(p1.centreY-pos.x)) + 1;
		/*if(rect.overlaps(p1.rect))
		{
			if(p1.getX()>pos.x)
				pos.x-=p1.rect.width;
			else
				pos.x+=p1.rect.width;
		}*/
		if(isleft)
			rect= new Rectangle(pos.x-width, pos.y, fixwidth, height);
		else
			rect= new Rectangle(pos.x, pos.y, fixwidth, height);
		if(!isfly)
		{
			vel.y+=acc.y*delta;
	    	pos.y+=vel.y*delta;
		}
		if(pos.y-1>=ground) 
		{
			acc.y=0;
			vel.y=0;
			pos.y=ground+1;
		}
		else
			acc.y=800;
		if(hp<1)
		{
			isAlive=false;
		}
		/*if(near(p1))
		{
			if(State==state.idle)
				attack(p1);
		}
		else if(State==state.idle && distance<alertrange )
		{
			move(p1.getX(),p1.getY());
			//System.out.println(type+"moving");
		}
		else
		{
			State=state.idle;
			//System.out.println(type+"idle");
		}*/
		if(attackdelay>-1)
			attackdelay-=delta;
		//System.out.println(attackdelay);
		if(State==state.attack)
		{
			if(mp>100){
				dialog=dialogs[bossoff[type]+currentspec];
				dialogtime = 2;
				mp=0;
				currentspec++;
				specfired = true;
				if(currentspec>specseq[type])
					currentspec=0;
			}
	    	//basic.timer+=delta;
			timer+=delta;
			width=tempwidth;
			for(int i=0;i<hitTime[type].length;i++)
			if(timer>hitTime[type][i])
			{
				//System.out.println("shot"+i+":"+shot[i]);
				if(!shot[i])//near(p1) && !shot )
					{
					//System.out.println(hitTime[type].length+" type:"+type+"attacking"+i);
					if((this.pos.x-p1.getX())<0)
						isleft=true;
					else if((p1.getX()-this.pos.x)<0)
						isleft=false;
					if(isranger) { //range class enemy
						world.enmage(mage());
						if(isboss){
							mp+=10;
							mpb.update(mp);
						}
					}
					else{
					    damage=(int) (att*1.3+(Assetloader.getrandom(5)-0.1f)*(att-p1.getDef()));
					    if(damage<0)
					    	damage=0;
					    else if(damage>att*1.3)
					    	damage=(int) (att*1.3);
						p1.hurt(damage,isleft);
						if(isboss){
							mp+=10;
							mpb.update(mp);
						}
					}
					if(i>=hitTime[type].length-1){
							//System.out.println("delay");
							attackdelay=attackdely[type];
					}
					shot[i]=true;
								//basic.hit(p1,pos.x,pos.y,!isleft,att);
				}
				if(timer>atttime){
					System.out.println("reset shots a");
					for(int j=0;j<shot.length;j++)
						shot[j]=false;
					timer=0;
					setIdle();
					width=fixwidth;
				}
			}
		}
		else
		{
			width=fixwidth;
			/*for(boolean b:shot)
				b=false;
			timer=0;
	    	basic.delay-=delta;*/
		}
		if(State==state.idle)
		{
			if(walk && !isAlert(p1))
			{
				if(idlewalkdist<0)
					vel.x=isleft?movevel:-movevel;
				if(idlewalkdist>500)
				{
					vel.x=-vel.x;
					isleft=!isleft;
					idlewalkdist=1;
				}
				/*if(pos.x>p1.getX() && pos.x < p1.getX()+p1.rect.width)
					vel.x=0;*/
				pos.x+=vel.x*delta;
				idlewalkdist+=(vel.x>0?vel.x:-vel.x)*delta;
			}
			else if(walk && !movenear(p1)){//distance>50){
				pos.x+=vel.x*delta;
				/*if(p1.isjump)
				pos.y=p1.getY()-20;*/
			}
			else
				vel.x=0;
		}
	}
	public void hurt(int damage , int move) {
		// TODO Auto-generated method stub
		if(isAlive)
		{
			hp-=damage;
			hpb.update(hp);
			hurtdamage=damage;
			//pos.x-=isleft?(tempwidth-fixwidth):-(tempwidth-fixwidth);
			hurttime=0;
			hurt=true;
			if(type==1)
				width=fixwidth*1.5f;
			if(Assetloader.getrandom(hurtratio)<2 && isIdle())
			{
					hittime=0;
					State=state.hit;
			}
		}
	}
	public boolean isScrolledLeft()
	{
		return pos.x<0;
	}
	public void setX(float f)
	{
		pos.x+=f;
	}
	public float getX() {
		return pos.x;
	}
	public float getY() {
		return pos.y;
	}
	public void move(float x,float y) {
		boolean temp=isleft;
		if((this.pos.x-x)<0)
			isleft=true;
		else if((x-this.pos.x)<0)
			isleft=false;
		if(isleft!=temp)
			{
			if(isleft) this.pos.x+=width; else this.pos.x-=width;
			}
		if(walk)
		{
			vel.x=isleft?movevel:-movevel;
			if(isfly)
				vel.y=(this.pos.y-y)>50? -movevel/5:(y-this.pos.y)>50?movevel/5:0;
		}
	}
	public boolean ishurt()
	{
		return hurt;
	}
	public boolean ishit()
	{
		return State==state.hit;
	}
	//when state hit active
	public void hurtnomore()
	{
		State=state.idle;
		//pos.x+=isleft?(tempwidth-fixwidth):-(tempwidth-fixwidth);
		hurt=false;
		if(type==1)
			width=fixwidth;
	}
	//when not in hit state but is hurt
	public void resetHurt(){
		hurt=false;
	}
	public void reset(int nextInt, int nextInt2) {
		// TODO Auto-generated method stub
		isAlive=true;
		pos.x=nextInt;
		pos.y=nextInt2;
		if(type==1)
		width=60;
		hurt=false;
		isscored=false;
		hp=health[type];
		spawnTime=0.60f;
		timer=0;
		State=state.idle;
		hpb.update(hp);
		
	}
	public float getwidth()
	{
		if(isleft)
			return -width;
		else
			return width;
	}
	public boolean near(Player p1)
	{
		//Euclidean distance
		//distance=(float) Math.sqrt((p1.centreX-pos.x)*(p1.centreY-pos.x));
		//distanceY=(float) Math.sqrt((p1.getY()-pos.y)*(p1.getY()-pos.y));
		return distance<attackrange && (isfly?true:((p1.getY()<(pos.y+height) && p1.getY()+p1.getheight()>pos.y)));//(p1.getY()+p1.getheight())>pos.y;
	}
	public boolean movenear(Player p1)
	{
		return distance<attackrange * 0.5f ;
	}
	public void setfly()
	{
		isfly=true;
	}
	public void attack(Player p1)
	{
		State=state.attack;
		stop();
	}
	public void stop() {
		// TODO Auto-generated method stub
		vel.x=0;
		vel.y=0;
	}
	public Vector2 getPos() {
		// TODO Auto-generated method stub
		return pos;
	}
	public boolean isIdle() {
		// TODO Auto-generated method stub
		return State==state.idle;
	}
	public boolean isAttacking()
	{
		return State==state.attack;
	}
	public void fallto(int x)
	{
		pos.x+=x;
	}
	public TextureRegion currentframe(float delta)
	{
		switch(State)
		{
		case attack:return(Assetloader.enemyattackA[type-minionoffset].getKeyFrame(timer));
		case idle:
			if(vel.x!=0)	return(Assetloader.enemywalkA[type-minionoffset].getKeyFrame(delta));
			else 			return(Assetloader.enemystandA[type-minionoffset].getKeyFrame(delta));
		case hit:return(Assetloader.enemyhitA[type-minionoffset].getKeyFrame(hittime));
		default:return(Assetloader.enemystandA[type-minionoffset].getKeyFrame(delta));
		}
	}
	public boolean isAlert(Player p1)
	{
		//distance=(float) Math.sqrt((p1.centreX-pos.x)*(p1.centreY-pos.x));
		return distance<alertrange && isboss?true:(isfly?true:((p1.getY()<(pos.y+height) && p1.getY()+p1.getheight()>pos.y)));
	}
	
	public boolean attackrelay(){
		return  attackdelay<0;
	}
	public void setIdle()
	{
		State=state.idle;
		idlewalkdist=-50;
	}
	public void enableWalk()
	{
		walk=true;
	}
	public void disableWalk()
	{
		walk=false;
	}
	
	public boolean canWalk(){
		return walk;
	}
	public boolean onScreen(int gameWidth)
	{
		return (	( (pos.x - (gameWidth * 0.5f) ) > - (alertrange * 2) ) && ( (pos.x - getwidth() + (gameWidth * 0.5f) ) < (768 + (alertrange * 2) ) )	);
	}
	public void collides(Player p1)
	{
		if(this.rect.overlaps(p1.rect))
		{
			if(p1.isleft)
			{
				p1.moveleft=false;
			}
			else
			{
				p1.moveright=false;
			}
		}
	}
	public bullet mage() {
		// TODO Auto-generated method stub
		bullet e=new bullet((pos.x+width/2)+(isleft?-20:20), (pos.y+height/2),!isleft);
		return e;
	}
	
	public barrage specbrg(){
		return new barrage((float) (Assetloader.getrandom(600)+20), 10, 40, 40, 1);
	}
	
	public void OffScreen(){
		isOnScreen=false;
	}
	
	public boolean isOnScreen(){
		return isOnScreen;
	}
	public int getBaseHealth() {
		return health[type];
	}
	
}
