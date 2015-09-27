package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.DeathBringer;
import com.beta.equipment.Equipment;


public class AcDeathBringer extends Actions{


	public AcDeathBringer(int player,Equipment equip) {

		super.setStats(true, "DeathBringer", new DeathBringer(player,equip), 'd', player, 3000, 'a',equip,8);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new DeathBringer(super.getPlayer(),super.getEquip());
	}

}
