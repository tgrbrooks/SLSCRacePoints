package com.upquark.slscracepoints;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class EnterResultsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_results);

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
			View rootView = inflater.inflate(R.layout.fragment_enter_results,
					container, false);
			return rootView;
		}
	}
	
	/** Called when user clicks new race button*/
	public void goNewRaceScreen(View view){
		// starts new race activity
		 Intent intent = new Intent(this, NewRace.class);
		 startActivity(intent);
	}
	/** Called when user clicks edit race button*/
	public void goEditRaceScreen(View view){
		// starts edit race activity
		 Intent intent = new Intent(this, EditRace.class);
		 startActivity(intent);
	}
	/** Called when user clicks new swimmer button*/
	public void goNewSwimmerScreen(View view){
		// starts new swimmer activity
		 Intent intent = new Intent(this, NewSwimmer.class);
		 startActivity(intent);
	}
	/** Called when user clicks edit swimmer button*/
	public void goEditSwimmerScreen(View view){
		// starts edit swimmer activity
		 Intent intent = new Intent(this, EditSwimmer.class);
		 startActivity(intent);
	}

}
