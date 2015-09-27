package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Growth;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class NormalStump extends Cham {



	@SuppressWarnings("unused")
	private final int id =12;
	
	protected String effect;
	public NormalStump(int player,Equipment equip) {


		effect="This champion cannot be\ndestroyed or targeted.";


		Abilities [] ab = {new Growth(),null,null};
		char [] typeGain={'z','n'};
		int [] gain={-50,250};
		
		super.setStats(player, "Normal Stump", effect, "Normal", 'n', 'i', -20, 25, ab, 100, typeGain, gain,200,'n',0,0,equip);
		super.createGraphics(TxtLoader.chams[6]);
		
		
	}

}
