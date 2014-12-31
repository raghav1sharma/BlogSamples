package com.example.sqlitedatabaseblog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MYDB";
	private static final String TABLE = "Person";

	public SqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createCommand = "create table " + TABLE + " (PersonId Integer primary key, Name varchar(20), City varchar(10))";
		db.execSQL(createCommand);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropTableCommand = "drop table if exists " + TABLE;
		db.execSQL(dropTableCommand);
		onCreate(db);		
	}
	
	public static String getTableName() {
		return TABLE;
	}

}
