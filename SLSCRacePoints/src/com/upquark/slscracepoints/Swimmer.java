package com.upquark.slscracepoints;

public class Swimmer {
	// private variables
	int _id;
	String surname;
	String forename;
	int dob_day;
	int dob_month;
	int dob_year;
	int handicap_min;
	int handicap_sec;
	String sex;
	
	// Empty constructor
	public Swimmer(){
		
	}
	// constructor
	public Swimmer(int id, String surname, String forename, int dob_day, int dob_month, int dob_year, int handicap_min, int handicap_sec, String sex){
		this._id = id;
		this.surname = surname;
		this.forename = forename;
		this.dob_day = dob_day;
		this.dob_month = dob_month;
		this.dob_year = dob_year;
		this.handicap_min = handicap_min;
		this.handicap_sec = handicap_sec;
		this.sex = sex;
	}
	// constructor
	public Swimmer(String surname, String forename, int dob_day, int dob_month, int dob_year, int handicap_min, int handicap_sec, String sex){
		this.surname = surname;
		this.forename = forename;
		this.dob_day = dob_day;
		this.dob_month = dob_month;
		this.dob_year = dob_year;
		this.handicap_min = handicap_min;
		this.handicap_sec = handicap_sec;
		this.sex = sex;
		}
	// getting id
	public int getID(){
		return this._id;
	}
	// setting id
	public void setID(int id){
		this._id = id;
	}
	// getting surname
	public String getSurname(){
		return this.surname;
	}
	// setting surname
	public void setSurname(String surname){
		this.surname = surname;
	}
	// getting forename
	public String getForename(){
		return this.forename;
	}
	// setting forename
	public void setForename(String forename){
		this.forename = forename;
	}
	// getting dob
	public int getDayDOB(){
		return this.dob_day;
	}
	public int getMonthDOB(){
		return this.dob_month;
	}
	public int getYearDOB(){
		return this.dob_year;
	}
	// setting dob
	public void setDayDOB(int dob_day){
		this.dob_day = dob_day;
	}
	public void setMonthDOB(int dob_month){
		this.dob_month = dob_month;
	}
	public void setYearDOB(int dob_year){
		this.dob_year = dob_year;
	}
	// getting handicap
	public int getHandicapMin(){
		return this.handicap_min;
	}
	public int getHandicapSec(){
		return this.handicap_sec;
	}
	// setting handicap
	public void setHandicapMin(int handicap_min){
		this.handicap_min = handicap_min;
	}
	public void setHandicapSec(int handicap_sec){
		this.handicap_sec = handicap_sec;
	}
	// getting sex
	public String getSex(){
		return this.sex;
	}
	// setting sex
	public void setSex(String sex){
		this.sex = sex;
	}
	
	
}
