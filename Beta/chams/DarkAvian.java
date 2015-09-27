package com.beta.chams;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.beta.abilities.Abilities;
import com.beta.abilities.DarksEdge;
import com.beta.abilities.Fly;
import com.beta.abilities.Tribute;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class DarkAvian extends Cham {

	

	private TextureRegion txtReg;
	private Texture txt;
	private Sprite sprite;
	private String effect;
	@SuppressWarnings("unused")
	private final int id =3;
	
	public DarkAvian(int player,Equipment equip){
		//Assign Texture 
		txt = TxtLoader.darkAvianTex;
		if(player==1){
			txtReg = new TextureRegion(txt,0,0,70,118);
			sprite= new Sprite(txtReg);
			sprite.setSize(70f, 118f);
		}
		else{
			txtReg = new TextureRegion(txt,140,0,70,118);
			sprite= new Sprite(txtReg);
			sprite.setSize(70f, 118f);
			
		}
		super.setSprite(sprite);
		txtReg = new TextureRegion(txt,0,0,70,118);
		super.setTxtReg(txtReg);
		//write effect 
		effect = "Whenever you sacrafice a\nchampion, gain +1 actions.";
		
		Abilities [] ab = {new Fly(), new Tribute() ,new DarksEdge()};

		char [] typeGain = {'z','d'};
		int [] gain = {-5, 200};

		super.setStats(player,"Dark Avian",effect,"Occult",'d','s',-10,5,ab,410,typeGain,gain,500,'d',10,-20,equip);
		super.setActive(false);
		
	}
	
	public void activate(){
		Beta.loader.setActionGain(Beta.loader.getActionGain()+1);
	}
}//end of class 
