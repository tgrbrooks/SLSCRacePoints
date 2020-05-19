package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditSwimmer extends AppCompatActivity implements OnItemClickListener {
	
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_edit_swimmer);
		
		DatabaseHandler db = new DatabaseHandler(this);
		// Create listview and set it to format in fragment
		lv = (ListView) findViewById(R.id.listView2);
		// get list of swimmers from database
		List<Swimmer> swimmerList = db.getAllSwimmers();
		// get list of names from swimmer list
		List<String> nameList = new ArrayList<String>();
		for (Swimmer sw : swimmerList) {
			String name = sw.getSurname() + ", " + sw.getForename();
			nameList.add(name);
		}
		// populate listview from list of names
		ArrayAdapter<String> arrayAdapter = new MyListAdapter<String>(this, R.layout.race_list, R.id.race_content, nameList);
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(this);

	
	}
	// behaviour for when list item is clicked
	public void onItemClick(AdapterView<?> parent, final View v, int position, long id) {
		// Starts new activity displaying swimmer details
		Intent intent = new Intent(this,EditIndividualActivity.class);
		DatabaseHandler db = new DatabaseHandler(this);
		List<Swimmer> swimmerList = db.getAllSwimmers();
		// Gets the database id from the list id
		int ID = (int) id;
		Swimmer swimmer = swimmerList.get(ID);
		int _id = swimmer.getID();
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
