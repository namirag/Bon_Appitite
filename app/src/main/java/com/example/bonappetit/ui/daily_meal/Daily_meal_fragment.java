package com.example.bonappetit.ui.daily_meal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.adapters.daily_meal_adapter;
import com.example.bonappetit.models.daily_meal_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
//import com.example.bonappetit.databinding.FragmentGalleryBinding;

public class Daily_meal_fragment extends Fragment {

    RecyclerView recyclerView;
    List<daily_meal_model> dailyMealModels;
    daily_meal_adapter dailyMealAdapter;

    //private FragmentGalleryBinding binding;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root =inflater.inflate(R.layout.daily_meal_fragment, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();
        db.collection("bonappetitimg")
                .orderBy("Key", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                dailyMealModels.add(new daily_meal_model(document.getData().get("Image").toString(),document.getData().get("Name").toString(),document.getData().get("Description").toString(),document.getData().get("Type").toString(),document.getData().get("Rating").toString()));

                            }
                            dailyMealAdapter.notifyDataSetChanged();
                        }
                    }
                });
        dailyMealAdapter = new daily_meal_adapter(getContext(),dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        return root;
    }

}