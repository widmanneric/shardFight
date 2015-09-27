package com.beta.actions;

import com.beta.chams.AzureDragon;
import com.beta.chams.Cham;
import com.beta.equipment.Equipment;

public class AcAzureDragon extends Actions{

	public AcAzureDragon(int player, Equipment equip) {
		super.setStats(true, "Azure Dragon", new AzureDragon(player,equip), 'b', player, 300, 'a', equip,1);
	}
	
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new AzureDragon(super.getPlayer(),super.getEquip());
	}
}
