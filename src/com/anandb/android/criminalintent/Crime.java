package com.anandb.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
	private UUID mID;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	
	public Crime() {
		mID = UUID.randomUUID();
		mDate = new Date();
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

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
}
