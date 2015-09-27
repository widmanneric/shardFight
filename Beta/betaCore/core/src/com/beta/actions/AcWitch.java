package com.beta.actions;


import com.beta.chams.Cham;
import com.beta.chams.Witch;
import com.beta.equipment.Equipment;

public class AcWitch extends Actions {


	public AcWitch(int player,Equipment equip) {
	
		super.setStats(true, "Witch", new Witch(player,equip), 'd', player, 500, 'a',equip,18);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new Witch(super.getPlayer(),super.getEquip());
	}
}
