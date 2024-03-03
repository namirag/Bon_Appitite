package com.example.bonappetit.adapters;


//import android.annotation.SuppressLint;
import android.app.Activity;
//import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.models.home_horizon_model;
import com.example.bonappetit.models.home_vertical_model;

import java.util.ArrayList;
//import java.util.List;

@SuppressWarnings({"ALL","CanBeFinal"})
public class home_horizon_adapter extends RecyclerView.Adapter<home_horizon_adapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<home_horizon_model> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public home_horizon_adapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<home_horizon_model> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    /* Context context;
    List<home_horizon_model> list;

    public home_horizon_adapters(Context context,List<home_horizon_model> list) {
        this.context = context;
        this.list = list;
    }*/

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_icons, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos = holder.getAdapterPosition();

        holder.imageview.setImageResource(list.get(pos).getImage());
        holder.name.setText(list.get(pos).getName());

        if (check) {
            ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();
            homeVerticalModels.add(new home_vertical_model(R.drawable.burger, "Burger1", "10:00-20:00", "5.0", "180/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.pizza, "Pizza", "11:00-20:00", "5.0", "150/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.briyani, "Biryani", "1:00-20:00", "5.0", "110/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.zigzag_spicy_pasta, "Pasta", "1:30-20:00", "5.0", "250/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.garlic_bread_1, "Garlic Bread", "10:00-20:00", "5.0", "180/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.noodles, "Alepino Noodels", "10:00-20:00", "5.0", "180/-"));
            homeVerticalModels.add(new home_vertical_model(R.drawable.ice_cream, "Ice Cream with scoops", "10:00-20:00", "5.0", "180/-"));

            updateVerticalRec.callback(pos, homeVerticalModels);
            check = false;
        }
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index = pos;
                    notifyDataSetChanged();
                    Log.d("working", "onClick: ");
                    if (pos == 0) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.burger, "Simple Burger", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.burger_cheeseburger, "Big-Burger Mayo", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.burger3, "Burger Cheese", "1:00-20:00", "5.0", "110/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.burger_4, "Non-veg Burger", "1:30-20:00", "5.0", "250/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);

                    } else if (pos == 1) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.pizza, "Chesse Pizza", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.pizza2, "Mexican Pizza", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.pizza3, "Italian Pizza", "1:00-20:00", "5.0", "110/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);
                    } else if (pos == 2) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.briyani, "Biryani", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.biryani_2, "Matka Biryani", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.biryani_3, "Kadhai Biryani", "1:00-20:00", "5.0", "110/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);

                    } else if (pos == 3) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.creamycheesepasta, "creamy Mac & Cheese pasta", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.lemon_pasta, "Lemon pasta", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.penne_pasta, "Penne pasta", "1:00-20:00", "5.0", "110/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.zigzag_spicy_pasta, "zigzag pasta", "11:00-20:00", "5.0", "150/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);
                    }
                    else if (pos == 4) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.garlic_bread_1, "Garlic Bread", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.garlic_bread_2, "Garlic Bread tosted", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.garlic_bread_3, "Slim Morning GBread", "1:00-20:00", "5.0", "110/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.garlic_bread_4, "Chessey Baked Garlic Bread", "11:00-20:00", "5.0", "150/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);
                    }
                    else if (pos == 5) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.noodles, "Alepino Noodels", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.noodles2, "Spicy classic row Noodles", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.noodles_3, "Long stright Italian Noodles", "1:00-20:00", "5.0", "110/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.noodles_4, "Vegies-blasted Noodles", "11:00-20:00", "5.0", "150/-"));

                        updateVerticalRec.callback(pos, homeVerticalModels);
                    }
                    else if (pos == 6) {

                        ArrayList<home_vertical_model> homeVerticalModels = new ArrayList<>();

                        homeVerticalModels.add(new home_vertical_model(R.drawable.ice_cream, "Ice Cream with scoops", "10:00-20:00", "5.0", "180/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.ice_cream_2, "Chocolate Icecream", "11:00-20:00", "5.0", "150/-"));
                        homeVerticalModels.add(new home_vertical_model(R.drawable.ice_cream_3, "Vanilla Ice Cream", "1:00-20:00", "5.0", "110/-"));


                        updateVerticalRec.callback(pos, homeVerticalModels);
                    }
                }
            });

            if(select) {
                if (pos == 0) {
                    holder.cardview.setBackgroundResource(R.drawable.default_bg);
                    select = false;
                }
            }
            else{
                if(row_index == pos){
                    holder.cardview.setBackgroundResource(R.drawable.default_bg);
                }
                else{
                    holder.cardview.setBackgroundResource(R.drawable.change_bg);
                }
            }
        }
    //}


    @Override
    public int getItemCount() {return list.size();}

    @SuppressWarnings({"InnerClassMayBeStatic","CanBeFinal"})
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView name;
        CardView cardview;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imageview = itemView.findViewById(R.id.horizon_img);
            name = itemView.findViewById(R.id.horizon_text);
            cardview = itemView.findViewById(R.id.card_view);
        }
        }
}
