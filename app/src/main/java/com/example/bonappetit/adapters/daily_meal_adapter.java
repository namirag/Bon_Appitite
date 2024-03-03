package com.example.bonappetit.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bonappetit.R;
import com.example.bonappetit.activities.DetailActivity;
import com.example.bonappetit.activities.Registrationactivity;
import com.example.bonappetit.activities.Welcomeactivity;
import com.example.bonappetit.models.daily_meal_model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class daily_meal_adapter extends RecyclerView.Adapter<daily_meal_adapter.ViewHolder> {

    Context context;
    List<daily_meal_model> list;

    public daily_meal_adapter(Context context, List<daily_meal_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_items,parent,false));
    }
    

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Picasso.get().load(list.get(pos).getImage()).error(R.drawable.briyani).into(holder.imageView);
        holder.name.setText(list.get(pos).getName());
        holder.type.setText(list.get(pos).getType());
        holder.description.setText(list.get(pos).getDescription());
        holder.rating.setText(list.get(pos).getRating());


        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("working", list.get(pos).getType());
                //startActivity(new Intent(daily_meal_adapter.this,DetailActivity.class));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("type",list.get(pos).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,type,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_food_daily);
            name = itemView.findViewById(R.id.text_food_daily);
            description = itemView.findViewById(R.id.text_dec_daily);
            type = itemView.findViewById(R.id.text_type_daily);
            rating = itemView.findViewById(R.id.text_rate_daily);
        }
    }
}
