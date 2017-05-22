package Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.ramsumgame.RamSumGame;

public class LoadScreen implements Screen{

	private RamSumGame game;
	String username;
	private int level;
	
	public LoadScreen(int level,RamSumGame game,String username)
	{
		this.level=level;
		this.game=game;
		this.username=username;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("disposed");
		this.game=null;
		this.username=null;
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
		game.isstart=true;
		game.setScreen(new GameScreen(game,false,username,level,false));
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

}
