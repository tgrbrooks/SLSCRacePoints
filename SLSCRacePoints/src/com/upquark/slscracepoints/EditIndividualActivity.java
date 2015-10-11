package com.upquark.slscracepoints;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditIndividualActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_edit_individual);

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
		String handi_min = Integer.toString(handicap_min);
		int handicap_sec = swimmer.getHandicapSec();
		String handi_sec = Integer.toString(handicap_sec);
		String sex = swimmer.getSex();
		
		// set the EditText hints as previous details
		final EditText surnameHint = (EditText) findViewById(R.id.edit_surname);
		surnameHint.setHint(surname);
		final EditText forenameHint = (EditText) findViewById(R.id.edit_forename);
		forenameHint.setHint(forename);
		final EditText dobDayHint = (EditText) findViewById(R.id.edit_dob_day);
		dobDayHint.setHint(day);
		final EditText dobMonthHint = (EditText) findViewById(R.id.edit_dob_month);
		dobMonthHint.setHint(month);
		final EditText dobYearHint = (EditText) findViewById(R.id.edit_dob_year);
		dobYearHint.setHint(year);
		final EditText handicapMinHint = (EditText) findViewById(R.id.edit_handicap_min);
		handicapMinHint.setHint(handi_min);
		final EditText handicapSecHint = (EditText) findViewById(R.id.edit_handicap_sec);
		handicapSecHint.setHint(handi_sec);
		final EditText sexHint = (EditText) findViewById(R.id.edit_sex);
		sexHint.setHint(sex);
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
	public void editSwimmer(View view) {
		// old information
		// Get the id from the intent
		Intent intent1 = getIntent();
		int id = intent1.getIntExtra("id", 0);
		// Retrieve info from database and convert to strings
		DatabaseHandler db = new DatabaseHandler(this);
		Swimmer swimmer = db.getSwimmer(id);
		String oldSurname = swimmer.getSurname();
		String oldForename = swimmer.getForename();
		int oldDob_day = swimmer.getDayDOB();
		String oldDay = Integer.toString(oldDob_day);
		int oldDob_month = swimmer.getMonthDOB();
		String oldMonth = Integer.toString(oldDob_month);
		int oldDob_year = swimmer.getYearDOB();
		String oldYear = Integer.toString(oldDob_year);
		int oldHandicap_min = swimmer.getHandicapMin();
		String oldHandi_min = Integer.toString(oldHandicap_min);
		int oldHandicap_sec = swimmer.getHandicapSec();
		String oldHandi_sec = Integer.toString(oldHandicap_sec);
		String oldSex = swimmer.getSex();
		// gets surname
	    EditText editText = (EditText) findViewById(R.id.edit_surname);
		String surname = editText.getText().toString();
		editText.getEditableText().toString();
		// gets forename
	    EditText editText1 = (EditText) findViewById(R.id.edit_forename);
		String forename = editText1.getText().toString();
		editText1.getEditableText().toString();
		// gets day dob
	    EditText editText2 = (EditText) findViewById(R.id.edit_dob_day);
		String dob_day = editText2.getText().toString();
		editText2.getEditableText().toString();
		// gets month dob
	    EditText editText3 = (EditText) findViewById(R.id.edit_dob_month);
		String dob_month = editText3.getText().toString();
		editText3.getEditableText().toString();
		// gets year dob
	    EditText editText4 = (EditText) findViewById(R.id.edit_dob_year);
		String dob_year = editText4.getText().toString();
		editText4.getEditableText().toString();
		// gets handicap min
	    EditText editText5 = (EditText) findViewById(R.id.edit_handicap_min);
		String handicap_min = editText5.getText().toString();
		editText5.getEditableText().toString();
		// gets handicap sec
	    EditText editText6 = (EditText) findViewById(R.id.edit_handicap_sec);
		String handicap_sec = editText6.getText().toString();
		editText6.getEditableText().toString();
		// gets sex
	    EditText editText7 = (EditText) findViewById(R.id.edit_sex);
		String sex = editText7.getText().toString();
		editText7.getEditableText().toString();
		String m = "m";
		String f = "f";
		// if fields are not changed old information is used
		if (surname.isEmpty()) {surname = oldSurname;} else{}
		if (forename.isEmpty()) {forename = oldForename;}else{}
		if (dob_day.isEmpty()) {dob_day = oldDay;}else{}
		if (dob_month.isEmpty()) {dob_month = oldMonth;}else{}
		if (dob_year.isEmpty()) {dob_year = oldYear;}else{}
		if (handicap_min.isEmpty()) {handicap_min = oldHandi_min;}else{}
		if (handicap_sec.isEmpty()) {handicap_sec = oldHandi_sec;}else{}
		if (sex.isEmpty()) {sex = oldSex;} else{}
		if ( notNumeric(dob_day) || notNumeric(dob_month) || notNumeric(dob_year) || notNumeric(handicap_min) || notNumeric(handicap_sec)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Text in Number Field");
			builder.setMessage("Please only use numbers for dates and handicaps");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();
		} else {
			// converts stings to doubles and integers for comparison and entering
			double day = Double.parseDouble(dob_day);
			int dobday = Integer.parseInt(dob_day);
			double month = Double.parseDouble(dob_month);
			int dobmonth = Integer.parseInt(dob_month);
			double year = Double.parseDouble(dob_year);
			int dobyear = Integer.parseInt(dob_year);
			double hc_min = Double.parseDouble(handicap_min);
			int hand_min = Integer.parseInt(handicap_min);
			double hc_sec = Double.parseDouble(handicap_sec);
			int hand_sec = Integer.parseInt(handicap_sec);
			if (day > 31 || day < 0 || month < 0 || month > 12 || year < 1914) {
				// shows error if date not entered correctly
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Incorrect Date");
				builder.setMessage("Please Enter a Valid Date");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();	
			} else {
				if ( hc_min < 0 || hc_min > 100 || hc_sec < 0 || hc_sec >= 60) {
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle("Incorrect Handicap");
					builder.setMessage("Please Enter a Valid Handicap");
					builder.setPositiveButton("OK", null);
					AlertDialog dialog = builder.show();
				} else {
			if (sex.equals(m) || sex.equals(f)) {
				// update all handicap results
				ResultHandler rh = new ResultHandler(this);
				List<Result> resultList = rh.findBySwimmer(id);
				for (Result res : resultList) {
					int result_id = res.getResultID();
					int time_min = res.getTimeMin();
					int time_sec = res.getTimeSec();
					int pos = res.getPosition();
					int race_id = res.getRaceID();
					RaceHandler rch = new RaceHandler(this);
					Race rce = rch.getRace(race_id);
					double race_length = rce.getLength();
					double final_time = time_sec + time_min*60 + ((hand_sec + hand_min*60)*race_length)/100;
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
					rh.updateResult(result_id, new Result(race_id, id, time_min, time_sec, hc_time_min, hc_time_sec, pos));
				}
				// add swimmer to swimmer list
				DatabaseHandler db1 = new DatabaseHandler(this);
				db1.updateSwimmer(id, new Swimmer(surname, forename, dobday, dobmonth, dobyear, hand_min, hand_sec, sex));
				Intent intent = new Intent(this,EditSwimmer.class);
				startActivity(intent);				
			} else{
				// shows error if sex not entered correctly
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Incorrect Sex");
				builder.setMessage("Please Enter m or f in lowercase");
				builder.setPositiveButton("OK", null);
				AlertDialog dialog = builder.show();						
			}
		}
			}
		}
	}
	
	/** Called when user clicks delete button*/
	public void delete(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Delete");
		builder.setMessage("Are you sure you want to delete this entry");
		builder.setPositiveButton("No", null);
		// deletes entry from database and goes back to edit swimmer page
		builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Get the id from the intent
				Intent intent1 = getIntent();
				int id = intent1.getIntExtra("id", 0);
				DatabaseHandler db1 = new DatabaseHandler(getApplicationContext());
				db1.deleteSwimmer(id);
				ResultHandler rh = new ResultHandler(getApplicationContext());
				// get list of races from database
				List<Result> resultList = rh.findBySwimmer(id);
				for (Result res : resultList) {
					int result_id = res.getResultID();
					rh.deleteResult(result_id);
				}
				Intent intent = new Intent(getApplicationContext(), EditSwimmer.class);
				startActivity(intent);
			}
		});
		AlertDialog dialog = builder.show();	
	}

}
