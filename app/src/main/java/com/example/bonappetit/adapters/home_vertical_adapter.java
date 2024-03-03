package com.example.bonappetit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.models.home_vertical_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class home_vertical_adapter extends RecyclerView.Adapter<home_vertical_adapter.ViewHolder> {

    private BottomSheetDialog botton_sheetdialog;
    Context context;
    List<home_vertical_model> list;
    //initiallizing FirebaseFireStore instance as db
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid = currentUser.getUid();
    // List<home_vertical_model> list;

    public home_vertical_adapter(Context context, List<home_vertical_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_icons,parent,false));
    }
//getting position when user clicks it.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos = holder.getAdapterPosition();
        final String mName = list.get(pos).getName();
        final String mTiming = list.get(pos).getTiming();
        final String mRating = list.get(pos).getRating();
        final String mPrice = list.get(pos).getPrice();
        final int mImage = list.get(pos).getImage();
//setting postion to viewholder.
        holder.imageview.setImageResource(list.get(pos).getImage());
        holder.name.setText(list.get(pos).getName());
        holder.timing.setText(list.get(pos).getTiming());
        holder.rating.setText(list.get(pos).getRating());
        holder.price.setText(list.get(pos).getPrice());

//Getting positions and adding items to FireBase and accessing from My Cart and sending a toast message to
// user as "Added to Cart" if successful else "Error Occured".
        holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            botton_sheetdialog = new BottomSheetDialog(context, R.style.BottomSheetTheme);
            View sheetview = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null);
            sheetview.findViewById(R.id.bottom_add_cart).setOnClickListener(new View.OnClickListener() {

                        /*@Override
                        public void onClick(View view) {
                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                            // Log.d("cartb", "onClick: working");
                            botton_sheetdialog.dismiss();
                        }*/
                        public void onClick(View view) {
                            //add
                            // Create a new user with a first and last name
                            //Log.d("cartb", "onClick: working");
                            Map<String, Object> cart_items = new HashMap<>();

                            cart_items.put("name", list.get(pos).getName());
                            cart_items.put("rating", list.get(pos).getRating());
                            cart_items.put("price", list.get(pos).getPrice());
                            cart_items.put("quantity", "1");
                            cart_items.put("image", list.get(pos).getImage());
                            cart_items.put("uid", uid);

                            // Add a new document with a generated ID
                            db.collection("cart").whereEqualTo("uid",uid).whereEqualTo("name", list.get(pos).getName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                                    if (queryDocumentSnapshots.isEmpty()){
                                        db.collection("cart")
                                                .add(cart_items)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                                        Toast.makeText(holder.itemView.getContext(), "Added to a Cart", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(holder.itemView.getContext(), "Exception error", Toast.LENGTH_SHORT).show();
                                                        botton_sheetdialog.dismiss();
                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(holder.itemView.getContext(),"Already Added.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                    /* @Override
                          public void onClick(View view) {
                              Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                                // Log.d("cartb", "onClick: working");
                                    botton_sheetdialog.dismiss();
                                    }
                                 });
                              }
                           });*/

                    ImageView bottomimg = sheetview.findViewById(R.id.bottom_img);
                    TextView bottomname = sheetview.findViewById(R.id.bottom_name);
                    TextView bottomrate = sheetview.findViewById(R.id.bottom_rating);
                    TextView bottomprice = sheetview.findViewById(R.id.bottom_txt_dollar);

                    bottomname.setText(mName);
                    bottomrate.setText(mRating);
                    bottomprice.setText(mPrice);
                    bottomimg.setImageResource(mImage);

                    botton_sheetdialog.setContentView(sheetview);
                    botton_sheetdialog.show();
                }
            });
        }

            /**
             * Returns the total number of items in the data set held by the adapter.
             *
             * @return The total number of items in this adapter.
             */
            @Override
            public int getItemCount() {
                return list.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                ImageView imageview;
                TextView name, timing, rating, price;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    imageview = itemView.findViewById(R.id.img_burger);
                    name = itemView.findViewById(R.id.name);
                    timing = itemView.findViewById(R.id.timing);
                    rating = itemView.findViewById(R.id.rating);
                    price = itemView.findViewById(R.id.price);
                }
            }
    }
/*

package com.example.bonappetit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bonappetit.R;
import com.example.bonappetit.models.HomeVerModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    List<HomeVerModel> list;
//Initiallizing FireBaseFireStore instance as db.
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid = currentUser.getUid();
    public HomeVerAdapter(Context context, List<HomeVerModel> list) {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false));
    }
//Getting positions when user clicks it.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos = holder.getAdapterPosition();
        final String mName = list.get(pos).getName();
        final String mTiming = list.get(pos).getTiming();
        final String mRating = list.get(pos).getRating();
        final String mPrice = list.get(pos).getPrice();
        final int mImage = list.get(pos).getImage();
//Setting positions to holder.
        holder.imageView.setImageResource(list.get(pos).getImage());
        holder.name.setText(list.get(pos).getName());
        holder.timing.setText(list.get(pos).getTiming());
        holder.rating.setText(list.get(pos).getRating());
        holder.price.setText(list.get(pos).getPrice());
//Getting positions and adding items to FireBase and accessing from My Cart and sending a toast message to
// user as "Added to Cart" if successful else "Error Occured".
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);
                sheetView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Map<String, Object> cart_items = new HashMap<>();
                        cart_items.put("name",list.get(pos).getName());
                        cart_items.put("rating", list.get(pos).getRating());
                        cart_items.put("price", list.get(pos).getPrice());
                        cart_items.put("quantity", "1");
                        cart_items.put("image", list.get(pos).getImage());
                        cart_items.put("uid", uid);
                        // Add a new document with a generated ID

                        db.collection("cart").whereEqualTo("uid",uid).whereEqualTo("name",list.get(pos).getName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(queryDocumentSnapshots.isEmpty()){
                                    db.collection("cart")
                                            .add(cart_items)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    Toast.makeText(holder.itemView.getContext(), "Added to a Cart", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(holder.itemView.getContext(), "Exception error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                else{
                                    Toast.makeText(holder.itemView.getContext(),"Already Added.",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });
                ImageView bottomImage = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating = sheetView.findViewById(R.id.bottom_rate);

                bottomName.setText(mName);
                bottomPrice.setText(mPrice);
                bottomRating.setText(mRating);
                bottomImage.setImageResource(mImage);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });
    }
//Creating a View Holder and assigning corresponding images or text.
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,timing,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.burger_icon);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating = itemView.findViewById(R.id.rate);
            price = itemView.findViewById(R.id.price);
        }
    }
}*/
