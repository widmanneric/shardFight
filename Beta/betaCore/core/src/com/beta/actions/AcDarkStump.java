package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.DarkStump;
import com.beta.equipment.Equipment;

public class AcDarkStump extends Actions {


	public AcDarkStump(int player,Equipment equip) {
	
		super.setStats(true, "Dark Stump", new DarkStump(player,equip), 'd', player, 200, 'a',equip,7);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new DarkStump(super.getPlayer(),super.getEquip());
	}


}
