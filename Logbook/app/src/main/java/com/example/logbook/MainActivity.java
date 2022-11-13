package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    int totalPicture;
    String currentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputUrl = findViewById(R.id.inputUrl);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnPrev = findViewById(R.id.btnPrev);
        Button btnNext = findViewById(R.id.btnNext);
        TextView totalPicture = findViewById(R.id.indexView);
        ImageView imageView = findViewById(R.id.imageView);



        btnAdd.setOnClickListener(view -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

            String url = inputUrl.getText().toString().trim();
            Picasso.with(this).load(url).into(imageView);
            databaseHelper.insertImage(url);
            Toast.makeText(getApplicationContext(), "Picture added!", Toast.LENGTH_SHORT).show();
            inputUrl.setText("");
        });


        btnPrev.setOnClickListener((view)->{
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            int id = databaseHelper.getIdImage(this.currentUrl);
            if(id == 1){
                Toast.makeText(getApplicationContext(), "Min", Toast.LENGTH_SHORT).show();
            }else {
                currentUrl= databaseHelper.getImageById(id-1);
                Picasso.with(this).load(currentUrl).into(imageView);
            }

        });

        btnNext.setOnClickListener(view -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
           int id = databaseHelper.getIdImage(this.currentUrl);
           int count = databaseHelper.getSize();
           if(id == count){
               Toast.makeText(getApplicationContext(), "Max", Toast.LENGTH_SHORT).show();
               return ;
           }else {
               currentUrl= databaseHelper.getImageById(id+1);
               Picasso.with(this).load(currentUrl).into(imageView);
           }


        });

        // Default image
        currentUrl = "https://th.bing.com/th/id/OIP.3CRnT1KSwFkePYHzhcq6rgHaJQ?w=198&h=248&c=7&r=0&o=5&pid=1.7";
        Picasso.with(this).load(currentUrl).into(imageView);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.insertImage(currentUrl);
        totalPicture.setText(databaseHelper.getSize());


    }

}