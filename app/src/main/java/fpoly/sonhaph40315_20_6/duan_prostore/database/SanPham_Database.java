package fpoly.sonhaph40315_20_6.duan_prostore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SanPham_Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "SanPham.db";
    private static final int DB_VERSION = 1;
    public SanPham_Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_SanPham = "create table SanPham(id integer primary key autoincrement," + "name text not null," +
                "imagename text not null," +
                "price text not null," +
                "category text not null)";
        db.execSQL(create_SanPham);

        String insert_SanPham = "insert into SanPham(name,imagename,price,category) values" +
                "('Áo Trẻ Em 1', 'ic_kids1', '119,000 VND', 'Kids'),"
                + "('Áo Trẻ Em 2', 'ic_kids2', '129,000 VND', 'Kids'),"
                + "('Áo Nam 1', 'ic_kids3', '199,000 VND', 'Men'),"
                + "('Áo Nam 2', 'ic_kids4', '139,000 VND', 'Men'),"
                +"('Áo Nữ 1', 'ic_kids1', '159,000 VND', 'Women')";
        db.execSQL(insert_SanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists SanPham");
        onCreate(db);
    }
}
