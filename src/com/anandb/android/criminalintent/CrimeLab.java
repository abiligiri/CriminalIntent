package com.anandb.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;

public class CrimeLab {
	private static CrimeLab sInstance;
	@SuppressWarnings("unused")
	private Context mContext;
	private ArrayList<Crime> mCrimes;
	
	private CrimeLab(Context c) {
		mContext = c;
		mCrimes = new ArrayList<Crime>();
		
		populateCrimes();
	}
	
	public static CrimeLab get(Context c) {
		if (sInstance == null) {
			sInstance = new CrimeLab(c.getApplicationContext());
		}
		
		return sInstance;
	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public Crime getCrime(UUID uuid) {
		for (Crime c : mCrimes) {
			if (c.getID().equals(uuid)) {
				return c;
			}
		}
		
		return null;
	}
	
	private void populateCrimes() {
		for (int i = 0; i < 100; i++) {
			Crime c = new Crime();
			c.setTitle("Crime #" + i);
			mCrimes.add(c);
			c.setSolved(i % 2 == 0);
		}
	}
}
