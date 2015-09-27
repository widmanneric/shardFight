package com.beta.shards;

import com.beta.game.TxtLoader;

public class basicBlackShard extends Shard {

	public basicBlackShard(int player){
		
	
		char [] gainType = {'z','n','d'};
		int [] gain = {-5,100,200};
		
		//super constructor
		super.setStats("Basic Black Shard",player,super.createAni(TxtLoader.basicBlackShardTex),"Dark",500,gainType,gain,29);
	}
}
