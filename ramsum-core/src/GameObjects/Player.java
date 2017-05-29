package GameObjects;

import java.util.ArrayList;

import Helper.Assetloader;
import attacks.bullet;
import attacks.skill;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	private Vector2 pos;
	private Vector2 vel;
	private Vector2 acc;
	public final int walkvel=360;
	public float height,hurttime,maxhurttime=0.3f;
	private float width, ground, koTime,fixground;
	public float hp,mp;
	public boolean isAlive, isleft=false,isAttack=false,isjump=false,isgetup=false,hurt,duck,collide;
	public enum state{walk,idle,run,hurt,ko};
	public int damage,centreX,centreY,hurtDamage,worldCX=364;
	private int att,basehp,basemp,def;
	public state state;
	public skill basic,hurr,punch,slide,uperc,mage;
	public Rectangle rect,bulletrect;
	public String name;
	public int level=1,nextlevelxp,currentlevelxp;
	public boolean moveleft=true,moveright=true;
	public Player()
	{
		//state=state.idle;
	}

public Player(int x,int y,float width,float height,float g,Boolean left,int xp)
{
	level = (int) (Math.floor(25 + Math.sqrt(625 + 100 * xp)) / 50);
	nextlevelxp = 25 * (level) * ( 2 + (level));
	currentlevelxp = 25 * (level - 1) * (2 + (level - 1));
	System.out.println(nextlevelxp+":"+currentlevelxp);
	basehp = 2000+100*level;
	hp=basehp;
	basemp=1000+100*level;
	mp=basemp;
	setAtt(150+10*level);
	def=100+5*level;
	this.width=width;
	this.height=height;
	this.setGround(g);
	this.fixground=g;
	this.worldCX=x;
	pos = new Vector2(x, y);
	vel = new Vector2(0, 0);
	acc = new Vector2(0, 460);
	isAlive = true;
	state=state.idle;
	rect = new Rectangle(x+width/4,y,width/2,height);
	bulletrect = new Rectangle(x-10,y-10,width+20,height+20);
	punch=new skill(1);
	hurr=new skill(2);
	slide=new skill(3);
	uperc=new skill(4);
	mage= new skill(5);
	basic=punch;
	isleft=left;
}
public float getwidth()
{
	if(isleft)
		return -width;
	else
		return width;
}
public float getheight()
{
	return height;
}

public bullet mage() {
	// TODO Auto-generated method stub
	bullet e=new bullet(centreX+(isleft?-20:20), centreY, att * 2, isleft);
	mp-=30;
	isAttack=true;
	width=96;
	if(basic.getId()!=5)
		basic=mage;
	basic.hit();
	return e;
}

public void attack(ArrayList<Enemy> en)
{
	if(!isAttack &&!(state==state.ko))
	{
		isAttack=true;
		width=96;
		if(basic.getId()!=1)
			basic=punch;
		basic.hit(en,pos.x,pos.y,isleft,getAtt());
	}
}
public void hurricane(ArrayList<Enemy> en)
{
	if(mp>100)
	if(!isAttack &&!(state==state.ko))
	{
	isAttack=true;
	width=96;
	if(basic.getId()!=2)
	basic=hurr;
	mp-=100;
	basic.hit(en, pos.x,pos.y,isleft,getAtt());
	}
}
public void hurricane(Player p)
{
	if(mp>100)
	if(!isAttack &&!(state==state.ko))
	{
	isAttack=true;
	width=96;
	if(basic.getId()!=2)
	basic=hurr;
	mp-=100;
	basic.hit(p, pos.x,pos.y,isleft,getAtt());
	}
}
public void slideatt(ArrayList<Enemy> en)
{
	if(mp>60)
	if(!isAttack && state==state.walk && !isjump)
	{
	isAttack=true;
	width=106;
	if(basic.getId()!=3)
	basic=slide;
	mp-=60;
	basic.hit(en, pos.x,pos.y,isleft,getAtt());
	}
	else
	{
		//show message cant slide during jump or idle
	}
}
public void slideatt(Player p)
{
	if(mp>60)
	if(state==state.walk && !isjump)
	{
	isAttack=true;
	width=106;
	if(basic.getId()!=3)
	basic=slide;
	mp-=60;
	basic.hit(p, pos.x,pos.y,isleft,getAtt());
	}
	else
	{
		//show message cant slide during jump or idle
	}
}
public void AirPunch(ArrayList<Enemy> en)
{
	if(mp>10)
	if(!isAttack &&!(state==state.ko))
	{
	height=80;
	if(isjump)
		this.vel.y-=150;
	else if(pos.y>getGround()-80)
		this.vel.y-=330;
	isAttack=true;
	if(basic.getId()!=4)
	basic=uperc;
	mp-=10;
	basic.hit(en, pos.x,pos.y,isleft,getAtt());
	}
}
public void AirPunch(Player p)
{
	if(mp>10)
	if(!isAttack &&!(state==state.ko))
	{
	height=80;
	isjump=true;
	if(isjump)
		this.vel.y-=150;
	else
		this.vel.y-=330;
	isAttack=true;
	if(basic.getId()!=4)
	basic=uperc;
	mp-=10;
	basic.hit(p, pos.x,pos.y,isleft,getAtt());
	}
}
public void attack(Player p)
{
	if(!isAttack &&!(state==state.ko))
	{isAttack=true;
	width=96;
	basic.hit(p,pos.x,pos.y,isleft,getAtt());
	}
}
public float getX() {
	return pos.x;
}

