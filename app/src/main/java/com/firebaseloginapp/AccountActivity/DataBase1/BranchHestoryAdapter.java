package com.firebaseloginapp.AccountActivity.DataBase1;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;
import com.firebaseloginapp.R;

public class BranchHestoryAdapter extends CursorAdapter {

    BranchCursorAdapter2 branchCursorAdapter2;

    public BranchHestoryAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item_history, parent,false);
        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.name2History);
        TextView location = (TextView) view.findViewById(R.id.locationDataBase2);

        int nameColumnIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_NAME);
        int locationColumnIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_Location);



        int id = cursor.getInt(cursor.getColumnIndex(BranchEntry._ID));


        String BranchkName = cursor.getString(nameColumnIndex);
        String BranchkLocation = cursor.getString(locationColumnIndex);



        final Uri uri = ContentUris.withAppendedId(BranchEntry.CONTENT_URI, id);


        name.setText("Name : " + BranchkName);
        location.setText("Location : " + BranchkLocation);


    }
}
