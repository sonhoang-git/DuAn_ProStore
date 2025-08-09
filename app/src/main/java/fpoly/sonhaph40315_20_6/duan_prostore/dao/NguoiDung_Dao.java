package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.sonhaph40315_20_6.duan_prostore.database.NguoiDung_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.NguoiDung_Model;

public class NguoiDung_Dao {

    private final NguoiDung_Database database;
    private final SQLiteDatabase db;
    private static final String TABLE_NAME = "NguoiDung";

    public NguoiDung_Dao(Context context) {
        database = new NguoiDung_Database(context);
        db = database.getWritableDatabase();
    }

    // Kiểm tra đăng nhập
    public NguoiDung_Model checkLogin(String email, String password) {
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?",
                new String[]{email, password}
        );

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String fullname = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String userPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String role = cursor.getString(cursor.getColumnIndexOrThrow("role"));

            cursor.close();
            return new NguoiDung_Model(id, fullname, userEmail, phone, address, role, userPassword);
        }
        cursor.close();
        return null;
    }

    // Lấy 1 người dùng (ví dụ người dùng đầu tiên hoặc hiện tại)
    public NguoiDung_Model get_NguoiDung() {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " LIMIT 1", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String role = cursor.getString(cursor.getColumnIndexOrThrow("role"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            cursor.close();
            return new NguoiDung_Model(id, fullName, email, phone, address, role, password);
        }
        cursor.close();
        return null;
    }

    // Thêm người dùng mới với các trường fullname, email, phone, address, role, password
    public boolean add_NguoiDung(NguoiDung_Model nguoiDung) {
        // Kiểm tra email đã tồn tại chưa
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ?", new String[]{nguoiDung.getEmail()});
        if(cursor.getCount() > 0) {
            cursor.close();
            return false;
        }
        cursor.close();

        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", nguoiDung.getFullname());
        contentValues.put("email", nguoiDung.getEmail());
        contentValues.put("phone", nguoiDung.getPhone());
        contentValues.put("address", nguoiDung.getAddress());
        contentValues.put("role", nguoiDung.getRole() != null ? nguoiDung.getRole() : "user");
        contentValues.put("password", nguoiDung.getPassword());

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Cập nhật người dùng
    public boolean updateUser(NguoiDung_Model user) {
        ContentValues values = new ContentValues();
        values.put("fullname", user.getFullname());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());
        values.put("role", user.getRole());
        values.put("password", user.getPassword());

        int rows = db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(user.getId())});
        return rows > 0;
    }
    public boolean insertUser(String fullname, String email, String password, String role) {
        // Lấy SQLiteDatabase từ NguoiDung_Database
        SQLiteDatabase db = database.getWritableDatabase();

        // Kiểm tra email đã tồn tại chưa
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false; // Email đã tồn tại
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("email", email);
        values.put("password", password);
        values.put("role", role != null ? role : "user");

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

}
