package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.NormalStump;
import com.beta.equipment.Equipment;

public class AcNormalStump extends Actions {


	public AcNormalStump(int player,Equipment equip) {

		super.setStats(true, "Normal Stump", new NormalStump(player,equip), 'n', player, 200, 'a',equip,15);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new NormalStump(super.getPlayer(),super.getEquip());
	}
}
