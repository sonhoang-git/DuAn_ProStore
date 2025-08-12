package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DonHang_Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "donhang.db";
    public static final int DB_VERSION =4 ;

    public DonHang_Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE DonHang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "imageresid INTEGER, " +
                "name TEXT, " +
                "price TEXT, " +
                "size TEXT, " +
                "quantity INTEGER, " +
                "status TEXT, " +
                "fullName TEXT, " +
                "phone TEXT, " +
                "address TEXT" +
                ")";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ để tạo lại bảng mới có thêm các cột
        db.execSQL("DROP TABLE IF EXISTS DonHang");
        onCreate(db);
    }
}
