package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RaceListAdapter extends ArrayAdapter<Race> {
	
	public RaceListAdapter(Context context, List<Race> races) {
		super(context, R.layout.race_list, races);
	}
	
	private int green = Color.parseColor("#009D28");
	private int yellow = Color.parseColor("#FFD700");
	private int blue = Color.parseColor("#63B8FF");
	private int red = Color.parseColor("#EE0000");

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Race race = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.race_list, parent, false);
		}
		//View view = super.getView(position, convertView, parent);
		int j = 0;
		for (int i = 0; i < position + 1; i = i + 1) {
			if ( j == 0 ) {
				j = j + 1;
			} else {
				if ( j == 1 ) {
					j = j + 1;
				} else {
					if ( j == 2 ) {
						j = j + 1;
					} else {
						if ( j == 3 ) {
							j = j + 1;
						} else {
							if ( j == 4 ) {
								j = 1;
							}
						}
					}
				}
			}
		}
		if (j == 1) {
			convertView.setBackgroundColor(green);
		} else {
			if (j == 2 ) {
				convertView.setBackgroundColor(yellow);
			} else {
				if ( j == 3 ) {
					convertView.setBackgroundColor(blue);
				} else {
					if ( j == 4 ) {
						convertView.setBackgroundColor(red);
					}
				}
			}
		}
		TextView name = (TextView) convertView.findViewById(R.id.race_content);
		TextView year = (TextView) convertView.findViewById(R.id.year_content);
		name.setText(race.getName());
		year.setText(Integer.toString(race.getYear()));
		return convertView;
	}
}