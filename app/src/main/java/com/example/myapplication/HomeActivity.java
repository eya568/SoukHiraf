package com.example.myapplication;

import static com.example.myapplication.R.*;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_owner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 1. Find the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvArtisans);

        // 2. Set it to a 2-column Grid
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // 3. Create some dummy data using your Artisan class
        List<Artisan> artisanList = new ArrayList<>();

        // Replace R.drawable.placeholder_image with actual images if you have them in your drawable folder!
        artisanList.add(new Artisan("Amira's Kitchen", "Home Baker", "35 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Lumi Studio", "Candles", "18 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Pottery Haven", "Ceramics", "35 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Goody Shop", "Knitting", "35 DT+", R.drawable.placeholder_image));

        // 4. Create the Adapter and attach it
        ArtisanAdapter adapter = new ArtisanAdapter(artisanList);
        recyclerView.setAdapter(adapter);
    }
}