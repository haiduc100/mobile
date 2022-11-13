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
            currentUrl = url;
            inputUrl.setText("");
        });


        btnPrev.setOnClickListener((view)->{
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            int count = databaseHelper.getSize();
            Log.d("count","prev"+count);
//            if(count == 0){
//                Toast.makeText(getApplicationContext(), "Nothing to show!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            int id = databaseHelper.getIdImage(this.currentUrl);
//            if(id == 1){
//                Toast.makeText(getApplicationContext(), "Min", Toast.LENGTH_SHORT).show();
//            }else {
//                currentUrl= databaseHelper.getImageById(id-1);
//                Picasso.with(this).load(currentUrl).into(imageView);
//            }

        });

        btnNext.setOnClickListener(view -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            int count = databaseHelper.getSize();
            Log.d("count","next"+count);

//            if(count == 0){
//                Toast.makeText(getApplicationContext(), "Nothing to show!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            int id = databaseHelper.getIdImage(this.currentUrl);
//            if(id == count){
//                Toast.makeText(getApplicationContext(), "Max", Toast.LENGTH_SHORT).show();
//            }else {
//                currentUrl = databaseHelper.getImageById(id + 1);
//                Picasso.with(this).load(currentUrl).into(imageView);
//            }


        });



    }

}