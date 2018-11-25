package com.firebaseloginapp.AccountActivity.DataBase1.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

public class BranchProvider extends ContentProvider {
    public static final String LOG_TAG = BranchProvider.class.getSimpleName();
    private static final int Branch = 100;
    private static final int Branch_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(BranchContract.CONTENT_AUTHORITY, BranchContract.PATH_Branch, Branch);
        sUriMatcher.addURI(BranchContract.CONTENT_AUTHORITY, BranchContract.PATH_Branch + "/#", Branch_ID);
    }
    private BranchDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new BranchDbHelper(getContext());
        return true;    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);

        switch (match) {
            case Branch:
                cursor = database.query(BranchEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case Branch_ID:
                selection = BranchEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(BranchEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case Branch:
                return BranchEntry.CONTENT_LIST_TYPE;
            case Branch_ID:
                return BranchEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case Branch:
                return insertBranch(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertBranch(Uri uri, ContentValues values) {
        String name = values.getAsString(BranchEntry.COLUMN_Branch_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Branch requires a name");
        }



        String supplierEmail = values.getAsString(BranchEntry.COLUMN_Branch_Location);
        if (supplierEmail == null) {
            throw new IllegalArgumentException("Branch requires a supplier email");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(BranchEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case Branch:
                rowsDeleted = database.delete(BranchEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case Branch_ID:
                selection = BranchEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(BranchEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;    }



    private int updateBranch(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(BranchEntry.COLUMN_Branch_NAME)) {
            String name = values.getAsString(BranchEntry.COLUMN_Branch_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Branch requires a name");
            }
        }


        if (values.containsKey(BranchEntry.COLUMN_Branch_Location)) {
            String Semail = values.getAsString(BranchEntry.COLUMN_Branch_Location);
            if (Semail == null) {
                throw new IllegalArgumentException("Branch requires a ِEmail ");
            }
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(BranchEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }





    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case Branch:
                return updateBranch(uri, values, selection, selectionArgs);
            case Branch_ID:
                selection = BranchEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateBranch(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }



    }
}
