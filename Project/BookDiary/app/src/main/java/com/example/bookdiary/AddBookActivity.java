package com.example.bookdiary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddBookActivity extends AppCompatActivity {

    private EditText titleInput, authorInput;
    private Button saveButton, dateButton;
    private TextView dateTextView;

    private Date selectedDate = null;  // La fecha seleccionada, inicializada como null (tipo Date, no String)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titleInput = findViewById(R.id.titleInput);
        authorInput = findViewById(R.id.authorInput);
        saveButton = findViewById(R.id.saveButton);
        dateButton = findViewById(R.id.dateButton);  // Botón para abrir el selector de fecha
        dateTextView = findViewById(R.id.dateTextView);  // TextView para mostrar la fecha seleccionada

        // Configurar el botón "Añadir Fecha"
        dateButton.setOnClickListener(v -> openDatePicker());

        // Configurar el botón "Guardar"
        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String author = authorInput.getText().toString();

            // Si no se ha seleccionado una fecha, usamos "Fecha no especificada"
            String date = (selectedDate != null) ? selectedDate.toString() : "Fecha no especificada";

            // Crear un nuevo libro con la fecha seleccionada
            com.example.bookdiary.Book newBook = new com.example.bookdiary.Book(title, author, selectedDate);

            // Pasar el libro al MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("book", newBook);
            setResult(RESULT_OK, resultIntent);
            finish();  // Finalizar la actividad
        });
    }

    // Método para abrir el selector de fecha
    private void openDatePicker() {
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Enero es 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
            // Convertir el mes de 0-indexado a 1-indexado
            selectedMonth += 1;

            // Crear una nueva instancia de la clase Date
            selectedDate = new Date(selectedDayOfMonth, selectedMonth, selectedYear);

            // Mostrar la fecha en el TextView
            dateTextView.setText(selectedDate.toString());
        }, year, month, day);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
    }
}
