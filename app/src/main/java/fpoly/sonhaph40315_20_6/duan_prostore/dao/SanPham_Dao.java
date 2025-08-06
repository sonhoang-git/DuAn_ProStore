package fpoly.sonhaph40315_20_6.duan_prostore.dao;

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
        Cursor cursor = db.rawQuery("Select * FROM SanPham",null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String imagename = cursor.getString(cursor.getColumnIndexOrThrow("imagename"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));

                int imageResId = context.getResources().getIdentifier(imagename,"drawable",context.getPackageName());
                Product product = new Product(imageResId,name,price,category);
                list.add(product);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
