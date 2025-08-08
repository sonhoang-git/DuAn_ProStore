package fpoly.sonhaph40315_20_6.duan_prostore.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.sonhaph40315_20_6.duan_prostore.Database.ContactHelper;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.Contact;

public class ContactDao {
    private final ContactHelper dbHelper;

    public ContactDao(Context context) {
        dbHelper = new ContactHelper(context);
    }

   public boolean insertContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        values.put("email", contact.getEmail());
        values.put("address", contact.getAddress());
        values.put("message", contact.getMessage());
        values.put("date", contact.getDate());

        long result = db.insert("Contact", null, values);
        return result != -1;
    }


    public boolean updateContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        values.put("email", contact.getEmail());
        values.put("address", contact.getAddress());
        values.put("message", contact.getMessage());
        values.put("date", contact.getDate());

        int result = db.update("Contact", values, "id = ?", new String[]{String.valueOf(contact.getId())});
        return result > 0;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Contact", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                String address = cursor.getString(4);
                String message = cursor.getString(5);
                String date = cursor.getString(6);
                list.add(new Contact(id, name, phone, email, address, message,date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Contact", "id=?", new String[]{String.valueOf(id)});
    }
}
