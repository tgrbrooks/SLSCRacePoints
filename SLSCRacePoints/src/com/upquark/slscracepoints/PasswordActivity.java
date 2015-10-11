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
import android.widget.EditText;

public class PasswordActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);

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
			View rootView = inflater.inflate(R.layout.fragment_password,
					container, false);
			return rootView;
		}
	}
	/** Called when user clicks Submit button*/
	public void enterPassword(View view) {
		// creates new intent
		Intent intent = new Intent(this,EnterResultsActivity.class);
		// gets password attempt
		EditText editText = (EditText) findViewById(R.id.password_attempt);
		String password = editText.getText().toString();
		editText.getEditableText().toString();
		// compares attempt to correct password
		if (password.equals("admin")) {
			// if password is correct, start enter results activity
			startActivity(intent);
		}else{
			// if password is incorrect show error message
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Incorrect Password");
			builder.setMessage("Try Again");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();
		}
	}

}
