package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class DeathBringer extends Cham {


	private String effect;
	@SuppressWarnings("unused")
	private final int id =7;
	
	public DeathBringer(int player,Equipment equip) {

		effect="This champion can only\nbe destroyed by battle.";
		Abilities [] ab = {null,null,null};
		char [] typeGain={'z','d'};
		int [] gain={-50,1000};
		super.setStats(player, "Death Bringer", effect, "Occult", 'd', 'c', -15, 25, ab, 1250, typeGain, gain,3000,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[3]);
	}

}
