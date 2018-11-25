package com.firebaseloginapp.AccountActivity.DataBase1;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

import com.firebaseloginapp.R;

public class Menu2Activity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int BRANCH_LOADER = 0;
    BranchCursorAdapter2 mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);


        ListView BranchListView = findViewById(R.id.list2);
        mCursorAdapter = new BranchCursorAdapter2(this, null);
        BranchListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(BRANCH_LOADER, null, this);



        BranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(view.getContext(), BranchEntry.COLUMN_Branch_NAME.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BranchEntry._ID,
                BranchEntry.COLUMN_Branch_NAME,
                BranchEntry.COLUMN_Branch_Location};

        return new CursorLoader(this,
                BranchEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }
}
