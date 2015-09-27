package com.beta.chams;


import com.beta.abilities.Abilities;
import com.beta.abilities.Laser;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.Controls;
import com.beta.game.TxtLoader;

public class RebootRobot extends Cham {



	protected String effect;
	@SuppressWarnings("unused")
	private final int id =14;
	
	public RebootRobot(int player,Equipment equip) {

	

		effect="When this champion is summoned\ntarget champion becomes active.";

		Abilities [] ab = {new Laser(),null,null};
		char [] typeGain={'z','n'};
		int [] gain={-50,50};
		super.setStats(player,"Reboot Robot", effect, "Normal", 'n', 'n', -20, 25, ab, 200, typeGain, gain,300,'n',0,0,equip);
		super.createGraphics(TxtLoader.chams[8]);
	}

	public void activate(){
		Controls.mode=6;
		Beta.mech.numberOfTargets=1;
		Beta.mech.legalTargetType='a';
		Beta.mech.currCham=this;
		Beta.loader.canChangeTarget=true;
		Beta.loader.drawTarget(this,true);
		Beta.loader.drawSelectTarget(true);

	}
	public void targetsConfirmed() {
		// TODO Auto-generated method stub
		Beta.mech.targets.get(0).setActive(true);
		Beta.mech.currCham=null;
		Controls.mode=0;
		Beta.mech.legalTargetType='z';
		Beta.mech.targets.clear();
		Beta.loader.canChangeTarget=false;
		Beta.loader.drawTarget(this, false);
		Beta.loader.drawSelectTarget(false);
	}

}
