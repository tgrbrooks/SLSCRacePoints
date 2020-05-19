package com.upquark.slscracepoints;

import java.util.List;

import com.upquark.slscracepoints.R.color;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyListAdapter<String> extends ArrayAdapter<String> {

	public MyListAdapter(Context context, int resource, int textViewResourceId,
			List<String> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	private int green = Color.parseColor("#009D28");
	private int yellow = Color.parseColor("#FFD700");
	private int blue = Color.parseColor("#63B8FF");
	private int red = Color.parseColor("#EE0000");



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
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
			view.setBackgroundColor(green);
		} else {
			if (j == 2 ) {
				view.setBackgroundColor(yellow);
			} else {
				if ( j == 3 ) {
					view.setBackgroundColor(blue);
				} else {
					if ( j == 4 ) {
						view.setBackgroundColor(red);
					}
				}
			}
		}
		return view;
	}
}
