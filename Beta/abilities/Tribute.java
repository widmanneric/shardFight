package com.beta.abilities;

import com.beta.game.Beta;

public class Tribute extends Abilities  {

	public Tribute(){
		super.setStats("Tribute", 0, 'd', 100, "Sacrafice a champion, gain\n+200 dark AP.",'a',false);
		
	}
	public void activate(int row, int col, int player){
		//deal damage 
		Beta.mech.setGain('d', 200);
		Beta.mech.checkTrigger('s');
		Beta.mech.destroy(player,row,col);
	}
}
