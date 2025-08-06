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

    public void add_GioHang(Product product) {
        Cursor cursor = db.rawQuery("Select * from GioHang where name = ? and size = ?", new String[]{product.getName(), product.getSize()});
        if (cursor.moveToFirst()) {
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity")) + 1;
            ContentValues values = new ContentValues();
            values.put("quantity", quantity);
            db.update("GioHang", values, "name = ? and size = ?", new String[]{product.getName(), product.getSize()});

        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("imageresid", product.getImageResId());
            contentValues.put("name", product.getName());
            contentValues.put("price", product.getPrice());
            contentValues.put("size", product.getSize());
            contentValues.put("quantity", 1);
            db.insert("GioHang", null, contentValues);
        }
        cursor.close();
    }
    public void updateQuantity(String name, String size, int newQuantity) {
        ContentValues values = new ContentValues();
        values.put("quantity", newQuantity);
        db.update("GioHang", values, "name = ? AND size = ?", new String[]{name, size});
    }
    public void remove_GioHang(Product product) {
        Cursor cursor = db.rawQuery("Select * from GioHang where name = ? and size = ?", new String[]{product.getName(), product.getSize()});
        if (cursor.moveToFirst()) {
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
            if (quantity > 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("quantity", quantity - 1);
                db.update("GioHang", contentValues, "name = ? and size = ?", new String[]{product.getName(), product.getSize()});
            } else {
                db.delete("GioHang", "name = ? and size = ?", new String[]{product.getName(), product.getSize()});
            }
        }
        cursor.close();
    }

    public List<Product> getAllGioHang() {
        List<Product> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from GioHang", null);
        if (cursor.moveToFirst()) {
            do {
                int imageresid = cursor.getInt(cursor.getColumnIndexOrThrow("imageresid"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String size = cursor.getString(cursor.getColumnIndexOrThrow("size"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                Product product = new Product( imageresid,name,price,"Cart");
                product.setSize(size);
                product.setQuantity(quantity);
                list.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void clearCart() {
        db.delete("GioHang", null, null);
    }


}
