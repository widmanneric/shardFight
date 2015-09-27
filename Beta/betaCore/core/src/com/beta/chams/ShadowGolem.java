package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.ShadowBall;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class ShadowGolem extends Cham {


	protected String effect;
	@SuppressWarnings("unused")
	private final int id =15;
	
	public ShadowGolem(int player,Equipment equip) {

		effect="";

		Abilities [] ab = {new ShadowBall(),null, null};
		char [] typeGain={'z','d'};
		int [] gain={-20,300};
		super.setStats(player,"Shadow Golem", effect, "Occult", 'd', 'z', -15, 15, ab, 830, typeGain, gain,700,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[9]);
	}

}
