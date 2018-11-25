package com.firebaseloginapp.AccountActivity.DataBase1;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.firebaseloginapp.R;

import java.util.ArrayList;
import java.util.List;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

public class BranchCursorAdapter2 extends CursorAdapter {
    private static ArrayList list ;

    List<Integer> selectedItemsPositions;//to store all selected items position

    CheckBox checkBox;


    public static ArrayList array (){

        return list  ;
    }


    public BranchCursorAdapter2(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
//        selectedItemsPositions = new ArrayList<>();
            list=new ArrayList<>();

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item_database2, viewGroup,false);


//        CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkbox);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                int position = (int) compoundButton.getTag();
//                if (b) {
//                    //check whether its already selected or not
//                    if (!selectedItemsPositions.contains(position))
//                        selectedItemsPositions.add(position);
//                } else {
//                    //remove position if unchecked checked item
//                    selectedItemsPositions.remove((Object) position);
//                }
//
//            }
//        });



//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                int position = (int) compoundButton.getTag();
//                if (b) {
//                    //check whether its already selected or not
//                    if (!selectedItemsPositions.contains(position))
//                        selectedItemsPositions.add(position);
//                } else {
//                    //remove position if unchecked checked item
//                    selectedItemsPositions.remove((Object) position);
//                }
//            }
//        });


        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {



        TextView name = (TextView) view.findViewById(R.id.name2);
        TextView location = (TextView) view.findViewById(R.id.locationDataBase3);

        int nameColumnIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_NAME);
        int locationColumnIndex = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_Location);



        int id = cursor.getInt(cursor.getColumnIndex(BranchEntry._ID));


        String BranchkName = cursor.getString(nameColumnIndex);
        String BranchkLocation = cursor.getString(locationColumnIndex);



        final Uri uri = ContentUris.withAppendedId(BranchEntry.CONTENT_URI, id);


        name.setText("Name : " + BranchkName);
        location.setText("Location :  "+ BranchkLocation);


//
//        checkBox=(CheckBox)view.findViewById(R.id.checkbox);
//        if (checkBox.isChecked()){
//
//            list.add(BranchkName);
//
//        }




//




    }



}
