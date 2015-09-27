package com.beta.abilities;

import com.beta.game.Beta;

public class Growth extends Abilities {

	public Growth() {
		super.setStats("Growth", 0, 'n', 50, "Gain +100 Normal AP.",'n',false);
	}

	public void activate(){
		
			Beta.mech.setGain('n', 100);
	}
}
