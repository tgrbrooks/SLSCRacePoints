package com.upquark.slscracepoints;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TimeList {
	
	// private variables
	ArrayAdapter<String> arrayAdapter;
	int pos;
	String time_min;
	String time_sec;
	
	// Empty constructor
	public TimeList() {
	}
	
	// Constructor
	public TimeList(ArrayAdapter<String> arrayAdapter, int pos, String time_min, String time_sec) {
		this.arrayAdapter = arrayAdapter;
		this.pos = pos;
		this.time_min = time_min;
		this.time_sec = time_sec;
	}
	
	// getting array
	public ArrayAdapter<String> getArray() {
		return this.arrayAdapter;
	}
	// setting swimmers
	public void setSwimmers(ArrayAdapter<String> arrayAdapter) {
		this.arrayAdapter = arrayAdapter;
	}
	
	// getting position
	public int getPosition() {
		return this.pos;
	}
	// setting position
	public void setPosition(int pos) {
		this.pos = pos;
	}

	// getting time
	public String getTimeMin() {
		return this.time_min;
	}
	public String getTimeSec() {
		return this.time_sec;
	}
	// setting time
	public void setTimeMin(String time_min) {
		this.time_min = time_min;
	}
	public void setTimeSec(String time_sec) {
		this.time_sec = time_sec;
	}

}
