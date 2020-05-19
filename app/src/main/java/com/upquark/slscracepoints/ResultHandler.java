package com.upquark.slscracepoints;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultHandler extends SQLiteOpenHelper {
	
	// all static variable
	// database version
	private static final int DATABASE_VERSION = 3;
	// database name
	private static final String DATABASE_NAME = "resultManager";
	// results table name
	private static final String TABLE_RESULTS = "results";
	// results table columns names
	private static final String KEY_RESULT_ID = "result_id";
	private static final String KEY_RACE_ID = "race_id";
	private static final String KEY_SWIMMER_ID = "swimmer_id";
	private static final String KEY_TIME_MIN = "time_min";
	private static final String KEY_TIME_SEC = "time_sec";
	private static final String KEY_HC_TIME_MIN = "hc_time_min";
	private static final String KEY_HC_TIME_SEC = "hc_time_sec";
	private static final String KEY_POSITION = "pos";
	
	private SQLiteDatabase mDb;
	private ResultHandler mDbHelper;
	
	public ResultHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_RACE_TABLE = "CREATE TABLE " + TABLE_RESULTS + "(" + KEY_RESULT_ID + " INTEGER PRIMARY KEY," + KEY_RACE_ID + " INTEGER," + KEY_SWIMMER_ID +" INTEGER," + KEY_TIME_MIN +" INTEGER," + KEY_TIME_SEC + " INTEGER," + KEY_HC_TIME_MIN + " INTEGER," + KEY_HC_TIME_SEC + " INTEGER," + KEY_POSITION + " INTEGER" + ")";
		db.execSQL(CREATE_RACE_TABLE);
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
		// create tables again
		onCreate(db);
	}
	
	// Adding new result
	public long addResult(Result result) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_RACE_ID, result.getRaceID());
		values.put(KEY_SWIMMER_ID, result.getSwimmerID());
		values.put(KEY_TIME_MIN, result.getTimeMin());
		values.put(KEY_TIME_SEC, result.getTimeSec());
		values.put(KEY_HC_TIME_MIN, result.getHCTimeMin());
		values.put(KEY_HC_TIME_SEC, result.getHCTimeSec());
		values.put(KEY_POSITION, result.getPosition());
		// inserting row
		long id = db.insert(TABLE_RESULTS, null, values);
		db.close(); // Closing database connection
		return id;
	}
	
	// Getting single result
	public Result getResult(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_RESULTS, new String[] { KEY_RESULT_ID,
	            KEY_RACE_ID, KEY_SWIMMER_ID, KEY_TIME_MIN, KEY_TIME_SEC, KEY_HC_TIME_MIN, KEY_HC_TIME_SEC, KEY_POSITION}, KEY_RESULT_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Result result = new Result(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)));
		// return result
		return result;
	}
	
	// Getting all results
	public List<Result> getAllResults() {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_TIME_MIN + ", " + KEY_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				// Adding result to list
				resultList.add(result);
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}
	
	// Getting all results post handicap
	public List<Result> getAllResultsHC() {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_HC_TIME_MIN + ", " + KEY_HC_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				// Adding result to list
				resultList.add(result);
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}
	
	// Getting result count
	public int getResultCount() {
		String countQuery = "SELECT * FROM " + TABLE_RESULTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		//return count
		return cursor.getCount();
	}
	
	// Updating single result
	public boolean updateResult(int id, Result result) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_RACE_ID, result.getRaceID());
		values.put(KEY_SWIMMER_ID, result.getSwimmerID());
		values.put(KEY_TIME_MIN, result.getTimeMin());
		values.put(KEY_TIME_SEC, result.getTimeSec());
		values.put(KEY_HC_TIME_MIN, result.getHCTimeMin());
		values.put(KEY_HC_TIME_SEC, result.getHCTimeSec());
		values.put(KEY_POSITION, result.getPosition());
		// updating row
		return db.update(TABLE_RESULTS, values, KEY_RESULT_ID + " = " + id, null) > 0;
	}
	
	// Deleting single result
	public boolean deleteResult(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.delete(TABLE_RESULTS, KEY_RESULT_ID + " = " + id, null) > 0;
	}
	
	// Find all results for a race
	public List<Result> findByRace(int id) {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_TIME_MIN + ", " + KEY_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				int race_id = result.getRaceID();
				if (race_id == id) {
				// Adding result to list
				resultList.add(result);
				}
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}
	
	// Find all results for a race post handicap
	public List<Result> findByRaceHC(int id) {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_HC_TIME_MIN + ", " + KEY_HC_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				int race_id = result.getRaceID();
				if (race_id == id) {
				// Adding result to list
				resultList.add(result);
				}
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}
	
	// Find all results for a swimmer
	public List<Result> findBySwimmer(int id) {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_TIME_MIN + ", " + KEY_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				int swimmer_id = result.getSwimmerID();
				if (swimmer_id == id) {
				// Adding result to list
				resultList.add(result);
				}
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}
	
	// Find all results for a swimmer post handicap
	public List<Result> findBySwimmerHC(int id) {
		List<Result> resultList = new ArrayList<Result>();
		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " ORDER BY " + KEY_HC_TIME_MIN + ", " + KEY_HC_TIME_SEC;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Result result = new Result();
				result.setResultID(Integer.parseInt(cursor.getString(0)));
				result.setRaceID(Integer.parseInt(cursor.getString(1)));
				result.setSwimmerID(Integer.parseInt(cursor.getString(2)));
				result.setTimeMin(Integer.parseInt(cursor.getString(3)));
				result.setTimeSec(Integer.parseInt(cursor.getString(4)));
				result.setHCTimeMin(Integer.parseInt(cursor.getString(5)));
				result.setHCTimeSec(Integer.parseInt(cursor.getString(6)));
				result.setPosition(Integer.parseInt(cursor.getString(7)));
				int swimmer_id = result.getSwimmerID();
				if (swimmer_id == id) {
				// Adding result to list
				resultList.add(result);
				}
			} while (cursor.moveToNext());
		}
		// return result list
		return resultList;
	}

}