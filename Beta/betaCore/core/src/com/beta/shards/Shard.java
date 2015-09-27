package com.beta.shards;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shard {

	//variables
	private String name;
	private int player;
	private Animation ani;
	private int offset;
	private int sp;
	private int actSp;
	private String type;
	private char [] gainType;
	private int [] gain;
	
	public void setStats(String name,int player,Animation ani,String type,int sp,char [] gainType,int [] gain,int offset) {
		
		this.name=name;
		this.player=player;
		this.ani=ani;
		this.offset=offset;
		this.sp=sp;
		actSp=sp;
		this.type=type;
		this.gainType=gainType;
		this.gain=gain;
		
	}
	public String getName(){
		return name;
	}
	public Animation getAnimation(){
		return ani;
	}
	public int getPlayer(){
		return player;
	}
	
	public int getOffset(){
		return offset;
	}
	public int getSp(){
		return sp;
	}
	public int getActSp() {
		return actSp;
	}
	public void setActSp(int actSp) {
		this.actSp = actSp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public char[] getGainType() {
		return gainType;
	}
	public void setGainType(char[] gainType) {
		this.gainType = gainType;
	}
	public int[] getGain() {
		return gain;
	}
	public void setGain(int[] gain) {
		this.gain = gain;
	}
	public Animation createAni(Texture tex){
		//loadTexture 
				Texture shard = tex;
				TextureRegion[][] tmpFrames = TextureRegion.split(shard, 32, 32);
				int index =0;
				TextureRegion [] shardF = new TextureRegion[8];
				for(int i =0; i<tmpFrames.length ; i+=1){
					for(int j=0;j<tmpFrames[0].length;j+=1){
						shardF[index]=tmpFrames[i][j];
						index+=1;
					}
				}//end of loop
				return new Animation(1f/4f,shardF);
	}
	
}//end of class
