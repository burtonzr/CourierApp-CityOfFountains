package com.example.cityoffountains.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cityoffountains.Model.Cart;
import com.example.cityoffountains.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "CityOfFountains.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE Couriers (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT, vehicle TEXT, age TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE Carts (ID INTEGER PRIMARY KEY AUTOINCREMENT, OrderID INTEGER, ProductName TEXT, ProductPrice TEXT, Quantity TEXT, CustomerID TEXT, RestaurantID INTEGER, Status TEXT)");
        db.execSQL("CREATE TABLE Orders (ID INTEGER PRIMARY KEY AUTOINCREMENT, CustomerID TEXT, deliveryAddress TEXT, deliveryPersonnel TEXT, totalAmount TEXT, paymentMethod TEXT, cardNumber TEXT, Status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setOrderID() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Carts SET OrderID = (SELECT ID FROM Orders ORDER BY ID DESC LIMIT 1) WHERE Status = 0";
        db.execSQL(query);
        db.close();
    }

    // Update Cart Status
    public void setStatus(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("UPDATE Carts SET Status = 1 WHERE CustomerID='%s'", username);
        db.execSQL(query);
        db.close();
    }

    // Insert in Database
    public boolean insert(String name, String email, String phone, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long ins = db.insert("Users", null, contentValues);
        if(ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertCourier(String name, String email, String phone, String vehicle, String age, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("vehicle", vehicle);
        contentValues.put("age", age);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long ins = db.insert("Couriers", null, contentValues);
        if(ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email=?", new String[] {email});
        if(cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=?", new String[] {username});
        if(cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[] {username, password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void createOrder(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Orders(CustomerID, deliveryAddress, deliveryPersonnel, totalAmount, paymentMethod, cardNumber, Status) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                order.getCustomerID(),
                order.getdeliveryAddress(),
                order.getdeliveryPersonnel(),
                order.gettotalAmount(),
                order.getpaymentMethod(),
                order.getcardNumber(),
                order.getStatus());
        db.execSQL(query);
    }

    public void addToCart(Cart cart) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Carts (OrderID, ProductName, ProductPrice, Quantity, CustomerID, RestaurantID, Status) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                cart.getOrderID(),
                cart.getProductName(),
                cart.getProductPrice(),
                cart.getQuantity(),
                cart.getCustomerID(),
                cart.getRestaurantID(),
                cart.getStatus());
        db.execSQL(query);
    }

    public List<Cart> getCartsByCustomerStatus(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Carts WHERE Status = 0 AND CustomerID=?", new String[] {username});

        final List<Cart> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                result.add(new Cart(c.getInt(c.getColumnIndex("OrderID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("ProductPrice")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("CustomerID")),
                        c.getInt(c.getColumnIndex("RestaurantID")),
                        c.getString(c.getColumnIndex("Status"))
                ));
            } while(c.moveToNext());
        }

        return result;
    }

    public List<Cart> getCartsByCustomerCompleted(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Carts WHERE Status = 1 AND CustomerID=? ORDER BY ID DESC", new String[] {username});

        final List<Cart> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                result.add(new Cart(c.getInt(c.getColumnIndex("OrderID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("ProductPrice")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("CustomerID")),
                        c.getInt(c.getColumnIndex("RestaurantID")),
                        c.getString(c.getColumnIndex("Status"))
                ));
            } while(c.moveToNext());
        }

        return result;
    }
}
