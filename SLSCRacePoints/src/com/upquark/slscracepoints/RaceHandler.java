package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RaceHandler extends SQLiteOpenHelper {
	
	// all static variable
	// database version
	private static final int DATABASE_VERSION = 2;
	// database name
	private static final String DATABASE_NAME = "raceManager";
	// swimmers table name
	private static final String TABLE_RACES = "races";
	// swimmers table columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_RACE_DAY = "race_day";
	private static final String KEY_RACE_MONTH = "race_month";
	private static final String KEY_RACE_YEAR = "race_year";
	private static final String KEY_LENGTH = "race_length";
	private static final String KEY_NUMBER = "swimmer_number";
	
	private SQLiteDatabase mDb;
	private RaceHandler mDbHelper;
	
	public RaceHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_RACE_TABLE = "CREATE TABLE " + TABLE_RACES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_RACE_DAY +" INTEGER," + KEY_RACE_MONTH +" INTEGER," + KEY_RACE_YEAR +" INTEGER," + KEY_LENGTH + " DOUBLE," + KEY_NUMBER + " INTEGER" + ")";
		db.execSQL(CREATE_RACE_TABLE);
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RACES);
		// create tables again
		onCreate(db);
	}
	
	// Adding new race
	public long addRace(Race race) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, race.getName());
		values.put(KEY_RACE_DAY, race.getDay());
		values.put(KEY_RACE_MONTH, race.getMonth());
		values.put(KEY_RACE_YEAR, race.getYear());
		values.put(KEY_LENGTH, race.getLength());
		values.put(KEY_NUMBER, race.getNumber());
		// inserting row
		long id = db.insert(TABLE_RACES, null, values);
		db.close(); // Closing database connection
		return id;
	}
	
	// Getting single race
	public Race getRace(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_RACES, new String[] { KEY_ID,
	            KEY_NAME, KEY_RACE_DAY, KEY_RACE_MONTH, KEY_RACE_YEAR, KEY_LENGTH, KEY_NUMBER }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Race race = new Race(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Double.parseDouble(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
		// return race
		return race;
	}
	
	// Getting all races
	public List<Race> getAllRaces() {
		List<Race> raceList = new ArrayList<Race>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RACES + " ORDER BY " + KEY_RACE_YEAR + ", " + KEY_RACE_MONTH + ", " + KEY_RACE_DAY;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Race race = new Race();
				race.setID(Integer.parseInt(cursor.getString(0)));
				race.setName(cursor.getString(1));
				race.setDay(Integer.parseInt(cursor.getString(2)));
				race.setMonth(Integer.parseInt(cursor.getString(3)));
				race.setYear(Integer.parseInt(cursor.getString(4)));
				race.setLength(Double.parseDouble(cursor.getString(5)));
				race.setNumber(Integer.parseInt(cursor.getString(6)));
				// Adding race to list
				raceList.add(race);
			} while (cursor.moveToNext());
		}
		// return race list
		return raceList;
	}
	
	// Getting race count
	public int getRaceCount() {
		String countQuery = "SELECT * FROM " + TABLE_RACES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		//return count
		return cursor.getCount();
	}
	
	// Updating single race
	public boolean updateRace(int id, Race race) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, race.getName());
		values.put(KEY_RACE_DAY, race.getDay());
		values.put(KEY_RACE_MONTH, race.getMonth());
		values.put(KEY_RACE_YEAR, race.getYear());
		values.put(KEY_LENGTH, race.getLength());
		values.put(KEY_NUMBER, race.getNumber());
		// updating row
		return db.update(TABLE_RACES, values, KEY_ID + " = " + id, null) > 0;
	}
	
	// Deleting single race
	public boolean deleteRace(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.delete(TABLE_RACES, KEY_ID + " = " + id, null) > 0;//new String[] { String.valueOf(swimmer.getID()) });
		//db.close();
	}

}
