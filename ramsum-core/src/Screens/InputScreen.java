package Screens;



//import appwarp.WarpController;

import Helper.Assetloader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.ramsumgame.RamSumGame;
import com.sun.prism.GraphicsPipeline.ShaderType;

public class InputScreen implements Screen{

	 private SpriteBatch batch ;
	 private ShapeRenderer shape;
     private BitmapFont font ;
     private String buttons="Connect",play="New Game",msg1,msg2,str;
     private RamSumGame game;
     private Rectangle cnt,ply,x30,x100,x300,matrix[];
     private float gameWidth,gameHeight,Height2,speed[];
     private int n=5, ym;
     private String[] chn={"A","C","B","E","Ñ","Ù","Ħ","Ø"};
     private Color green=Color.GREEN;
 	 public OrthographicCamera cam;
    
     private boolean display;
     
     private inputhandler inp;
	public int inputX;
	public int inputY;
     public InputScreen(RamSumGame game)
     {
         gameWidth = 768;
         gameHeight = 480;
         Height2 = gameHeight * 2;
    	 this.game=game;
         display = false;
         buttons="Connect";
         cnt = new Rectangle((gameWidth / 2) - 40,127,120,40);
         ply = new Rectangle((gameWidth / 2) - 60,gameHeight*0.7f - 13,120,40);
         x30 = new Rectangle(gameWidth-180,10,50,30);
         x100 = new Rectangle(gameWidth-130,10,50,30);
         x300 = new Rectangle(gameWidth-80,10,50,30);
         n=30;
         matrix = new Rectangle[n];
         for(int i=0;i<n;i++)
        	 matrix[i] = new Rectangle(Assetloader.getrandom((int) gameWidth), 0 - Assetloader.getrandom(300), 10, 10);
         speed = new float[n];
         for(int i=0;i<n;i++)
        	 speed[i]=Assetloader.getrandom(150)+50;
         cam=new OrthographicCamera();
		 cam.setToOrtho(true,gameWidth,gameHeight);
         shape=new ShapeRenderer();
         shape.setProjectionMatrix(cam.combined);
         batch = new SpriteBatch();
         batch.setProjectionMatrix(cam.combined);
         font=new BitmapFont(Gdx.files.internal("font/font.fnt"));
         font.setScale(0.6f, -0.6f);
         inp = new inputhandler();
         Gdx.input.setInputProcessor(inp);
    	 display =true;
    	 msg1="In Year 21XX a Mechanic is revived as a Bionic.";
    	 msg2="You must Find his past and shape the future.";
   }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		buttons=null;
		msg1=null;
		msg2=null;
		play=null;
		cnt=null;
		ply=null;
		cam=null;
		shape.dispose();
		batch.dispose();
		font.dispose();
		inp=null;
		System.out.println("disposed");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         if(display)
         {
         /*shape.begin(ShapeType.Filled);
         shape.setAutoShapeType(true);
         shape.setColor(Color.GREEN);*/
         for(int i=0 ; i<n ; i++){
        	 //shape.rect(matrix[i].x,matrix[i].y,matrix[i].width,matrix[i].height);
        	 matrix[i].y += speed[i]* delta;
        	 if(matrix[i].y > Height2){
        		 matrix[i].y = 0;
        		 matrix[i].x = Assetloader.getrandom((int) gameWidth);
        	 }
         }
        // shape.end();
         batch.begin();
         font.setColor(green);
         font.setScale(0.8f,-0.8f);
         for(int i=0 ; i<n ; i++){
        	 //ym =(int) Assetloader.getrandom(20);
        	 for (int y=0; y<25 ;y++){
        		 font.setColor(green.r,green.g,green.b,1-(0.025f * y));
        		 str = ""+ chn[(int) Assetloader.getrandom(chn.length)];
        		 font.draw(batch,str, matrix[i].x, matrix[i].y-20*(y+1));
        	 }
         }
         font.setColor(Color.WHITE);
         font.setScale(0.6f, -0.6f);
         font.draw(batch, msg1, (gameWidth / 2) - (msg1.length() * 4.2f), gameHeight*0.4f);
         font.draw(batch, msg2, (gameWidth / 2) - (msg2.length() * 4.2f), gameHeight*0.4f + 20);
         //font.draw(batch, buttons, cnt.x, 140);
         font.setColor(green);
         font.draw(batch, "X30", x30.x+10, x30.y+7);
         font.draw(batch, "X100", x100.x+5, x100.y+7);
         font.draw(batch, "X300", x300.x+5, x300.y+7);
         font.draw(batch, play, ply.x + 60 - (play.length() * 4.2f), gameHeight*0.7f);
         batch.end();
         shape.begin(ShapeType.Line);
        //if(buttons.equals("Connect"))
         //shape.rect(cnt.x,cnt.y,cnt.width,cnt.height);
         shape.setColor(Color.GREEN);
         shape.rect(ply.x,ply.y,ply.width,ply.height);
         shape.rect(x30.x,x30.y,x30.width,x30.height);
         shape.rect(x100.x,x100.y,x100.width,x100.height);
         shape.rect(x300.x,x300.y,x300.width,x300.height);
         shape.end();
         
         }
         if(game.iscanceled)
         {
        	 game.iscanceled=false;
        	 buttons="Connect";
        	 play="New Game";
         }
         
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	class inputhandler implements InputProcessor{

		@Override
		public boolean keyDown(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub

			inputX=(int) ((int)screenX*gameWidth/Gdx.graphics.getWidth());
			inputY=(int) ((int)screenY*gameHeight/Gdx.graphics.getHeight());
			System.out.println(screenX+":"+screenY);
			if(cnt.contains(inputX,inputY) && buttons.equals("Connect"))
			{
				try {
					
					if(game.asr.getSignedInGPGS())
					{
						game.asr.stargame();
						buttons="Connecting...";
					}
					else
						game.asr.loginGPGS();
				}
				catch(Exception e){ 
					e.printStackTrace();
				}
			}
			if(ply.contains(inputX,inputY) && play.equals("New Game"))
			{
				play="Loading...";
				game.isstart=true;
				dispose();
				game.setScreen(new GameScreen(game,false,1,true,false));
			}
			if(x30.contains(inputX,inputY))
			{
				n=30;
		        matrix = new Rectangle[n];
		        for(int i=0;i<n;i++)
		        	matrix[i] = new Rectangle(Assetloader.getrandom((int) gameWidth), 0 - Assetloader.getrandom(300), 10, 10);
		        speed = new float[n];
		        for(int i=0;i<n;i++)
		        	 speed[i]=Assetloader.getrandom(150)+50;
			}
			if(x100.contains(inputX,inputY))
			{
				n=100;
		        matrix = new Rectangle[n];
		        for(int i=0;i<n;i++)
		        	matrix[i] = new Rectangle(Assetloader.getrandom((int) gameWidth), 0 - Assetloader.getrandom(300), 10, 10);
		        speed = new float[n];
		        for(int i=0;i<n;i++)
		        	 speed[i]=Assetloader.getrandom(150)+50;
			}
			if(x300.contains(inputX,inputY))
			{
				n=300;
		        matrix = new Rectangle[n];
		        for(int i=0;i<n;i++)
		        	matrix[i] = new Rectangle(Assetloader.getrandom((int) gameWidth), 0 - Assetloader.getrandom(300), 10, 10);
		        speed = new float[n];
		        for(int i=0;i<n;i++)
		        	 speed[i]=Assetloader.getrandom(150)+50;
			}
			return false;
		}

		@Override
		public boolean touchDragged(int arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}


}
