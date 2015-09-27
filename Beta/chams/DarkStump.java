package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.abilities.Growth;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class DarkStump extends Cham{


	private String effect;
	@SuppressWarnings("unused")
	private final int id =6;
	
	public DarkStump(int player,Equipment equip) {

		effect="This champion cannot be\ndestroyed or targeted.";
		Abilities [] ab = {new Growth(),null,null};
		char [] typeGain={'z','d'};
		int [] gain={-50,250};
		super.setStats(player, "Dark Stump", effect, "Occult", 'd', 'i', -20, 25, ab, 100, typeGain, gain,200,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[2]);
	}

}
