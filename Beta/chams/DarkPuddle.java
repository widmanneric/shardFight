package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class DarkPuddle extends Cham {


	private String effect;
	@SuppressWarnings("unused")
	private final int id =4;
	
	public DarkPuddle(int player,Equipment equip) {

		
		effect="When this champion is destroyed,\ngain +100 Ice Ap.";
		
		Abilities [] ab = {null,null,null};
		char [] typeGain={'b','d'};
		int [] gain={100,100};
		super.setStats(player,"Dark Puddle", effect, "Occult", 'd', 'd', -20, 20, ab, 150, typeGain, gain,200,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[0]);
	}
	

	public void activate(){
		if(Beta.playerTurn==super.getPlayer()){
			Beta.mech.setGain('b',100);
		}
		else{
			Beta.mech.oppSetGain('b',100);
		}

	}

}
