package com.beta.abilities;

import com.beta.game.Beta;

public class Disable extends Abilities{

	public Disable() {
		// TODO Auto-generated constructor stub
		super.setStats("Disable",0, 'd',300, "Negate target champions effect.",'b',false);
		
	}

	public void activate(int row, int col, int player){
		Beta.mech.getBoard(player)[row][col].setTriggerType('z');

	}
}
