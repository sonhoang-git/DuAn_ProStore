package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.database.NguoiDung_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.LienHe_Model;

public class LienHe_Dao {
    private final SQLiteDatabase db;

    public LienHe_Dao(Context context) {
        NguoiDung_Database database = new NguoiDung_Database(context);
        db = database.getWritableDatabase();
    }

    public long add_LienHe(LienHe_Model model) {
        ContentValues values = new ContentValues();
        values.put("fullname", model.getFullname());
        values.put("phone", model.getPhone());
        values.put("email", model.getEmail());
        values.put("address", model.getAddress());
        values.put("noidung", model.getNoidung());

        return db.insert("LienHe", null, values);
    }

    public ArrayList<LienHe_Model> getAllLienHe() {
        ArrayList<LienHe_Model> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM LienHe", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String fullname = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String noidung = cursor.getString(cursor.getColumnIndexOrThrow("noidung"));

                // Lưu ý thứ tự tham số đúng theo constructor:
                list.add(new LienHe_Model(id, fullname, email, phone, address, noidung));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean updateContact(LienHe_Model contact) {
        ContentValues values = new ContentValues();
        values.put("fullname", contact.getFullname());
        values.put("phone", contact.getPhone());
        values.put("email", contact.getEmail());
        values.put("address", contact.getAddress());
        values.put("noidung", contact.getNoidung());

        int result = db.update("LienHe", values, "id = ?", new String[]{String.valueOf(contact.getId())});
        return result > 0;
    }
}

