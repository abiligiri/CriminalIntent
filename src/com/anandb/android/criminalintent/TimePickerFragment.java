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
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment {
	public static final String PICKER_DATE = "crime_date";
	private Date mDate;
	
	public static TimePickerFragment newInstance(Date d) {
		Bundle args = new Bundle();
		args.putSerializable(PICKER_DATE, d);
		
		TimePickerFragment tpf = new TimePickerFragment();
		tpf.setArguments(args);
		
		return tpf;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		TimePicker tp = (TimePicker) getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
		mDate = (Date) getArguments().getSerializable(PICKER_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(mDate);
	
		tp.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		tp.setCurrentMinute(c.get(Calendar.MINUTE));
		tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Calendar c = Calendar.getInstance();
				c.setTime(mDate);
				c.set(Calendar.HOUR_OF_DAY, hourOfDay);
				c.set(Calendar.MINUTE,minute);
				mDate = c.getTime();
			}
		});
				
		return new AlertDialog.Builder(getActivity())
		.setView(tp)
		.setTitle(R.string.time_picker_title)
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
