package com.example.mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addBook extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b1;
    EditText t1,t2,t3,t4;
    TextView tv;
    bookHelper bh;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("Add Book");

        b1 = findViewById(R.id.btnInsert);
        t1 = findViewById(R.id.txtBookName);
        t2 = findViewById(R.id.txtBookAuthor);
        t3 = findViewById(R.id.txtISBN);
        t4 = findViewById(R.id.txtPrice);
        tv = findViewById(R.id.viewBookID);

        spinner = findViewById(R.id.spCategory);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();

        Intent intent = getIntent();
        final String bookID = intent.getStringExtra("bID");
        if(!TextUtils.isEmpty(bookID))
        {
            tv.setText(bookID);
            t1.setText(intent.getStringExtra("bookName"));
            t2.setText(intent.getStringExtra("bookAuthor"));
            t3.setText(intent.getStringExtra("bookIsbn"));
            t4.setText(intent.getStringExtra("bookPrice"));
            spinner.setSelection(Integer.parseInt(intent.getStringExtra("categoryID"))-1);
            b1.setText("Update");
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tv.getText().toString()))
                {
                    bh = new bookHelper(getApplicationContext());
                    book bk = new book(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),Double.parseDouble(t4.getText().toString()),categoryID);
                    String msg = bh.insert(bk);
                    Toast.makeText(addBook.this, "Inserted : " + msg, Toast.LENGTH_SHORT).show();
                    clear();
                    startActivity(new Intent(addBook.this,MainActivity.class));
                }
                else
                {
                    bh = new bookHelper(getApplicationContext());
//                    book bk = new book(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),Double.parseDouble(t4.getText().toString()),categoryID);
                    String msg = bh.update(Integer.parseInt(tv.getText().toString()),t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),Double.parseDouble(t4.getText().toString()),categoryID);
                    Toast.makeText(addBook.this, "Updated : " + msg, Toast.LENGTH_SHORT).show();
                    clear();
                    startActivity(new Intent(addBook.this,MainActivity.class));
                }
            }
        });

    }

    private void loadSpinnerData() {
        bh = new bookHelper(getApplicationContext());
        List<String> fetchCategory = bh.fetchCategory();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fetchCategory);
        spinner.setAdapter(arrayAdapter);
    }

    public void clear()
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
    }

    int categoryID = 0;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bh = new bookHelper(getApplicationContext());
        Cursor cursor = bh.fetchCategoryID(parent.getItemAtPosition(position).toString());
        Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

        if(cursor.moveToFirst()){
            do{
                categoryID = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}