package com.example.sqlitedatabaseblog;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView databaseValues;
	private SqliteHelper mySqlite;
	private SQLiteDatabase myDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseValues = (TextView)findViewById(R.id.databaseValues);
		mySqlite  = new SqliteHelper(this);
		myDatabase = mySqlite.getWritableDatabase();
		addEntriesIntoDatabase();
		retrieveValuesFromDatabase();
		closeDatabase();
		
	}

	private void closeDatabase() {
		myDatabase.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
	private void addEntriesIntoDatabase() {
		String insertPerson1 = "insert into " + SqliteHelper.getTableName() + " values(1, 'Raghav', 'Gurdaspur')";
		String insertPerson2 = "insert into " + SqliteHelper.getTableName() + " values(2, 'Rahul', 'Amritsar')";
		String insertPerson3 = "insert into " + SqliteHelper.getTableName() + " values(3, 'Rajiv', 'Dhariwal')";
		ContentValues contentValues = new ContentValues();
		contentValues.put("PersonId", 4);
		contentValues.put("Name", "Raminder");
		contentValues.put("City", "Delhi");
		myDatabase.execSQL(insertPerson1);
		myDatabase.execSQL(insertPerson2);
		myDatabase.execSQL(insertPerson3);
		myDatabase.insert(SqliteHelper.getTableName(), null, contentValues);
	}
	
	private void retrieveValuesFromDatabase() {
		String selectAllCommand = "select * from " + SqliteHelper.getTableName();
		Cursor cursor = myDatabase.rawQuery(selectAllCommand, null);
		cursor.moveToFirst();
		StringBuilder builder = new StringBuilder();
		while(!cursor.isAfterLast()) {
			builder.append("PersonID: " + cursor.getInt(0) + "		" + 
									"Name: " + cursor.getString(1) + "		" + 
									"City: " + cursor.getString(2) + "\n");
			cursor.moveToNext();
		}
		databaseValues.setText(builder.toString());
	}
}
