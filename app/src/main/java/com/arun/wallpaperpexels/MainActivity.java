package com.arun.wallpaperpexels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mbus,mcar,mtrain,mtrending;
    EditText editText;
    ImageButton search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycleview);
        mnature = findViewById(R.id.nature);
        mbus = findViewById(R.id.bus);
        mcar = findViewById(R.id.car);
        mtrain = findViewById(R.id.train);
        mtrending = findViewById(R.id.treanding);
        editText = findViewById(R.id.editTextTextPersonName);
        search = findViewById(R.id.imageView);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos();

        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="nature";
                getSearchImage(query);
            }
        });

        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="car";
                getSearchImage(query);
            }
        });

        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="bus";
                getSearchImage(query);
            }
        });

        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="train";
                getSearchImage(query);
            }
        });

        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString();
                if (query.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    getSearchImage(query);
                }
            }
        });


    }

    private void getSearchImage(String query) {

        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Try again ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos(){
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Try again ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

}