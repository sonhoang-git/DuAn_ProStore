package fpoly.sonhaph40315_20_6.duan_prostore.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "order_db";
    private static final int DB_VERSION = 2;

    public OrderHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenKhachHang TEXT, " +
                "soDienThoai TEXT, " +
                "diaChi TEXT, " +
                "hinhThucThanhToan TEXT, " +
                "ngayDat TEXT, " +
                "tongTien REAL, " +
                "sanPham TEXT, " +
                "trangThai TEXT)";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }
}
