package com.beta.actions;

import com.beta.equipment.Equipment;
import com.beta.game.Beta;

public class AcIceRamp extends Actions {

	
	public AcIceRamp(int player, Equipment equip) {
		super.setDontTarget(true);
		super.setEffect("Gain +2 Actions");
		super.setStats(false, "Iceramp", null, 'b', player, 500, 'a',null,12);
	}
	
	public void activate(){
		Beta.loader.setActionGain(Beta.loader.getActionGain()+3);
	}

}
