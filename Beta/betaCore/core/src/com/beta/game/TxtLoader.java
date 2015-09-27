package com.beta.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TxtLoader {

	//archon 
	public static Texture archonTex;
	public static Texture bloodMoonTex;
	public static Texture darkAvianTex;
	public static Texture darkPuddleTex;
	

	//__________________
	//shards
	public static Texture basicBlackShardTex;
	public static Texture basicBlueShardTex;
	public static Texture basicRedShardTex;
	
	//________________________________
	//spriteMap
	public static TextureRegion [] chams;
	private Texture chamTex;
	
	public TxtLoader() {
		//archon
		archonTex = new Texture("cham/archon/archon.png");
		//bloodmoonImp
		bloodMoonTex= new Texture("cham/bloodMoonImp/bloodMoonImp.png");
		//darkAvian
		darkAvianTex= new Texture("cham/darkAvian/darkAvian.png");
		//darkPuddle
		darkPuddleTex = new Texture("cham/darkPuddle/darkPuddle.png");
		//_________________
		//shards 
		basicBlackShardTex = new Texture("shards/blackShard.png");
		//blue
		basicBlueShardTex =  new Texture("shards/blueShard.png");
		//red
		basicRedShardTex= new Texture("shards/redShard.png");
		
		//___________________
		//spritemap
		this.chamTex= new Texture ("chams.png");
		TxtLoader.chams=new TextureRegion[15];
		for(int i=0; i<15;i+=1){
			chams[i]=createRegion(i);
		}
	}
	
	/**
	 * Creates the the textureRegion of Champion in given slot i. 
	 * @param i current iteration 
	 * @return texture region containing graphics for given champion in Champion pixelmap
	 */
	public TextureRegion createRegion(int i){
		return new TextureRegion(this.chamTex,i*180,0,90,90);
		
	}
}//end of class
