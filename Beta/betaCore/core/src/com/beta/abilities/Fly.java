package com.beta.abilities;

import com.beta.game.DamageCalculator;

public class Fly extends Abilities{

	public Fly(){
		super.setStats("Fly", 150, 'n', 300, "This champion gains\ninvulnerability for one turn.",'e',true);
		
	}
	public void activate(int row, int col, int player){
		//deal damage 
		DamageCalculator.dealDamage(row, col, player, this);
	}
}
