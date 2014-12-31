package com.example.db4oandroidapplication;

import java.io.File;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4odoc.f1.chapter1.JuniorPilot;
import com.db4odoc.f1.chapter1.Pilot;


public class MainActivity extends Activity {

	private TextView databaseValues;
	private ObjectContainer db;
	private static final String DB4OFILENAME = "DatabaseName.DB4O";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseValues = (TextView) findViewById(R.id.databaseValues);
		final EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		  final String dbPath = getApplicationContext().getDir("data", 0) + File.separator + DB4OFILENAME;  
		  new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				db = Db4oEmbedded.openFile(config, dbPath);
				insertValues();	
				retrieveValues();
				return null;
			}
		  }.execute();
	}

	private void retrieveValues() {
		Pilot allPilots = new Pilot(null, 0);
		ObjectSet<Pilot> pilots = db.queryByExample(allPilots);
		final StringBuilder builder = new StringBuilder();
		for(Pilot pilot : pilots) {
				builder.append("Name: " + pilot.getName() + "		" + 
										"Points: " + pilot.getPoints() + "		" + 
										"\n");
			
		}
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				databaseValues.setText(builder.toString());
				
			}
		});
	}

	private void insertValues() {
		Pilot pilot1 = new Pilot("Michael Schumacher", 100);
		db.store(pilot1);
		Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
		db.store(pilot2);
		JuniorPilot juniorPilot1 = new JuniorPilot("Raghav", 10);
		JuniorPilot juniorPilot2 = new JuniorPilot("Sharma", 52);
		db.store(juniorPilot1);
		db.store(juniorPilot2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
