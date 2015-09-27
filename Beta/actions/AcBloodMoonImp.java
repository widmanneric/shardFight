package com.beta.actions;

import com.beta.chams.BloodMoonImp;
import com.beta.chams.Cham;
import com.beta.equipment.Equipment;


public class AcBloodMoonImp extends Actions {

	
	public AcBloodMoonImp(int player,Equipment equip) {
	
		super.setStats(true, "BloodMoon Imp", new BloodMoonImp(player,equip), 'n', player, 50, 'a',equip,2);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new BloodMoonImp(super.getPlayer(),super.getEquip());
	}
}
