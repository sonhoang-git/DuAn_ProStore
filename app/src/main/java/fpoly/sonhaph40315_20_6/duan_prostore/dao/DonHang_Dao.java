package fpoly.sonhaph40315_20_6.duan_prostore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.database.DonHang_Database;
import fpoly.sonhaph40315_20_6.duan_prostore.model.DonHang_Model;
import fpoly.sonhaph40315_20_6.duan_prostore.model.ThongKe_Model;

public class DonHang_Dao {
    private final SQLiteDatabase db;

    public DonHang_Dao(Context context) {
        DonHang_Database database = new DonHang_Database(context);
        db = database.getWritableDatabase();
    }

    // Thêm đơn hàng
    public long add_DonHang(DonHang_Model donhang) {
        ContentValues values = new ContentValues();
        values.put("imageresid", donhang.getImageresid());
        values.put("name", donhang.getName());
        values.put("price", donhang.getPrice());
        values.put("size", donhang.getSize());
        values.put("quantity", donhang.getQuantity());
        values.put("status", donhang.getStatus());
        values.put("fullName", donhang.getFullName());
        values.put("phone", donhang.getPhone());
        values.put("address", donhang.getAddress());
        return db.insert("DonHang", null, values);
    }

    // Lấy tất cả đơn hàng
    public ArrayList<DonHang_Model> getAllDonHang() {
        ArrayList<DonHang_Model> list = new ArrayList<>();
        String query = "SELECT id, imageresid, name, price, size, quantity, status, fullName, phone, address FROM DonHang";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int imageresid = cursor.getInt(1);
                String name = cursor.getString(2);
               String price = cursor.getString(3);

               // int price = cursor.getInt(3);
                String size = cursor.getString(4);
                int quantity = cursor.getInt(5);
                String status = cursor.getString(6);
                String fullName = cursor.getString(7);
                String phone = cursor.getString(8);
                String address = cursor.getString(9);

                list.add(new DonHang_Model(id, imageresid, name, price, size, quantity, status, fullName, phone, address));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Lấy đơn hàng theo trạng thái
    public ArrayList<DonHang_Model> getDonHangByStatus(String statusFilter) {
        ArrayList<DonHang_Model> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT id, imageresid, name, price, size, quantity, status, fullName, phone, address FROM DonHang WHERE status = ?", new String[]{statusFilter});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int imageresid = cursor.getInt(1);
                String name = cursor.getString(2);
                String price = cursor.getString(3);
                String size = cursor.getString(4);
                int quantity = cursor.getInt(5);
                String status = cursor.getString(6);
                String fullName = cursor.getString(7);
                String phone = cursor.getString(8);
                String address = cursor.getString(9);

                list.add(new DonHang_Model(id, imageresid, name, price, size, quantity, status, fullName, phone, address));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    //

    // Cập nhật trạng thái đơn hàng
    public int updateStatus(int id, String newStatus) {
        ContentValues values = new ContentValues();
        values.put("status", newStatus);
        return db.update("DonHang", values, "id = ?", new String[]{String.valueOf(id)});
    }

    // Xóa tất cả đơn hàng
    public void clearAll() {
        db.delete("DonHang", null, null);
    }

    // Xóa đơn hàng theo ID
    public void deleteById(int id) {
        db.delete("DonHang", "id = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<DonHang_Model> getDonHangChoXacNhan() {
        return getDonHangByStatus("Chờ xác nhận");
    }

    public ArrayList<ThongKe_Model> getSoldProducts() {
        ArrayList<ThongKe_Model> result = new ArrayList<>();
        // Truy vấn nhóm theo name sản phẩm, tổng số lượng và doanh thu
        String sql = "SELECT name, SUM(quantity) as totalQuantity, " +
                "SUM(REPLACE(REPLACE(price, ' VND', ''), ',', '')) as totalRevenue " +
                "FROM DonHang GROUP BY name";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int totalQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("totalQuantity"));
                double totalRevenue = cursor.getDouble(cursor.getColumnIndexOrThrow("totalRevenue"));

                result.add(new ThongKe_Model(name, totalQuantity, totalRevenue));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
