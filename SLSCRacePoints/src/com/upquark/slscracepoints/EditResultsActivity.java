
	package com.upquark.slscracepoints;

	import java.util.ArrayList;
import java.util.List;

	import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

	public class EditResultsActivity extends ActionBarActivity {

		private ListView lv;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.fragment_edit_results);
			
			// Create listview and set it to format in fragment
			lv = (ListView) findViewById(R.id.listViewTimes);

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
			// populates spinner with list of swimmers
			/**int[] spinnerIds =  new int[] {R.id.eswimmer_spinner1, R.id.eswimmer_spinner2, R.id.eswimmer_spinner3, R.id.eswimmer_spinner4, R.id.eswimmer_spinner5, R.id.eswimmer_spinner6, R.id.eswimmer_spinner7, R.id.eswimmer_spinner8, R.id.eswimmer_spinner9, R.id.eswimmer_spinner10, R.id.eswimmer_spinner11, R.id.eswimmer_spinner12, R.id.eswimmer_spinner13, R.id.eswimmer_spinner14, R.id.eswimmer_spinner15, R.id.eswimmer_spinner16, R.id.eswimmer_spinner17, R.id.eswimmer_spinner18, R.id.eswimmer_spinner19, R.id.eswimmer_spinner20, R.id.eswimmer_spinner21, R.id.eswimmer_spinner22, R.id.eswimmer_spinner23, R.id.eswimmer_spinner24, R.id.eswimmer_spinner25, R.id.eswimmer_spinner26, R.id.eswimmer_spinner27, R.id.eswimmer_spinner28, R.id.eswimmer_spinner29, R.id.eswimmer_spinner30, R.id.eswimmer_spinner31, R.id.eswimmer_spinner32, R.id.eswimmer_spinner33, R.id.eswimmer_spinner34, R.id.eswimmer_spinner35, R.id.eswimmer_spinner36, R.id.eswimmer_spinner37, R.id.eswimmer_spinner38, R.id.eswimmer_spinner39, R.id.eswimmer_spinner40, R.id.eswimmer_spinner41, R.id.eswimmer_spinner42, R.id.eswimmer_spinner43, R.id.eswimmer_spinner44, R.id.eswimmer_spinner45, R.id.eswimmer_spinner46, R.id.eswimmer_spinner47, R.id.eswimmer_spinner48, R.id.eswimmer_spinner49, R.id.eswimmer_spinner50, R.id.eswimmer_spinner51, R.id.eswimmer_spinner52, R.id.eswimmer_spinner53, R.id.eswimmer_spinner54, R.id.eswimmer_spinner55, R.id.eswimmer_spinner56, R.id.eswimmer_spinner57, R.id.eswimmer_spinner58, R.id.eswimmer_spinner59, R.id.eswimmer_spinner60};
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
			ResultHandler re = new ResultHandler(this);
			// gets list of results for that race
			List<Result> resList = re.findByRace(id);
			DatabaseHandler dbh = new DatabaseHandler(this);
			/**List<String> oldNameList = new ArrayList<String>();
			List<String> oldTimeMinList = new ArrayList<String>();
			List<String> oldTimeSecList = new ArrayList<String>();*/
			List<Integer> positionList = new ArrayList<Integer>();
			List<TimeList> tList = new ArrayList<TimeList>();
			int i = 0;
			for (Result rslt : resList) {
				// gets id and name of swimmers in that race
				int swimmer_id = rslt.getSwimmerID();
				Swimmer swimmer = dbh.getSwimmer(swimmer_id);
				String swimmer_name = swimmer.getSurname() + ", " + swimmer.getForename() + " (" + swimmer.getID() + ")";
				int position = 0;
				// find the position of the swimmer in the spinner menu by comparing names
				for (int k = 0; k < nameList.size(); k = k + 1) {
					if (swimmer_name.equals(nameList.get(k))) {
						position = k;
						positionList.add(position);
					}
				}
				int swimmer_time_min = rslt.getTimeMin();
				int swimmer_time_sec = rslt.getTimeSec();
				String time_min = Integer.toString(swimmer_time_min);
				String time_sec = Integer.toString(swimmer_time_sec);
				// creates lists of previous results
				/**oldNameList.add(swimmer_name);
				oldTimeMinList.add(time_min);
				oldTimeSecList.add(time_sec);*/
				TimeList addList = new TimeList(arrayAdapter, positionList.get(i), time_min, time_sec);
				tList.add(addList);
				i = i + 1;
			}
			if ( tList.size() == swimmer_no ) {
				// populate listview from list of names
				ArrayAdapter<TimeList> array = new TimeListAdapter(this, tList);
				lv.setAdapter(array);
			} else {
				if ( tList.size() < swimmer_no ) {
					for ( int j = tList.size(); j < swimmer_no ; j = j + 1) {
						String empty = "na";
						TimeList emptyList = new TimeList(arrayAdapter, 0, empty, empty);
						tList.add(emptyList);
					}
					// populate listview from list of names
					ArrayAdapter<TimeList> array = new TimeListAdapter(this, tList);
					lv.setAdapter(array);
				} else {
					for ( int m = swimmer_no; m < tList.size() ; m = m + 1) {
						tList.remove(m);
					}
					// populate listview from list of names
					ArrayAdapter<TimeList> array = new TimeListAdapter(this, tList);
					lv.setAdapter(array);
				}
			}
			// makes correct number of layouts visible
			/**int[] layoutIds =  new int[] {R.id.eswimmer1, R.id.eswimmer2, R.id.eswimmer3, R.id.eswimmer4, R.id.eswimmer5, R.id.eswimmer6, R.id.eswimmer7, R.id.eswimmer8, R.id.eswimmer9, R.id.eswimmer10, R.id.eswimmer11, R.id.eswimmer12, R.id.eswimmer13, R.id.eswimmer14, R.id.eswimmer15, R.id.eswimmer16, R.id.eswimmer17, R.id.eswimmer18, R.id.eswimmer19, R.id.eswimmer20, R.id.eswimmer21, R.id.eswimmer22, R.id.eswimmer23, R.id.eswimmer24, R.id.eswimmer25, R.id.eswimmer26, R.id.eswimmer27, R.id.eswimmer28, R.id.eswimmer29, R.id.eswimmer30, R.id.eswimmer31, R.id.eswimmer32, R.id.eswimmer33, R.id.eswimmer34, R.id.eswimmer35, R.id.eswimmer36, R.id.eswimmer37, R.id.eswimmer38, R.id.eswimmer39, R.id.eswimmer40, R.id.eswimmer41, R.id.eswimmer42, R.id.eswimmer43, R.id.eswimmer44, R.id.eswimmer45, R.id.eswimmer46, R.id.eswimmer47, R.id.eswimmer48, R.id.eswimmer49, R.id.eswimmer50, R.id.eswimmer51, R.id.eswimmer52, R.id.eswimmer53, R.id.eswimmer54, R.id.eswimmer55, R.id.eswimmer56, R.id.eswimmer57, R.id.eswimmer58, R.id.eswimmer59, R.id.eswimmer60};
			for (int j = 0; j < swimmer_no; j = j + 1) {
				LinearLayout ll = (LinearLayout) findViewById(layoutIds[j]);
				ll.setVisibility(View.VISIBLE);
			}
			// sets spinner initial position from previous results
			for (int m = 0; m < oldNameList.size(); m = m + 1) {
				Spinner spinner = (Spinner) findViewById(spinnerIds[m]);
				spinner.setSelection(positionList.get(m));
			}
			// set edit text hints to times from previous results
			int[] timeMinIds = new int[] {R.id.erace_time_min1, R.id.erace_time_min2, R.id.erace_time_min3, R.id.erace_time_min4, R.id.erace_time_min5, R.id.erace_time_min6, R.id.erace_time_min7, R.id.erace_time_min8, R.id.erace_time_min9, R.id.erace_time_min10, R.id.erace_time_min11, R.id.erace_time_min12, R.id.erace_time_min13, R.id.erace_time_min14, R.id.erace_time_min15, R.id.erace_time_min16, R.id.erace_time_min17, R.id.erace_time_min18, R.id.erace_time_min19, R.id.erace_time_min20, R.id.erace_time_min21, R.id.erace_time_min22, R.id.erace_time_min23, R.id.erace_time_min24, R.id.erace_time_min25, R.id.erace_time_min26, R.id.erace_time_min27, R.id.erace_time_min28, R.id.erace_time_min29, R.id.erace_time_min30, R.id.erace_time_min31, R.id.erace_time_min32, R.id.erace_time_min33, R.id.erace_time_min34, R.id.erace_time_min35, R.id.erace_time_min36, R.id.erace_time_min37, R.id.erace_time_min38, R.id.erace_time_min39, R.id.erace_time_min40, R.id.erace_time_min41, R.id.erace_time_min42, R.id.erace_time_min43, R.id.erace_time_min44, R.id.erace_time_min45, R.id.erace_time_min46, R.id.erace_time_min47, R.id.erace_time_min48, R.id.erace_time_min49, R.id.erace_time_min50, R.id.erace_time_min51, R.id.erace_time_min52, R.id.erace_time_min53, R.id.erace_time_min54, R.id.erace_time_min55, R.id.erace_time_min56, R.id.erace_time_min57, R.id.erace_time_min58, R.id.erace_time_min59, R.id.erace_time_min60};
			for (int n = 0; n < oldTimeMinList.size(); n = n + 1) {
				EditText raceTime = (EditText) findViewById(timeMinIds[n]);
				raceTime.setHint(oldTimeMinList.get(n));
			}
			int[] timeSecIds = new int[] {R.id.erace_time_sec1, R.id.erace_time_sec2, R.id.erace_time_sec3, R.id.erace_time_sec4, R.id.erace_time_sec5, R.id.erace_time_sec6, R.id.erace_time_sec7, R.id.erace_time_sec8, R.id.erace_time_sec9, R.id.erace_time_sec10, R.id.erace_time_sec11, R.id.erace_time_sec12, R.id.erace_time_sec13, R.id.erace_time_sec14, R.id.erace_time_sec15, R.id.erace_time_sec16, R.id.erace_time_sec17, R.id.erace_time_sec18, R.id.erace_time_sec19, R.id.erace_time_sec20, R.id.erace_time_sec21, R.id.erace_time_sec22, R.id.erace_time_sec23, R.id.erace_time_sec24, R.id.erace_time_sec25, R.id.erace_time_sec26, R.id.erace_time_sec27, R.id.erace_time_sec28, R.id.erace_time_sec29, R.id.erace_time_sec30, R.id.erace_time_sec31, R.id.erace_time_sec32, R.id.erace_time_sec33, R.id.erace_time_sec34, R.id.erace_time_sec35, R.id.erace_time_sec36, R.id.erace_time_sec37, R.id.erace_time_sec38, R.id.erace_time_sec39, R.id.erace_time_sec40, R.id.erace_time_sec41, R.id.erace_time_sec42, R.id.erace_time_sec43, R.id.erace_time_sec44, R.id.erace_time_sec45, R.id.erace_time_sec46, R.id.erace_time_sec47, R.id.erace_time_sec48, R.id.erace_time_sec49, R.id.erace_time_sec50, R.id.erace_time_sec51, R.id.erace_time_sec52, R.id.erace_time_sec53, R.id.erace_time_sec54, R.id.erace_time_sec55, R.id.erace_time_sec56, R.id.erace_time_sec57, R.id.erace_time_sec58, R.id.erace_time_sec59, R.id.erace_time_sec60};
			for (int n = 0; n < oldTimeSecList.size(); n = n + 1) {
				EditText raceTime = (EditText) findViewById(timeSecIds[n]);
				raceTime.setHint(oldTimeSecList.get(n));
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
			//int[] spinnerIds =  new int[] {R.id.eswimmer_spinner1, R.id.eswimmer_spinner2, R.id.eswimmer_spinner3, R.id.eswimmer_spinner4, R.id.eswimmer_spinner5, R.id.eswimmer_spinner6, R.id.eswimmer_spinner7, R.id.eswimmer_spinner8, R.id.eswimmer_spinner9, R.id.eswimmer_spinner10, R.id.eswimmer_spinner11, R.id.eswimmer_spinner12, R.id.eswimmer_spinner13, R.id.eswimmer_spinner14, R.id.eswimmer_spinner15, R.id.eswimmer_spinner16, R.id.eswimmer_spinner17, R.id.eswimmer_spinner18, R.id.eswimmer_spinner19, R.id.eswimmer_spinner20, R.id.eswimmer_spinner21, R.id.eswimmer_spinner22, R.id.eswimmer_spinner23, R.id.eswimmer_spinner24, R.id.eswimmer_spinner25, R.id.eswimmer_spinner26, R.id.eswimmer_spinner27, R.id.eswimmer_spinner28, R.id.eswimmer_spinner29, R.id.eswimmer_spinner30, R.id.eswimmer_spinner31, R.id.eswimmer_spinner32, R.id.eswimmer_spinner33, R.id.eswimmer_spinner34, R.id.eswimmer_spinner35, R.id.eswimmer_spinner36, R.id.eswimmer_spinner37, R.id.eswimmer_spinner38, R.id.eswimmer_spinner39, R.id.eswimmer_spinner40, R.id.eswimmer_spinner41, R.id.eswimmer_spinner42, R.id.eswimmer_spinner43, R.id.eswimmer_spinner44, R.id.eswimmer_spinner45, R.id.eswimmer_spinner46, R.id.eswimmer_spinner47, R.id.eswimmer_spinner48, R.id.eswimmer_spinner49, R.id.eswimmer_spinner50, R.id.eswimmer_spinner51, R.id.eswimmer_spinner52, R.id.eswimmer_spinner53, R.id.eswimmer_spinner54, R.id.eswimmer_spinner55, R.id.eswimmer_spinner56, R.id.eswimmer_spinner57, R.id.eswimmer_spinner58, R.id.eswimmer_spinner59, R.id.eswimmer_spinner60};
			//int[] timeMinIds = new int[] {R.id.erace_time_min1, R.id.erace_time_min2, R.id.erace_time_min3, R.id.erace_time_min4, R.id.erace_time_min5, R.id.erace_time_min6, R.id.erace_time_min7, R.id.erace_time_min8, R.id.erace_time_min9, R.id.erace_time_min10, R.id.erace_time_min11, R.id.erace_time_min12, R.id.erace_time_min13, R.id.erace_time_min14, R.id.erace_time_min15, R.id.erace_time_min16, R.id.erace_time_min17, R.id.erace_time_min18, R.id.erace_time_min19, R.id.erace_time_min20, R.id.erace_time_min21, R.id.erace_time_min22, R.id.erace_time_min23, R.id.erace_time_min24, R.id.erace_time_min25, R.id.erace_time_min26, R.id.erace_time_min27, R.id.erace_time_min28, R.id.erace_time_min29, R.id.erace_time_min30, R.id.erace_time_min31, R.id.erace_time_min32, R.id.erace_time_min33, R.id.erace_time_min34, R.id.erace_time_min35, R.id.erace_time_min36, R.id.erace_time_min37, R.id.erace_time_min38, R.id.erace_time_min39, R.id.erace_time_min40, R.id.erace_time_min41, R.id.erace_time_min42, R.id.erace_time_min43, R.id.erace_time_min44, R.id.erace_time_min45, R.id.erace_time_min46, R.id.erace_time_min47, R.id.erace_time_min48, R.id.erace_time_min49, R.id.erace_time_min50, R.id.erace_time_min51, R.id.erace_time_min52, R.id.erace_time_min53, R.id.erace_time_min54, R.id.erace_time_min55, R.id.erace_time_min56, R.id.erace_time_min57, R.id.erace_time_min58, R.id.erace_time_min59, R.id.erace_time_min60};
			//int[] timeSecIds = new int[] {R.id.erace_time_sec1, R.id.erace_time_sec2, R.id.erace_time_sec3, R.id.erace_time_sec4, R.id.erace_time_sec5, R.id.erace_time_sec6, R.id.erace_time_sec7, R.id.erace_time_sec8, R.id.erace_time_sec9, R.id.erace_time_sec10, R.id.erace_time_sec11, R.id.erace_time_sec12, R.id.erace_time_sec13, R.id.erace_time_sec14, R.id.erace_time_sec15, R.id.erace_time_sec16, R.id.erace_time_sec17, R.id.erace_time_sec18, R.id.erace_time_sec19, R.id.erace_time_sec20, R.id.erace_time_sec21, R.id.erace_time_sec22, R.id.erace_time_sec23, R.id.erace_time_sec24, R.id.erace_time_sec25, R.id.erace_time_sec26, R.id.erace_time_sec27, R.id.erace_time_sec28, R.id.erace_time_sec29, R.id.erace_time_sec30, R.id.erace_time_sec31, R.id.erace_time_sec32, R.id.erace_time_sec33, R.id.erace_time_sec34, R.id.erace_time_sec35, R.id.erace_time_sec36, R.id.erace_time_sec37, R.id.erace_time_sec38, R.id.erace_time_sec39, R.id.erace_time_sec40, R.id.erace_time_sec41, R.id.erace_time_sec42, R.id.erace_time_sec43, R.id.erace_time_sec44, R.id.erace_time_sec45, R.id.erace_time_sec46, R.id.erace_time_sec47, R.id.erace_time_sec48, R.id.erace_time_sec49, R.id.erace_time_sec50, R.id.erace_time_sec51, R.id.erace_time_sec52, R.id.erace_time_sec53, R.id.erace_time_sec54, R.id.erace_time_sec55, R.id.erace_time_sec56, R.id.erace_time_sec57, R.id.erace_time_sec58, R.id.erace_time_sec59, R.id.erace_time_sec60};
			ResultHandler re = new ResultHandler(this);
			List<Result> resList = re.findByRace(race_id);
			List<String> oldTimeMinList = new ArrayList<String>();
			List<String> oldTimeSecList = new ArrayList<String>();
			List<Integer> oldPositionList = new ArrayList<Integer>();
			List<Integer> resIDList = new ArrayList<Integer>();
			// gets list of previous times and results database ids
			for (Result rslt : resList) {
				int swimmer_time_min = rslt.getTimeMin();
				int swimmer_time_sec = rslt.getTimeSec();
				int res_id = rslt.getResultID();
				resIDList.add(res_id);
				String time_min = Integer.toString(swimmer_time_min);
				String time_sec = Integer.toString(swimmer_time_sec);
				oldTimeMinList.add(time_min);
				oldTimeSecList.add(time_sec);
				int pos = rslt.getPosition();
				oldPositionList.add(pos);
			}
			if ( swimmer_no < resList.size() ) {
				for (int k = swimmer_no; k < resList.size(); k = k + 1) {
					int res_id = resIDList.get(k);
					re.deleteResult(res_id);
				}
			}
			int flag = 0;
			for (int j = 0; j < swimmer_no; j = j + 1) {
				if ( flag==1 ) {
					break;
				}
				// gets time
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
				// if this is a new entry
				if ( j >= oldTimeMinList.size()) {
					if ( Time_min.isEmpty() || Time_sec.isEmpty()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setTitle("Missing Time");
						builder.setMessage("Please Fill All Times");
						builder.setPositiveButton("OK", null);
						AlertDialog dialog = builder.show();
						flag = 1;
					} else {
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
				} else {
					if ( Time_min.isEmpty()) {
					} else {
					if ( notNumeric(Time_min)) {
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setTitle("Text in Time Field");
						builder.setMessage("Please only use numbers for times");
						builder.setPositiveButton("OK", null);
						AlertDialog dialog = builder.show();
						flag = 1;
					} else {
						double time_min = Double.parseDouble(Time_min);
						if ( time_min < 0 || time_min > 10000 ) {
							AlertDialog.Builder builder = new AlertDialog.Builder(this);
							builder.setTitle("Invalid Time");
							builder.setMessage("Please enter a valid time");
							builder.setPositiveButton("OK", null);
							AlertDialog dialog = builder.show();
							flag = 1;
						} else {}
					}
				}
					if (Time_sec.isEmpty()) {
					} else {
					if (notNumeric(Time_sec)) {
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setTitle("Text in Time Field");
						builder.setMessage("Please only use numbers for times");
						builder.setPositiveButton("OK", null);
						AlertDialog dialog = builder.show();
						flag = 1;
					} else {
						double time_sec = Double.parseDouble(Time_sec);
						if (time_sec < 0 || time_sec >= 60) {
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
			}
			if ( flag == 0 ) {
				for (int j = 0; j < swimmer_no; j = j + 1) {
					// gets swimmer id
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
					// if this is a new entry
					if ( j >= oldTimeMinList.size() ) {
						// add new database entry
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
						long ID = db.addResult(new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_sec, 0));
					} else {
						int pos = oldPositionList.get(j);
						// if this is an old entry being updated
						if (Time_min.isEmpty()) {
							// if time not changed use old time
							Time_min = oldTimeMinList.get(j);
							int time_min = Integer.parseInt(Time_min);
							if (Time_sec.isEmpty()) {
								Time_sec = oldTimeSecList.get(j);
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
								int reid = resIDList.get(j);
								db.updateResult(reid, new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_sec, pos));
							} else {
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
								int reid = resIDList.get(j);
								db.updateResult(reid, new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_sec, pos));
							}
						} else {
							if (Time_sec.isEmpty()) {
								int time_min = Integer.parseInt(Time_min);
								Time_sec = oldTimeSecList.get(j);
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
								int reid = resIDList.get(j);
								db.updateResult(reid, new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_sec, pos));
							} else {
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
								int reid = resIDList.get(j);
								db.updateResult(reid, new Result(race_id, swimmer_id, time_min, time_sec, hc_time_min, hc_time_sec, pos));
							}
						}	
					}
				}
				Intent intent2 = new Intent(this, FinalRaceActivity.class);
				intent2.putExtra("id", race_id);
				startActivity(intent2);
			}
		}

	}
