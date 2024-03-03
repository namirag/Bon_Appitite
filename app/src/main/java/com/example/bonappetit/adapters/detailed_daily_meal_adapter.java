package com.example.bonappetit.adapters;

import android.annotation.SuppressLint;
//import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
//import com.example.bonappetit.activities.DetailActivity;
import com.example.bonappetit.activities.DetailActivity;
import com.example.bonappetit.models.detailed_daily_meal_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class detailed_daily_meal_adapter extends RecyclerView.Adapter<detailed_daily_meal_adapter.ViewHolder> {
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */

    List<detailed_daily_meal_model> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid = currentUser.getUid();

    public detailed_daily_meal_adapter(List<detailed_daily_meal_model> detailed_daily_meal_modelList) {
        this.list = detailed_daily_meal_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_daily_meal_items,parent,false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        holder.imageview.setImageResource(list.get(pos).getImage());
        holder.name.setText(list.get(pos).getName());
        holder.des.setText(list.get(pos).getDes());
        holder.rating.setText(list.get(pos).getRating());
        holder.price.setText(list.get(pos).getPrice());
        holder.timing.setText(list.get(pos).getTiming());

        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
                // Create a new user with a first and last name
                Map<String, Object> cart_items = new HashMap<>();

                cart_items.put("name",list.get(pos).getName());
                cart_items.put("rating", list.get(pos).getRating());
                cart_items.put("price", list.get(pos).getPrice());
                cart_items.put("image", list.get(pos).getImage());
                cart_items.put("quantity", "1");
                cart_items.put("uid", uid);

               // Add a new document with a generated ID
                db.collection("cart").whereEqualTo("uid",uid).whereEqualTo("name",list.get(pos).getName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){
                            db.collection("cart")
                                    .add(cart_items)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                            Toast.makeText(holder.add_cart.getContext(), "Added to a Cart", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.add_cart.getContext(), "Exception error", Toast.LENGTH_SHORT).show();
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
        TextView name,des,rating,price,timing;
        Button add_cart;//add

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            des = itemView.findViewById(R.id.detailed_des);
            rating = itemView.findViewById(R.id.detailed_rating);
            price = itemView.findViewById(R.id.detailed_txt_dollar);
            timing = itemView.findViewById(R.id.detailed_time);
            add_cart = itemView.findViewById(R.id.add_cart);//add

        }
    }
}
/*
package com.example.bonappetit.adapters;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import com.example.bonappetit.R;
        import com.example.bonappetit.models.DetailedDailyModel;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.QuerySnapshot;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class DetailedDailyAdapter extends RecyclerView.Adapter<DetailedDailyAdapter.ViewHolder> {

    List<DetailedDailyModel> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid = currentUser.getUid();
    public DetailedDailyAdapter(List<DetailedDailyModel> detailedDailyModelList) {
        this.list=detailedDailyModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daily_meal_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.imageView.setImageResource(list.get(pos).getImage());
        holder.price.setText(list.get(pos).getPrice());
        holder.name.setText(list.get(pos).getName());
        holder.description.setText(list.get(pos).getDescription());
        holder.timing.setText(list.get(pos).getTiming());
        holder.rating.setText(list.get(pos).getRating());
        holder.addto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
                // Create a new user with a first and last name
                // It is a wapper class in java in map the object on it's particular position
                Map<String, Object> cart_items = new HashMap<>();
                cart_items.put("name",list.get(pos).getName());
                cart_items.put("rating", list.get(pos).getRating());
                cart_items.put("price", list.get(pos).getPrice());
                cart_items.put("image", list.get(pos).getImage());
                cart_items.put("quantity", "1");
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
                                            Toast.makeText(holder.addto.getContext(), "Added to a Cart", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.addto.getContext(), "Exception error", Toast.LENGTH_SHORT).show();
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
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,description,rating,timing;
        Button addto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.detailed_price);
            description = itemView.findViewById(R.id.detailed_description);
            rating = itemView.findViewById(R.id.rate2);
            timing = itemView.findViewById(R.id.time2);
            addto = itemView.findViewById(R.id.addto);
        }
    }
}
*/