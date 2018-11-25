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

import com.firebaseloginapp.R;

import java.util.ArrayList;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

public class BranchCursorAdapter extends CursorAdapter{


    ArrayList arrayList= new ArrayList();

    ArrayList<String> arrayList2= new ArrayList();


    public BranchCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }






    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_database, parent,false);






    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        int pos = cursor.getPosition();

        TextView location = (TextView)view.findViewById(R.id.locationDataBase1);
        TextView name = (TextView) view.findViewById(R.id.name);

        int locationColunIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_Location);
        int nameColumnIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_NAME);



        int id = cursor.getInt(cursor.getColumnIndex(BranchEntry._ID));


        String BranchName = cursor.getString(nameColumnIndex);
        String BrunchLocation = cursor.getString(locationColunIndex);

        arrayList.add(BranchName);

        arrayList2.add(BranchName);

        final Uri uri = ContentUris.withAppendedId(BranchEntry.CONTENT_URI, id);


        name.setText("Name : " + BranchName);
        location.setText("Location: " + BrunchLocation);
    }





}
