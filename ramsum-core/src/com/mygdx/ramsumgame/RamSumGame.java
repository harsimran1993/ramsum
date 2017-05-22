package com.mygdx.ramsumgame;

import org.json.JSONException;
import org.json.JSONObject;

import GameWorld.GameWorld;
import Helper.Assetloader;
import Screens.GameScreen;
import Screens.InputScreen;
import Screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class RamSumGame extends Game{
	public boolean isstart=false;
	public boolean iscanceled=false;
	public String update;
	public boolean full=true;
	public ActionResolver asr;
	public RamSumGame(ActionResolver asr) {
		super();
		this.asr=asr;
		Assetloader.init();
	}

	@Override
	public void create () {
		Assetloader.load();
		setScreen(new SplashScreen(this));
		//isstart=true;
		//setScreen(new GameScreen(this));
	}
	
	public void dispose(){
		super.dispose();
		Assetloader.dispose();
	}

	

	public void disconnect()
	{
		//isstart=false;
		asr.leave();
	}
}
