package com.example.sqlitepractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlitepractise.model.Product;


public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + Constant.PRODUCT_TABLE_NAME + "("
                + Constant.PRODUCT_id + " INTEGER PRIMARY KEY," + Constant.PRODUCT_NAME + " TEXT,"
                + Constant.PRODUCT_PRICE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
    public boolean addProduct(Product product){
            SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Constant.PRODUCT_NAME,"rahim");
        values.put(Constant.PRODUCT_PRICE,"677");
        if(db.insert(Constant.PRODUCT_TABLE_NAME,null,values)>0){
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }

    }
    public Product getproduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM"+Constant.PRODUCT_TABLE_NAME+"WHERE id="+String.valueOf(id);

        Cursor cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();

            Product product = new Product(
                    cursor.getInt(cursor.getColumnIndex(Constant.PRODUCT_id)),
                    cursor.getString(cursor.getColumnIndex(Constant.PRODUCT_NAME)),
                    cursor.getString(cursor.getColumnIndex(Constant.PRODUCT_PRICE))
            );
            cursor.close();
            db.close();
            return product;
        }
        else
            db.close();
            return null;
    }
}
