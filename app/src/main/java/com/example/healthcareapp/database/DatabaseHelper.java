package com.example.healthcareapp.database;


import java.io.File;
import java.text.SimpleDateFormat;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private String DB_PATH;
    private String DB_NAME;
    private Cursor cursor;
    private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public DatabaseHelper(Context p,String dbname) {
        super(p, dbname + ".db", null, 1);
        DB_NAME = dbname + ".db";
    }

    public String getNow(){
        return sdf.format(new java.util.Date());
    }

    public Cursor doQuery(String sql, String[] params) {
        try {
            cursor= getReadableDatabase().rawQuery(sql, params);
            return cursor;
        } catch (SQLException mSQLException) {
            System.err.println("-- doQuery --\n"+sql);
            mSQLException.printStackTrace(System.err);
            return null;
        }
    }

    public void doUpdate(String sql, String[] params) {
        try {
            getWritableDatabase().execSQL(sql, params);
        } catch (SQLException mSQLException) {
            System.err.println("-- doUpdate --\n"+sql);
            mSQLException.printStackTrace(System.err);
        }
    }


    public Cursor doQuery(String sql) {
        try {
            cursor = getReadableDatabase().rawQuery(sql,null);
            return cursor;
        } catch (SQLException mSQLException) {
            System.err.println("-- doQuery --\n"+sql);
            mSQLException.printStackTrace();
            return null;
        }
    }

    public void doUpdate(String sql) {
        try {
            this.getWritableDatabase().execSQL(sql);
        } catch (SQLException mSQLException) {
            System.err.println("-- doUpdate --\n"+sql);
            mSQLException.printStackTrace(System.err);
        }
    }

    public long getSize()
    {
        /* Open the database object in "read" mode. */
        final SQLiteDatabase db = getReadableDatabase();

        /* Get length of database file. */
        final String dbPath       = db.getPath();
        final File   dbFile       = new File(dbPath);
        final long   dbFileLength = dbFile.length();

        return (dbFileLength);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table userDetails(username text not null, email text not null, password text not null, primary key (email))");
        db.execSQL("Insert into userDetails(name, email, password) values ('alfred','alfred@gmail.com', 'alfred@12')");
    }

    public SQLiteDatabase getDB(){
        return getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
