package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// all static variable
	// database version
	private static final int DATABASE_VERSION = 3;
	// database name
	private static final String DATABASE_NAME = "swimmersManager";
	// swimmers table name
	private static final String TABLE_SWIMMERS = "swimmers";
	// swimmers table columns names
	private static final String KEY_ID = "id";
	private static final String KEY_SURNAME = "surname";
	private static final String KEY_FORENAME = "forename";
	private static final String KEY_DOB_DAY = "dob_day";
	private static final String KEY_DOB_MONTH = "dob_month";
	private static final String KEY_DOB_YEAR = "dob_year";
	private static final String KEY_HANDICAP_MIN = "handicap_min";
	private static final String KEY_HANDICAP_SEC = "handicap_sec";
	private static final String KEY_SEX = "Sex";
	
	private SQLiteDatabase mDb;
	private DatabaseHandler mDbHelper;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_SWIMMER_TABLE = "CREATE TABLE " + TABLE_SWIMMERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SURNAME + " TEXT," + KEY_FORENAME + " TEXT," + KEY_DOB_DAY +" INTEGER," + KEY_DOB_MONTH +" INTEGER," + KEY_DOB_YEAR +" INTEGER," + KEY_HANDICAP_MIN + " INTEGER," + KEY_HANDICAP_SEC + " INTEGER," + KEY_SEX + " TEXT" + ")";
		db.execSQL(CREATE_SWIMMER_TABLE);
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWIMMERS);
		// create tables again
		onCreate(db);
	}
	
	// Adding new swimmer
	public void addSwimmer(Swimmer swimmer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SURNAME, swimmer.getSurname());
		values.put(KEY_FORENAME, swimmer.getForename());
		values.put(KEY_DOB_DAY, swimmer.getDayDOB());
		values.put(KEY_DOB_MONTH, swimmer.getMonthDOB());
		values.put(KEY_DOB_YEAR, swimmer.getYearDOB());
		values.put(KEY_HANDICAP_MIN, swimmer.getHandicapMin());
		values.put(KEY_HANDICAP_SEC, swimmer.getHandicapSec());
		values.put(KEY_SEX, swimmer.getSex());
		// inserting row
		db.insert(TABLE_SWIMMERS, null, values);
		db.close(); // Closing database connection
	}
	// Getting single swimmer
	public Swimmer getSwimmer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_SWIMMERS, new String[] { KEY_ID,
	            KEY_SURNAME, KEY_FORENAME, KEY_DOB_DAY, KEY_DOB_MONTH, KEY_DOB_YEAR, KEY_HANDICAP_MIN, KEY_HANDICAP_SEC, KEY_SEX }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Swimmer swimmer = new Swimmer(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), cursor.getString(8));
		// return swimmer
		return swimmer;
	}
	// Getting all swimmers
	public List<Swimmer> getAllSwimmers() {
		List<Swimmer> swimmerList = new ArrayList<Swimmer>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_SWIMMERS + " ORDER BY " + KEY_SURNAME + " COLLATE NOCASE ASC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Swimmer swimmer = new Swimmer();
				swimmer.setID(Integer.parseInt(cursor.getString(0)));
				swimmer.setSurname(cursor.getString(1));
				swimmer.setForename(cursor.getString(2));
				swimmer.setDayDOB(Integer.parseInt(cursor.getString(3)));
				swimmer.setMonthDOB(Integer.parseInt(cursor.getString(4)));
				swimmer.setYearDOB(Integer.parseInt(cursor.getString(5)));
				swimmer.setHandicapMin(Integer.parseInt(cursor.getString(6)));
				swimmer.setHandicapSec(Integer.parseInt(cursor.getString(7)));
				swimmer.setSex(cursor.getString(8));
				// Adding swimmer to list
				swimmerList.add(swimmer);
			} while (cursor.moveToNext());
		}
		// return swimmer list
		return swimmerList;
	}

	// Getting contacts count
	public int getContactsCount() {
		String countQuery = "SELECT * FROM " + TABLE_SWIMMERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		//return count
		return cursor.getCount();
	}
	
	// Updating single swimmer
	public boolean updateSwimmer(int id, Swimmer swimmer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SURNAME, swimmer.getSurname());
		values.put(KEY_FORENAME, swimmer.getForename());
		values.put(KEY_DOB_DAY, swimmer.getDayDOB());
		values.put(KEY_DOB_MONTH, swimmer.getMonthDOB());
		values.put(KEY_DOB_YEAR, swimmer.getYearDOB());
		values.put(KEY_HANDICAP_MIN, swimmer.getHandicapMin());
		values.put(KEY_HANDICAP_SEC, swimmer.getHandicapSec());
		values.put(KEY_SEX, swimmer.getSex());
		// updating row
		return db.update(TABLE_SWIMMERS, values, KEY_ID + " = " + id, null) > 0;
	}
	// Deleting single swimmer
	public boolean deleteSwimmer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.delete(TABLE_SWIMMERS, KEY_ID + " = " + id, null) > 0;
	}

}

