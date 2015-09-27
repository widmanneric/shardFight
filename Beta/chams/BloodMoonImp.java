package com.beta.chams;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.beta.abilities.Abilities;
import com.beta.abilities.Stab;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class BloodMoonImp extends Cham {


	transient private TextureRegion txtReg;
	transient private Texture txt;
	transient private Sprite sprite;
	private String effect;



	public BloodMoonImp(int player,Equipment equip){
		txt = TxtLoader.bloodMoonTex;
		//Assign Texture 
		if(player==1){
			txtReg = new TextureRegion(txt,0,0,60,60); 
			sprite= new Sprite(txtReg);
			sprite.setSize(60f, 60f);
		}
		else{
			txtReg = new TextureRegion(txt,120,0,60,60);
			sprite= new Sprite(txtReg);
			sprite.setSize(60f, 60f);
		}
		super.setSprite(sprite);
		txtReg= new TextureRegion(txt,0,0,60,60);
		super.setTxtReg(txtReg);
		effect= "When this champion is destroyed\n gain +100 dark AP.";
		Abilities [] ab = {new Stab(), null ,null};
		char [] typeGain = {'z','z'};
		int [] gain = {-5, -5};
		//super constrctor
		super.setStats(player,"Bloodmoon Imp",effect,"Occult",'n','d',-10,40,ab,30,typeGain,gain,50,'n',15,15,equip);
		super.setActive(false);
	}//end of method 

	public void activate(){
		if(Beta.playerTurn==super.getPlayer()){
			Beta.mech.setGain('d',100);
		}
		else{
			Beta.mech.oppSetGain('d',100);
		}

	}
	
}
