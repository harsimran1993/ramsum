package com.mygdx.ramsumgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.ramsumgame.RamSumGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title="ram sum";
		config.width=768;
		config.height=480;
		new LwjglApplication(new RamSumGame(new ActionResolverDesktop()), config);
	}
}
