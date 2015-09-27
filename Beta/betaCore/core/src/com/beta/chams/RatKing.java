package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Bite;
import com.beta.abilities.Multiply;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.Controls;
import com.beta.game.TxtLoader;


public class RatKing extends Cham {


	protected String effect;
	@SuppressWarnings("unused")
	private final int id =13;

	public RatKing(int player,Equipment equip) {

		
		Abilities [] ab = {new Multiply(), new Bite(), null};
		char [] typeGain={'z','d'};
		int [] gain={-10,100};
		super.setStats(player,"Rat King", "When Rat King is summoned,\ngain +50 Dark AP for each copy\nof Rat King on the board.", "Occult", 'd', 'n', -20, 25, ab, 400, typeGain, gain,700,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[7]);
	}

	public void activate(){
		int counter=0;
		for(int i=0;i<2;i+=1){
			for(int j=0;j<9;j+=1){
				if(Beta.mech.getBoard(super.getPlayer())[i][j]instanceof RatKing){
					counter+=1;
				}
			}
		}
		Beta.mech.setGain('d', 50*counter);
		Controls.mode=0;
		Beta.loader.drawDeductAp(false);
		Beta.loader.setActionGain(-1);
		Beta.loader.drawActionGain();
		Beta.loader.drawActionCast(false);
		Beta.mech.checkTrigger('a');
		Beta.mech.currAction=null;
	}

}
