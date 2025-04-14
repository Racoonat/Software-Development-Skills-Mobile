package com.example.bookdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> bookList;
    private ArrayAdapter<String> adapter;
    private ListView listViewBooks;
    private Button btnAddBook, btnReadingGoal;

    private ActivityResultLauncher<Intent> addBookLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewBooks = findViewById(R.id.bookListView);
        btnAddBook = findViewById(R.id.addBookButton);
        btnReadingGoal = findViewById(R.id.readingGoalButton);

        bookList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listViewBooks.setAdapter(adapter);

        // Launcher moderno para recibir resultado desde AddBookActivity
        addBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Book receivedBook = (Book) data.getSerializableExtra("book");

                            if (receivedBook != null) {
                                String bookInfo = receivedBook.getTitle() + " - " +
                                        receivedBook.getAuthor() + " (" +
                                        receivedBook.getDateRead().getFormatted() + ")";

                                bookList.add(bookInfo);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                addBookLauncher.launch(intent);
            }
        });

        btnReadingGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.bookdiary.ReadingGoalActivity.class);
                startActivity(intent);
            }
        });
    }
}