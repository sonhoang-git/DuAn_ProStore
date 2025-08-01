package fpoly.sonhaph40315_20_6.duan_prostore.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Database.OrderDetailHelper;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.OrderDetail;

public class OrderDetailDao {
    private SQLiteDatabase db;

    public OrderDetailDao(Context context) {
        OrderDetailHelper helper = new OrderDetailHelper(context);
        db = helper.getWritableDatabase();
    }

    // Thêm chi tiết đơn hàng
    public long insertOrderDetail(OrderDetail detail) {
        ContentValues values = new ContentValues();
        values.put("order_id", detail.getOrderId());
        values.put("product_name", detail.getTenSanPham()); // dùng tenSanPham
        values.put("quantity", detail.getSoLuong());
        values.put("price", detail.getDonGia());
        return db.insert("OrderDetails", null, values);
    }

    // Lấy danh sách chi tiết theo order_id
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM OrderDetails WHERE order_id = ?", new String[]{String.valueOf(orderId)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int oid = cursor.getInt(1);
            String tenSP = cursor.getString(2);      // đọc đúng theo model
            int soLuong = cursor.getInt(3);
            double donGia = cursor.getDouble(4);
            list.add(new OrderDetail(id, oid, tenSP, soLuong, donGia));
        }
        cursor.close();
        return list;
    }

    public int deleteById(int id) {
        return db.delete("OrderDetails", "id = ?", new String[]{String.valueOf(id)});
    }

    public int deleteByOrderId(int orderId) {
        return db.delete("OrderDetails", "order_id = ?", new String[]{String.valueOf(orderId)});
    }
}
