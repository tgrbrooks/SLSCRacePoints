package com.upquark.slscracepoints;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewRace extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_race);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_new_race,
					container, false);
			return rootView;
		}
	}
	
	public static boolean notNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return true;
		}
		return false;
	}
	
	/** Called when user clicks continue button*/
	public void enterNewRace(View view) {
		// gets name
	    EditText editText = (EditText) findViewById(R.id.race_name);
		String name = editText.getText().toString();
		editText.getEditableText().toString();
		// gets day
	    EditText editText2 = (EditText) findViewById(R.id.day);
		String day = editText2.getText().toString();
		editText2.getEditableText().toString();
		// gets month
	    EditText editText3 = (EditText) findViewById(R.id.month);
		String month = editText3.getText().toString();
		editText3.getEditableText().toString();
		// gets year dob
	    EditText editText4 = (EditText) findViewById(R.id.year);
		String year = editText4.getText().toString();
		editText4.getEditableText().toString();
		// gets length
	    EditText editText5 = (EditText) findViewById(R.id.no_yards);
		String race_length = editText5.getText().toString();
		editText5.getEditableText().toString();
		// gets number of swimmers
	    EditText editText6 = (EditText) findViewById(R.id.no_swimmers);
		String no_swimmers = editText6.getText().toString();
		editText6.getEditableText().toString();
		// shows error if field missing
		if (name.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty() || race_length.isEmpty() || no_swimmers.isEmpty() ) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Missing Field");
			builder.setMessage("Please Fill All Fields");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();			
		} else { 
			if( notNumeric(day) || notNumeric(month) || notNumeric(year) || notNumeric(race_length) || notNumeric(no_swimmers)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Text in Number Fields");
				builder.setMessage("Please use only numbers for dates, lengths and numbers of swimmers");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();
			} else {
			// converts stings to doubles and integers for comparison and entering
			double race_day = Double.parseDouble(day);
			int raceday = Integer.parseInt(day);
			double race_month = Double.parseDouble(month);
			int racemonth = Integer.parseInt(month);
			double race_year = Double.parseDouble(year);
			int raceyear = Integer.parseInt(year);
			double len = Double.parseDouble(race_length);
			double swim = Double.parseDouble(no_swimmers);
			int swimno = Integer.parseInt(no_swimmers);
			if (race_day > 31 || race_day < 0 || race_month < 0 || race_month > 12 || race_year < 1914 || race_year > 3000) {
				// shows error if date not entered correctly
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Incorrect Date");
				builder.setMessage("Please Enter a Valid Date");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();	
			} else {
			if (len < 0 || len > 100000) {
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
					RaceHandler db = new RaceHandler(this);
					long ID = db.addRace(new Race(name, raceday, racemonth, raceyear, len, swimno));	
					Intent intent = new Intent(this,TimesActivity.class);
					// Sends database id to new activity
					int id = (int) ID;
					intent.putExtra("id", id);
					startActivity(intent);
				}
			}
		}
		}
		}
	}
	
}
