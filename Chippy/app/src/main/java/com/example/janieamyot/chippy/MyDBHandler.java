package com.example.janieamyot.chippy;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChippyDB.db";
    public static final String TABLE_ACCOUNT = "accounts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACCOUNT_TYPE = "accountType";

    public static final String TABLE_SERVICE = "services";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVICE_NAME = "name";
    public static final String COLUMN_HOURLY_RATE = "hourlyRate";
    public static final String COLUMN_CATEGORY = "category";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Creates the accounts table
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "+TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_TYPE+" TEXT,"+COLUMN_USERNAME+" TEXT PRIMARY KEY,"+COLUMN_NAME+" TEXT,"+COLUMN_LASTNAME+" TEXT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD+" TEXT"+")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

        //Creates the services table
        String CREATE_SERVICES_TABLE = "CREATE TABLE "+TABLE_SERVICE+"("+COLUMN_ID+" INTEGER PRIMARY KEY,"+COLUMN_SERVICE_NAME+" TEXT,"+COLUMN_HOURLY_RATE+" TEXT,"+COLUMN_CATEGORY+" TEXT"+")";
        db.execSQL(CREATE_SERVICES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
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
            if(type.equals("Admin")){
                account = new Admin();
            }else if(type.equals("ServiceProvider")){
                account = new ServiceProvider();
            }else if(type.equals("HomeOwner")){
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

    public boolean adminExists(){
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
                if(type.equals("Admin")){
                    account = new Admin();
                }else if(type.equals("ServiceProvider")){
                    account = new ServiceProvider();
                }else if(type.equals("HomeOwner")){
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

    public void addService(Service service){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_NAME, service.getName());
        values.put(COLUMN_HOURLY_RATE, service.getHourlyRate());
        values.put(COLUMN_CATEGORY, service.getCategory().getLabel());

        db.insert(TABLE_SERVICE, null, values);

        db.close();
    }

    public boolean deleteService(String serviceName){
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_SERVICE + " WHERE " + COLUMN_SERVICE_NAME + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    public ArrayList<Service> findAllServices(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Service> services = new ArrayList<Service>();

        if(cursor.moveToFirst()){
            do{
                services.add(new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), new Category(cursor.getString(3))));
            }while (cursor.moveToNext());
            cursor.close();
        } else{
            services = null;
        }
        db.close();
        return services;
    }

    public ArrayList<Service> findServicesByCategory(Category category){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE+" WHERE "+COLUMN_CATEGORY+" = \""+category.getLabel()+"\"";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Service> services = new ArrayList<Service>();

        if(cursor.moveToFirst()){
            do{
                services.add(new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), new Category(cursor.getString(3))));
            }while (cursor.moveToNext());
            cursor.close();
        } else{
            services = null;
        }
        db.close();
        return services;
    }

    public void editServiceHourlyRate(String name, double hourlyRate){

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOURLY_RATE, hourlyRate);

        db.update(TABLE_SERVICE, values, COLUMN_SERVICE_NAME + "= " + name, null);

        db.close();
    }

    public Service findService(String serviceName){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE +" WHERE "+COLUMN_NAME+" = \""+serviceName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        Service service;

        if(cursor.moveToFirst()){
            service = new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), new Category(cursor.getString(3)));
            cursor.close();
        } else{
            service = null;
        }
        db.close();
        return service;
    }

}
