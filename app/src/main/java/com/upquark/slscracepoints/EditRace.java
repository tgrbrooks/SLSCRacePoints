package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditRace extends AppCompatActivity implements OnItemClickListener {

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_edit_race);

		RaceHandler db = new RaceHandler(this);
		// Create listview and set it to format in fragment
		lv = (ListView) findViewById(R.id.listView4);
		// get list of races from database
		List<Race> raceList = db.getAllRaces();
		// get list of names from swimmer list
		List<String> nameList = new ArrayList<String>();
		for (Race rce : raceList) {
			String name = rce.getName();
			nameList.add(name);
		}
		// populate listview from list of names
		ArrayAdapter<Race> arrayAdapter = new RaceListAdapter(this, raceList);
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(this);
	}
	
	// behaviour for when list item is clicked
	public void onItemClick(AdapterView<?> parent, final View v, int position, long id) {
		// Starts new activity displaying swimmer details
		Intent intent = new Intent(this,EditRaceDetailsActivity.class);
		RaceHandler db = new RaceHandler(this);
		List<Race> raceList = db.getAllRaces();
		// Gets the database id from the list id
		int ID = (int) id;
		Race race = raceList.get(ID);
		int _id = race.getID();
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
