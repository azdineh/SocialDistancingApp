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
 * Use the {@link RecPlacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecPlacesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Destination> destinations;
    RecyclerView recyclerView;

    public RecPlacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecPlacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecPlacesFragment newInstance(String param1, String param2) {
        RecPlacesFragment fragment = new RecPlacesFragment();
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
        View v= inflater.inflate(R.layout.fragment_rec_places, container, false);

        recyclerView=v.findViewById(R.id.places_recycleçview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Destination> recommendedDestinations=new ArrayList<>();

        if(CitizenActivity.citizenRequest!=null && !CitizenActivity.citizenRequest.getDestination().isAvailable()){

            Destination d1=CitizenActivity.citizenRequest.getDestination();

            for(int i=0;i<CitizenActivity.destinations.size();i++){
                Destination d=CitizenActivity.destinations.get(i);
                if(d.isAvailable() && d.getType()==d1.getType() && d.getName()!=d1.getName()){
                    recommendedDestinations.add(d);
                }
            }



        }

        DestinationCardRecycleViewAdapter dcra=new DestinationCardRecycleViewAdapter(recommendedDestinations);
        recyclerView.setAdapter(dcra);

        return v;
    }
}