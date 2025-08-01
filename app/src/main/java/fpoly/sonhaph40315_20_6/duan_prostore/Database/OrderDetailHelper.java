package fpoly.sonhaph40315_20_6.duan_prostore.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDetailHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clothes.db"; // Dùng chung DB với app
    private static final int DATABASE_VERSION = 1;

    public OrderDetailHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS OrderDetails (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_id INTEGER, " +
                "product_name TEXT, " +
                "quantity INTEGER, " +
                "price REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nếu nâng cấp sau này
        db.execSQL("DROP TABLE IF EXISTS OrderDetails");
        onCreate(db);
    }
}
