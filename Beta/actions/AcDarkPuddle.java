package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.DarkPuddle;
import com.beta.equipment.Equipment;

public class AcDarkPuddle extends Actions {

	
	public AcDarkPuddle(int player,Equipment equip) {
		
		super.setStats(true, "Dark Puddle", new DarkPuddle(player,equip), 'd', player, 200, 'a',equip,4);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new DarkPuddle(super.getPlayer(),super.getEquip());
	}

}
