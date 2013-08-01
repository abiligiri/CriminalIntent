package com.anandb.android.criminalintent;

import java.util.UUID;

public class Crime {
	private UUID mID;
	private String mTitle;
	
	public Crime() {
		mID = UUID.randomUUID();
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getID() {
		return mID;
	}
}