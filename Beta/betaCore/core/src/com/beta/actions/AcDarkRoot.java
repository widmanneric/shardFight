package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.DarkRoot;
import com.beta.equipment.Equipment;

public class AcDarkRoot extends Actions{


	public AcDarkRoot(int player,Equipment equip) {
	
		super.setStats(true, "Dark Root", new DarkRoot(player,equip), 'd', player, 1000, 'a',equip,6);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new DarkRoot(super.getPlayer(),super.getEquip());
	}

}
