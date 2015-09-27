package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.Golem;
import com.beta.equipment.Equipment;

public class AcGolem extends Actions {


	public AcGolem(int player,Equipment equip) {
	
		super.setStats(true, "Golem", new Golem(player,equip), 'n', player, 700, 'a',equip,11);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new Golem(super.getPlayer(),super.getEquip());
	}
}
