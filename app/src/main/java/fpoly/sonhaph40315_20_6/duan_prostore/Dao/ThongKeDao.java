package fpoly.sonhaph40315_20_6.duan_prostore.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Database.TopSanPhamHelper;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.TopSanPham;

public class ThongKeDao {
    private SQLiteDatabase db;

    public ThongKeDao(Context context) {
        db = TopSanPhamHelper.getDatabase(context);
    }

    public double getDoanhThu(String fromDate, String toDate) {
        Cursor c = db.rawQuery("SELECT SUM(total_price) FROM Orders WHERE created_at BETWEEN ? AND ?", new String[]{fromDate, toDate});
        double total = 0;
        if (c.moveToFirst()) {
            total = c.getDouble(0);
        }
        c.close();
        return total;
    }

    public int getSoDon(String fromDate, String toDate) {
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM Orders WHERE created_at BETWEEN ? AND ?", new String[]{fromDate, toDate});
        int count = 0;
        if (c.moveToFirst()) {
            count = c.getInt(0);
        }
        c.close();
        return count;
    }

    public List<TopSanPham> getTopSanPham(String fromDate, String toDate) {
        List<TopSanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(
                "SELECT Products.name, SUM(OrderDetails.quantity) as total_sold " +
                        "FROM OrderDetails " +
                        "JOIN Orders ON OrderDetails.order_id = Orders.id " +
                        "JOIN Products ON OrderDetails.product_id = Products.id " +
                        "WHERE Orders.created_at BETWEEN ? AND ? " +
                        "GROUP BY Products.id ORDER BY total_sold DESC LIMIT 5",
                new String[]{fromDate, toDate});
        while (c.moveToNext()) {
            String name = c.getString(0);
            int soLuong = c.getInt(1);
            list.add(new TopSanPham(name, soLuong));
        }
        c.close();
        return list;
    }
}

