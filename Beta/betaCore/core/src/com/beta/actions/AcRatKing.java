package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.RatKing;
import com.beta.equipment.Equipment;

public class AcRatKing extends Actions{


	public AcRatKing(int player,Equipment equip) {
	
		super.setStats(true, "Rat King", new RatKing(player,equip), 'd', player, 700, 'a',equip,15);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new RatKing(super.getPlayer(),super.getEquip());
	}

}
