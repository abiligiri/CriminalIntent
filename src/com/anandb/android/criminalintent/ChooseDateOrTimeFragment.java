package com.anandb.android.criminalintent;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ChooseDateOrTimeFragment extends DialogFragment {
	public static final String CRIME_DATE = "crime_date";
	private static final String DATE_PICKER = "date_picker";
	private static final String TIME_PICKER = "date_picker";
	private static final int REQUEST_DATE = 0;
	private static final int REQUEST_TIME = 1;
	
	private Date mDate;
	
	public static ChooseDateOrTimeFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(CRIME_DATE, date);
		
		ChooseDateOrTimeFragment f = new ChooseDateOrTimeFragment();
		f.setArguments(args);
		
		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date) getArguments().getSerializable(CRIME_DATE);
		
		return new AlertDialog.Builder(getActivity())
		.setPositiveButton(R.string.dateOrTime_picker_pickDate, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				DatePickerFragment dpf = DatePickerFragment.newInstance(mDate);
				dpf.setTargetFragment(ChooseDateOrTimeFragment.this, REQUEST_DATE);
				dpf.show(getActivity().getSupportFragmentManager(), DATE_PICKER);
				
			}
		})
		.setNegativeButton(R.string.dateOrTime_picker_pickTime, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TimePickerFragment tpf = TimePickerFragment.newInstance(mDate);
				tpf.setTargetFragment(ChooseDateOrTimeFragment.this, REQUEST_TIME);
				tpf.show(getActivity().getSupportFragmentManager(), TIME_PICKER);
			}
		})
		.create();
				
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		
		if (requestCode == REQUEST_DATE || requestCode == REQUEST_TIME) {
			mDate = (Date) data.getSerializableExtra(DatePickerFragment.PICKER_DATE);
			
			Intent i = new Intent();
			i.putExtra(CRIME_DATE, mDate);
			
			getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
		}
	}
}
