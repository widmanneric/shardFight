package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.FlameStump;
import com.beta.equipment.Equipment;

public class AcFlameStump extends Actions{


	public AcFlameStump(int player,Equipment equip) {
		super.setStats(true, "Flame Stump", new FlameStump(player,equip), 'f', player, 200, 'a',equip,10);
	}

	public Cham createNewCham(){
		return new FlameStump(super.getPlayer(),super.getEquip());
	}
}
