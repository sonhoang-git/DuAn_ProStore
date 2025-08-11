package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Product;
import fpoly.sonhaph40315_20_6.duan_prostore.database.GioHang_Database;

public class GioHang_Dao {
    private final SQLiteDatabase db;

    public GioHang_Dao(Context context) {
        GioHang_Database dbHelper = new GioHang_Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean add_GioHang(Product product) {
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang WHERE name = ? AND size = ?",
                new String[]{product.getName(), product.getSize()});
        boolean result = false;
        try {
            if (cursor.moveToFirst()) {
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity")) + 1;
                ContentValues values = new ContentValues();
                values.put("quantity", quantity);
                int rows = db.update("GioHang", values, "name = ? AND size = ?",
                        new String[]{product.getName(), product.getSize()});
                result = rows > 0;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("imageresid", product.getImageResId()); // Lưu đúng ảnh drawable
                contentValues.put("name", product.getName());
                contentValues.put("price", String.valueOf(product.getPrice()));
                contentValues.put("size", product.getSize());
                contentValues.put("quantity", product.getQuantity());
                contentValues.put("category", product.getCategory());
                long insertResult = db.insert("GioHang", null, contentValues);
                result = insertResult != -1;
            }
        } finally {
            cursor.close();
        }
        return result;
    }

    public List<Product> getAllGioHang() {
        List<Product> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int imageresid = cursor.getInt(cursor.getColumnIndexOrThrow("imageresid"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String priceStr = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String size = cursor.getString(cursor.getColumnIndexOrThrow("size"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));

                double price = 0;
                try {
                    price = Double.parseDouble(priceStr.replaceAll("[^\\d.]", ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                list.add(new Product(id, imageresid, name, price, quantity, size, category));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void clearCart() {
        db.delete("GioHang", null, null);
    }
}
