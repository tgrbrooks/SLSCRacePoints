package com.upquark.slscracepoints;

public class Result {

	// private variables
	int result_id;
	int race_id;
	int swimmer_id;
	int time_min;
	int time_sec;
	int hc_time_min;
	int hc_time_sec;
	int pos;
	
	// Empty constructor
	public Result() {
	}
	
	// Constructor
	public Result(int result_id, int race_id, int swimmer_id, int time_min, int time_sec, int hc_time_min, int hc_time_sec, int pos) {
		this.result_id = result_id;
		this.race_id = race_id;
		this.swimmer_id = swimmer_id;
		this.time_min = time_min;
		this.time_sec = time_sec;
		this.hc_time_min = hc_time_min;
		this.hc_time_sec = hc_time_sec;
		this.pos = pos;
	}
	
	// Constructor
	public Result(int race_id, int swimmer_id, int time_min, int time_sec, int hc_time_min, int hc_time_sec, int pos) {
		this.race_id = race_id;
		this.swimmer_id = swimmer_id;
		this.time_min = time_min;
		this.time_sec = time_sec;
		this.hc_time_min = hc_time_min;
		this.hc_time_sec = hc_time_sec;
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
	
	// getting race id
	public int getRaceID() {
		return this.race_id;
	}
	// setting race id
	public void setRaceID(int race_id) {
		this.race_id = race_id;
	}
	
	// getting swimmer id
	public int getSwimmerID() {
		return this.swimmer_id;
	}
	// setting swimmer id
	public void setSwimmerID(int swimmer_id) {
		this.swimmer_id = swimmer_id;
	}
	
	// getting time
	public int getTimeMin() {
		return this.time_min;
	}
	public int getTimeSec() {
		return this.time_sec;
	}
	// setting time
	public void setTimeMin(int time_min) {
		this.time_min = time_min;
	}
	public void setTimeSec(int time_sec) {
		this.time_sec = time_sec;
	}
	
	// getting post handicap time
	public int getHCTimeMin() {
		return this.hc_time_min;
	}
	public int getHCTimeSec() {
		return this.hc_time_sec;
	}
	// setting post handicap time
	public void setHCTimeMin(int hc_time_min) {
		this.hc_time_min = hc_time_min;
	}
	public void setHCTimeSec(int hc_time_sec) {
		this.hc_time_sec = hc_time_sec;
	}
	
	// getting position
	public int getPosition() {
		return this.pos;
	}
	// setting position
	public void setPosition(int pos) {
		this.pos = pos;
	}
	
}
