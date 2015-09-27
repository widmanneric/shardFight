package com.beta.abilities;

import com.beta.game.Beta;
import com.beta.game.DamageCalculator;


public class DarksEdge extends Abilities{

	public DarksEdge(){
		super.setStats("Dark's Edge", 1000, 'd', 1000, "As an additional cost\nsacrifice 2 champions.",'a',true);
		super.setMultiTarget(true);
	}
	public void activate(){
		Beta.mech.legalTargetType='a';
		Beta.mech.numberOfTargets=2;
	}

	public void targetsConfirmed(){
		if(Beta.mech.legalTargetType=='a'){
			for(int i =0; i<2;i+=1){
				Beta.mech.destroy(Beta.mech.targets.get(i).getPlayer(),Beta.mech.targets.get(i).getPosX()-1,Beta.mech.targets.get(i).getPosY()-1);
				Beta.mech.checkTrigger('s');
			}
			Beta.mech.numberOfTargets=1;
			Beta.mech.legalTargetType='e';
			Beta.mech.targets.clear();
			return;
		}
		if(Beta.mech.legalTargetType=='e'){
			DamageCalculator.dealDamage(Beta.mech.targets.get(0).getPosX()-1,Beta.mech.targets.get(0).getPosY()-1, Beta.mech.targets.get(0).getPlayer(), this);
			Beta.loader.setDrawAbHud(false);
			Beta.loader.drawDeductAp(true);
			Beta.loader.drawDamage();
			Beta.mech.checkTrigger('a');
			Beta.mech.abCham.setActive(false);
			Beta.mech.currAb=null;
			Beta.mech.targets.clear();
			Beta.loader.drawTarget(Beta.mech.abCham, false);
			Beta.mech.legalTargetType='a';
			Beta.mech.numberOfTargets=2;
		}
	}
	public void reset(){
		Beta.mech.targets.clear();
		Beta.mech.legalTargetType='a';
		Beta.mech.numberOfTargets=2;
	}
}