public float getY() {
	return pos.y;
}

public void setX(float f) {
	 pos.x=f;
}

public void setY(float f) {
	 pos.y=f;
}

public void walk(float sx,boolean left){
	if(state==state.ko)
		getup();
	else if(!isjump || true) //short circuit remove true to renable
	{
		this.vel.x=sx;
		/*if(!isleft)
			setX(getX()+40);
		else
			setX(getX()-50);*/
		isleft=left;
		state=state.walk;
	}
}
public void jump(){
	if(state==state.ko)
		getup();
	else if(!isjump){
		isjump=true;
		if(vel.y<-100)
			this.vel.y-=150;
		else
			this.vel.y-=330;
	//state=state.jump;
	}
}

public void stopx()
{
	state=state.idle;
}

public boolean isFalling() {
	return vel.y > 110;
}

public void update(float delta) {
	//jump();
	//System.out.println(state);
	moveleft=moveright=true;
	if(mp<basemp)
		mp+=basemp*0.004*delta;
	if(hp<basehp)
		hp+=basehp*0.004*delta;
	if(hp<1)
	{
		isAlive=false;
		state=state.ko;
		vel.x=isleft?walkvel:-walkvel;
	}
	if(state==state.ko)
	{
		if(koTime<1)
			koTime+=delta;
		else
			{
				vel.x=0;
			if(isgetup)
			{
				koTime+=delta;
				if(koTime>2.6)
				{
					koTime=0;
					state=state.idle;
					isgetup=false;
				}
			}
			}
	}
	if(isleft)
		{
		//rect= new Rectangle(pos.x-width+width/4, pos.y, width/2, height);
		rect.x = pos.x-width+width/4;
		rect.y = pos.y;
		bulletrect.x = pos.x - 10 -width;
		bulletrect.y = pos.y - 10;
		centreX =(int) (pos.x-32);
		centreY =(int) (pos.y+32);
		}
	else
		{
		//rect= new Rectangle(pos.x+width/4, pos.y, width/2, height);
		rect.x = pos.x+width/4;
		rect.y = pos.y;
		bulletrect.x = pos.x - 10;
		bulletrect.y = pos.y - 10;
		centreX =(int) (pos.x+32);
		centreY =(int) (pos.y+32);
		}
	if(pos.y<getGround()-1)
	{
			//acc.y=860;
		vel.add(acc.scl(delta));
	    acc.scl(1/delta);
	}
	else{ 
		if(state==state.idle)
			vel.x=0;
		/*if(!isjump && !isAttack)
		{
		vel.y=0;
		acc.y=0;
		}*/
	}
	if(pos.y-1>=getGround()) 
		{
		//stopx();
		isjump=false;
		//acc.y=0;
		vel.y=0;
		pos.y=getGround()-1;
		}
	if(pos.y<5) pos.y=5;
	//vel.add(acc.scl(delta));
   // acc.scl(1/delta);
    //pos.add(vel.scl(delta));
    //vel.scl(1/delta);
    pos.y+=vel.y*delta;
    if(pos.x>worldCX-width/2 && !isleft)//480-width && !isleft)
    	pos.x=worldCX-width/2;//480-width;
    if(pos.x<worldCX+width/2 && isleft)//width+1 && isleft)
    	pos.x=worldCX+width/2;//width+1;
    if(basic.timer>basic.maxtime && isAttack)
    {
    	stopatt();
    }
    if(isAttack){
    	basic.timer+=delta;
    	//System.out.println(basic.timer);
    }
    else
    {
    	basic.delay-=delta;
    	width=65;
    	height=65;
    }
}

