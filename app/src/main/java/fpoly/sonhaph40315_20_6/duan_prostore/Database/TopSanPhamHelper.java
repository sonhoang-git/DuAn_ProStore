package fpoly.sonhaph40315_20_6.duan_prostore.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TopSanPhamHelper {
    private static final String DB_NAME = "clothes.db";
    private static SQLiteDatabase db;

    public static SQLiteDatabase getDatabase(Context context) {
        if (db == null || !db.isOpen()) {
            db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        }
        return db;
    }
}
