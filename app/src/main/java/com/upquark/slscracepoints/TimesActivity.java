package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class TimesActivity extends AppCompatActivity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_times);
		
		// Create listview and set it to format in fragment
		lv = (ListView) findViewById(R.id.listViewTimes2);

		DatabaseHandler db = new DatabaseHandler(this);
		// get list of swimmers from database
		List<Swimmer> swimmerList = db.getAllSwimmers();
		// get list of names from swimmer list
		List<String> nameList = new ArrayList<String>();
		for (Swimmer sw : swimmerList) {
			String name = sw.getSurname() + ", " + sw.getForename() + " (" + sw.getID() + ")";
			nameList.add(name);
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.swim_spin, nameList);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// array of spinner ids
		/**int[] spinnerIds =  new int[] {R.id.swimmer_spinner1, R.id.swimmer_spinner2, R.id.swimmer_spinner3, R.id.swimmer_spinner4, R.id.swimmer_spinner5, R.id.swimmer_spinner6, R.id.swimmer_spinner7, R.id.swimmer_spinner8, R.id.swimmer_spinner9, R.id.swimmer_spinner10, R.id.swimmer_spinner11, R.id.swimmer_spinner12, R.id.swimmer_spinner13, R.id.swimmer_spinner14, R.id.swimmer_spinner15, R.id.swimmer_spinner16, R.id.swimmer_spinner17, R.id.swimmer_spinner18, R.id.swimmer_spinner19, R.id.swimmer_spinner20, R.id.swimmer_spinner21, R.id.swimmer_spinner22, R.id.swimmer_spinner23, R.id.swimmer_spinner24, R.id.swimmer_spinner25, R.id.swimmer_spinner26, R.id.swimmer_spinner27, R.id.swimmer_spinner28, R.id.swimmer_spinner29, R.id.swimmer_spinner30, R.id.swimmer_spinner31, R.id.swimmer_spinner32, R.id.swimmer_spinner33, R.id.swimmer_spinner34, R.id.swimmer_spinner35, R.id.swimmer_spinner36, R.id.swimmer_spinner37, R.id.swimmer_spinner38, R.id.swimmer_spinner39, R.id.swimmer_spinner40, R.id.swimmer_spinner41, R.id.swimmer_spinner42, R.id.swimmer_spinner43, R.id.swimmer_spinner44, R.id.swimmer_spinner45, R.id.swimmer_spinner46, R.id.swimmer_spinner47, R.id.swimmer_spinner48, R.id.swimmer_spinner49, R.id.swimmer_spinner50, R.id.swimmer_spinner51, R.id.swimmer_spinner52, R.id.swimmer_spinner53, R.id.swimmer_spinner54, R.id.swimmer_spinner55, R.id.swimmer_spinner56, R.id.swimmer_spinner57, R.id.swimmer_spinner58, R.id.swimmer_spinner59, R.id.swimmer_spinner60};
		// populates all spinners with swimmer names
		for (int i = 0; i < 60; i = i + 1) {
			Spinner spinner = (Spinner) findViewById(spinnerIds[i]);
			spinner.setAdapter(arrayAdapter);
		}*/
		
		// Get the id from the intent
		Intent intent = getIntent();
		int id = intent.getIntExtra("id", 0);
		RaceHandler rh = new RaceHandler(this);
		Race race = rh.getRace(id);
		int swimmer_no = race.getNumber();
		List<TimeList> tList = new ArrayList<TimeList>();
		String empty = "na";
		for ( int k = 0; k < swimmer_no; k = k + 1 ) {
			TimeList addList = new TimeList(arrayAdapter, 0, empty, empty);
			tList.add(addList);
		}
		// populate listview from list of names
		ArrayAdapter<TimeList> array = new TimeListAdapter(this, tList);
		lv.setAdapter(array);
		// array of layout ids
		/**int[] layoutIds =  new int[] {R.id.swimmer1, R.id.swimmer2, R.id.swimmer3, R.id.swimmer4, R.id.swimmer5, R.id.swimmer6, R.id.swimmer7, R.id.swimmer8, R.id.swimmer9, R.id.swimmer10, R.id.swimmer11, R.id.swimmer12, R.id.swimmer13, R.id.swimmer14, R.id.swimmer15, R.id.swimmer16, R.id.swimmer17, R.id.swimmer18, R.id.swimmer19, R.id.swimmer20, R.id.swimmer21, R.id.swimmer22, R.id.swimmer23, R.id.swimmer24, R.id.swimmer25, R.id.swimmer26, R.id.swimmer27, R.id.swimmer28, R.id.swimmer29, R.id.swimmer30, R.id.swimmer31, R.id.swimmer32, R.id.swimmer33, R.id.swimmer34, R.id.swimmer35, R.id.swimmer36, R.id.swimmer37, R.id.swimmer38, R.id.swimmer39, R.id.swimmer40, R.id.swimmer41, R.id.swimmer42, R.id.swimmer43, R.id.swimmer44, R.id.swimmer45, R.id.swimmer46, R.id.swimmer47, R.id.swimmer48, R.id.swimmer49, R.id.swimmer50, R.id.swimmer51, R.id.swimmer52, R.id.swimmer53, R.id.swimmer54, R.id.swimmer55, R.id.swimmer56, R.id.swimmer57, R.id.swimmer58, R.id.swimmer59, R.id.swimmer60};
		// makes correct number of layouts visible
		for (int j = 0; j < swimmer_no; j = j + 1) {
			LinearLayout ll = (LinearLayout) findViewById(layoutIds[j]);
			ll.setVisibility(View.VISIBLE);
		}*/
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
	
	public static boolean notNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return true;
		}
		return false;
	}
	
	public void submitResults(View view) {

		// Get the id from the intent
		Intent intent = getIntent();
		int race_id = intent.getIntExtra("id", 0);
		RaceHandler rh = new RaceHandler(this);
		Race race = rh.getRace(race_id);
		double race_length = race.getLength();
		int swimmer_no = race.getNumber();
		//int[] spinnerIds =  new int[] {R.id.swimmer_spinner1, R.id.swimmer_spinner2, R.id.swimmer_spinner3, R.id.swimmer_spinner4, R.id.swimmer_spinner5, R.id.swimmer_spinner6, R.id.swimmer_spinner7, R.id.swimmer_spinner8, R.id.swimmer_spinner9, R.id.swimmer_spinner10, R.id.swimmer_spinner11, R.id.swimmer_spinner12, R.id.swimmer_spinner13, R.id.swimmer_spinner14, R.id.swimmer_spinner15, R.id.swimmer_spinner16, R.id.swimmer_spinner17, R.id.swimmer_spinner18, R.id.swimmer_spinner19, R.id.swimmer_spinner20, R.id.swimmer_spinner21, R.id.swimmer_spinner22, R.id.swimmer_spinner23, R.id.swimmer_spinner24, R.id.swimmer_spinner25, R.id.swimmer_spinner26, R.id.swimmer_spinner27, R.id.swimmer_spinner28, R.id.swimmer_spinner29, R.id.swimmer_spinner30, R.id.swimmer_spinner31, R.id.swimmer_spinner32, R.id.swimmer_spinner33, R.id.swimmer_spinner34, R.id.swimmer_spinner35, R.id.swimmer_spinner36, R.id.swimmer_spinner37, R.id.swimmer_spinner38, R.id.swimmer_spinner39, R.id.swimmer_spinner40, R.id.swimmer_spinner41, R.id.swimmer_spinner42, R.id.swimmer_spinner43, R.id.swimmer_spinner44, R.id.swimmer_spinner45, R.id.swimmer_spinner46, R.id.swimmer_spinner47, R.id.swimmer_spinner48, R.id.swimmer_spinner49, R.id.swimmer_spinner50, R.id.swimmer_spinner51, R.id.swimmer_spinner52, R.id.swimmer_spinner53, R.id.swimmer_spinner54, R.id.swimmer_spinner55, R.id.swimmer_spinner56, R.id.swimmer_spinner57, R.id.swimmer_spinner58, R.id.swimmer_spinner59, R.id.swimmer_spinner60};
		//int[] timeMinIds = new int[] {R.id.race_time_min1, R.id.race_time_min2, R.id.race_time_min3, R.id.race_time_min4, R.id.race_time_min5, R.id.race_time_min6, R.id.race_time_min7, R.id.race_time_min8, R.id.race_time_min9, R.id.race_time_min10, R.id.race_time_min11, R.id.race_time_min12, R.id.race_time_min13, R.id.race_time_min14, R.id.race_time_min15, R.id.race_time_min16, R.id.race_time_min17, R.id.race_time_min18, R.id.race_time_min19, R.id.race_time_min20, R.id.race_time_min21, R.id.race_time_min22, R.id.race_time_min23, R.id.race_time_min24, R.id.race_time_min25, R.id.race_time_min26, R.id.race_time_min27, R.id.race_time_min28, R.id.race_time_min29, R.id.race_time_min30, R.id.race_time_min31, R.id.race_time_min32, R.id.race_time_min33, R.id.race_time_min34, R.id.race_time_min35, R.id.race_time_min36, R.id.race_time_min37, R.id.race_time_min38, R.id.race_time_min39, R.id.race_time_min40, R.id.race_time_min41, R.id.race_time_min42, R.id.race_time_min43, R.id.race_time_min44, R.id.race_time_min45, R.id.race_time_min46, R.id.race_time_min47, R.id.race_time_min48, R.id.race_time_min49, R.id.race_time_min50, R.id.race_time_min51, R.id.race_time_min52, R.id.race_time_min53, R.id.race_time_min54, R.id.race_time_min55, R.id.race_time_min56, R.id.race_time_min57, R.id.race_time_min58, R.id.race_time_min59, R.id.race_time_min60};
		//int[] timeSecIds = new int[] {R.id.race_time_sec1, R.id.race_time_sec2, R.id.race_time_sec3, R.id.race_time_sec4, R.id.race_time_sec5, R.id.race_time_sec6, R.id.race_time_sec7, R.id.race_time_sec8, R.id.race_time_sec9, R.id.race_time_sec10, R.id.race_time_sec11, R.id.race_time_sec12, R.id.race_time_sec13, R.id.race_time_sec14, R.id.race_time_sec15, R.id.race_time_sec16, R.id.race_time_sec17, R.id.race_time_sec18, R.id.race_time_sec19, R.id.race_time_sec20, R.id.race_time_sec21, R.id.race_time_sec22, R.id.race_time_sec23, R.id.race_time_sec24, R.id.race_time_sec25, R.id.race_time_sec26, R.id.race_time_sec27, R.id.race_time_sec28, R.id.race_time_sec29, R.id.race_time_sec30, R.id.race_time_sec31, R.id.race_time_sec32, R.id.race_time_sec33, R.id.race_time_sec34, R.id.race_time_sec35, R.id.race_time_sec36, R.id.race_time_sec37, R.id.race_time_sec38, R.id.race_time_sec39, R.id.race_time_sec40, R.id.race_time_sec41, R.id.race_time_sec42, R.id.race_time_sec43, R.id.race_time_sec44, R.id.race_time_sec45, R.id.race_time_sec46, R.id.race_time_sec47, R.id.race_time_sec48, R.id.race_time_sec49, R.id.race_time_sec50, R.id.race_time_sec51, R.id.race_time_sec52, R.id.race_time_sec53, R.id.race_time_sec54, R.id.race_time_sec55, R.id.race_time_sec56, R.id.race_time_sec57, R.id.race_time_sec58, R.id.race_time_sec59, R.id.race_time_sec60};
		int flag = 0;
		for (int j = 0; j < swimmer_no; j = j + 1) {
			if (flag == 1) {
				break;
			}
		    /**EditText editText = (EditText) findViewById(timeMinIds[j]);
			String Time_min = editText.getText().toString();
			editText.getEditableText().toString();
			EditText edit = (EditText) findViewById(timeSecIds[j]);
			String Time_sec = edit.getText().toString();
			edit.getEditableText().toString();*/
			View v = lv.getChildAt(j);
			EditText etimeMin = (EditText) v.findViewById(R.id.race_time_min);
			String Time_min = etimeMin.getText().toString();
			etimeMin.getEditableText().toString();
			EditText etimeSec = (EditText) v.findViewById(R.id.race_time_sec);
			String Time_sec = etimeSec.getText().toString();
			etimeSec.getEditableText().toString();
			if (Time_min.isEmpty() || Time_sec.isEmpty()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Missing Time");
				builder.setMessage("Please Fill All Times");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();
				flag = 1;
			} else{
				if ( notNumeric(Time_min) || notNumeric(Time_sec)) {
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle("Text in Time Field");
					builder.setMessage("Please only use numbers for times");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();
					flag = 1;
				} else {
					double time_min = Double.parseDouble(Time_min);
					double time_sec = Double.parseDouble(Time_sec);
					if ( time_min < 0 || time_min > 10000 || time_sec < 0 || time_sec >= 60) {
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setTitle("Invalid Time");
						builder.setMessage("Please enter a valid time");
						builder.setPositiveButton("OK", null);
						AlertDialog dialog = builder.show();
						flag = 1;
					} else {}
				}
			}
		}
		if (flag == 0) {
			for (int j = 0; j < swimmer_no; j = j + 1) {
				/**Spinner spinner = (Spinner) findViewById(spinnerIds[j]);
				String text = spinner.getSelectedItem().toString();*/
				View v = lv.getChildAt(j);
				Spinner spinner = (Spinner) v.findViewById(R.id.swimmer_spinner);
				String text = spinner.getSelectedItem().toString();
				int start = text.indexOf("(");
				int end = text.indexOf(")");
				String swim_id = text.substring(start + 1, end);
				int swimmer_id = Integer.parseInt(swim_id);
				// gets time
			    /**EditText editText = (EditText) findViewById(timeMinIds[j]);
				String Time_min = editText.getText().toString();
				editText.getEditableText().toString();
				EditText edit = (EditText) findViewById(timeSecIds[j]);
				String Time_sec = edit.getText().toString();
				edit.getEditableText().toString();*/
				EditText etimeMin = (EditText) v.findViewById(R.id.race_time_min);
				String Time_min = etimeMin.getText().toString();
				etimeMin.getEditableText().toString();
				EditText etimeSec = (EditText) v.findViewById(R.id.race_time_sec);
				String Time_sec = etimeSec.getText().toString();
				etimeSec.getEditableText().toString();
				int time_min = Integer.parseInt(Time_min);
				int time_sec = Integer.parseInt(Time_sec);
				DatabaseHandler dbh = new DatabaseHandler(this);
				Swimmer swimmer = dbh.getSwimmer(swimmer_id);
				int handicap_min = swimmer.getHandicapMin();
				int handicap_sec = swimmer.getHandicapSec();
				double final_time = time_sec + time_min*60 + ((handicap_sec + handicap_min*60)*race_length)/100;
				int fin_time = (int) Math.round(final_time);
				int hc_time_sec = 1;
				int hc_time_min = 0;
				for ( int i = 1; i < fin_time; i = i + 1) {
					if (hc_time_sec == 60) {
						hc_time_min = hc_time_min + 1;
						hc_time_sec = 0;
					}
					hc_time_sec = hc_time_sec + 1;
				}
				ResultHandler db = new ResultHandler(this);
				long ID = db.addResult(new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_min, 0));
				Intent intent2 = new Intent(this, FinalRaceActivity.class);
				intent2.putExtra("id", race_id);
				startActivity(intent2);
			}
		}
	}

}
