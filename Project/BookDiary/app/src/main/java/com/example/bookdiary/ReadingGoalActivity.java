package com.example.midiariodelecturas;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadingGoalActivity extends AppCompatActivity {

    private int goal = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_goal);

        TextView goalText = findViewById(R.id.goalText);
        goalText.setText("Meta de lectura: " + goal + " libros en 2025");

        // Aquí puedes agregar barra de progreso o contador
    }
}
