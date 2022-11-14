package com.example.logbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  String currentUrl;
  int count;
  int currentNum;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EditText inputUrl = findViewById(R.id.inputUrl);
    Button btnAdd = findViewById(R.id.btnAdd);
    Button btnPrev = findViewById(R.id.btnPrev);
    Button btnNext = findViewById(R.id.btnNext);
    TextView totalPicture = findViewById(R.id.indexView);
    ImageView imgView = findViewById(R.id.imageView);
    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
    if (databaseHelper.getSize() > 0 && currentUrl == null) {
      currentUrl = databaseHelper.getImageById("1");
      count = databaseHelper.getSize();
      currentNum = 1;
      totalPicture.setText(currentNum + " / " + count);
      Picasso.with(this).load("" + currentUrl).into(imgView);
    }

    btnAdd.setOnClickListener(view -> {
      String url = inputUrl.getText().toString().trim();
      currentUrl = url;
      Picasso.with(this).load(currentUrl).into(imgView);

      databaseHelper.insertImage(this.currentUrl);
      Toast
        .makeText(getApplicationContext(), "Picture added!", Toast.LENGTH_SHORT)
        .show();
      count += 1;
      currentNum = count;
      totalPicture.setText(currentNum + " / " + count);
      inputUrl.setText("");
    });

    btnPrev.setOnClickListener(view -> {
      if (count == 0) {
        Toast
          .makeText(
            getApplicationContext(),
            "Nothing to show!",
            Toast.LENGTH_SHORT
          )
          .show();
        return;
      }
      int id = databaseHelper.getIdImage(currentUrl);
      currentNum = id;

      if (id == 1) {
        currentNum = id;

        Picasso.with(this).load(currentUrl).into(imgView);
        Toast
          .makeText(getApplicationContext(), "Min", Toast.LENGTH_SHORT)
          .show();
        totalPicture.setText(currentNum + " / " + count);
      } else {
        currentNum = id - 1;
        currentUrl = databaseHelper.getImageById(String.valueOf(id - 1));
        Picasso.with(this).load(currentUrl).into(imgView);
        totalPicture.setText(currentNum + " / " + count);
      }
    });

    btnNext.setOnClickListener(view -> {
      if (count == 0) {
        Toast
          .makeText(
            getApplicationContext(),
            "Nothing to show!",
            Toast.LENGTH_SHORT
          )
          .show();
        return;
      }
      int id = databaseHelper.getIdImage(this.currentUrl);
      if (id == count) {
        currentNum = id;
        Picasso.with(this).load(currentUrl).into(imgView);
        Toast
          .makeText(getApplicationContext(), "Max", Toast.LENGTH_SHORT)
          .show();
        totalPicture.setText(currentNum + " / " + count);
      } else {
        currentNum = id + 1;
        currentUrl = databaseHelper.getImageById(String.valueOf(id + 1));
        Picasso.with(this).load(currentUrl).into(imgView);
        totalPicture.setText(currentNum + " / " + count);
      }
    });
  }
}
