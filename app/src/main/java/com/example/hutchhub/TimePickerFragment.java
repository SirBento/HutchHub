package com.example.hutchhub;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.text.format.DateFormat;
import java.util.Calendar;
import io.reactivex.annotations.NonNull;


public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}