package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplaySwimmerActivity extends AppCompatActivity implements OnItemClickListener {

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_display_swimmer);

		// Get the id from the intent
		Intent intent = getIntent();
		int id = intent.getIntExtra("id", 0);
		// Retrieve info from database and convert to strings
		DatabaseHandler db = new DatabaseHandler(this);
		Swimmer swimmer = db.getSwimmer(id);
		String surname = swimmer.getSurname();
		String forename = swimmer.getForename();
		int dob_day = swimmer.getDayDOB();
		String day = Integer.toString(dob_day);
		int dob_month = swimmer.getMonthDOB();
		String month = Integer.toString(dob_month);
		int dob_year = swimmer.getYearDOB();
		String year = Integer.toString(dob_year);
		int handicap_min = swimmer.getHandicapMin();
		int handicap_sec = swimmer.getHandicapSec();
		String handi_min = Integer.toString(handicap_min);
		String handi_sec = Integer.toString(handicap_sec);
		String sex = swimmer.getSex();
		
		// Set the text view fields
		final TextView name = (TextView) findViewById(R.id.display_name);
		name.setText(forename + " " + surname);
		final TextView dob = (TextView) findViewById(R.id.display_dob);
		dob.setText(day + "/" + month + "/" + year);
		final TextView hcm = (TextView) findViewById(R.id.display_handicap_min);
		hcm.setText(handi_min);
		final TextView hcs = (TextView) findViewById(R.id.display_handicap_sec);
		hcs.setText(handi_sec);
		final TextView sx = (TextView) findViewById(R.id.display_sex);
		sx.setText(sex);
		
		ResultHandler dbs = new ResultHandler(this);
		RaceHandler dbh = new RaceHandler(this);
		// Create listview and set it to format in fragment
		lv = (ListView) findViewById(R.id.listView6);
		// get list of races from database
		List<Result> resultList = dbs.findBySwimmerHC(id);
		List<ResultList> rList = new ArrayList<ResultList>();
		for (Result rslt : resultList) {
			int race_id = rslt.getRaceID();
			Race race = dbh.getRace(race_id);
			String race_name = "|" + race.getName();
			int yr = race.getYear();
			int handicap_time_min = rslt.getHCTimeMin();
			int handicap_time_sec = rslt.getHCTimeSec();
			int swimmer_time_min = rslt.getTimeMin();
			int swimmer_time_sec = rslt.getTimeSec();
			if (handicap_time_sec < 10) {
				if (swimmer_time_sec < 10) {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + "0" + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + "0" + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(yr, race_name, time, hc_time);
					rList.add(finList);
				} else {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + "0" + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(yr, race_name, time, hc_time);
					rList.add(finList);
				}
				
			} else {
				if (swimmer_time_sec < 10) {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + "0" + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(yr, race_name, time, hc_time);
					rList.add(finList);
				} else {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(yr, race_name, time, hc_time);
					rList.add(finList);
				}
			}
		}
		// populate listview from list of names
		ArrayAdapter<ResultList> arrayAdapter = new ResultListAdapter(this, rList);
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(this);
	}
	
	// behaviour for when list item is clicked
	public void onItemClick(AdapterView<?> parent, final View v, int position, long id) {
		// Get the id from the intent
		Intent intent1 = getIntent();
		int _id_ = intent1.getIntExtra("id", 0);
		// Starts new activity displaying race details
		Intent intent = new Intent(this,DisplayRaceActivity.class);
		ResultHandler dbs = new ResultHandler(this);
		List<Result> resultList = dbs.findBySwimmerHC(_id_);
		// Gets the database id from the list id
		int ID = (int) id;
		Result result = resultList.get(ID);
		int _id = result.getRaceID();
		// Sends database id to new activity
		intent.putExtra("id", _id);
		startActivity(intent);	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
