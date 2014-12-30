package com.example.newormlite;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.javaclasses.Person;
import com.example.javaclasses.SqliteHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends Activity {
	private RuntimeExceptionDao<Person, Integer> runtimeExceptionDao;
	private TextView databaseValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseValues = (TextView) findViewById(R.id.DatabaseValues);
		insertion();
		retrieve();
	}

	private void retrieve() {
		List<Person> listOfPersons = runtimeExceptionDao.queryForAll();
		StringBuilder builder = new StringBuilder();
		for(Person person : listOfPersons) {
			builder.append("PersonId : " + person.getPersonId()  + "   " + "Name : " + person.getName() + "      " + "City : " + person.getCity() + "\n");

		}

		databaseValues.setText(builder.toString());		
	}

	private void insertion() {
		runtimeExceptionDao = SqliteHelper.getRuntimeExceptionPersonDao(this);
		runtimeExceptionDao.create(new Person(1, "Raghav", "Gurdaspur"));
		runtimeExceptionDao.create(new Person(2, "Rahul", "Amritsar"));
		runtimeExceptionDao.create(new Person(3, "Rajiv", "Dhariwal"));
		runtimeExceptionDao.create(new Person(4, "Ravin", "Dinanagar"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
}
