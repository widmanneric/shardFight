package com.beta.actions;

import com.beta.chams.Cham;
import com.beta.chams.RebootRobot;
import com.beta.equipment.Equipment;

public class AcRebootRobot extends Actions {

	
	public AcRebootRobot(int player,Equipment equip) {

		super.setStats(true, "Reboot Robot", new RebootRobot(player,equip), 'n', player, 300, 'a',equip,16);
	}
	public Cham createNewCham() {
		// TODO Auto-generated method stub
		return new RebootRobot(super.getPlayer(),super.getEquip());
	}

}
