package com.sim.socialdistancingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecTimesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecTimesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<Destination> destinations;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RecTimesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecTimesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecTimesFragment newInstance(String param1, String param2) {
        RecTimesFragment fragment = new RecTimesFragment();
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
        View v= inflater.inflate(R.layout.fragment_rec_times, container, false);

        recyclerView=v.findViewById(R.id.times_recycleçview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        destinations=new ArrayList<>();

        if(CitizenActivity.citizenRequest!=null && !CitizenActivity.citizenRequest.getDestination().isAvailable()){
            Destination d1 = null,d2=null,d3=null;
            try {
                d1= (Destination) CitizenActivity.citizenRequest.getDestination().clone();
                d1.setWhenAvailable("Today From 18:00 To 22:00");
                d2= (Destination) CitizenActivity.citizenRequest.getDestination().clone();
                d2.setWhenAvailable("Tomorrow From 10:00 To 12:00");
                d3= (Destination) CitizenActivity.citizenRequest.getDestination().clone();
                d3.setWhenAvailable("Next Tuesday From 10:00 To 12:00");

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            destinations.add(d1);
            destinations.add(d2);
            destinations.add(d3);
        }

        DestinationCardRecycleViewAdapter dcra=new DestinationCardRecycleViewAdapter(destinations);
        recyclerView.setAdapter(dcra);

        return v;
    }
}