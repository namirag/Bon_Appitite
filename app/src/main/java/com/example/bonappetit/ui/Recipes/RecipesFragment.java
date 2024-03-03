package com.example.bonappetit.ui.Recipes;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.bonappetit.R;
        import com.example.bonappetit.adapters.RecipesAdapter;
        import com.example.bonappetit.models.RecipesModel;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.QueryDocumentSnapshot;
        import com.google.firebase.firestore.QuerySnapshot;

        import java.util.ArrayList;
        import java.util.List;

public class RecipesFragment extends Fragment {

    ImageView imageView;
    List<RecipesModel> list;
    RecipesAdapter recipesAdapter;
    RecyclerView recyclerView;

    public RecipesFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipesfragment, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.recyclerView);
        imageView = view.findViewById(R.id.recipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        db.collection("Recipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                list.add(new RecipesModel(document.getData().get("Image").toString(),document.getData().get("Name").toString(),document.getData().get("Ingredents").toString(),document.getData().get("Method").toString()));
                            }
                            recipesAdapter.notifyDataSetChanged();
                        }
                    }
                });


        recipesAdapter = new RecipesAdapter(getContext(),list);
        recyclerView.setAdapter(recipesAdapter);

        return view;

    }

}