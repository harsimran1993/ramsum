package GameWorld;

import java.util.ArrayList;
import java.util.Random;

import ui_objects.Bar;

import GameObjects.Background;
import GameObjects.Enemy;
import GameObjects.Grass;
import GameObjects.Platforms;
import GameObjects.Player;
import GameObjects.SceneObj;
import Helper.Assetloader;
import box2dLight.DirectionalLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import GameObjects.ScrollHandler;

public class GameRenderer {

	private GameWorld Myworld;
	private SpriteBatch batcher;
	private ShapeRenderer shaper;
	private OrthographicCamera cam;
	private ScrollHandler scroller;
	private int gameHeight, gameWidth, lastLevel;
	private float halfWidth,halfHeight;
	private Background bg1,bg2;
	private Grass frontGrass, backGrass, backGrass2,waterf,waterb,waterb2;
	private Player p1;
	private ArrayList<Enemy> en;
	private Bar hp,mp,xp;
	private Platforms[] platforms;
	private Color c,batcherclr;
	private String str,DIG,Speaker;
	private Rectangle mager;
	World worldleft;
	static RayHandler rayHandler;
	// set to .2 for proper darkness
	private static float ambientLight =0.2f;
	
	public GameRenderer(GameWorld world, int gameHeight, int gameWidth,
			int midPointY) {
		// TODO Auto-generated constructor stub
		
		Myworld=world;
		cam=new OrthographicCamera();
		cam.setToOrtho(true,gameWidth,gameHeight);
        shaper=new ShapeRenderer();
        shaper.setProjectionMatrix(cam.combined);
        batcher = new SpriteBatch();
        // Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);
        batcherclr = batcher.getColor();
        this.gameHeight = gameHeight;
        this.gameWidth=gameWidth;
        this.halfWidth = gameWidth * 0.5f;
        this.halfHeight = gameHeight * 0.5f;
        new Random();
        en= Myworld.en;
    	scroller = Myworld.getScroller();
    	platforms= Myworld.platforms;
		worldleft = new World(new Vector2(0, 0), true);
		rayHandler = new RayHandler(worldleft);
        rayHandler.setCulling(true);
		rayHandler.setCombinedMatrix(cam.combined);
		c=new Color().set(0, 0, 0, world.level.intensity);
		new DirectionalLight(rayHandler, 90, c, 30);
		//new ConeLight(rayHandler, 50, Color.CYAN, 450,150,0, 90, 30);
		//new PointLight(rayHandler, 50,Color.CYAN,120,50,50);
		rayHandler.setAmbientLight(ambientLight);
        initGameObjects();
        initAssets();
	}
	 private void drawGrass() {
	        // Draw the grass
		 if(Myworld.level.water)
		 {
			 batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 0.8f);
		     batcher.draw(Assetloader.grass[Myworld.level.grass], waterf.getX(), waterf.getY()- waterf.getHeight(),
		    		 waterf.getWidth(), waterf.getHeight()*2);
		     //Assetloader.font.draw(batcher, "front",  frontGrass.getX(),frontGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], waterb.getX(), waterb.getY()- waterb.getHeight(),
		    		 waterb.getWidth(), waterb.getHeight()*2);
		     //Assetloader.font.draw(batcher, "back",  backGrass.getX(),backGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], waterb2.getX(), waterb2.getY()- waterb2.getHeight(),
		    		 waterb2.getWidth(), waterb2.getHeight()*2);
		     //layer bottom
			 batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 0.9f);
		     batcher.draw(Assetloader.grass[Myworld.level.grass], frontGrass.getX(), frontGrass.getY()- frontGrass.getHeight()/2,
		                frontGrass.getWidth(), frontGrass.getHeight()*1.5f);
		     //Assetloader.font.draw(batcher, "front",  frontGrass.getX(),frontGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], backGrass.getX(), backGrass.getY()- frontGrass.getHeight()/2,
		                backGrass.getWidth(), backGrass.getHeight()*1.5f);
		     //Assetloader.font.draw(batcher, "back",  backGrass.getX(),backGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], backGrass2.getX(), backGrass2.getY()- frontGrass.getHeight()/2,
		                backGrass2.getWidth(), backGrass2.getHeight()*1.5f);

			 batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 1f);
		 }
		 else{
		     batcher.draw(Assetloader.grass[Myworld.level.grass], frontGrass.getX(), frontGrass.getY(),
		                frontGrass.getWidth(), frontGrass.getHeight());
		     //Assetloader.font.draw(batcher, "front",  frontGrass.getX(),frontGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], backGrass.getX(), backGrass.getY(),
		                backGrass.getWidth(), backGrass.getHeight());
		     //Assetloader.font.draw(batcher, "back",  backGrass.getX(),backGrass.getY());
		     batcher.draw(Assetloader.grass[Myworld.level.grass], backGrass2.getX(), backGrass2.getY(),
		                backGrass2.getWidth(), backGrass2.getHeight());
		     //Assetloader.font.draw(batcher, "back2",  backGrass2.getX(),backGrass2.getY()); if(Myworld.level.grass == 2)
		 }
		 if(Myworld.level.upwall){
		     batcher.draw(Assetloader.upwall[Myworld.level.upwallid], frontGrass.getX(), 0,
		    		 frontGrass.getWidth(), frontGrass.getHeight() * 1.5f);
		     //Assetloader.font.draw(batcher, "front",  frontGrass.getX(),frontGrass.getY());
		     batcher.draw(Assetloader.upwall[Myworld.level.upwallid], backGrass.getX(), 0,
		    		 backGrass.getWidth(), backGrass.getHeight() * 1.5f);
		     //Assetloader.font.draw(batcher, "back",  backGrass.getX(),backGrass.getY());
		     batcher.draw(Assetloader.upwall[Myworld.level.upwallid], backGrass2.getX(), 0,
		    		 backGrass2.getWidth(), backGrass2.getHeight() * 1.5f);
		 }
	    }

	private void initAssets() {
		// TODO Auto-generated method stub
		
	}

	private void initGameObjects() {
		// TODO Auto-generated method stub
		loadplayer();
		bg1=scroller.getBg1();
		bg2=scroller.getBg2();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        backGrass2= scroller.getBackGrass2();
        waterf=scroller.getwaterf();
        waterb=scroller.getwaterb();
        waterb2=scroller.getwaterb2();
        hp=new Bar(p1.getHP(), (int) halfWidth -121+90, 17, -90, 12);
        mp=new Bar(p1.getMP(), (int) halfWidth + 31, 17, 90, 12);
        xp=Myworld.xpBar;
        lastLevel = p1.level;
	}
	
	public void loadplayer()
	{
		p1=Myworld.getPlayer();
	}

	public void render(float runTime,float delta) {
		// TODO Auto-generated method stub

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(lastLevel != p1.level){
	        hp=new Bar(p1.getHP(), (int) halfWidth -121+90, 17, -90, 12);
	        mp=new Bar(p1.getMP(), (int) halfWidth + 31, 17, 90, 12);
	        xp=Myworld.xpBar;
			lastLevel=p1.level;
			//System.out.println("changed render xpbar");
		}
		hp.updateleft(p1.hp);
		mp.updateleft(p1.mp);
		xp.updateleft(Myworld.xp-p1.currentlevelxp);
		batcher.begin();
		//background
		 batcher.draw(Assetloader.cloud[Myworld.level.back], bg1.getX(), bg1.getY(),
	                bg1.getWidth(), bg1.getHeight());
		 batcher.draw(Assetloader.cloud[Myworld.level.back], bg2.getX(), bg2.getY(),
	                bg2.getWidth(), bg2.getHeight());
		if(Myworld.level.grass!=2)
		drawGrass();

		//scene objects
		for(SceneObj obj:Myworld.objs)
		obj.render(batcher);

		batcher.end();
		shaper.begin(ShapeType.Filled);
		shaper.setColor(Color.BLACK);
		shaper.rect(Myworld.start.x,Myworld.start.y,Myworld.start.width,Myworld.start.height);
		shaper.rect(Myworld.end.x,Myworld.end.y,Myworld.end.width,Myworld.end.height);
		shaper.end();
		batcher.begin();
		//platforms
		for(Platforms plat:platforms)
			if(plat.onScreen(gameWidth))
				plat.render(batcher,runTime);
		
		
		/*if(Myworld.ismuLt())
		{
		batcher.draw(Myworld.getopponent().currentframe(runTime), Myworld.getopponent().getX(),Myworld.getopponent().getY(),Myworld.getopponent().getwidth(),Myworld.getopponent().getheight());
		Assetloader.font.draw(batcher, ""+Myworld.getopponent().name, Myworld.getopponent().getX()+(Myworld.getopponent().isleft?-45:35), Myworld.getopponent().getY()-20);
		Assetloader.font.draw(batcher, ""+Myworld.getopponent().hp, Myworld.getopponent().getX()+(Myworld.getopponent().isleft?-50:30), Myworld.getopponent().getY()-40);
		if(Myworld.getopponent().ishurt() && Myworld.getopponent().hurttime<1.2f)
		{
			Assetloader.font.setColor(Color.YELLOW);
			Assetloader.font.setScale(1.2f,1.2f);
			Assetloader.font.draw(batcher, ""+p1.basic.damage, Myworld.getopponent().getX()+(Myworld.getopponent().isleft?-50:30), Myworld.getopponent().getY()-30-Myworld.getopponent().hurttime*3);
			Myworld.getopponent().hurttime+=delta;
			Assetloader.font.setColor(Color.WHITE);
			Assetloader.font.setScale(1f,1f);
		}
		else if(Myworld.getopponent().ishurt())
		{
			Myworld.getopponent().hurtnomore();
		}
		
		}
		else */
		for (Enemy enemy : en) 
		{
			//renderdamage point above enemy while hurt else put enemy out of hurt stage
			if(enemy.ishurt() && enemy.hurttime<enemy.hurtmax)
			{
				Assetloader.font.setColor(Color.RED);
				Assetloader.font.setScale(1.2f,-1.2f);
				Assetloader.font.draw(batcher, ""+enemy.hurtdamage, enemy.getX()- (enemy.isleft?enemy.width:0), enemy.getY()-50-enemy.hurttime*60);
				enemy.hurttime+=delta;
			}
			else if(enemy.ishurt())
			{
				enemy.resetHurt();
			}
			//render enemy
			if(enemy.isOnScreen()){
				if(enemy.isAlive)
				{
					batcher.draw(enemy.currentframe(runTime),enemy.getX(), enemy.getY(), enemy.getwidth(), enemy.height);

					Assetloader.font.setColor(Color.WHITE);
					Assetloader.font.setScale(0.6f,-0.6f);
					Assetloader.font.draw(batcher, ""+enemy.hp, enemy.getX() - (enemy.isleft?enemy.width:0), enemy.getY()-20);
					if(enemy.isboss && enemy.dialogtime>0){
						Assetloader.font.setColor(Color.YELLOW);
						Assetloader.font.setScale(0.75f,-0.75f);
						Assetloader.font.draw(batcher, enemy.dialog, enemy.getX()-(enemy.isleft?enemy.width:0), enemy.getY()-50);
						Assetloader.font.setColor(Color.WHITE);
					}
				}
				else
				{
					batcher.draw(Assetloader.blastA.getKeyFrame(enemy.spawnTime),enemy.getX()-20, enemy.getY()-10, (enemy.getwidth()>0)?100:-100, 100);
				}
			}
		}
		//player hurt
		if(p1.ishurt())
		{
			if(p1.hurttime<p1.maxhurttime)
			{
				Assetloader.font.setColor(Color.RED);
				Assetloader.font.setScale(1.2f,-1.2f);
				if(p1.hurtDamage!=0)
						Assetloader.font.draw(batcher, ""+p1.hurtDamage, p1.getX()+(p1.isleft?-50:30), p1.getY()-50-p1.hurttime*60);
				p1.hurttime+=delta;
				Assetloader.font.setColor(Color.WHITE);
			}
			else
				p1.hurtnomore();
		}
		//player title
		//Assetloader.font.draw(batcher, ""+p1.name, p1.getX()+(p1.isleft?p1.getwidth():0), p1.getY()-20);
		batcher.draw(p1.currentframe(runTime), p1.getX(),p1.getY(),p1.getwidth(),p1.getheight());
		if(p1.isAttack )
		{
			switch (p1.basic.getId()) {
			case 2:
				batcher.draw(Assetloader.specialeffA[2].getKeyFrame(runTime), p1.getX()-(p1.getwidth()/2),p1.getY()-p1.getheight()/1.5f,p1.getwidth()*2,p1.getheight()*2);
				break;

			default:
				break;
			}
		}
		//onwin message
		if(Myworld.isWin())
		{
			
			if(p1.isAlive)
			{
				String msg="victory";
				Assetloader.font.draw(batcher,msg, halfWidth-(str.length()*3)/2, halfHeight-2);
			}
			else
			{
				String msg="loss";
				Assetloader.font.draw(batcher,msg, halfWidth-(msg.length()*3)/2, halfHeight-2);
			}
		}
		if(Myworld.level.grass==2)
		drawGrass();

		batcher.setColor(Color.GREEN);
		for(int i=0;i<Myworld.mage.size();i++){
			mager=Myworld.mage.get(i).rect;
			batcher.draw(Assetloader.bulletA.getKeyFrame(runTime), mager.x, mager.y, mager.width, mager.height);
		}
		
		batcher.setColor(Color.YELLOW);
		for(int i=0;i<Myworld.enemage.size();i++)
		{
			mager=Myworld.enemage.get(i).rect;
			batcher.draw(Assetloader.bulletA.getKeyFrame(runTime), mager.x, mager.y, mager.width, mager.height);
		}
		batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 1f);

		batcher.end();
		rayHandler.updateAndRender();
		/*shaper.begin(ShapeType.Filled);
		shaper.setAutoShapeType(true);
		shaper.setColor(Color.BLACK);
		shaper.rect(Myworld.start.x,Myworld.start.y,Myworld.start.width,Myworld.start.height);
		shaper.rect(Myworld.end.x,Myworld.end.y,Myworld.end.width,Myworld.end.height);
		shaper.end();*/
		
		if(Myworld.isCUTSCENE()){
			//cutscene render
			batcher.begin();
			batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 0.8f);
			batcher.draw(Assetloader.ui[12],10, gameHeight*0.88f, gameWidth-20, gameHeight*0.12f);
			batcher.setColor(batcherclr.r, batcherclr.g, batcherclr.b, 1f);
			Speaker=Myworld.level.dialogs[Myworld.currentDialog].speaker;
			DIG=Speaker+" : "+Myworld.level.dialogs[Myworld.currentDialog].dialog;
			Assetloader.font.setColor(Color.BLACK);
			//Assetloader.font.setScale(1.2f,-1.2f);
			Assetloader.font.draw(batcher,DIG,20, gameHeight*0.9f);
			batcher.end();
		}
		else
		{

			//ui
			//stat-bars
			shaper.begin(ShapeType.Filled);
			shaper.setAutoShapeType(true);
			shaper.setColor(Color.RED);
			shaper.rect(hp.innerbar.x,hp.innerbar.y,hp.innerbar.width,hp.innerbar.height);
			shaper.setColor(Color.BLUE);
			shaper.rect(mp.innerbar.x,mp.innerbar.y,mp.innerbar.width,mp.innerbar.height);
			shaper.setColor(Color.ORANGE);
			shaper.rect(xp.innerbar.x,xp.innerbar.y,xp.innerbar.width,xp.innerbar.height);
			shaper.end();
			
			batcher.begin();
			//attack-icon
			batcher.draw(Assetloader.ui[p1.isAttack?2:1],Myworld.c5.x-Myworld.c5.radius, Myworld.c5.y-Myworld.c5.radius, Myworld.c5.radius * 2, Myworld.c5.radius * 2);
			//potion-icon
			batcher.draw(Assetloader.ui[0],Myworld.s[4].x-Myworld.s[4].radius, Myworld.s[4].y-Myworld.s[4].radius, Myworld.s[4].radius * 2, Myworld.s[4].radius * 2);
			//mage-icon
			//batcher.draw(Assetloader.ui[3],Myworld.s[3].x-Myworld.s[3].radius-5, Myworld.s[3].y-Myworld.s[3].radius-5, Myworld.s[3].radius * 2 + 10, Myworld.s[3].radius * 2 + 10);
			batcher.draw(Assetloader.ui[6],Myworld.s[3].x-Myworld.s[3].radius, Myworld.s[3].y-Myworld.s[3].radius, Myworld.s[3].radius * 2, Myworld.s[3].radius * 2);
			//slide-icon
			//batcher.draw(Assetloader.ui[3],Myworld.s[0].x-Myworld.s[0].radius-5, Myworld.s[0].y-Myworld.s[0].radius-5, Myworld.s[0].radius * 2 + 10, Myworld.s[0].radius * 2 + 10);
			batcher.draw(Assetloader.ui[9],Myworld.s[0].x-Myworld.s[0].radius, Myworld.s[0].y-Myworld.s[0].radius, Myworld.s[0].radius * 2, Myworld.s[0].radius * 2);
			//airpunch-icon
			//batcher.draw(Assetloader.ui[3],Myworld.s[1].x-Myworld.s[1].radius-5, Myworld.s[1].y-Myworld.s[1].radius-5, Myworld.s[1].radius * 2 + 10, Myworld.s[1].radius * 2 + 10);
			batcher.draw(Assetloader.ui[8],Myworld.s[1].x-Myworld.s[1].radius, Myworld.s[1].y-Myworld.s[1].radius, Myworld.s[1].radius * 2, Myworld.s[1].radius * 2);
			//hurricane-icon
			//batcher.draw(Assetloader.ui[3],Myworld.s[2].x-Myworld.s[2].radius-5, Myworld.s[2].y-Myworld.s[2].radius-5, Myworld.s[2].radius * 2 + 10, Myworld.s[2].radius * 2 + 10);
			batcher.draw(Assetloader.ui[7],Myworld.s[2].x-Myworld.s[2].radius, Myworld.s[2].y-Myworld.s[2].radius, Myworld.s[2].radius * 2, Myworld.s[2].radius * 2);
			//switchoff
			batcher.draw(Assetloader.ui[13],Myworld.c6.x-Myworld.c6.radius, Myworld.c6.y-Myworld.c6.radius, Myworld.c6.radius * 2, Myworld.c6.radius * 2);

			if(Myworld.lvlbtn)
			{
			//next-level
			batcher.draw(Assetloader.ui[15],Myworld.nxtlvl.x-Myworld.nxtlvl.radius, Myworld.nxtlvl.y-Myworld.nxtlvl.radius, Myworld.nxtlvl.radius * 2, Myworld.nxtlvl.radius * 2);
			}
			//stats
			batcher.draw(Assetloader.ui[19],halfWidth - 150, 5, 300,50);
			//str="LVL:"+p1.level+"  xp:"+(Myworld.xp-p1.currentlevelxp)+"/"+p1.nextlevelxp;
			str=""+p1.level;
			Assetloader.font.setColor(Color.BLACK);
			Assetloader.font.setScale(0.6f,-0.6f);
			Assetloader.font.draw(batcher, str, halfWidth-(3*(str.length()+1)), 16);
			Assetloader.font.setScale(0.75f,-0.75f);
	
			//level data
			Assetloader.font.setColor(Color.WHITE);
			Assetloader.font.setScale(0.6f,-0.6f);
			Assetloader.font.draw(batcher,""+Myworld.potions, Myworld.s[4].x-Myworld.s[4].radius, Myworld.s[4].y-Myworld.s[4].radius);
			batcher.end();
			
		/*	shaper.begin(ShapeType.Line);
			shaper.setColor(Color.RED);
			shaper.rect(p1.rect.x, p1.rect.y, p1.rect.width, p1.rect.height);
			for(Enemy enemy:en)
			{
				shaper.rect(enemy.rect.x,enemy.rect.y,enemy.rect.width,enemy.rect.height);
				shaper.circle(enemy.getX(),enemy.getY(), 5);
			}*/
			/*shaper.circle(Myworld.c1.x, Myworld.c1.y, Myworld.c1.radius);
			shaper.circle(Myworld.c2.x, Myworld.c2.y, Myworld.c2.radius);
			shaper.circle(Myworld.c3.x, Myworld.c3.y, Myworld.c3.radius);
			shaper.circle(Myworld.c4.x, Myworld.c4.y, Myworld.c4.radius);
			shaper.circle(Myworld.c5.x, Myworld.c5.y, Myworld.c5.radius);
			shaper.circle(Myworld.c6.x, Myworld.c6.y, Myworld.c6.radius);
			shaper.line(gameWidth-75, 30, gameWidth-75, 50);*/
	
			shaper.begin(ShapeType.Line);
			shaper.setColor(Color.WHITE);
			/*if(Myworld.lvlbtn)
			{
				shaper.circle(Myworld.nxtlvl.x, Myworld.nxtlvl.y, Myworld.nxtlvl.radius);
				//shaper.line(440, 60, 460, 60);
				//shaper.line(450, 50, 460, 60);
				//shaper.line(460, 60, 450, 70);
			}*/
				
			//shaper.rect(p1.getX(),p1.getY(),p1.getwidth(),p1.getheight());
			//shaper.rect(p1.rect.x,p1.rect.y,p1.rect.width,p1.rect.height);
			//shaper.rect(p1.bulletrect.x,p1.bulletrect.y,p1.bulletrect.width,p1.bulletrect.height);
			//shaper.rect(platforms[0].getX(),platforms[0].getY(),platforms[0].getwidth(),platforms[0].getheight());
		
			Myworld.mpad.render(shaper);
			/*for(int i=0;i<Myworld.s.length;i++)
				shaper.circle(Myworld.s[i].x, Myworld.s[i].y, Myworld.s[i].radius);
			//shaper.circle(p1.getX(),p1.getY(), 5);
			//stat-bars
			shaper.rect(hp.bar.x,hp.bar.y,hp.bar.width,hp.bar.height);
			shaper.rect(mp.bar.x,mp.bar.y,mp.bar.width,mp.bar.height);
			shaper.rect(xp.bar.x,xp.bar.y,xp.bar.width,xp.bar.height);*/
			shaper.setAutoShapeType(true);
			/*for (Enemy enemy : en)
				shaper.rect(enemy.rect.x, enemy.rect.y, enemy.rect.width, enemy.rect.height);*/
			shaper.set(ShapeType.Filled);
			for (Enemy enemy : en)
				if(enemy.isAlive){
					shaper.rect(enemy.getX()-(enemy.isleft?enemy.width:0),enemy.getY(),enemy.hpb.bar.width,enemy.hpb.bar.height);
					if(enemy.isboss)
						shaper.rect(enemy.getX()-(enemy.isleft?enemy.width:0),enemy.getY()-7,enemy.mpb.bar.width,enemy.mpb.bar.height);
				}
			shaper.setColor(Color.RED);
			//shaper.rect(hp.innerbar.x,hp.innerbar.y,hp.innerbar.width,hp.innerbar.height);
			for (Enemy enemy : en) {
				//shaper.rect(enemy.rect.x, enemy.rect.y, enemy.rect.width, enemy.rect.height);
				if(enemy.isAlive)
					shaper.rect(enemy.getX()-(enemy.isleft?enemy.width:0),enemy.getY(),enemy.hpb.innerbar.width,enemy.hpb.innerbar.height);
				//shaper.rect(enemy.basic.rect.x, enemy.basic.rect.y, enemy.basic.rect.width, enemy.basic.rect.height);
			}
			shaper.setColor(Color.BLUE);
			//shaper.rect(mp.innerbar.x,mp.innerbar.y,mp.innerbar.width,mp.innerbar.height);
			for (Enemy enemy : en) {
				//shaper.rect(enemy.rect.x, enemy.rect.y, enemy.rect.width, enemy.rect.height);
				if(enemy.isAlive){
					if(enemy.isboss)
						shaper.rect(enemy.getX()-(enemy.isleft?enemy.width:0),enemy.getY()-7,enemy.mpb.innerbar.width,enemy.mpb.innerbar.height);
						
				}
				//shaper.rect(enemy.basic.rect.x, enemy.basic.rect.y, enemy.basic.rect.width, enemy.basic.rect.height);
			}
			//shaper.setColor(Color.ORANGE);
			//shaper.rect(xp.innerbar.x,xp.innerbar.y,xp.innerbar.width,xp.innerbar.height);
			
	
			/*shaper.setColor(Color.GREEN);
			for(int i=0;i<Myworld.mage.size();i++)
			{
				mager=Myworld.mage.get(i).rect;
				shaper.rect(mager.x,mager.y,mager.width,mager.height);
			}
			shaper.setColor(Color.YELLOW);
			for(int i=0;i<Myworld.enemage.size();i++)
			{
				mager=Myworld.enemage.get(i).rect;
				shaper.rect(mager.x,mager.y,mager.width,mager.height);
			}*/
			for(int i=0;i<Myworld.barrage.size();i++)
			{
				mager=Myworld.barrage.get(i).rect;
				if(Myworld.barrage.get(i).isActive()){
					shaper.setColor(Color.GREEN);
					shaper.rect(mager.x,mager.y,mager.width,mager.height);
				}
				else if(Myworld.barrage.get(i).isBlasted()){
					shaper.setColor(Color.RED);
					shaper.rect(mager.x,mager.y,mager.width,mager.height);
				}
			}
			/*if(p1.isAttack)
			shaper.rect(p1.basic.rect.x, p1.basic.rect.y, p1.basic.rect.width, p1.basic.rect.height);
			
			/*if(Myworld.getopponent().isAttack && Myworld.getopponent().skill.delay>0)
			shaper.rect(Myworld.getopponent().skill.rect.x, Myworld.getopponent().skill.rect.y, Myworld.getopponent().skill.rect.width, Myworld.getopponent().skill.rect.height);
			*/
			shaper.end();
			//ui end
		}
	}
	public void dispose() {
		// TODO Auto-generated method stub
		shaper.dispose();
		batcher.dispose();
		rayHandler.dispose();
		worldleft.dispose();
	}

}
