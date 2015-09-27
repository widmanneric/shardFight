package com.beta.game;


import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javafx.util.Pair;

import com.beta.actions.Actions;
import com.beta.equipment.Equipment;


public class P {



	private int nCounters;
	private int dCounters;
	private int fCounters;
	private int iCounters;
	private int wCounters;
	private int lCounters;
	private int gCounters;
	private int pCounters;
	private int actions;
	private int player;
	private ArrayList<Actions> action;
	private ArrayList<ArrayList<Pair<Integer,Integer>>> setLists= new ArrayList<ArrayList<Pair<Integer,Integer>>>();
	private ArrayList<Pair<Integer,Integer>> currList = new ArrayList<Pair<Integer,Integer>>();

	public P() {

		//int
		action = new ArrayList<Actions>();

	}
	public int getPlayer(){
		return player;
	}
	public void setPlayer(int player){this.player=player;}
	public int getActions() {
		return actions;
	}
	public void setActions(int actions) {
		this.actions = actions;
	}
	public void sortAction(int left, int right){
		int i =left;
		int j =right;
		int pivot=action.get(i+(j-i)/2).getCost();
		while(i<=j){
			while(action.get(i).getCost()<pivot){
				i+=1;
			}
			while(action.get(j).getCost()>pivot){
				j-=1;
			}
			if(i<=j){
				Actions temp= action.get(i);
				action.set(i,action.get(j));
				action.set(j, temp);
				i+=1;
				j-=1;
			}
			if(left<j){
				sortAction(left,j);
			}
			if(i<right){
				sortAction(i,right);
			}
		}

	}
	public void addAction(Actions action){
		this.action.add(action);
		if(action.getEquip()!=null){
			Pair<Integer,Integer> temp = new Pair<Integer,Integer>(action.getId(), action.getEquip().getId());
			this.currList.add(temp);
		}
		else{
			Pair<Integer,Integer> temp = new Pair<Integer,Integer>(action.getId(), -1);
			this.currList.add(temp);
		}



	}
	public void removeAction(){
		currList.remove(currList.size()-1);
	
	}
	public ArrayList<Actions> getActionList(){
		return this.action;
	}
	@SuppressWarnings("unchecked")
	public void setActionList(ArrayList<Actions> list){
		this.action=(ArrayList<Actions>) list.clone();
		sortAction(0, action.size()-1);
		for(int i=0;i<action.size();i+=1){

			if(action.get(i).getEquip()!=null){
				this.currList.add(new Pair<Integer,Integer>(action.get(i).getId(),action.get(i).getEquip().getId()));
			}
			else 
				this.currList.add(new Pair<Integer,Integer>(action.get(i).getId(),-1));
		}
	}

	public int getCounters(char temp) {
		// TODO Auto-generated method stub

		switch(temp){
		case 'n': return nCounters;
		case 'd': return dCounters;
		case 'f': return fCounters;
		case 'b': return iCounters;
		case 'w' : return wCounters;
		case 'l' : return lCounters;
		case 'g' : return gCounters;
		case 'p': return pCounters;
		}
		return 0;

	}

	public void setCounters(char type, int i) {
		// TODO Auto-generated method stub
		switch(type){
		case 'n': this.nCounters=i;
		break;
		case 'd': this.dCounters=i;
		break;
		case 'f': this.fCounters=i;
		break;
		case 'b': this.iCounters=i;
		break;
		case 'w' : this.wCounters=i;
		break;
		case 'l' : this.lCounters=i;
		break;
		case 'g' : this.gCounters=i;
		break;
		case 'p': this.pCounters=i;
		}

	}
	public void addGains(char typeGain, int gain) {

		this.setCounters(typeGain, gain+this.getCounters(typeGain));

	}//end of method

	public ArrayList<ArrayList<Pair<Integer, Integer>>> getSetList(){
		return setLists;
	}
	public void setSetList(ArrayList<ArrayList<Pair<Integer,Integer>>> list){
		this.setLists = list;
	}
	public void setCurrList(ArrayList<Pair<Integer,Integer>> list){
		this.currList=list;
	}
	public ArrayList<Pair<Integer,Integer>> getCurrList(){
		return this.currList;
	}

	public void saveCurrList(){
		this.setLists.add(currList);
	}
	@SuppressWarnings("rawtypes")
	public void populateActionList(int z) {
		ArrayList<Pair<Integer,Integer>> list =this.setLists.get(z);

		for(int i=0; i<list.size();i+=1){
			Actions tmp;
			Class tmpC = Constants.actionId.get(list.get(i).getKey());
			Constructor []tmpCon = tmpC.getConstructors();
			tmpC =Constants.equipId.get(list.get(i).getValue());
			try {
				if(list.get(i).getValue()>=0){
					Equipment tmpEq = (Equipment) tmpC.newInstance();
					tmp= (Actions) tmpCon[0].newInstance(this.getPlayer(),tmpEq);
				}
				else{
					tmp= (Actions) tmpCon[0].newInstance(this.getPlayer(),null);
				}
				this.addAction(tmp);
				System.out.println(tmp.getName()+" loaded!\n");

			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------\n");

	}
}
