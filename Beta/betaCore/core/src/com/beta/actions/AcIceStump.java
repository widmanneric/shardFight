package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.IceStump;
import com.beta.equipment.Equipment;

public class AcIceStump extends Actions {


	public AcIceStump(int player, Equipment equip) {

		super.setStats(true, "Ice Stump", new IceStump(player,equip), 'b', player, 200, 'a',equip,13);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new IceStump(super.getPlayer(),super.getEquip());
	}

}
