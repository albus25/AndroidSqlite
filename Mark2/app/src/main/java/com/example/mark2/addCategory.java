package com.example.mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addCategory extends AppCompatActivity {
    Button b1;
    EditText t1;
    bookHelper bh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        setTitle("Add Category");

        b1 = findViewById(R.id.btnCatAdd);
        t1 = findViewById(R.id.txtCatName);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bh = new bookHelper(getApplicationContext());
                category ct = new category(t1.getText().toString());
                String msg = bh.insertCategory(ct);
                Toast.makeText(addCategory.this, "Yeah : " + msg, Toast.LENGTH_SHORT).show();
                clear();
                startActivity(new Intent(addCategory.this,MainActivity.class));
            }
        });
    }

    public void clear()
    {
        t1.setText("");
    }
}