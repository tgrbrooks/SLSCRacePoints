package com.upquark.slscracepoints;

import java.util.Objects;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NewSwimmer extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_swimmer);

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
			View rootView = inflater.inflate(R.layout.fragment_new_swimmer,
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
	
	/** Called when user clicks submit button*/
	public void enterNewSwimmer(View view) {
		// gets surname
	    EditText editText = (EditText) findViewById(R.id.surname);
		String surname = editText.getText().toString();
		editText.getEditableText().toString();
		// gets forename
	    EditText editText1 = (EditText) findViewById(R.id.forename);
		String forename = editText1.getText().toString();
		editText1.getEditableText().toString();
		// gets day dob
	    EditText editText2 = (EditText) findViewById(R.id.dob_day);
		String dob_day = editText2.getText().toString();
		editText2.getEditableText().toString();
		// gets month dob
	    EditText editText3 = (EditText) findViewById(R.id.dob_month);
		String dob_month = editText3.getText().toString();
		editText3.getEditableText().toString();
		// gets year dob
	    EditText editText4 = (EditText) findViewById(R.id.dob_year);
		String dob_year = editText4.getText().toString();
		editText4.getEditableText().toString();
		// gets handicap min
	    EditText editText5 = (EditText) findViewById(R.id.handicap_min);
		String handicap_min = editText5.getText().toString();
		editText5.getEditableText().toString();
		// gets handicap sec
	    EditText editText6 = (EditText) findViewById(R.id.handicap_sec);
		String handicap_sec = editText6.getText().toString();
		editText6.getEditableText().toString();
		// gets sex
	    EditText editText7 = (EditText) findViewById(R.id.sex);
		String sex = editText7.getText().toString();
		editText7.getEditableText().toString();
		String m = "m";
		String f = "f";
		// shows error if date not entered correctly
		if (surname.isEmpty() || forename.isEmpty() || dob_day.isEmpty() || dob_month.isEmpty() || dob_year.isEmpty() || handicap_min.isEmpty() || handicap_sec.isEmpty() || sex.isEmpty() ) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Missing Field");
			builder.setMessage("Please Fill All Fields");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();			
		} else { 
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
				// add swimmer to swimmer list
				DatabaseHandler db = new DatabaseHandler(this);
				db.addSwimmer(new Swimmer(surname, forename, dobday, dobmonth, dobyear, hand_min, hand_sec, sex));
				Intent intent = new Intent(this,EnterResultsActivity.class);
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
	}

}
