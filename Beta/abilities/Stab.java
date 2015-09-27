package com.beta.abilities;


import com.beta.game.DamageCalculator;

public class Stab extends Abilities {

	public Stab() {
		super.setStats("Stab", 30, 'n', 10, "",'e',true);
	
	}
	public void activate(int row, int col, int player){
		//deal damage 
		DamageCalculator.dealDamage(row, col, player, this);
		
	}
}
