package com.anandb.android.criminalintent;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	public static final String PICKER_DATE = "crime_date";
	private Date mDate;
	
	public static DatePickerFragment newInstance(Date d) {
		Bundle args = new Bundle();
		args.putSerializable(PICKER_DATE, d);
		
		DatePickerFragment dpf = new DatePickerFragment();
		dpf.setArguments(args);
		
		return dpf;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		DatePicker dp = (DatePicker) getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		mDate = (Date) getArguments().getSerializable(PICKER_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(mDate);
	
		dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Calendar c = Calendar.getInstance();
				c.setTime(mDate);
				c.set(year, monthOfYear, dayOfMonth);
				mDate = c.getTime();
				getArguments().putSerializable(PICKER_DATE, mDate);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
		.setView(dp)
		.setTitle(R.string.date_picker_title)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sendResult(Activity.RESULT_OK);
			}
		})
		.create();
	}
	
	private void sendResult(int resultCode) {
		Intent i = new Intent();
		i.putExtra(PICKER_DATE, mDate);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}

}
