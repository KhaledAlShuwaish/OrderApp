package com.firebaseloginapp.AccountActivity.DataBase1;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

import com.firebaseloginapp.R;

import static com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry.CONTENT_URI;

public class MenuActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int BRANCH_LOADER = 0;
    BranchCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


//        Button done = (Button)findViewById(R.id.done);
//
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
//                startActivity(intent);
//
//
//            }
//        });

        ListView BranchListView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        BranchListView.setEmptyView(emptyView);


        mCursorAdapter = new BranchCursorAdapter(this, null);
        BranchListView.setAdapter(mCursorAdapter);


        BranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MenuActivity.this, ShowBranchActivity.class);


                Uri currentBranchUri = ContentUris.withAppendedId(CONTENT_URI, id);

                intent.setData(currentBranchUri);

                startActivity(intent);

            }
        });

        getLoaderManager().initLoader(BRANCH_LOADER, null, this);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            insertBranch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void insertBranch() {
        Intent intent = new Intent(MenuActivity.this, InsertActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_menue, menu);
        return true;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BranchEntry._ID,
                BranchEntry.COLUMN_Branch_NAME,
                BranchEntry.COLUMN_Branch_Location};

        return new CursorLoader(this,
                CONTENT_URI,
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
