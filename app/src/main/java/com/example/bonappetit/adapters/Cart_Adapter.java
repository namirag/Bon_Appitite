package com.example.bonappetit.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.models.Cart_Model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder> {
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

    List<Cart_Model> cart_modelList;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public Cart_Adapter(List<Cart_Model> cart_modelList) {
        this.cart_modelList = cart_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_items,parent,false));
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

        holder.image.setImageResource(cart_modelList.get(pos).getImage());
        holder.name.setText(cart_modelList.get(pos).getName());
        holder.price.setText(cart_modelList.get(pos).getPrice());
        holder.quantity.setText(cart_modelList.get(pos).getQuantity());
        holder.minus.setOnClickListener(View -> {
            FirebaseFirestore.getInstance().collection("cart").whereEqualTo("uid",uid).whereEqualTo("name",cart_modelList.get(pos).getName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                    int quantity =  Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).get("quantity").toString());
                    if(quantity == 1){
                        queryDocumentSnapshots.getDocuments().get(0).getReference().delete();
                    }
                    else{
                        queryDocumentSnapshots.getDocuments().get(0).getReference().update("quantity",--quantity);
                    }
                }
            });
        });
        holder.plus.setOnClickListener(View -> {
            FirebaseFirestore.getInstance().collection("cart").whereEqualTo("uid",uid).whereEqualTo("name",cart_modelList.get(pos).getName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                    int quantity =  Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).get("quantity").toString());
                        queryDocumentSnapshots.getDocuments().get(0).getReference().update("quantity",++quantity);
                }
            });
        });
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return cart_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,plus,minus;
        TextView name,price,quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.txt_price_num);
            quantity = itemView.findViewById(R.id.quan);
            plus=itemView.findViewById(R.id.plus);
            minus=itemView.findViewById(R.id.minus);
        }
    }
}
