package com.beta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Controls implements InputProcessor{


	Mech mech;

	//current state of game
	//mode -1: in animation
	//mode 0: no target 
	//mode 1: ablitiy activated 
	//mode 2: target selected 
	//mode 3: actionHud;
	//mode 4: in actioncast
	//mode 5: additional targets
	//mode 6: normal effect
	public static int mode;
	GlyphLayout layout;
	SpriteRender loader = Beta.loader;
	//controls


	//target type for mode 1



	public Controls(Mech mech){
		this.mech = mech;
		mode =0;
		layout = new GlyphLayout();
	}

	@Override
	public boolean keyDown(int keycode) {

		//combat controls
		if(loader.step==1){
			//escape target phase
			if(keycode==Keys.ESCAPE){
				if(mode==1){
					mech.abPressed(-1);
					mode=0;
				}
				if(mode==3){
					mode=0;
					loader.drawActionHud=false;
					loader.drawActionList=false;
				}
				if(mode==4){
					mode=0;
					loader.drawActionCast(false);
				}
				if(mode==5){
					Beta.mech.currAb.reset();
					loader.drawAbHud=false;
					loader.drawTarget(null, false);
					mode=0;
				}
				if(mode==6){
					loader.drawActionCast(false);
					loader.drawTarget(null, false);
					mode=0;
				}
				loader.drawSelectTarget(false);
			}
			//______________________________________________________________________________
			//actions 
			if(keycode==Keys.Q){
				if(mode==0){
					loader.drawActionHud=true;
					Controls.mode=3;
					loader.drawCursorAc(false);
					loader.drawActionList=true;
				}
			}
			//end turn
			if(keycode==Keys.SPACE){
				if(mode==0){
					if(Beta.playerTurn==1){
						Beta.mech.resolveStartTurn(2);
					}
					else{
						Beta.mech.resolveStartTurn(1);
					}
					mode=-1;
				}
			}

			//_______________________________________________________________________________
			//ab click 
			if(mode==0){
				if(Beta.mech.getTargetCham()!=null&&Beta.mech.getTargetCham().getPlayer()==Beta.playerTurn)
					switch(keycode){
					case Keys.NUM_1: mech.abPressed(0);
					break;
					case Keys.NUM_2:
						if(mech.targetCham.getAb()[1]!=null){
							mech.abPressed(1);
						}
						break;
					case Keys.NUM_3: 
						if(mech.targetCham.getAb()[2]!=null){
							mech.abPressed(2);
						}
						break;
					}

				//_____________________________________________________________________
				//draw opp action points 
				if(keycode==Keys.TAB){
					loader.setApHud(true);
				}
			}
		}

		if(loader.step==3){
			loader.step=2;
			loader.titleFont.setColor(Color.WHITE);
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode==Keys.TAB){
			loader.setApHud(false);
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		 
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {


		screenY=810-screenY;

		//combat controls
		if(loader.step==1){
			//mode 0
			if(mode==0){
				//combat buttons
				if(button == Buttons.LEFT){
					//surrender
					if(screenX>810&&screenX<930&&screenY>30&&screenY<80){
						Gdx.app.exit();
					}
					//end turn 
					if(screenX>834&&screenX<934&&screenY>86&&screenY<146){
						if(Beta.playerTurn==1){
							Beta.mech.resolveStartTurn(2);
						}
						else{
							Beta.mech.resolveStartTurn(1);
						}
						mode=-1;

					}
					//actions
					if(screenX>836&&screenX<930&&screenY>154&&screenY<220){
						loader.drawActionHud=true;
						Controls.mode=3;
						loader.drawCursorAc(false);
						loader.drawActionList=true;
					}
				}

				//____________________________________________________________________________________________________
				//champion click
				if(button == Buttons.LEFT||button==Buttons.RIGHT){
					//iterate through board 1 
					for(int i =0; i <2; i+=1){
						for(int j=0; j<9; j+=1){
							if(screenX>i*90&&screenX<(i*90)+90&&screenY>j*90&&screenY<(j*90)+90){
								if(i==0&&j==4){
									mech.setTargetShard(0);
									continue;
								}
								mech.setTarget(i,j,1);
							}
						}
					}
					//iterate through board2 
					for(int i =0; i<2; i+=1){
						for(int j =0; j<9;j+=1){
							if(screenX>(i+7)*90&&screenX<((i+7)*90)+90&&screenY>j*90&&screenY<(j*90)+90){
								if(i==1&&j==4){
									mech.setTargetShard(1);
									continue;
								}
								mech.setTarget(i, j, 2);
							}
						}
					}
				}

				//______________________________________________________________________________________________________
				//look for ablitiy click
				for(int i=0;i<3;i+=1){
					if(screenX>820&&screenX<1200&&screenY>(i*60)+385&&screenY<((i*60)+415)+35){
						if(Beta.mech.getTargetCham()!=null&&( Beta.mech.getTargetCham()).getPlayer()==Beta.playerTurn){
							switch (i){
							case 0: i=2;
							break;
							case 1: i=1;
							break;
							case 2: i=0;
							break;
							}
							if(Beta.mech.getTargetCham().getAb()[i]!=null){
								mech.abPressed(i);
								return false;
							}
						}
					}
				}
			}

			//____________________________________________________________________________________________________________________________
			//look for ability target click
			if(mode==1){
				if(button==Buttons.LEFT||button==Buttons.RIGHT){
					if(mech.getTargetType()=='e'||mech.getTargetType()=='b'){
						if(Beta.playerTurn==1){

							//iterate through board 2 
							for(int i =0; i<2; i+=1){
								for(int j =0; j<9;j+=1){
									if(screenX>(i+7)*90&&screenX<((i+7)*90)+90&&screenY>j*90&&screenY<(j*90)+90){
										if(i==1&&j==4){

										}
										mech.activateAb(i, j, 2);
										return false;
									}
								}
							}
						}

						//iterate through board 2
						else{
							for(int i =0; i <2; i+=1){
								for(int j=0; j<9; j+=1){
									if(screenX>i*90&&screenX<(i*90)+90&&screenY>j*90&&screenY<(j*90)+90){
										if(i==0&&j==4){

										}
										mech.activateAb(i, j, 1);
										return false;
									}
								}
							}
						}
					}

					else if(mech.getTargetType()=='a'||mech.getTargetType()=='b'){
						if(Beta.playerTurn==2){
							//iterate through board 2 
							for(int i =0; i<2; i+=1){
								for(int j =0; j<9;j+=1){
									if(screenX>(i+7)*90&&screenX<((i+7)*90)+90&&screenY>j*90&&screenY<(j*90)+90){
										if(i==1&&j==4){

										}
										mech.activateAb(i, j, 2);
										return false;
									}
								}
							}
						}
						//iterate through board 1
						else{
							for(int i =0; i <2; i+=1){
								for(int j=0; j<9; j+=1){
									if(screenX>i*90&&screenX<(i*90)+90&&screenY>j*90&&screenY<(j*90)+90){
										if(i==0&&j==4){

										}
										mech.activateAb(i, j, 1);
										return false;
									}
								}
							}
						}
					}
				}
			}

			//______________________________________________________________________________________________________
			//activate action
			if(mode==3){
				P tempP = Beta.mech.getPlayer();
				for(int i=0; i<tempP.getActionList().size();i+=1){
					if(tempP.getActionList().get(i)!=null){
						layout.setText(loader.subFont, "x"+tempP.getActionList().get(i).getCost()+" "+tempP.getActionList().get(i).getName());
						if(i<10){
							if((screenX>((3*810)/8)-((3*layout.width)/8))&&screenX<((3*810)/8)-((3*layout.width)/8)+layout.width&&screenY>(40*i)+235-layout.height&&screenY<(40*i)+235){
								mech.activateAction(tempP.getActionList().get(i));
								return false;
							}
						}
						else{
							if((screenX>((5*810)/8)-((5*layout.width)/8))&&screenX<((5*810)/8)-((5*layout.width)/8)+layout.width&&screenY>(40*(i-10))+235-layout.height&&screenY<(40*(i-10))+235){
								mech.activateAction(tempP.getActionList().get(i));
								return false;
							}

						}
					}
				}
			}
			//_________________________________________________________________________________________________________
			//targetClicks 
			if(mode==4||mode==5||mode==6){
				//iterate through board 2 
				for(int i =0; i<2; i+=1){
					for(int j =0; j<9;j+=1){
						if(screenX>(i+7)*90&&screenX<((i+7)*90)+90&&screenY>j*90&&screenY<(j*90)+90){
							mech.setLastClick(i, j, 2);
							return false;
						}
					}
				}
				//iterate through board one 
				for(int i =0; i <2; i+=1){
					for(int j=0; j<9; j+=1){
						if(screenX>i*90&&screenX<(i*90)+90&&screenY>j*90&&screenY<(j*90)+90){
							mech.setLastClick(i, j, 1);
							return false;
						}
					}
				}

			}
		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//menu
		else if(loader.step==2){
			mech.activateMenuButton();
		}
		else if(loader.step==3){
			mech.loadSave();
		}



		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		screenY=810-screenY;
		
		//combat controls
		if(loader.step==1){
			//______________________________________________________________________________________________________
			//equipment hover
			if(screenX>Constants.leftHudBorder&&screenX<Constants.equipmentRightBound&&screenY<Constants.equipmentUpperBound&&screenY>Constants.equipmentLowerBount){
				loader.drawEquipmentInfo(true);
				return false;
			}
			else{
				loader.drawEquipmentInfo(false);
			}
			//________________________________________________________________________________________________________
			if(mode==0){
				//draw cursor at the surrender
				if(screenX>810&&screenX<930&&screenY>22&&screenY<88&&mode!=-1){
					mech.drawCursorSur(true);
				}
				else{
					mech.drawCursorSur(false);
				}

				//draw cursot at end turn 
				if(screenX>834&&screenX<934&&screenY>88&&screenY<154&&mode!=-1){
					mech.drawCursorEnd(true);
				}
				else{
					mech.drawCursorEnd(false);
				}

				//drawCursor at end turn
				if(screenX>836&&screenX<930&&screenY>154&&screenY<220&&mode!=-1){
					mech.drawCursorAc(true);
				}
				else{
					mech.drawCursorAc(false);
				}

				//draw hoverAb
				for(int i=0;i<3;i+=1){
					if(screenX>790&&screenX<990&&screenY>(i*60)+395&&screenY<(i*60)+405+40){
						loader.drawHoverAb(i);
						return false;
					}
					else if(i==2){
						loader.drawHoverAb(-1);
						return false;
					}
				}
			}
			//________________________________________________________________________________________________
			//action hover
			if(mode==3){
				P tempP=Beta.mech.getPlayer();
				for(int i=0; i<tempP.getActionList().size();i+=1){
					if(tempP.getActionList().get(i)!=null){
						layout.setText(loader.subFont, "x"+tempP.getActionList().get(i).getCost()+" "+tempP.getActionList().get(i).getName());
						if(i<10){
							if((screenX>((3*810)/8)-((3*layout.width)/8))&&screenX<((3*810)/8)-((3*layout.width)/8)+layout.width&&screenY>(40*i)+235-layout.height&&screenY<(40*i)+235){
								if(tempP.getActionList().get(i).isCham()){
									mech.setTargetCham(tempP.getActionList().get(i).getNewCham());
									return false;
								}
								else{
									mech.setTargetAction(tempP.getActionList().get(i));
									return false;
								}
							}
						}
						else{
							if(screenX>(((5*810)/8)-((5*layout.width)/8))&&screenX<(((5*810)/8)-((5*layout.width)/8))+layout.width&&screenY>(40*(i-10))+235-layout.height&&screenY<(40*(i-10))+235){
								if(tempP.getActionList().get(i).isCham()){
									mech.setTargetCham(tempP.getActionList().get(i).getNewCham());
									return false;
								}
								else{
									mech.setTargetAction(tempP.getActionList().get(i));
									return false;
								}
							}
						}
					}
				}
			}

			//__________________________________________________________________________________________________
			//targetClick
			if(mode==1||mode==6||mode==5){
				//iterate through board 1 
				for(int i =0; i <2; i+=1){
					for(int j=0; j<9; j+=1){
						if(screenX>i*90&&screenX<(i*90)+90&&screenY>j*90&&screenY<(j*90)+90){
							if(i==0&&j==4){
								mech.setTargetShard(0);
								continue;
							}
							mech.setTarget(i,j,1);
						}
					}
				}
				//iterate through board2 
				for(int i =0; i<2; i+=1){
					for(int j =0; j<9;j+=1){
						if(screenX>(i+7)*90&&screenX<((i+7)*90)+90&&screenY>j*90&&screenY<(j*90)+90){
							if(i==1&&j==4){
								mech.setTargetShard(1);
								continue;
							}
							mech.setTarget(i, j, 2);
						}
					}
				}
			}
		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//menu
		else if(loader.step==2){
			for(int i=0;i<4;i+=1){
				if(screenY<310-(i*66.75)&&screenY>250-(i*66.75)){
					loader.drawHoverMenu(i);
					return false;
				}
			}
			loader.drawHoverMenu(-1);
		}

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		else if(loader.step==3){
			for(int i =0; i<Constants.profiles.size();i+=1){
				if(screenY<730-(i*80)&&screenY>670-(i*80)&&screenX<500){
					loader.drawHoverSave(i);
					return false;
				}
				else if(screenY>730&&screenX<400){
					loader.drawHoverSave(-1);
				}
				else{
					loader.drawHoverSave(-2);
				}
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

}
