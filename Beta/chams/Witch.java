package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Lightning;
import com.beta.abilities.ShadowBeam;
import com.beta.abilities.Spark;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.TxtLoader;

public class Witch extends Cham {


	protected String effect;
	@SuppressWarnings("unused")
	private final int id =17;

	public Witch(int player,Equipment equip) {


		Abilities [] ab = {new Lightning(),new Spark(), new ShadowBeam()};
		char [] typeGain={'l','d'};
		int [] gain={100,200};
		super.setStats(player, "Witch", "Whenever you sacrafice a\nchampion,gain +50 dark AP.", "Dark", 'd', 's', -20, 20, ab, 530, typeGain, gain,500,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[11]);
	}

	public void activate(){
		Beta.mech.setGain('d', 50);
	}
}
