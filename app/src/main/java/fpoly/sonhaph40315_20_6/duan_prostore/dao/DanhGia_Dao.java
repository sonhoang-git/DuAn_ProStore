package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.database.NguoiDung_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DanhGia_Model;

public class DanhGia_Dao {
    private SQLiteDatabase db;

    public DanhGia_Dao(Context context) {
        NguoiDung_Database dbHelper = new NguoiDung_Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertDanhGia(DanhGia_Model dg) {
        ContentValues values = new ContentValues();
        values.put("address", dg.getAddress());
        values.put("avata", dg.getAvata());
        values.put("namesanpham", dg.getNamesanpham());
        values.put("username", dg.getUserName());
        values.put("rating", dg.getRating());
        values.put("noidung", dg.getNoidung());
        values.put("price", dg.getPrice());
        return db.insert("DanhGia", null, values);
    }
    public ArrayList<DanhGia_Model> getAllDanhGia() {
        ArrayList<DanhGia_Model> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT id, address, avata, namesanpham, username, rating, noidung, price FROM DanhGia ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String address = cursor.getString(1);
                String avata = cursor.getString(2);
                String nameSP = cursor.getString(3);
                String user = cursor.getString(4);
                int rating = cursor.getInt(5);
                String noidung = cursor.getString(6);
                int price = cursor.getInt(7);

                DanhGia_Model dg = new DanhGia_Model(id, address, avata, nameSP, user, rating, noidung, price);
                list.add(dg);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<DanhGia_Model> getDanhGiaByStars(int stars) {
        ArrayList<DanhGia_Model> list = new ArrayList<>();
        Cursor cursor;
        if (stars <= 0) {
            cursor = db.rawQuery("SELECT id, address, avata, namesanpham, username, rating, noidung, price FROM DanhGia ORDER BY id DESC", null);
        } else {
            cursor = db.rawQuery("SELECT id, address, avata, namesanpham, username, rating, noidung, price FROM DanhGia WHERE rating = ? ORDER BY id DESC", new String[]{String.valueOf(stars)});
        }
        if (cursor.moveToFirst()) {
            do {
                list.add(new DanhGia_Model(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getInt(7)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Xóa 1 đánh giá theo id
    public int deleteDanhGia(int id) {
        return db.delete("DanhGia", "id=?", new String[]{String.valueOf(id)});
    }

    // Cập nhật đánh giá (nếu cần)
    public int updateDanhGia(DanhGia_Model dg) {
        ContentValues values = new ContentValues();
        values.put("address", dg.getAddress());
        values.put("avata", dg.getAvata());
        values.put("namesanpham", dg.getNamesanpham());
        values.put("username", dg.getUserName());
        values.put("rating", dg.getRating());
        values.put("noidung", dg.getNoidung());
        values.put("price", dg.getPrice());
        return db.update("DanhGia", values, "id=?", new String[]{String.valueOf(dg.getId())});
    }
}
