package com.example.seminar2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("seminar123","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("seminar123","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("seminar123","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("seminar123","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("seminar123","onDestroy");
    }
}