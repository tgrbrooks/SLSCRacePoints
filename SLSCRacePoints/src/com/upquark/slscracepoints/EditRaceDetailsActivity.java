package com.upquark.slscracepoints;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class EditRaceDetailsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_edit_race_details);

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
		double len = race.getLength();
		String lngth = Double.toString(len);
		int swimmer_no = race.getNumber();
		String swimno = Integer.toString(swimmer_no);
		
		// set the EditText hints as previous details
		final EditText nameHint = (EditText) findViewById(R.id.edit_race_name);
		nameHint.setHint(name);
		final EditText DayHint = (EditText) findViewById(R.id.edit_day);
		DayHint.setHint(day);
		final EditText MonthHint = (EditText) findViewById(R.id.edit_month);
		MonthHint.setHint(month);
		final EditText YearHint = (EditText) findViewById(R.id.edit_year);
		YearHint.setHint(year);
		final EditText lengthHint = (EditText) findViewById(R.id.edit_no_yards);
		lengthHint.setHint(lngth);
		final EditText numberHint = (EditText) findViewById(R.id.edit_no_swimmers);
		numberHint.setHint(swimno);
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

	/** Called when user clicks submit button*/
	public void editRace(View view) {
		// old information
		// Get the id from the intent
		Intent intent1 = getIntent();
		int id = intent1.getIntExtra("id", 0);
		// Retrieve info from database and convert to strings
		RaceHandler db = new RaceHandler(this);
		Race race = db.getRace(id);
		String oldName = race.getName();
		int old_day = race.getDay();
		String oldDay = Integer.toString(old_day);
		int old_month = race.getMonth();
		String oldMonth = Integer.toString(old_month);
		int old_year = race.getYear();
		String oldYear = Integer.toString(old_year);
		double oldLength = race.getLength();
		String oldLen = Double.toString(oldLength);
		int oldNumber = race.getNumber();
		String oldNum = Integer.toString(oldNumber);
		// gets name
	    EditText editText = (EditText) findViewById(R.id.edit_race_name);
		String name = editText.getText().toString();
		editText.getEditableText().toString();
		// gets day
	    EditText editText2 = (EditText) findViewById(R.id.edit_day);
		String day = editText2.getText().toString();
		editText2.getEditableText().toString();
		// gets month
	    EditText editText3 = (EditText) findViewById(R.id.edit_month);
		String month = editText3.getText().toString();
		editText3.getEditableText().toString();
		// gets year
	    EditText editText4 = (EditText) findViewById(R.id.edit_year);
		String year = editText4.getText().toString();
		editText4.getEditableText().toString();
		// gets length
	    EditText editText5 = (EditText) findViewById(R.id.edit_no_yards);
		String len = editText5.getText().toString();
		editText5.getEditableText().toString();
		// gets swimmer no
	    EditText editText6 = (EditText) findViewById(R.id.edit_no_swimmers);
		String num = editText6.getText().toString();
		editText6.getEditableText().toString();
		// if fields are not changed old information is used
		if (name.isEmpty()) {name = oldName;} else{}
		if (day.isEmpty()) {day = oldDay;}else{}
		if (month.isEmpty()) {month = oldMonth;}else{}
		if (year.isEmpty()) {year = oldYear;}else{}
		if (len.isEmpty()) {len = oldLen;}else{}
		if (num.isEmpty()) {num = oldNum;} else{}	
		if( notNumeric(day) || notNumeric(month) || notNumeric(year) || notNumeric(len) || notNumeric(num)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Text in Number Fields");
			builder.setMessage("Please use only numbers for dates, lengths and numbers of swimmers");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();
		} else {
			// converts stings to doubles and integers for comparison and entering
			double race_day = Double.parseDouble(day);
			int rday = Integer.parseInt(day);
			double race_month = Double.parseDouble(month);
			int rmonth = Integer.parseInt(month);
			double race_year = Double.parseDouble(year);
			int ryear = Integer.parseInt(year);
			double lngth = Double.parseDouble(len);
			int swimno = Integer.parseInt(num);
			double swim = Double.parseDouble(num);
			if (race_day > 31 || race_day < 0 || race_month < 0 || race_month > 12 || race_year < 1914 || race_year > 3000) {
				// shows error if date not entered correctly
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Incorrect Date");
				builder.setMessage("Please Enter a Valid Date");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();	
			} else {
			if (lngth < 0 || lngth > 100000) {
				// shows error if length not entered correctly
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Incorrect Length");
				builder.setMessage("Please Enter a Valid Length");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();					
			} else{
				if (swim < 0 || swim > 60) {
					// shows error if number of swimmers not entered correctly
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle("Incorrect Number of Swimmers");
					builder.setMessage("Please Enter a Valid Number of Swimmers < 60");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();	
				} else{
					// add race to race list and moves to entering times page
					RaceHandler db1 = new RaceHandler(this);
					db1.updateRace(id, new Race(name, rday, rmonth, ryear, lngth, swimno));	
					Intent intent = new Intent(this,EditResultsActivity.class);
					// Sends database id to new activity
					intent.putExtra("id", id);
					startActivity(intent);
				}
			}
		}
		}
	}
	
	/** Called when user clicks delete button */
	public void deleteRace(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Delete");
		builder.setMessage("Are you sure you want to delete this race");
		builder.setPositiveButton("No", null);
		// deletes entry from database and goes back to edit race page
		builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Get the id from the intent
				Intent intent1 = getIntent();
				int id = intent1.getIntExtra("id", 0);
				RaceHandler db1 = new RaceHandler(getApplicationContext());
				db1.deleteRace(id);
				ResultHandler rh = new ResultHandler(getApplicationContext());
				// deletes all results associated with race
				List<Result> resultList = rh.findByRace(id);
				for (Result res : resultList) {
					int result_id = res.getResultID();
					rh.deleteResult(result_id);
				}
				Intent intent = new Intent(getApplicationContext(), EditRace.class);
				startActivity(intent);
			}
		});
		AlertDialog dialog = builder.show();	
	}

}
