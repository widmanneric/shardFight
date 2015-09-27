package com.beta.actions;


import com.beta.chams.Cham;
import com.beta.chams.ShadowMage;
import com.beta.equipment.Equipment;

public class AcShadowMage extends Actions {


	public AcShadowMage(int player,Equipment equip) {
	
		super.setStats(true, "Shadow Mage", new ShadowMage(player,equip), 'd', player, 500, 'a',equip,17);
	}

	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new ShadowMage(super.getPlayer(),super.getEquip());
	}
}
