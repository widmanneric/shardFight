package com.beta.ap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ApCounters {
	TextureRegion [] counters;
	Texture counterTex;
	public ApCounters(){
		counterTex = new Texture("counters/counters.png");
		TextureRegion [] []tmpFrame= TextureRegion.split(counterTex, 20, 20);
		int tmpCounter =0;
		counters = new TextureRegion [8];
		for(int i = 0; i< tmpFrame.length;i+=1){
			for(int j =0; j<tmpFrame[0].length;j+=1){
				counters[tmpCounter]= tmpFrame[i][j];
				tmpCounter+=1;
			}
		}
	}//end of constructor 

	public TextureRegion getCounterTex(char type){
		
		switch(type){
		case 'd': return counters[0];
		case 'n': return counters [1];
		case 'f': return counters [2];
		case 'b':return counters [3];
		case 'w':return counters [4];
		case 'l':return counters[5];
		case 'g':return counters[6];
		case 'p':return counters [7];
		}
		return null;
	}
}//end of class 
