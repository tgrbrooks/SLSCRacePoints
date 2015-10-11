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

public class ResultListAdapter extends ArrayAdapter<ResultList> {

	public ResultListAdapter(Context context, List<ResultList> results) {
		super(context, R.layout.result_list, results);
	}
	
	private int green = Color.parseColor("#009D28");
	private int yellow = Color.parseColor("#FFD700");
	private int blue = Color.parseColor("#63B8FF");
	private int red = Color.parseColor("#EE0000");

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResultList result = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_list, parent, false);
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
		TextView pos = (TextView) convertView.findViewById(R.id.list_pos);
		TextView name = (TextView) convertView.findViewById(R.id.list_sw_name);
		TextView time = (TextView) convertView.findViewById(R.id.list_time);
		TextView hc_time = (TextView) convertView.findViewById(R.id.list_hc_time);
		pos.setText(Integer.toString(result.getPosition()));
		name.setText(result.getName());
		time.setText(result.getTime());
		hc_time.setText(result.getHCTime());
		return convertView;
	}
}
