package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NguoiDung_Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "NguoiDung.db";
    private static final int DB_VERSION = 1;

    public NguoiDung_Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_NguoiDung = "create table NguoiDung(id integer primary key autoincrement," +
                "fullname text not null," +
                "email text not null," +
                "phone text not null," +
                "address text not null)";
        sqLiteDatabase.execSQL(create_NguoiDung);
        String create_LienHe = "create table LienHe(id integer primary key autoincrement , " +
                "fullname text not null , " +
                "email text not null , " +
                "phone text not null ," +
                "address text not null," +
                "noidung text not null )";
        sqLiteDatabase.execSQL(create_LienHe);
        String createDanhGia = "CREATE TABLE DanhGia (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "address TEXT NOT NULL, " +        // ✅ Thêm dấu phẩy
                "avata INTEGER NOT NULL, " +
                "namesanpham TEXT NOT NULL, " +
                "username TEXT NOT NULL, " +
                "rating INTEGER, " +
                "noidung TEXT NOT NULL, " +
                "price INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createDanhGia);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists NguoiDung");
        sqLiteDatabase.execSQL("drop table if exists LienHe");
        sqLiteDatabase.execSQL("drop table if exists DanhGia");
        onCreate(sqLiteDatabase);
    }
}
