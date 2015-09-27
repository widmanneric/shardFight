package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Growth;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class IceStump extends Cham {


	@SuppressWarnings("unused")
	private final int id =11;
	
	public IceStump(int player , Equipment equip) {
		Abilities[] tempAb = {new Growth(),null,null};
		char [] charA ={'z','b'};
		int [] intA ={-5,250};
		super.setStats(player, "Ice Stump", "This champion cannot be\ndestroyed or targeted.", "Ice", 'b', 'i', -20, 25, tempAb, 100, charA, intA, 200, 'b', 0, 0, equip);
		super.createGraphics(TxtLoader.chams[13]);
	}

}
