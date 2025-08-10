package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DanhGia_Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "DanhGia.db";
    private static final int DB_VERSION = 1;

    public DanhGia_Database(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE DanhGia (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "address TEXT," +
                "namesanpham TEXT," +
                "userName INTEGER," +
                "rating TEXT," +
                "noidung TEXT,"+
                "price INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DanhGia");
        onCreate(db);
    }
}
