package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.Product;
import fpoly.sonhaph40315_20_6.duan_prostore.database.SanPham_Database;

public class SanPham_Dao {

    private final SanPham_Database dbHelper;
    private Context context;

    public SanPham_Dao(SanPham_Database dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.context = context;
    }

    public ArrayList<Product> getSanPham() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int imageResId = cursor.getInt(cursor.getColumnIndex("imageResId"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                String category = cursor.getString(cursor.getColumnIndex("category"));


                Product product = new Product(id, imageResId, name, price, quantity, size, category);
                list.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public long insertSanPham(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imageResId", product.getImageResId());  // đúng với DB
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("quantity", product.getQuantity());
        values.put("size", product.getSize());
        values.put("category", product.getCategory());

        return db.insert("SanPham", null, values);
    }

    public int updateSanPham(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imageResId", product.getImageResId());  // đúng với DB
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("quantity", product.getQuantity());
        values.put("size", product.getSize());
        values.put("category", product.getCategory());

        return db.update("SanPham", values, "id = ?", new String[]{String.valueOf(product.getId())});
    }


    public int deleteSanPham(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("SanPham", "id = ?", new String[]{String.valueOf(id)});
    }
}

