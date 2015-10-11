package com.upquark.slscracepoints;

public class ResultList {

	// private variables
	int result_id;
	int pos;
	String name;
	String time;
	String hc_time;
	
	// Empty constructor
	public ResultList() {
	}
	
	// Constructor
	public ResultList(int result_id, int pos, String name, String time, String hc_time) {
		this.result_id = result_id;
		this.pos = pos;
		this.name = name;
		this.time = time;
		this.hc_time = hc_time;
		this.pos = pos;
	}
	
	// Constructor
	public ResultList(int pos, String name, String time, String hc_time) {
		this.pos = pos;
		this.name = name;
		this.time = time;
		this.hc_time = hc_time;
		this.pos = pos;
	}
	
	// getting result id
	public int getResultID() {
		return this.result_id;
	}
	// setting result id
	public void setResultID(int result_id) {
		this.result_id = result_id;
	}
	
	// getting position
	public int getPosition() {
		return this.pos;
	}
	// setting position
	public void setPosition(int pos) {
		this.pos = pos;
	}
	// getting race id
	public String getName() {
		return this.name;
	}
	// setting race id
	public void setName(String name) {
		this.name = name;
	}
	
	// getting time
	public String getTime() {
		return this.time;
	}
	// setting time
	public void setTime(String time) {
		this.time = time;
	}
	
	// getting post handicap time
	public String getHCTime() {
		return this.hc_time;
	}
	// setting post handicap time
	public void setHCTime(String hc_time) {
		this.hc_time = hc_time;
	}	
	
}
