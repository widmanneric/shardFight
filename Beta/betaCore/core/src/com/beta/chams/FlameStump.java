package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Growth;
import com.beta.equipment.Equipment;
import com.beta.game.TxtLoader;

public class FlameStump extends Cham {

	@SuppressWarnings("unused")
	private final int id =9;

	public FlameStump(int player,Equipment equip) {
		
		
		Abilities [] ab = {new Growth(),null,null};
		char [] type = {'z','f'};
		int [] gain ={-5,250};
		super.setStats(player, "Flame Stump", "This champion cannot be\ndestroyed or targeted.", "Fire: ", 'f', 'i', -20,25, 
				ab, 100, type, gain, 200, 'f', 0, 0,equip);
		super.createGraphics(TxtLoader.chams[12]);
	}

}
