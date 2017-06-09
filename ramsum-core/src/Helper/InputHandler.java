package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Logger;
import com.sun.org.apache.xpath.internal.operations.And;

import GameObjects.Player;
import GameObjects.Player.state;
import GameWorld.GameRenderer;
import GameWorld.GameWorld;

public class InputHandler implements InputProcessor {
	private Player p1;
	private GameWorld myWorld;
	int i=0;
	int inputX,inputY,movepointer,attackpointer;
	float height,width;

	public InputHandler(GameWorld world, GameRenderer renderer,float gameWidth,float gameHeight) {
		// TODO Auto-generated constructor stub
		this.myWorld = world;
		this.height=gameHeight;
		this.width=gameWidth;
		loadplayer();
	}
	
	public void loadplayer()
	{
		p1=myWorld.getPlayer();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(myWorld.isWin())
			{
				//myWorld.reset();
				myWorld.disconnect();
			}
		if(myWorld.isCUTSCENE()){
			return true;
		}
		if(keycode==Input.Keys.N)
		{
			myWorld.levelupdate=true;
			myWorld.disconnect();
		}
		if(keycode==Input.Keys.DPAD_UP)
		{
			p1.jump();
			//myWorld.sendData(3);
		}
		if(keycode==Input.Keys.DPAD_DOWN)
		{
			//duck
			p1.duck();
		}
		if(keycode==Input.Keys.DPAD_LEFT)
		{

			p1.walk(-p1.walkvel,true);
			//myWorld.sendData(1);
			
		}
		if(keycode==Input.Keys.DPAD_RIGHT)
		{

			p1.walk(p1.walkvel,false);
			//myWorld.sendData(2);
			
		}
		if(keycode==Input.Keys.Z)
		{
			/*if(myWorld.ismuLt())
				{
				p1.attack(myWorld.getopponent());
				myWorld.sendData(4);
				}
			else*/
				p1.attack(myWorld.en);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(myWorld.isCUTSCENE()){
			return true;
		}
		// TODO Auto-generated method stub
		if(keycode==Input.Keys.DPAD_RIGHT||keycode==Input.Keys.DPAD_LEFT)
		{
			p1.stopx();
		}
		if(keycode==Input.Keys.DPAD_DOWN)
			p1.stand();
		//myWorld.sendData(5);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		// TODO Auto-generated method stub
		inputX=(int) (screenX*width/Gdx.graphics.getWidth());
		inputY=(int) (screenY*height/Gdx.graphics.getHeight());
		if(myWorld.isCUTSCENE()){
			if(inputX > 10 && inputX < myWorld.gameWidth-20 && inputY > myWorld.gameHeight*0.88f){
				if(myWorld.endCuteScene()){
					myWorld.setNORM();
				}
				myWorld.currentDialog++;
			}
			return true;
		}
		if(myWorld.isWin())
			{
				myWorld.reset();
			}
		/*if(myWorld.c1.contains(inputX,inputY))
		{
			
			 p1.walk(-p1.walkvel,true);
			 myWorld.sendData(1);
			
		}
		if(myWorld.c2.contains(inputX,inputY))
		{
	
			p1.walk(p1.walkvel,false);
			myWorld.sendData(2);
			
		}
		if(myWorld.c3.contains(inputX,inputY))
		{
			p1.jump();
			myWorld.sendData(3);
		}*/

		if(myWorld.nxtlvl.contains(inputX,inputY) && myWorld.lvlbtn)
		{
				myWorld.levelupdate=true;
				myWorld.disconnect();
		}
		if(myWorld.c6.contains(inputX,inputY))
		{
			//myWorld.disconnect();
			if(myWorld.isPause()){
				myWorld.setNORM();
			}
			else{
			myWorld.setPause();
			}
		}
		if(myWorld.c5.contains(inputX,inputY))
		{
			/*if(myWorld.ismuLt())
				p1.attack(myWorld.getopponent());
			else*/
				p1.attack(myWorld.en);
			//myWorld.sendData(4);
		}
		if(myWorld.s[0].contains(inputX,inputY))
		{
			/*if(myWorld.ismuLt())
				p1.slideatt(myWorld.getopponent());
			else*/
				p1.slideatt(myWorld.en);
			//myWorld.sendData(7);
		}
		if(myWorld.s[1].contains(inputX,inputY))
		{
			/*if(myWorld.ismuLt())
				p1.AirPunch(myWorld.getopponent());
			else*/
				p1.AirPunch(myWorld.en);
			//myWorld.sendData(7);
		}
		if(myWorld.s[2].contains(inputX,inputY))
		{
			/*if(myWorld.ismuLt())
				p1.hurricane(myWorld.getopponent());
			else*/
				p1.hurricane(myWorld.en);
			//myWorld.sendData(7);
		}
		if(myWorld.s[3].contains(inputX,inputY))
		{
			if(p1.mp-100>0)
				myWorld.mage.add(p1.mage());
		}
		if(myWorld.s[4].contains(inputX,inputY))
		{
			myWorld.healplayer();
			//myWorld.sendData(15);
		}
		if(inputX<200)
		{
		this.movepointer=pointer;
		myWorld.mpad.set(inputX, inputY);
		}
		else
			this.attackpointer=pointer;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		if(myWorld.isCUTSCENE()){
		}
		// TODO Auto-generated method stub
		inputX=(int) (screenX*width/Gdx.graphics.getWidth());
		inputY=(int) (screenY*height/Gdx.graphics.getHeight());
	/*	if(myWorld.c1.contains(inputX,inputY) || myWorld.c2.contains(inputX,inputY))
		{
			p1.stopx();
		}*/
		//myWorld.sendData(5);
		if(pointer==movepointer)
		{
			myWorld.mpad.hide();
			p1.stopx();
			movepointer=-50;
		}
		else
		{
			attackpointer=-20;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if(myWorld.isCUTSCENE()){
			return true;
		}
		// TODO Auto-generated method stub
		try{
		int X=(int) (screenX*width/Gdx.graphics.getWidth());
		int Y=(int) (screenY*height/Gdx.graphics.getHeight());
		if(pointer==movepointer){
		 myWorld.mpad.drag(X, Y);
		 float angle = (float) Math.toDegrees(Math.atan2(X - myWorld.mpad.base.x, Y - myWorld.mpad.base.y));
		 angle+=45;//rotate 45 degree counterclockwise to get X graph with 0 at lower left wrt screen
		 if(angle < 0){
		        angle += 360;
		    }
		 	if(angle>240)//left
				 {
					 if(myWorld.mpad.dir!=myWorld.mpad.dir.left)
					 { 
						 System.out.println("left"+X);
						 p1.walk(-p1.walkvel,true);
						// myWorld.sendData(1);
						 myWorld.mpad.dir=myWorld.mpad.dir.left;
					 }
				 }
				 else if(angle>180)//up
				 {
					 if(myWorld.mpad.dir!=myWorld.mpad.dir.up)
					 { 
						 System.out.println("up");
						 p1.jump();
						// myWorld.sendData(3);
						 myWorld.mpad.dir=myWorld.mpad.dir.up;
					 }
						
				 }
				 else if(angle>90) //right
				 {
					 if(myWorld.mpad.dir!=myWorld.mpad.dir.right)
					 { 
						 System.out.println("right");
						 p1.walk(p1.walkvel,false);
						// myWorld.sendData(2);
						 myWorld.mpad.dir=myWorld.mpad.dir.right;
					 }
				 }
				 else//down
				 {
					 if(myWorld.mpad.dir!=myWorld.mpad.dir.down && !myWorld.mpad.collides())
					 { 
						 System.out.println("down");
						 p1.duck();
						 myWorld.mpad.dir=myWorld.mpad.dir.down;
					 }
				 }
		}
		else{
			//attack pad code here
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		if(myWorld.isCUTSCENE()){
			return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		if(myWorld.isCUTSCENE()){
			return true;
		}
		return false;
	}

}
