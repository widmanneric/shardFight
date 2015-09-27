package com.beta.actions;

import com.beta.equipment.Equipment;
import com.beta.game.Beta;

public class AcFlameRamp extends Actions {


	public AcFlameRamp(int player, Equipment equip) {
		
		super.setDontTarget(true);
		super.setEffect("Gain +3 Actions");
		super.setStats(false, "Flameramp", null, 'f', player, 500, 'a',null,9);
	}
	
	public void activate(){
		Beta.loader.setActionGain(Beta.loader.getActionGain()+3);
	}

}
