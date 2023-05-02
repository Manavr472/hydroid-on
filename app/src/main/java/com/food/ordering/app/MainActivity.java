package com.food.ordering.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.ordering.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int selected_category_id;

    private int[] image = {R.drawable.basiel, R.drawable.beet_leaves,R.drawable.mixed_baby_leaves, R.drawable.kale, R.drawable.lettuce, R.drawable.broccoli, R.drawable.cabbage_green, R.drawable.red_cabbage
            , R.drawable.potato, R.drawable.sweet_potato};
    private double[] rating = {3.5, 4, 4.3, 4.1, 5, 4.3, 4.1, 3.9, 4.7, 4.9};
    private String[] dish_name;
    private int[] price = {225, 120, 110, 200, 150, 50, 180, 80, 50, 100};
    private int[] category_id = {1, 1, 1, 1, 1, 2, 2, 2, 3, 3};
    private int[] id = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private String[] cat_name;


    private List<DishModel> dishModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        dish_name = res.getStringArray(R.array.dish_names);
        cat_name = res.getStringArray(R.array.category_names);


        Bundle extras = getIntent().getExtras();
        String value;
        textView = findViewById(R.id.category_name);
        if (extras != null) {
            value = extras.getString("name");
            selected_category_id = extras.getInt("id");
            textView.setText(value);
            //The key argument here must match that used in the other activity
        }


        ImageView backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        recyclerView = findViewById(R.id.dish_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareTheList();
        dishAdapter adapter = new dishAdapter(dishModelList);
        recyclerView.setAdapter(adapter);


        //cart button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.toCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cart.class);
                startActivity(i);
            }
        });
    }

    private void prepareTheList() {
        dishModelList.clear();
        int count = 0;
        for (int p_id : id) {
            if (category_id[count] == selected_category_id) {
                DishModel dishModel = new DishModel(image[count], rating[count], price[count], dish_name[count], id[count], cat_name[selected_category_id - 1]);
                dishModelList.add(dishModel);
            }
            count++;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}