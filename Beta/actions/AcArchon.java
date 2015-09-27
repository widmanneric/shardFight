package com.beta.actions;

import com.beta.chams.Archon;
import com.beta.chams.Cham;
import com.beta.equipment.Equipment;

public class AcArchon extends Actions {


	
	public AcArchon(int player,Equipment equip) {
		
		super.setStats(true, "Archon", new Archon(player,equip), 'f', player, 500, 'a',equip,0);

	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new Archon(super.getPlayer(),super.getEquip());
	}
}
