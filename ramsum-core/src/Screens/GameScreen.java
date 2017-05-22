package Screens;

import GameWorld.GameRenderer;
import GameWorld.GameWorld;
import Helper.InputHandler;

//import appwarp.WarpController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.ramsumgame.RamSumGame;

public class GameScreen implements Screen{

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	private InputHandler iph;
	private RamSumGame game;
	private String username;

	public GameScreen(RamSumGame ramSumGame,Boolean multiplayer, String username, int level,Boolean newgame) {
		Gdx.graphics.getWidth();
        Gdx.graphics.getHeight();
        float gameWidth = 768;
        float gameHeight = 480;
        int midPointY = (int) (gameHeight / 2);
        this.game=ramSumGame;
        this.username=username;
		world = new GameWorld(ramSumGame,(int)gameWidth,(int)gameHeight,multiplayer,username,level,newgame); // initialize world
		renderer = new GameRenderer(world,(int)gameHeight,(int)gameWidth,midPointY); // initialize renderer
		iph=new InputHandler(world,renderer,gameWidth,gameHeight);
		Gdx.input.setInputProcessor(iph);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		try{
		runTime += delta;
		world.update(delta);
        renderer.render(runTime,delta);
        if(Double.isNaN(world.getPlayer().getX()))
        {
        	System.out.println("player not resumed retrying...");
    		world.resume();
    		iph.loadplayer();
    		renderer.loadplayer();
        }
        if(!game.isstart)
        {
        	dispose();
        	if(world.levelupdate)
        	{
        		System.out.println("updating");
        		game.setScreen(new LoadScreen(world.nxtlevel,game,username));
        	}
        	else
        		game.setScreen(new InputScreen(game));
        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		world.pause();
		//Assetloader.dispose();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		//Assetloader.load();
		world.resume();
		iph.loadplayer();
		renderer.loadplayer();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//world.save();
		world.dispose();
		renderer.dispose();
	}

}
