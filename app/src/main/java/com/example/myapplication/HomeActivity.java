package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        RecyclerView recyclerView = findViewById(R.id.rvMain);

        // 1. Header Adapter
        HeaderAdapter headerAdapter = new HeaderAdapter();

        // 2. Artisan Adapter (Dummy data)
        List<Artisan> artisanList = new ArrayList<>();

        artisanList.add(new Artisan("Amira's Kitchen", "Home Baker","⭐⭐⭐⭐" ,"35 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Lumi Studio", "Candles", "⭐⭐⭐","18 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Pottery Haven", "Ceramics","⭐⭐⭐⭐⭐", "35 DT+", R.drawable.placeholder_image));
        artisanList.add(new Artisan("Goody Shop", "Knitting","⭐⭐⭐", "35 DT+", R.drawable.placeholder_image));

        ArtisanAdapter artisanAdapter = new ArtisanAdapter(artisanList);

        // 3. Combine them!
        ConcatAdapter concatAdapter = new ConcatAdapter(headerAdapter, artisanAdapter);
        recyclerView.setAdapter(concatAdapter);

        // 4. Make a 2-column grid, but let the Header (position 0) span both columns!
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // If position is 0 (Header), return 2 (full width). Otherwise return 1 (half width).
                return position == 0 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
    }
}