package com.example.janieamyot.chippy;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String TABLE_ACCOUNT = "accounts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACCOUNT_TYPE = "accounttype";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "+TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_TYPE+" TEXT,"+COLUMN_USERNAME+" TEXT PRIMARY KEY,"+COLUMN_NAME+" TEXT,"+COLUMN_LASTNAME+" TEXT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD+" TEXT"+")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        onCreate(db);
    }

    public void addAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        String type = "";

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, account.getUserName());
        values.put(COLUMN_NAME, account.getName());
        values.put(COLUMN_LASTNAME, account.getLastName());
        values.put(COLUMN_EMAIL, account.getEmail());
        values.put(COLUMN_PASSWORD, account.getPassword());
        if(account instanceof Admin){
            type = "Admin";
        }else if(account instanceof ServiceProvider){
            type = "ServiceProvider";
        }else if(account instanceof HomeOwner){
            type = "HomeOwner";
        }
        values.put(COLUMN_ACCOUNT_TYPE, type);

        db.insert(TABLE_ACCOUNT, null, values);

        db.close();
    }

    public Account findAccountByUserName(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_ACCOUNT +" WHERE "+COLUMN_USERNAME+" = \""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);

        Account account;

        if(cursor.moveToFirst()){
            String type = cursor.getString(0);
            if(type == "Admin"){
                account = new Admin();
            }else if(type == "ServiceProvider"){
                account = new ServiceProvider();
            }else if(type == "HomeOwner"){
                account = new HomeOwner();
            }else{
                account = null;
            }
            if(account != null){
                account.setUserName(cursor.getString(1));
                account.setName(cursor.getString(2));
                account.setLastName(cursor.getString(3));
                account.setEmail(cursor.getString(4));
                account.setPassword(cursor.getString(5));
            }
            cursor.close();
        } else{
            account = null;
        }
        db.close();
        return account;
    }

    public boolean findAdminAccount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String accountType = "Admin";
        boolean flag;

        String query = "Select * FROM "+ TABLE_ACCOUNT +" WHERE "+COLUMN_ACCOUNT_TYPE+" = \""+accountType+"\"";
        Cursor cursor = db.rawQuery(query, null);

        //Account account;

        if(cursor.moveToFirst()){
            flag = true;
            cursor.close();
        } else{
            flag = false;
        }
        db.close();
        return flag;
    }

    public ArrayList<Account> findAllAccounts(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_ACCOUNT;
        Cursor cursor = db.rawQuery(query, null);

        Account account;
        ArrayList<Account> accounts = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                String type = cursor.getString(0);
                if(type == "Admin"){
                    account = new Admin();
                }else if(type == "ServiceProvider"){
                    account = new ServiceProvider();
                }else if(type == "HomeOwner"){
                    account = new HomeOwner();
                }else{
                    account = null;
                }
                if(account != null){
                    account.setUserName(cursor.getString(1));
                    account.setName(cursor.getString(2));
                    account.setLastName(cursor.getString(3));
                    account.setEmail(cursor.getString(4));
                    account.setPassword(cursor.getString(5));
                    accounts.add(account);
                }

            }while (cursor.moveToNext());
            cursor.close();
        } else{
            accounts = null;
        }
        db.close();
        return accounts;
    }

    public boolean deleteAccountByUserName(String accountUserName){
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_ACCOUNT + " WHERE " + COLUMN_USERNAME + " = \"" + accountUserName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(1);
            db.delete(TABLE_ACCOUNT, COLUMN_USERNAME + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

}
