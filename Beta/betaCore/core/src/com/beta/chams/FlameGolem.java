package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.abilities.FlameBurst;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class FlameGolem extends Cham {



	private String effect;
	@SuppressWarnings("unused")
	private final int id =8;
	public FlameGolem(int player,Equipment equip) {
	

		effect="";

		Abilities [] ab = {new FlameBurst(),null, null};
		char [] typeGain={'z','f'};
		int [] gain={-20,300};
		super.setStats(player, "Flame Golem", effect, "Fire", 'f', 'z', -20, 25, ab, 830, typeGain, gain,700,'f',0,0,equip);
		super.createGraphics(TxtLoader.chams[4]);
	}

}
