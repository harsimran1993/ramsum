package Screens;



//import appwarp.WarpController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.ramsumgame.RamSumGame;

public class InputScreen implements Screen{

	 private SpriteBatch batch ;
	 private ShapeRenderer shape;
     private BitmapFont font ;
     private String username="Ram Sum";
     private String message,buttons="Connect",play="Single Player";
     private RamSumGame game;
     private Rectangle cnt,ply;
 	 public OrthographicCamera cam;
    
     private boolean display;
     
     private inputhandler inp;
	public int inputX;
	public int inputY;
     public InputScreen(RamSumGame game)
     {
    	 this.game=game;
         display = false;
         buttons="Connect";
         cnt=new Rectangle(180,127,120,40);
         ply=new Rectangle(180,187,120,40);
         cam=new OrthographicCamera();
		 cam.setToOrtho(true,480,320);
         shape=new ShapeRenderer();
         shape.setProjectionMatrix(cam.combined);
         batch = new SpriteBatch();
         batch.setProjectionMatrix(cam.combined);
         font=new BitmapFont(true);
         inp = new inputhandler();
         Gdx.input.setInputProcessor(inp);
         try{
         username=game.asr.username();
         }
         catch(Exception e){username="Ram_Sum";}
         if(username.equals("none"))
        		 username="Ram Sum";
    	 display =true;
    	 message = "Hello, <"+username+"> Welcome to the world of fighter ";
   }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		message=null;
		username=null;
		buttons=null;
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
	public void render(float arg0) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         if(display)
         {
         batch.begin();
         font.draw(batch, message, 35, 80);
         font.draw(batch, buttons, 210, 140);
         font.draw(batch, play, 200, 200);
         batch.end();
         shape.begin(ShapeType.Line);
         if(buttons.equals("Connect"))
         shape.rect(cnt.x,cnt.y,cnt.width,cnt.height);
         shape.rect(ply.x,ply.y,ply.width,ply.height);
         shape.end();
         }
         if(game.iscanceled)
         {
        	 game.iscanceled=false;
        	 buttons="Connect";
        	 play="Single Player";
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

			inputX=(int)screenX*480/Gdx.graphics.getWidth();
			inputY=(int)screenY*320/Gdx.graphics.getHeight();
			System.out.println(screenX);
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
			if(ply.contains(inputX,inputY) && play.equals("Single Player"))
			{
				play="Loading...";
				game.isstart=true;
				dispose();
				game.setScreen(new GameScreen(game,false,username,1,true));
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
