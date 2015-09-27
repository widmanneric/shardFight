package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.DarkAvian;
import com.beta.equipment.Equipment;

public class AcDarkAvian extends Actions {
	
	public AcDarkAvian(int player,Equipment equip) {
	
		super.setStats(true, "Dark Avian", new DarkAvian(player,equip), 'd', player, 500, 'a',equip,3);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new DarkAvian(super.getPlayer(),super.getEquip());
	}
}
