package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GioHang_Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "GioHang.db";
    private static final int DB_VERSION = 1;
    public GioHang_Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_GioHang = "create table GioHang(id integer primary key autoincrement, " +
                "imageresid integer ," +
                "name text,price text ," +
                "size text," +
                "quantity integer)";
        db.execSQL(create_GioHang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists GioHang");
        onCreate(db);
    }
}
