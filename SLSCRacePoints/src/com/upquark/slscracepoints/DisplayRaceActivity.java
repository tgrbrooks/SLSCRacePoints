package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class DisplayRaceActivity extends ActionBarActivity implements OnItemClickListener {

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_display_race);

		// Get the id from the intent
		Intent intent = getIntent();
		int id = intent.getIntExtra("id", 0);
		// Retrieve info from database and convert to strings
		RaceHandler db = new RaceHandler(this);
		Race race = db.getRace(id);
		String name = race.getName();
		int race_day = race.getDay();
		String day = Integer.toString(race_day);
		int race_month = race.getMonth();
		String month = Integer.toString(race_month);
		int race_year = race.getYear();
		String year = Integer.toString(race_year);
		double race_length = race.getLength();
		String len = Double.toString(race_length);
		int no_swimmers = race.getNumber();
		String swim = Integer.toString(no_swimmers);
		
		// Set the text view fields
		final TextView race_name = (TextView) findViewById(R.id.display_race_name);
		race_name.setText(name);
		final TextView date = (TextView) findViewById(R.id.display_date);
		date.setText(day + "/" + month + "/" + year);
		final TextView ln = (TextView) findViewById(R.id.display_length);
		ln.setText(len + " yards");
		final TextView sw = (TextView) findViewById(R.id.display_number);
		sw.setText(swim);
		
		ResultHandler dbs = new ResultHandler(this);
		DatabaseHandler dbh = new DatabaseHandler(this);
		// Create listview and set it to format in fragment
		lv = (ListView) findViewById(R.id.listView5);
		// get list of races from database
		/**List<Result> resultList = dbs.findByRaceHC(id);
		int i = 1;
		for (Result rslt : resultList) {
			int pos = i;
			int race_id = rslt.getRaceID();
			int result_id = rslt.getResultID();
			int swimmer_id = rslt.getSwimmerID();
			int tm = rslt.getTimeMin();
			int ts = rslt.getTimeSec();
			int htm = rslt.getHCTimeMin();
			int hts = rslt.getHCTimeSec();
			dbs.updateResult(result_id, new Result(race_id, swimmer_id, tm, ts, htm, hts, pos));
			i = i + 1;
		}*/
		List<Result> resList = dbs.findByRaceHC(id);
		// get list of names from swimmer list
		List<ResultList> rList = new ArrayList<ResultList>();
		for (Result rslt : resList) {
			int pos = rslt.getPosition();
			String posit = Integer.toString(pos);
			int swimmer_id = rslt.getSwimmerID();
			Swimmer swimmer = dbh.getSwimmer(swimmer_id);
			String swimmer_name = "|" + swimmer.getSurname() + ", " + swimmer.getForename();
			int handicap_time_min = rslt.getHCTimeMin();
			int handicap_time_sec = rslt.getHCTimeSec();
			int swimmer_time_min = rslt.getTimeMin();
			int swimmer_time_sec = rslt.getTimeSec();
			if (handicap_time_sec < 10) {
				if (swimmer_time_sec < 10) {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + "0" + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + "0" + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(pos, swimmer_name, time, hc_time);
					rList.add(finList);
				} else {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + "0" + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(pos, swimmer_name, time, hc_time);
					rList.add(finList);
				}
				
			} else {
				if (swimmer_time_sec < 10) {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + "0" + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(pos, swimmer_name, time, hc_time);
					rList.add(finList);
				} else {
					String hc_time = "|" + Integer.toString(handicap_time_min) + "." + Integer.toString(handicap_time_sec);
					String time = "|" + Integer.toString(swimmer_time_min) + "." + Integer.toString(swimmer_time_sec);
					ResultList finList = new ResultList(pos, swimmer_name, time, hc_time);
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
		// Starts new activity displaying swimmer details
		Intent intent = new Intent(this,DisplaySwimmerActivity.class);
		ResultHandler dbs = new ResultHandler(this);
		List<Result> resultList = dbs.findByRaceHC(_id_);
		// Gets the database id from the list id
		int ID = (int) id;
		Result result = resultList.get(ID);
		int _id = result.getSwimmerID();
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
