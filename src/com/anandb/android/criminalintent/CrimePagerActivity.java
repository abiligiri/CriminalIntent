package com.anandb.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class CrimePagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Crime> mCrimes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.crime_pager);
		mCrimes = CrimeLab.get(this).getCrimes();		
		setContentView(mViewPager);
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return mCrimes.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				// TODO Auto-generated method stub
				Crime c = mCrimes.get(pos);
				CrimeFragment cf = CrimeFragment.newInstance(c.getID());
				return cf;
			}
		});
		
		setTitle(mCrimes.get(0).getTitle());
		
		UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		
		for (int i = 0; i < mCrimes.size(); i++) {
			if (mCrimes.get(i).getID().equals(crimeId)) {
				mViewPager.setCurrentItem(i);
				setTitle(mCrimes.get(i).getTitle());
				break;
			}
		}
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				Crime c = mCrimes.get(position);
				
				if (c.getTitle() != null) {
					setTitle(c.getTitle());
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int position) {
			}
		});
	}

}
