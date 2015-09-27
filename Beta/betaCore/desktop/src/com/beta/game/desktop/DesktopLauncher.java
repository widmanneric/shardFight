package com.beta.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.beta.game.Beta;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Beta";
		config.height= 810;
		config.width=1200;
		config.resizable=false;
		config.foregroundFPS=70;
		config.backgroundFPS=30;
		config.vSyncEnabled=true;
		config.addIcon("icon.png", FileType.Internal);
		new LwjglApplication(new Beta(), config);
	}
}
