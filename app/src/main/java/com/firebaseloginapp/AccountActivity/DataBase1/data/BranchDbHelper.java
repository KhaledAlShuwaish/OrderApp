package com.firebaseloginapp.AccountActivity.DataBase1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BranchDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "branch.db";
    private static final int DATABASE_VERSION = 1;

    public BranchDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + BranchContract.BranchEntry.TABLE_NAME + " ("
                + BranchContract.BranchEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BranchContract.BranchEntry.COLUMN_Branch_NAME + " TEXT NOT NULL,"
                + BranchContract.BranchEntry.COLUMN_Branch_Location + " TEXT);";
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
