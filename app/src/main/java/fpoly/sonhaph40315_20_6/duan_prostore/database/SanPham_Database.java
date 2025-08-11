package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fpoly.sonhaph40315_20_6.duan_prostore.R;

public class SanPham_Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "SanPham.db";
    private static final int DB_VERSION = 4;

    public SanPham_Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_SanPham = "CREATE TABLE SanPham (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "imageResId INTEGER NOT NULL," +       // Lưu id resource ảnh
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +               // Lưu giá dưới dạng số thực
                "quantity INTEGER NOT NULL," +
                "size TEXT NOT NULL," +
                "category TEXT NOT NULL" +
                ")";
        db.execSQL(create_SanPham);

        // Ví dụ thêm 1 số bản ghi mẫu
        String insert_SanPham = "INSERT INTO SanPham (imageResId, name, price, quantity, size, category) VALUES " +
                "(" + R.drawable.ic_kids1 + ", 'Áo Trẻ Em 1', 119000, 1, 'M', 'Kids')," +
                "(" + R.drawable.ic_kids2 + ", 'Áo Trẻ Em 2', 129000, 1, 'M', 'Kids')," +
                "(" + R.drawable.ic_kids3 + ", 'Áo Nam 1', 199000, 1, 'M', 'Men')," +
                "(" + R.drawable.ic_kids4 + ", 'Áo Nam 2', 139000, 1, 'M', 'Men')," +
                "(" + R.drawable.ic_kids3 + ", 'Áo Nữ 1', 159000, 1, 'M', 'Women')";
        db.execSQL(insert_SanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        onCreate(db);
    }
}
