package com.example.bonappetit.ui;

import static android.content.ContentValues.TAG;

//import android.app.Activity;
//import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bonappetit.R;
import com.example.bonappetit.adapters.Cart_Adapter;
import com.example.bonappetit.models.Cart_Model;
import com.example.bonappetit.models.daily_meal_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class My_cartFragment extends Fragment {
/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.

public class My_cartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public My_cartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment My_cartFragment.

    // TODO: Rename and change types and number of parameters
    public static My_cartFragment newInstance(String param1, String param2) {
        My_cartFragment fragment = new My_cartFragment();
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
    }*//*{@link My_cartFragment#newInstance} */

    Button make_order;
    List<Cart_Model> cart_modelList;
    Cart_Adapter cart_adapter;
    RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid = currentUser.getUid();

    public My_cartFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cart_modelList = new ArrayList<>();
       /* cart_modelList.add(new Cart_Model(R.drawable.dal_makhani,"Dal Makhani","260/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.briyani,"Biryani","200/-","4.8"));
        cart_modelList.add(new Cart_Model(R.drawable.goan_egg_curry,"Goan Egg Curry","290/-","4.8"));
        cart_modelList.add(new Cart_Model(R.drawable.gujrati_kadhi,"Gujrati Kadhi","210/-","4.1"));
        cart_modelList.add(new Cart_Model(R.drawable.boiled_rice,"Boiled_Rice","50/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.garlic_cheese_kulcha,"Garlic Cheese Kulcha","50/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.garlic_cheese_kulcha,"Garlic Cheese Kulcha","50/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.garlic_cheese_kulcha,"Garlic Cheese Kulcha","50/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.chapati,"chapati","30/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.chapati,"chapati","30/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.chapati,"chapati","30/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.chapati,"chapati","30/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.tea,"Tea","35/-","4.7"));
        cart_modelList.add(new Cart_Model(R.drawable.black_channa_cocnut_stew,"Black Channa Cocnut Stew","270/-","4.7"));*/


        make_order = view.findViewById(R.id.button_order);
        //Application context
        Checkout.preload(getContext());


        cart_adapter = new Cart_Adapter(cart_modelList);
        recyclerView.setAdapter(cart_adapter);
        //cart total amount
        final int[] total_amount = {0};

        db.collection("cart").whereEqualTo("uid",uid)

                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        cart_modelList.clear();
                        total_amount[0]=0;
                        for (DocumentSnapshot document : value.getDocuments()) {
                            cart_modelList.add(new Cart_Model(Integer.parseInt(document.getData().get("image").toString()), document.getData().get("name").toString(), document.getData().get("price").toString(), document.getData().get("quantity").toString()));
                            String price = document.getData().get("price").toString();
                            String quantity = document.getData().get("quantity").toString();
                            // the pricenum is geting integer price of item where substring is used for taking
                            // only integer value from 0-(-2) lenght subtracting the rupees sybmbol
                            int price_num = Integer.parseInt(price.substring(0, price.length() - 2));
                            int quantity_count = Integer.parseInt(quantity);
                            total_amount[0] += price_num * quantity_count;
                        }
                        cart_adapter.notifyDataSetChanged();
                        ((TextView) view.findViewById(R.id.txt_total_num)).setText(total_amount[0] + " /-");
                    }
                });

        make_order.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startpayment(total_amount[0]);
                Toast.makeText(view.getContext(), "Your Order has been placed, complete your payment.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void startpayment(int price){
        /**
         * Instantiate Checkout
         */

        /**
         * Set your logo here
         */
        //BNZPM2501F pan card detail
        // key id:-rzp_test_hS5hknw7yLCbYm
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_I50LWTuYWj0k9u");
        checkout.setImage(R.drawable.bon_appetit);
        /**
         * Reference to current activity
         */
        //final My_cartFragment activity = getActivity();

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            FirebaseFirestore.getInstance().collection("user").document(email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                        try {
                            JSONObject options = new JSONObject();

                            options.put("name", "Bon Appetit");
                            options.put("description", "Reference No. #123456");
                            options.put("image", "https://firebasestorage.googleapis.com/v0/b/bon-appetit-493b8.appspot.com/o/bon%20Appetit.jpg?alt=media&token=491cac6a-17cb-45a5-b9b2-44cafa619d1b");
                            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                            options.put("theme.color", "#3399cc");
                            options.put("currency", "INR");
                            options.put("amount", price * 100 + "");//pass amount in currency subunits
                            options.put("prefill.email", email);
                            options.put("prefill.contact", documentSnapshot.get("Phone_number"));

                            JSONObject retryObj = new JSONObject();
                            retryObj.put("enabled", true);
                            retryObj.put("max_count", 4);
                            options.put("retry", retryObj);

                            checkout.open(getActivity(), options);
                        }
                        catch(Exception e) {
                            Log.e(TAG, "Error in starting Razorpay Checkout", e);
                        }
                    }
                }
            });
    }

}