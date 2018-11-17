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

    //Accounts table
    public static final String TABLE_ACCOUNT = "accounts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACCOUNT_TYPE = "accountType";

    //Services table
    public static final String TABLE_SERVICE = "services";
    public static final String COLUMN_SERVICE_ID = "serviceId";
    public static final String COLUMN_SERVICE_NAME = "name";
    public static final String COLUMN_HOURLY_RATE = "hourlyRate";
    public static final String COLUMN_CATEGORY = "category";

    //Service provider profile table
    public static final String TABLE_SP_PROFILE = "serviceProviderProfile";
    public static final String COLUMN_STREET_NUMBER = "streetNumber";
    public static final String COLUMN_APARTMENT_NUMBER = "apartmentNumber";
    public static final String COLUMN_STREET_NAME = "streetName";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_LICENSED = "isLicensed";
    public static final String COLUMN_PHONE_NUMBER = "phoneNumber";

    //Service provider services table
    public static final String TABLE_SP_SERVICES = "serviceProviderServices";

    //Service provider availabilities table
    public static final String TABLE_SP_AVAILABILITIES = "serviceProviderAvailabilities";
    public static final String COLUMN_START_TIME = "startTime";
    public static final String COLUMN_END_TIME = "endTime";


    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Creates the accounts table
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "+TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_TYPE+" TEXT,"+COLUMN_USERNAME+" TEXT PRIMARY KEY,"+COLUMN_NAME+" TEXT,"+COLUMN_LASTNAME+" TEXT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD+" TEXT"+")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

        //Creates the services table
        String CREATE_SERVICES_TABLE = "CREATE TABLE "+TABLE_SERVICE+"("+COLUMN_SERVICE_ID+" INTEGER PRIMARY KEY,"+COLUMN_SERVICE_NAME+" TEXT,"+COLUMN_HOURLY_RATE+" TEXT,"+COLUMN_CATEGORY+" TEXT"+")";
        db.execSQL(CREATE_SERVICES_TABLE);

        //Creates the service provider profile table
        String CREATE_SP_PROFILE_TABLE = "CREATE TABLE "+TABLE_SP_PROFILE+"("+COLUMN_USERNAME+" TEXT PRIMARY KEY,"+COLUMN_STREET_NUMBER+" INTEGER,"+COLUMN_APARTMENT_NUMBER+" INTEGER,"+COLUMN_STREET_NAME+" TEXT,"+COLUMN_CITY+" TEXT,"+COLUMN_COUNTRY+" TEXT,"+COLUMN_DESCRIPTION+" TEXT,"+COLUMN_IS_LICENSED+" TEXT,"+COLUMN_PHONE_NUMBER+" TEXT,"+"FOREIGN KEY("+COLUMN_USERNAME+") REFERENCES "+TABLE_ACCOUNT+"("+COLUMN_USERNAME+")"+")";
        db.execSQL(CREATE_SP_PROFILE_TABLE);

        //Creates the service provider services table
        String CREATE_SP_SERVICES_TABLE = "CREATE TABLE "+TABLE_SP_SERVICES+"("+COLUMN_USERNAME+" TEXT,"+COLUMN_SERVICE_ID+" INTEGER,"+"FOREIGN KEY("+COLUMN_USERNAME+") REFERENCES "+TABLE_SP_PROFILE+"("+COLUMN_USERNAME+")"+")";
        db.execSQL(CREATE_SP_SERVICES_TABLE);

        //Creates the service provider availabilities tables
        String CREATE_SP_AVAILABILITIES_TABLE = "CREATE TABLE "+TABLE_SP_AVAILABILITIES+"("+COLUMN_USERNAME+" TEXT,"+COLUMN_START_TIME+" TEXT,"+COLUMN_END_TIME+" TEXT,"+"FOREIGN KEY("+COLUMN_USERNAME+") REFERENCES "+TABLE_SP_PROFILE+"("+COLUMN_USERNAME+")"+")";
        db.execSQL(CREATE_SP_AVAILABILITIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SP_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SP_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SP_AVAILABILITIES);
        onCreate(db);
    }

    //ACCOUNTS TABLE FUNCTIONS

    //To add an account to the database
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

    //To find an account by username
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

    //To determine if the admin account already exits
    public boolean adminExists(){
        SQLiteDatabase db = this.getReadableDatabase();

        String accountType = "Admin";
        boolean flag;

        String query = "Select * FROM "+ TABLE_ACCOUNT +" WHERE "+COLUMN_ACCOUNT_TYPE+" = \""+accountType+"\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            flag = true;
            cursor.close();
        } else{
            flag = false;
        }
        db.close();
        return flag;
    }

    //To find all the accounts in the database
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

    //To delete an account in the database based on its user name
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

    //SERVICES TABLE FUNCTIONS

    //To add a new service to the database
    public void addService(Service service){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_NAME, service.getName());
        values.put(COLUMN_HOURLY_RATE, service.getHourlyRate());
        values.put(COLUMN_CATEGORY, service.getCategory().getLabel());

        db.insert(TABLE_SERVICE, null, values);

        db.close();
    }

    //To delete a service in the database based on its name
    public boolean deleteService(String serviceName){
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_SERVICE + " WHERE " + COLUMN_SERVICE_NAME + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE, COLUMN_SERVICE_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    //To find all services in the database
    public ArrayList<Service> findAllServices(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Service> services = new ArrayList<Service>();

        if(cursor.moveToFirst()){
            do{
                services.add(new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), cursor.getString(3)));
            }while (cursor.moveToNext());
            cursor.close();
        } else{
            services = null;
        }
        db.close();
        return services;
    }

    //To find all services in the database in a given category
    public ArrayList<Service> findServicesByCategory(Category category){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE+" WHERE "+COLUMN_CATEGORY+" = \""+category.getLabel()+"\"";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Service> services = new ArrayList<Service>();

        if(cursor.moveToFirst()){
            do{
                services.add(new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), cursor.getString(3)));
            }while (cursor.moveToNext());
            cursor.close();
        } else{
            services = null;
        }
        db.close();
        return services;
    }

    //To edit the hourly rate of a service
    public void editServiceHourlyRate(String name, double hourlyRate){

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOURLY_RATE, hourlyRate);

        db.update(TABLE_SERVICE, values, COLUMN_SERVICE_NAME + "= " + name, null);

        db.close();
    }

    //To find a specific service in the database based on its name
    public Service findService(String serviceName){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "+ TABLE_SERVICE +" WHERE "+COLUMN_NAME+" = \""+serviceName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        Service service;

        if(cursor.moveToFirst()){
            service = new Service(Double.parseDouble(cursor.getString(2)), cursor.getString(1), cursor.getString(3));
            cursor.close();
        } else{
            service = null;
        }
        db.close();
        return service;
    }

    //SERVICE PROVIDER PROFILE FUNCTIONS

    //To add a column in the service provider profile table when the service provider completes his profile
    public void completeSpProfile(ServiceProvider sp){

    }

    //To edit the street number
    public void editStreetNumber(String userName, int newStreetNumber){

    }

    //To edit the apartment number
    public void editApartmentNumber(String userName, int newApartmentNumber){

    }

    //To edit the street name
    public void editStreetName(String userName, String newStreetName){

    }

    //To edit the city
    public void editCity(String userName, String newCity){

    }

    //To edit the country
    public void editCountry(String userName, String newCountry){

    }

    //To edit the description
    public void editDescription(String userName, String newDescription){

    }

    //To edit isLicensed
    public void editIsLicensed(String userName, boolean isLiscensed){

    }

    //To edit the phone number
    public void editPhoneNumber(String userName, String phoneNumber){

    }

    //To find the profile information of a specific service provider using his user name
    public ServiceProvider findServiceProvider(String userName){
        return new ServiceProvider();
    }

    //SERVICE PROVIDER SERVICES FUNCTIONS

    //To add a service associated to a specific service provider
    public void addSpService(String userName, String serviceName){

    }

    //To delete a service associated to a specific service provider
    public boolean deleteSpService(String userName, String serviceName){
        return false;
    }

    //To find the list of all the services associated to a service provider
    public ArrayList<Service> findAllSpServices(String userName){
        return null;
    }

    //SERVICE PROVIDER AVAILABILITIES FUNCTIONS

    
}
