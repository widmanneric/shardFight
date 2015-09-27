package com.beta.abilities;

import com.beta.game.DamageCalculator;

public class Blizzard extends Abilities {

	public Blizzard(){
		super.setStats("Blizzard",400, 'b',300, "",'e',true);
	}
	public void activate(int row, int col, int player){
		//deal damage 
		DamageCalculator.dealDamage(row, col, player, this);
	
	}
}
