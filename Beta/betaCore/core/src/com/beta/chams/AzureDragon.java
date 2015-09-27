package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Blizzard;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class AzureDragon extends Cham{

	


	public AzureDragon(int player, Equipment equip) {
		Abilities [] a = {new Blizzard(),null,null};
		int [] g = {-5,200};
		char [] c = {'z','b'};
		super.setStats(player, "Azure Dragon", "When this champion is destoryed\ngain +100 Ice AP.", "Ice", 'b', 'd', -20, 25, a, 365, c, g, 300, 'b', 0, 0, equip);
		super.createGraphics(TxtLoader.chams[14]);
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
