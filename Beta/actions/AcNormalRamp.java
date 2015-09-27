package com.beta.actions;

import com.beta.equipment.Equipment;
import com.beta.game.Beta;

public class AcNormalRamp extends Actions {


	public AcNormalRamp(int player, Equipment equip) {
		super.setDontTarget(true);
		super.setEffect("Gain +2 Actions");
		super.setStats(false, "Normramp", null, 'n', player, 500, 'a',null,14);
	}
	
	public void activate(){
		Beta.loader.setActionGain(Beta.loader.getActionGain()+3);
	}

}
