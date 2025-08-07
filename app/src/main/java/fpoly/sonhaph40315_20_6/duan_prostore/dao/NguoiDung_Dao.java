package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.sonhaph40315_20_6.duan_prostore.database.NguoiDung_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class NguoiDung_Dao {
    private final SQLiteDatabase db;

    public NguoiDung_Dao(Context context) {
        NguoiDung_Database database = new NguoiDung_Database(context);
            db = database.getWritableDatabase();
    }

    public long add_NguoiDung(NguoiDung_Model nguoiDung) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", nguoiDung.getFullname());
        contentValues.put("email", nguoiDung.getEmail());
        contentValues.put("phone", nguoiDung.getPhone());
        contentValues.put("address", nguoiDung.getAddress());
        return db.insert("NguoiDung", null, contentValues);
    }

    public NguoiDung_Model get_NguoiDung() {
        Cursor cursor = db.rawQuery("SELECT * FROM NguoiDung LIMIT 1", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));

            cursor.close();
            return new NguoiDung_Model(id, fullName, email, phone, address);
        }
        cursor.close();
        return null;
    }

    public void updateUser(NguoiDung_Model user) {
        ContentValues values = new ContentValues();
        values.put("fullname", user.getFullname());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());

        db.update("NguoiDung", values, "id = ?", new String[]{String.valueOf(user.getId())});
    }
}
