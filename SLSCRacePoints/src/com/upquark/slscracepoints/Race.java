package com.upquark.slscracepoints;

public class Race {

	// private variables
	int race_id;
	String race_name;
	int race_day;
	int race_month;
	int race_year;
	double race_length;
	int swimmer_no;
	
	// Empty constructor
	public Race() {
	}
	
	// Constructor
	public Race(int race_id, String race_name, int race_day, int race_month, int race_year, double race_length, int swimmer_no) {
		this.race_id = race_id;
		this.race_name = race_name;
		this.race_day = race_day;
		this.race_month = race_month;
		this.race_year = race_year;
		this.race_length = race_length;
		this.swimmer_no = swimmer_no;
	}
	
	// Constructor
	public Race(String race_name, int race_day, int race_month, int race_year, double race_length, int swimmer_no) {
		this.race_name = race_name;
		this.race_day = race_day;
		this.race_month = race_month;
		this.race_year = race_year;
		this.race_length = race_length;
		this.swimmer_no = swimmer_no;
	}
	
	// getting id
	public int getID() {
		return this.race_id;
	}
	// setting id
	public void setID(int race_id) {
		this.race_id = race_id;
	}
	
	// getting name
	public String getName() {
		return this.race_name;
	}
	// setting name
	public void setName(String race_name) {
		this.race_name = race_name;
	}
	
	// getting date
	public int getDay() {
		return this.race_day;
	}
	public int getMonth() {
		return this.race_month;
	}
	public int getYear() {
		return this.race_year;
	}
	// setting date
	public void setDay(int race_day) {
		this.race_day = race_day;
	}
	public void setMonth(int race_month) {
		this.race_month = race_month;
	}
	public void setYear(int race_year) {
		this.race_year = race_year;
	}
	
	// getting length
	public double getLength() {
		return this.race_length;
	}
	// setting length
	public void setLength(double race_length) {
		this.race_length = race_length;
	}
	
	// getting swimmer number
	public int getNumber() {
		return this.swimmer_no;
	}
	// setting swimmer number
	public void setNumber(int swimmer_no) {
		this.swimmer_no = swimmer_no;
	}
}