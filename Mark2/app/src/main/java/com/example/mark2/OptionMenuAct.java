package com.example.mark2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OptionMenuAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.bookMenu:
                startActivity(new Intent(OptionMenuAct.this,addBook.class));
                return true;

            case R.id.categoryMenu:
                startActivity(new Intent(OptionMenuAct.this,addCategory.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}