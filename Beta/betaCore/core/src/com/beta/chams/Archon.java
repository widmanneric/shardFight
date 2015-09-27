package com.beta.chams;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.beta.abilities.Abilities;
import com.beta.abilities.Blizzard;
import com.beta.abilities.FinalFire;
import com.beta.abilities.Sleep;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class Archon extends Cham{

	
	transient private Texture txt;
	transient private TextureRegion txtReg;
	transient private Sprite sprite;
	protected String effect;



	public Archon (int player,Equipment equip){
		txt = TxtLoader.archonTex;
		//Assign Texture 
		if(player==1){		
			txtReg = new TextureRegion(txt,0,0,125,125);
			sprite= new Sprite(txtReg);
			sprite.setSize(125f, 125f);

		}
		else{
			txtReg = new TextureRegion(txt,250,0,125,125);
			sprite= new Sprite(txtReg);
			sprite.setSize(125f, 125f);
		}
		super.setSprite(sprite);
		txtReg = new TextureRegion(txt,0,0,125,125);
		super.setTxtReg(txtReg);
		
		effect = "Whenever you activate a fire or ice \ntype ablitity or action, gain\n+50 Fire AP and +50 Ice AP.";

		Abilities [] ab = {new FinalFire(),new Blizzard(), new Sleep()};


		char [] typeGain = {'f','b'};
		int [] gain = {100, 100};
		//super constrctor
		super.setStats(player,"Archon",effect,"Fire",'f','a',-35,-5,ab,440,typeGain,gain,500,'f',-20,-10,equip);
		super.setActive(false);
		
		

	}//end of method

	public void activate(){

		if(Beta.mech.currAb!=null){
			if(Beta.mech.currAb.getcostType()=='b'||Beta.mech.currAb.getcostType()=='f'){
				Beta.mech.setGain('b', 50);
				Beta.mech.setGain('f', 50);
				return;
			}
		}

		if(Beta.mech.currAction!=null){
			if(Beta.mech.currAction.getCostType()=='b'||Beta.mech.currAction.getCostType()=='f'){
				Beta.mech.setGain('b', 50);
				Beta.mech.setGain('f', 50);
				return;			
			}
		}

	}

}//end of class
