package com.beta.game;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;




public class Profile {

	
	//profile info 

	private String userName;
	private P p;


	//file stuff
	FileOutputStream fout;
	ObjectOutputStream oos;

	public Profile(P p, String userName) {

		this.userName=userName;
		this.p=p;
	
	}

	public void save(){
	
		try {
			fout = new FileOutputStream("profiles/"+this.getUserName()+".sav");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(p.getSetList());
			oos.writeObject(this.getUserName());
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveCPU(){

		try {
			fout = new FileOutputStream("cpu/"+this.getUserName()+".cpu");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(p.getSetList());
			oos.writeObject(this.getUserName());
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getUserName() {return userName;}

	public void setUserName(String userName) {this.userName = userName;}
	


	public P getP(){
		System.out.println(this.getUserName()+"'s profile has been succesfully loaded!\n");
		return p;
	}

	public void setP(P p) {
		this.p=p;
	}



}