public TextureRegion currentframe(float delta)
{
	if(isAttack)
	{
		//System.out.println(Assetloader.attAnim.getKeyFrameIndex(skill.timer));
		//return Assetloader.attAnim.getKeyFrame(skill.timer);
		return basic.returnFrame(basic.timer);
	}
	if(hurt)
		return Assetloader.ramhurtA.getKeyFrame(hurttime);
	if(state==state.ko)
		return Assetloader.ramkoA.getKeyFrame(koTime);
	if(vel.y>-50 && vel.y<50 && isjump)
		return Assetloader.jump[1];
	if(isjump && !isFalling())
		return Assetloader.jump[0];
	else if(isFalling() && pos.y < ground-5)
		return Assetloader.jump[2];
	else
	if(state==state.idle)
		return Assetloader.idle.getKeyFrame(delta);
	else if(state==state.walk)
		return Assetloader.walk.getKeyFrame(delta);
	else
		return Assetloader.idle.getKeyFrame(delta);
	
}
public synchronized void hurt(int damage , boolean isleft2) {
	// TODO Auto-generated method stub
	if(isAlive &&!(state==state.ko))
	{
		hp-=damage;
		hurtDamage=damage;
		if(damage>basehp * 0.1f)
			ko();
		//pos.x+=isleft2?20:-20;
		vel.x+=isleft2?-5:5;
		/*if(vel.x>150)
			vel.x=150;
		if(vel.x<-150)
			vel.x=-50;
		/*if(state!=state.walk)
			this.isleft=isleft2;*/
		if(hurttime>maxhurttime)
		hurttime=0;
		hurt=true;
	}
}
public synchronized void hurt(int damage) {
	// TODO Auto-generated method stub
	if(isAlive &&!(state==state.ko))
	{
		hp-=damage;
		hurtDamage=damage;
		if(damage>299)
			ko();
		//pos.x+=isleft?-40:40;
		/*vel.x+=isleft?-5:5;
		if(vel.x>150)
			vel.x=150;
		if(vel.x<-150)
			vel.x=-150;*/
		if(hurttime>maxhurttime)
		hurttime=0;
		hurt=true;
	}
}
public void stopatt()
{
	isAttack=false;
	//width=80;
	/*if(state==state.attack)
		state=state.idle;*/
}

public boolean ishurt() {
	// TODO Auto-generated method stub
	return hurt;
}
public void hurtnomore()
{
	hurt=false;
	hurttime=0;
}
public void reset(int nextInt, int nextInt2) {
	// TODO Auto-generated method stub
	isAlive=true;
	//pos.x=nextInt;
	pos.y=nextInt2;
	hurt=false;
	hp=basehp;
	mp=basemp;
	
}

public void duck() {
	// TODO Auto-generated method stub
	if(state==state.idle)
	{
		duck=true;
	}
	pos.y+=11;
	resetGround();
}

public void stand() {
	// TODO Auto-generated method stub
	if(duck)
		duck=false;
}

private void getup()
{
	isgetup=true;
}

private void ko()
{
	state=state.ko;
	vel.x=isleft?100:-100;
	vel.y=-250;
}

public void setGround(float y) {
	// TODO Auto-generated method stub
	//collide=true;
	ground=y;
}
public void resetGround() {
	// TODO Auto-generated method stub
	//collide=false;
	setGround(fixground);
}
public void setFixGround(float y) {
	// TODO Auto-generated method stub
	//collide=true;
	fixground=y;
}
public float getVelX() {
	// TODO Auto-generated method stub
	return vel.x;
}

public void setVelx(float f) {
	// TODO Auto-generated method stub
	vel.x=f;
	
}

public int getAtt() {
	return att;
}

public int getDef(){
	return def;
}

public int getHP(){
	return basehp;
}

public int getMP(){
	return basemp;
}

public void setAtt(int att) {
	this.att = att;
}

public float getGround() {
	return ground;
}

public boolean isko() {
	// TODO Auto-generated method stub
	return state == state.ko;
}

public void updateLevel(int xp){
	level = (int) (Math.floor(25 + Math.sqrt(625 + 100 * xp)) / 50);
	nextlevelxp = 25 * (level) * ( 2 + (level));
	currentlevelxp = 25 * (level - 1) * (2 + (level - 1));
	basehp=2000+100*level;
	hp=basehp;
	basemp=1000+100*level;
	mp=basemp;
	setAtt(150+10*level);
	def=100+5*level;
}
}
