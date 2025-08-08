package fpoly.sonhaph40315_20_6.duan_prostore.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Database.OrderHelper;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.Order;

public class OrderDao {
    private SQLiteDatabase db;

    public OrderDao(Context context) {
        OrderHelper helper = new OrderHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Order order) {
        ContentValues values = new ContentValues();
        values.put("tenKhachHang", order.getTenKhachHang());
        values.put("soDienThoai", order.getSoDienThoai());
        values.put("diaChi", order.getDiaChi());
        values.put("hinhThucThanhToan", order.getHinhThucThanhToan());
        values.put("ngayDat", order.getNgayDat());
        values.put("tongTien", order.getTongTien());
        return db.insert("orders", null, values);
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM orders", null);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String tenKhachHang = c.getString(1);
            String soDienThoai = c.getString(2);
            String diaChi = c.getString(3);
            String hinhThucThanhToan = c.getString(4);
            String ngayDat = c.getString(5);
            double tongTien = c.getDouble(6);
            String sanPham = c.getString(7);
            String trangThai = c.getString(8);

            Order o = new Order(id, tenKhachHang, soDienThoai, diaChi,
                    hinhThucThanhToan, ngayDat, tongTien, sanPham, trangThai);

            list.add(o);
        }
        return list;
    }

}

