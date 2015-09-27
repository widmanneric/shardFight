package com.beta.actions;

import com.beta.equipment.Equipment;
import com.beta.game.Beta;

public class AcDarkRamp extends Actions{


	public AcDarkRamp(int player,Equipment equip) {
		super.setDontTarget(true);
		super.setEffect("Gain +2 Actions");
		super.setStats(false, "Darkramp", null, 'd', player, 500, 'a',null,5);
	}
	public void activate(){
		Beta.loader.setActionGain(Beta.loader.getActionGain()+3);
		
	}

}
