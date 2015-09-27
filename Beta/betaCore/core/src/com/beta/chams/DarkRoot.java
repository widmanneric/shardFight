package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.abilities.Growth;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class DarkRoot extends Cham {



	private String effect;
	@SuppressWarnings("unused")
	private final int id =5;
	
	public DarkRoot(int player,Equipment equip) {
		

		effect="When this champion is destoryed\ngain +1200 Dark AP.";

		Abilities [] ab = {new Growth(),null,null};
		char [] typeGain={'z','d'};
		int [] gain={-50,1000};
		super.setStats(player,"Dark Root", effect, "Occult", 'd', 'd', -15, 25, ab, 150, typeGain, gain,1000,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[1]);
	}

	public void activate(){

		if(Beta.playerTurn==super.getPlayer()){
			Beta.mech.setGain('d',1200);
		}
		else{
			Beta.mech.oppSetGain('d',1200);
		}

	}
}
