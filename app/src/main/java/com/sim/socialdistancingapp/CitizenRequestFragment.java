package com.sim.socialdistancingapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitizenRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitizenRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextInputLayout dropdwonDestination;
    AutoCompleteTextView dropdownContro;
    MaterialButton requestStartDateButton,requestEndDateButton,requestStartTimeButton,requestEndTimeButton,sendRequestButton;

    Boolean requestAccepted=false;
    CitizenRequest citizenRequest;

    List<Destination> availlableDestinations;


    private int mYear, mMonth, mDay, mHour, mMinute;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CitizenRequestFragment( ) {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitizenRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitizenRequestFragment newInstance(String param1, String param2,CitizenRequest citizenRequest) {
        CitizenRequestFragment fragment = new CitizenRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_citizen_request, container, false);

        dropdwonDestination=v.findViewById(R.id.dropdwon_destinations);
        dropdownContro=v.findViewById(R.id.dropdown_control);
        requestStartDateButton=v.findViewById(R.id.button_start_date);
        requestStartTimeButton=v.findViewById(R.id.button_start_time);
        requestEndDateButton=v.findViewById(R.id.button_end_date);
        requestEndTimeButton=v.findViewById(R.id.button_end_time);
        sendRequestButton=v.findViewById(R.id.send_request_button);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        requestStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDtate((MaterialButton) view);
            }

        });

        requestEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDtate((MaterialButton) view);
            }

        });


        requestStartTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    pickTime((MaterialButton) view);
            }
        });

        requestEndTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime((MaterialButton) view);
            }
        });

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(citizenRequest!=null){
                    dropdwonDestination.setError(null);
                    CitizenActivity.citizenRequest=citizenRequest;
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(getContext());
                    dialog.setTitle("Response")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    if(citizenRequest.getDestination().isAvailable())
                        requestAccepted=true;
                    else
                        requestAccepted=false;
                    if(requestAccepted){
                        dialog.setMessage("Your request has been accepted\nYou can go, taking care and respect the rights of others.")
                                .setIcon(R.drawable.ic_baseline_done_24);
                    }
                    else {
                        dialog.setMessage("Your request has not been accepted at this time\nBut, you can review other recommendations.")
                                .setIcon(R.drawable.ic_baseline_error_outline_24);
                    }
                    dialog.show();
                }
                else {
                    dropdwonDestination.setError("Select a destination");
                }
            }
        });

        availlableDestinations=new ArrayList<Destination>();
        availlableDestinations.add(new Destination("Taza","Supermarché Marjan",17,"market"));
        availlableDestinations.add(new Destination("Taza","Supermarché Atkaddaw",50,"market"));
        availlableDestinations.add(new Destination("Taza","Administration 1",14,"administration"));
        availlableDestinations.add(new Destination("Taza","Administration 2",50,"administration"));
        availlableDestinations.add(new Destination("Taza","Administration 3",40,"administration"));
        availlableDestinations.add(new Destination("Taza","Supermarché Assima",50,"market"));
        availlableDestinations.add(new Destination("Taza","Supermarché Assalam",44,"market"));
        availlableDestinations.add(new Destination("Taza","Administration 4",44,"administration"));

        CitizenActivity.destinations= (ArrayList<Destination>) availlableDestinations;


        ArrayAdapter<Destination> adapter=new ArrayAdapter<Destination>(getContext(),R.layout.dropdwon_item,availlableDestinations);

        dropdownContro.setAdapter(adapter);

        dropdownContro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                citizenRequest=new CitizenRequest(availlableDestinations.get(i));

            }
        });

        return v;
    }


    public void pickDtate(final MaterialButton materialButton){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.CustomStyle,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                materialButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void pickTime(final MaterialButton materialButton){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),R.style.CustomStyle, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                materialButton.setText(hourOfDay + ":" + minute);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public static void setViewPager(ViewPager viewPager) {
        viewPager = viewPager;
    }
}