package com.example.bonappetit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
import android.widget.ImageView;
//import android.widget.Toast;

import com.example.bonappetit.R;
import com.example.bonappetit.adapters.detailed_daily_meal_adapter;
import com.example.bonappetit.models.detailed_daily_meal_model;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<detailed_daily_meal_model> detailed_daily_meal_modellist;
    detailed_daily_meal_adapter Detailed_daily_meal_adapter;
    ImageView imageview;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String type = getIntent().getStringExtra("type");
        //Log.d("working", type);
        recyclerView = findViewById(R.id.rec_detail);
        imageview = findViewById(R.id.img_ac_detail);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailed_daily_meal_modellist = new ArrayList<>();
        Detailed_daily_meal_adapter = new detailed_daily_meal_adapter(detailed_daily_meal_modellist);
        recyclerView.setAdapter(Detailed_daily_meal_adapter);

        if(type!=null && type.equalsIgnoreCase("breakfast"))
        {
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.quinoa_upma,"Quinoa upma","Upma and veges","4.3","70/-","6:00-11:00 am"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.oats_porridge,"Oats porridge","oats and porridge","4.7","110/-","6:00-11:00 am"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.eggless_banana_pancake,"Egg-less banana pancake","Pancake batter and bananas ","4.6","150/-","6:00-11:00 am"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.chia_pudding,"Chia pudding","chia seeds and strawberry","4.8","100/-","6:00-11:00 am"));

            Detailed_daily_meal_adapter.notifyDataSetChanged();
        }
        if(type!=null && type.equalsIgnoreCase("lunch"))
        {
            imageview.setImageResource(R.drawable.lunch);
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.allahabad_ki_tehri,"Allahabad ki Tehri","rice,veges & chole","4.7","270/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.channa_khucha,"Channa Khucha","Channa gravey, khucha","4.7","210/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.cholebuture,"Chole Buture","chole grevey & bhutres","4.6","150/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.masala_bhindi,"Masala Bhindi","Bhindi & other veges","4.8","100/-","11:30-16:00"));

            Detailed_daily_meal_adapter.notifyDataSetChanged();
        }
        if(type!=null && type.equalsIgnoreCase("dinner"))
        {
            imageview.setImageResource(R.drawable.dinner);
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.black_channa_cocnut_stew,"Black Channa Coconut Stew","rice,veges & chole","4.7","270/-","17:00-23:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.dal_makhani,"Dal Makhani","Masala seeds grevy","4.7","260/-","17:00-23:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.goan_egg_curry,"Goan Egg Curry","Egg,heavy masala gravy","4.8","290/-","17:00-23:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.gujrati_kadhi,"Gujrati Kadhi","A light & healthy stew gravy","4.1","210/-","17:00-23:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.briyani,"Biryani","rice and gram masala","4.8","200/-","17:00-23:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.kholapuri_veg,"Kholapuri Veg","all the veges","4.1","210/-","17:00-23:00"));

            Detailed_daily_meal_adapter.notifyDataSetChanged();
        }
        if(type!=null && type.equalsIgnoreCase("drinks"))
        {
            imageview.setImageResource(R.drawable.drinks);
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.coffee,"Coffee","Coffee powder & milk","4.7","170/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.tea,"Tea","milk and tea, seeds & water","4.7","110/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.lemonade,"Lemonade","Lemon, water","4.6","60/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.green_tea,"Green Tea","Tea seeds in boiled hot water","4.8","70/-","11:30-16:00"));

            Detailed_daily_meal_adapter.notifyDataSetChanged();
        }
        if(type!=null && type.equalsIgnoreCase("sweets"))
        {
            imageview.setImageResource(R.drawable.sweets);
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.cake,"Cake","Cake","4.7","170/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.laddu,"Laddu","Laddu","4.7","110/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.ice_cream,"Ice-Cream","Ice-Cream","4.6","150/-","11:30-16:00"));
            detailed_daily_meal_modellist.add(new detailed_daily_meal_model(R.drawable.waffles,"Waffles","Waffles","4.8","100/-","11:30-16:00"));

            Detailed_daily_meal_adapter.notifyDataSetChanged();
        }
    }

}