package com.example.bonappetit.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.adapters.UpdateVerticalRec;
import com.example.bonappetit.adapters.home_horizon_adapter;
import com.example.bonappetit.models.home_horizon_model;
import com.example.bonappetit.models.home_vertical_model;
import com.example.bonappetit.adapters.home_vertical_adapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec{

    RecyclerView homeHorizontalRec,homeVerticalRec;
    //////////////////////////Horizontal\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    ArrayList<home_horizon_model> homeHorizonModelList;//= new ArrayList<>();
    //List<home_horizon_model> homeHorizonModelList;
    home_horizon_adapter homeHorizontalAdapters;

//////////////////////////////////vertical\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    ArrayList<home_vertical_model> homeVerticalModelList;// = new ArrayList<>();
    //List<home_vertical_model> homeVerticalModelList;
    home_vertical_adapter homeVerticalAdapters;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_home, container, false);

        homeHorizontalRec = root.findViewById(R.id.home_Hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_Ver_rec);

        /////////////////////////////horizontal RecyclerView\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        homeHorizonModelList = new ArrayList<>();

        homeHorizonModelList.add(new home_horizon_model(R.drawable.burger_icon,"Burger"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.pizza_icon,"Pizza"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.briyani_icon,"Biryani"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.pasta_icon,"pasta"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.garlicbreadicon,"Breads"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.noodlesicon,"Noodles"));
        homeHorizonModelList.add(new home_horizon_model(R.drawable.ice_cream_icon,"Ice Cream"));



        homeHorizontalAdapters = new home_horizon_adapter(this,getActivity(),homeHorizonModelList);
        homeHorizontalRec.setAdapter(homeHorizontalAdapters);
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        /////////////////////////////Vertical RecyclerView\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        homeVerticalModelList = new ArrayList<>();

       /* homeVerticalModelList.add(new home_vertical_model(R.drawable.burger,"Burger","10:00-20:00","5.0","180/-"));
        homeVerticalModelList.add(new home_vertical_model(R.drawable.pizza,"Pizza","11:00-20:00","5.0","150/-"));
        homeVerticalModelList.add(new home_vertical_model(R.drawable.lemon_pasta,"Lemon pasta","1:00-20:00","5.0","110/-"));
        homeVerticalModelList.add(new home_vertical_model(R.drawable.creamycheesepasta,"creamy cheese pasta","1:30-20:00","5.0","250/-"));*/

        homeVerticalAdapters = new home_vertical_adapter(getActivity(),homeVerticalModelList);
        homeVerticalRec.setAdapter(homeVerticalAdapters);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
       //name
        String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        name = name.substring(0,name.indexOf("@"));
        ((TextView)root.findViewById(R.id.txt_greeting)).setText("Hello "+name);
        return root;

    }

    @SuppressLint("NotifyDataSetChanged")
    public void callback(int position, ArrayList<home_vertical_model> list){
        //Log.d("working", list.get(0).getName());
            homeVerticalAdapters = new home_vertical_adapter(getContext(),list);
            homeVerticalAdapters.notifyDataSetChanged();
            homeVerticalRec.setAdapter(homeVerticalAdapters);

    }
}