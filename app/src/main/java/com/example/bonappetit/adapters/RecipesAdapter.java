package com.example.bonappetit.adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.bonappetit.R;
        import com.example.bonappetit.models.RecipesModel;
        import com.squareup.picasso.Picasso;

        import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

    List<RecipesModel> list;

    public RecipesAdapter(Context context, List<RecipesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fargment_recipes_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder holder, int position) {

        int pos = holder.getAdapterPosition();
        Picasso.get().load(list.get(pos).getImage()).error(R.drawable.allahabad_ki_tehri).into(holder.imageView);
        holder.name.setText(list.get(pos).getName());
        holder.ingredents.setText(list.get(pos).getIngredients());
        holder.method.setText(list.get(pos).getMethod());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,ingredents,method;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recipe);
            name = itemView.findViewById(R.id.text_name);
            ingredents = itemView.findViewById(R.id.ingredents);
            method = itemView.findViewById(R.id.method);
        }
    }
}
