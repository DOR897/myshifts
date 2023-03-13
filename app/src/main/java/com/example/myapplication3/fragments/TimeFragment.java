package com.example.myapplication3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.myapplication3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimeFragment extends Fragment {

    private Chronometer chronometer;
    private Button checkInButton;
    private Button checkOutButton;
    private Button mySalary;
    private Button myShifts;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private long elapsedTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time, container, false);

        chronometer = view.findViewById(R.id.chronometerTimePage);
        checkInButton = view.findViewById(R.id.buttonCheckInTimePage);
        checkOutButton = view.findViewById(R.id.buttonCheckOutTimePage);
        mySalary = view.findViewById(R.id.buttonMySalaryTimePage);
        myShifts = view.findViewById(R.id.buttonShiftsTimePage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users").child(firebaseUser.getUid());

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                databaseReference.child("hoursWorked").setValue(elapsedTime);
                chronometer.stop();
            }
        });
        mySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_timeFragment_to_salaryFragment);
            }
        });
        myShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_timeFragment_to_calendarFragment);
            }
        });



        return view;
    }
}
