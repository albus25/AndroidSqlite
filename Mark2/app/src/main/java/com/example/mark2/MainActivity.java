package com.example.mark2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends OptionMenuAct {
    ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    bookHelper bh;
    List<book> books;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String, String>>();

    final String[] from = new String[]{"tblbook._id as _id","tblbook.bname as bname","tblbook.bauthor as bauthor",
            "tblbook.isbn as isbn","tblbook.price as price","tblcategory.categoryName as categoryName"};
    Cursor cursor;
    final int[] to = new int[]{R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textCategoryName};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Show Data");

        books = new ArrayList<>();
        listView = findViewById(R.id.lstBook);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String,String> bk = arrayList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(bk.get("bname") + " kya karna hai iska?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            Intent intent = new Intent(MainActivity.this, addBook.class);
                            intent.putExtra("bID", bk.get("bid"));
                            intent.putExtra("bookName", bk.get("bname"));
                            intent.putExtra("bookAuthor", bk.get("bauthor"));
                            intent.putExtra("bookIsbn", bk.get("isbn"));
                            intent.putExtra("bookPrice", bk.get("price"));
                            intent.putExtra("categoryID",bk.get("categoryID"));
                            intent.putExtra("categoryName",bk.get("categoryName"));
                            startActivity(intent);
                        }catch (Exception e)
                        {
                            Log.e("","hola",e);
                        }
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                        String msg = bh.delete(Integer.parseInt(bk.get("bid")));
                        Toast.makeText(MainActivity.this, "Deleted : " + msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                    }
                });
                builder.show();
            }
        });

        try {
            bh = new bookHelper(getApplicationContext());
            books = bh.fetch();

            for(int i=0;i<books.size();i++)
            {
                book b = books.get(i);
                HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("bid",String.valueOf(b.getBid()));
                hashMap.put("bname",b.getBname());
                hashMap.put("bauthor",b.getBauthor());
                hashMap.put("isbn",b.getIsbn());
                hashMap.put("price",String.valueOf(b.getPrice()));
                hashMap.put("categoryID",String.valueOf(b.getC().getCategoryID()));
                hashMap.put("categoryName",b.getC().getCategoryName());

                arrayList.add(hashMap);
            }

            SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,arrayList,R.layout.book_display,
                    new String[]{"bid","bname","bauthor","isbn","price","categoryName"},
                    new int[]{R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textCategoryName});

            listView.setAdapter(adapter);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}