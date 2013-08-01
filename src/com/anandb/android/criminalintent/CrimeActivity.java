package com.anandb.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CrimeActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crime);
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment f = fm.findFragmentById(R.id.fragmentContainer);
		
		if (f == null) {
			f = new CrimeFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, f).commit();
		}
	}

}
