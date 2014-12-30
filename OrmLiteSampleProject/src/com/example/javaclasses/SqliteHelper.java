package com.example.javaclasses;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class SqliteHelper extends OrmLiteSqliteOpenHelper {

	 private static final String DATABASE_NAME = "ormlite.db";
	 private static final int DATABASE_VERSION = 1;
	 private static RuntimeExceptionDao<Person, Integer> runtimeExceptionPersonDao;

	 public SqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	 public static RuntimeExceptionDao<Person, Integer> getRuntimeExceptionPersonDao(Context context) {
		 if(runtimeExceptionPersonDao == null) {
			 SqliteHelper helper = new SqliteHelper(context);
			 runtimeExceptionPersonDao = helper.getRuntimeExceptionDao(Person.class);
		 }
		 return runtimeExceptionPersonDao;
	 }
	 
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Person.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Person.class , true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(sqLiteDatabase, connectionSource);
	}
	
	@Override
	public void close() {
		runtimeExceptionPersonDao = null;
		super.close();
	}
	

}
