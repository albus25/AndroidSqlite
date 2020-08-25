package com.example.mark2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class bookHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;

    static final int DB_VERSION = 1;
    static final String db_name = "mark2.db";
    public static final String table_name = "tblbook";
    public static final String table_category = "tblcategory";

    //Book
    public static final String _ID = "_id";
    public static final String bname = "bname";
    public static final String bauthor = "bauthor";
    public static final String price = "price";
    public static final String isbn = "isbn";
    public static final String categoryID = "categoryID";

    //Category
    public static final String Category_ID = "categoryID";
    public static final String Category_Name = "categoryName";

    public static final String create_table = "create table " + table_name + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + bname + " TEXT NOT NULL, " + bauthor + " TEXT NOT NULL, " + price + " FLOAT NOT NULL, " + isbn + " TEXT NOT NULL, " +
            Category_ID + " INTEGER, FOREIGN KEY(" + categoryID + ") REFERENCES " + table_category + " (" + Category_ID + ") );";

    public static final String create_table_category = "create table " + table_category + "(" + Category_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Category_Name + " TEXT NOT NULL);";

    public bookHelper(Context context)
    {
        super(context,db_name,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        db.execSQL(create_table_category);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_category);
        onCreate(db);
    }

    //Book
    public String insert(book bk)
    {
        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(bname,bk.getBname());
            contentValues.put(bauthor,bk.getBauthor());
            contentValues.put(isbn,bk.getIsbn());
            contentValues.put(price,bk.getPrice());
            contentValues.put(categoryID,bk.getcID());

            long id = sqLiteDatabase.insert(table_name,null,contentValues);
            if(id!=-1)
            {
                Toast.makeText(context, "Yeah", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Naah", Toast.LENGTH_SHORT).show();
            }

            return "Yeah " + id;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String update(int bookID,String bookName,String bookAuthor,String bookIsbn,Double bookPrice,int cID)
    {
        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(bname,bookName);
            contentValues.put(bauthor,bookAuthor);
            contentValues.put(price,bookPrice);
            contentValues.put(isbn,bookIsbn);
            contentValues.put(categoryID,cID);
            Log.e("cv:",contentValues.toString());
            long id = sqLiteDatabase.update(table_name,contentValues,_ID + "=?",new String[]{String.valueOf(bookID)});
            if(id == 1)
            {
                Toast.makeText(context, "Yeah", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Naah", Toast.LENGTH_SHORT).show();
            }

            return "Yeah " + id;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String delete(int bookID)
    {
        try {
            sqLiteDatabase = this.getWritableDatabase();
            long id = sqLiteDatabase.delete(table_name,_ID + "=?",new String[]{String.valueOf(bookID)});
            if(id == 1)
            {
                Toast.makeText(context, "Yeah", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Naah", Toast.LENGTH_SHORT).show();
            }

            return "Yeah " + id;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public List<book> fetch()
    {
        List<book> stringList = new ArrayList<book>();
        String query = "SELECT b._id,b.bname,b.bauthor,b.isbn,b.price,c.categoryID,c.categoryName FROM tblbook b,tblcategory c where " +
                "b.categoryID = c.categoryID";
        sqLiteDatabase = this.getReadableDatabase();
//        String[] columns = new String[]{_ID,bname,bauthor,price,isbn,Category_Name};
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        Log.e("Query : ",query);
        if(cursor.moveToFirst())
        {
            do{
                book b = new book();
                b.setBid(cursor.getInt(0));
                b.setBname(cursor.getString(1));
                b.setBauthor(cursor.getString(2));
                b.setIsbn(cursor.getString(3));
                b.setPrice(cursor.getDouble(4));

                category c = new category();
                c.setCategoryID(cursor.getInt(5));
                c.setCategoryName(cursor.getString(6));
                b.setC(c);

                stringList.add(b);
            }while (cursor.moveToNext());
        }
        for (int i=0;i<stringList.size();i++){
        Log.e("StringQuery : ", String.valueOf(stringList.get(i)));}
        return stringList;
    }

    //Category
    public String insertCategory(category c)
    {
        try{
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Category_Name,c.getCategoryName());

            long id = sqLiteDatabase.insert(table_category,null,contentValues);
            if(id != -1)
            {
                Toast.makeText(context, "Yeah", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Naah", Toast.LENGTH_SHORT).show();
            }

            return "Yeah " + id;
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<String> fetchCategory()
    {
        List<String> stringList = new ArrayList<String>();
        String query = "SELECT * FROM " + table_category;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery(query,null);

        if(cursor1.moveToFirst())
        {
            do{
                stringList.add(cursor1.getString(1));
            }while (cursor1.moveToNext());
        }

        return stringList;
    }

    public Cursor fetchCategoryID(String selectedValue)
    {
        int categoryID;
        String query = "SELECT categoryID FROM " + table_category;
        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = new String[]{Category_ID};
        Cursor cursor = sqLiteDatabase.query(table_category,columns,"categoryName=?",new String[]{selectedValue},null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
