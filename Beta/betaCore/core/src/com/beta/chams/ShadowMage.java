package com.beta.chams;

import com.beta.abilities.Abilities;
import com.beta.abilities.Disable;
import com.beta.abilities.Psybomb;
import com.beta.equipment.Equipment;
import com.beta.game.Beta;
import com.beta.game.Controls;
import com.beta.game.TxtLoader;

public class ShadowMage extends Cham {



	protected String effect;
	@SuppressWarnings("unused")
	private final int id =16;

	public ShadowMage(int player,Equipment equip) {

		effect="When this champion is summoned\nnegate target champions effect.";

		Abilities [] ab = {new Disable(), new Psybomb(),null};
		char [] typeGain={'p','d'};
		int [] gain={100,200};
		super.setStats(player,"Shadow Mage", effect, "Occult", 'd', 'n', -20, 20, ab, 520, typeGain, gain,500,'d',0,0,equip);
		super.createGraphics(TxtLoader.chams[10]);
	}
	public void activate(){
		Controls.mode=6;
		Beta.mech.numberOfTargets=1;
		Beta.mech.legalTargetType='b';
		Beta.mech.currCham=this;
		Beta.loader.canChangeTarget=true;
		Beta.loader.drawTarget(this,true);
		Beta.loader.drawSelectTarget(true);


	}
	public void targetsConfirmed() {
		// TODO Auto-generated method stub
		Beta.mech.targets.get(0).setTriggerType('z');
		Beta.mech.currCham=null;
		Controls.mode=0;
		Beta.mech.legalTargetType='z';
		Beta.mech.targets.clear();
		Beta.loader.canChangeTarget=false;
		Beta.loader.drawTarget(this, false);
		Beta.loader.drawSelectTarget(false);
	}

}
