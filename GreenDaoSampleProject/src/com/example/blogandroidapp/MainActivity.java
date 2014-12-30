package com.example.blogandroidapp;

import java.util.ArrayList;

import java.util.List;

import android.app.Activity;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.EditText;

import android.widget.TextView;

import com.example.blogandroidapp.dao.DaoMaster;

import com.example.blogandroidapp.dao.DaoMaster.DevOpenHelper;

import com.example.blogandroidapp.dao.DaoSession;

import com.example.blogandroidapp.dao.Persons;

import com.example.blogandroidapp.dao.PersonsDao;

public class MainActivity extends Activity {

	private SQLiteDatabase db;

	private PersonsDao personsDao;

	private TextView databaseValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		databaseValues = (TextView) findViewById(R.id.DatabaseValues);

		DevOpenHelper helper = new DevOpenHelper(this, "DatabaseName", null);

		db = helper.getWritableDatabase();

		DaoMaster master = new DaoMaster(db);

		DaoSession daoSession = master.newSession();

		personsDao = daoSession.getPersonsDao();

		insertion();

		retrieve();

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

	public void insertion() {

		Persons person1 = new Persons(1L, "Raghav", "Gurdaspur");

		Persons person2 = new Persons(2L, "Rahul", "Amritsar");

		Persons person3 = new Persons(3L, "Raminder", "Dhariwal");

		Persons person4 = new Persons(4L, "Rajiv", "Dinangar");

		List<Persons> listOfPersons = new ArrayList<Persons>();

		listOfPersons.add(person1);

		listOfPersons.add(person2);

		listOfPersons.add(person3);

		listOfPersons.add(person4);

		personsDao.insertInTx(listOfPersons);

	}

	public void retrieve() {

		List<Persons> listOfPersons = personsDao.queryBuilder().list();

		StringBuilder builder = new StringBuilder();

		for (Persons person : listOfPersons) {

			builder.append("PersonId : " + person.getPersonId() + "   "
					+ "Name : " + person.getName() + "      " + "City : "
					+ person.getCity() + "\n");

		}

		databaseValues.setText(builder.toString());

	}

}
