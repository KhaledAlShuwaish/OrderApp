package com.firebaseloginapp.AccountActivity.recycleview;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebaseloginapp.AccountActivity.Complaint;
import com.firebaseloginapp.AccountActivity.DataBase1.BranchHestoryAdapter;
import com.firebaseloginapp.R;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;


import java.util.List;

public class History extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;

    private RecyclerView.Adapter adapter;
    private List<list_item> list_items;
    private List<list_item> list_items2;

    private  MyAdapter Myadapt;
    private  Myadaper2 Myadapt2;
    private newAdapter newAdapter;
    private newAdapter newAdapter2;

    private int cost1;
    private int cost2;
    private Button comp;

    TextView total;

    private static final int BRANCH_LOADER = 0;
    BranchHestoryAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView BranchListView = findViewById(R.id.list3Hestory);
        mCursorAdapter = new BranchHestoryAdapter(this, null);
        BranchListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(BRANCH_LOADER, null, this);

        BranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(view.getContext(), BranchEntry.COLUMN_Branch_NAME.toString(),Toast.LENGTH_SHORT).show();
            }
        });



        list_items = MyAdapter.array();
        list_items2=Myadaper2.array();



        cost1= MyAdapter.getCost();
        cost2=Myadaper2.getCost();

        total =(TextView)findViewById(R.id.costHestory);

        int totalest = cost1+cost2;

        newAdapter=new newAdapter(list_items,this);
        newAdapter2=new newAdapter(list_items2,this);

        recyclerView3 = (RecyclerView)findViewById(R.id.recycleView3Hestory);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(newAdapter);

        recyclerView2 = (RecyclerView)findViewById(R.id.recycleView2History);
        recyclerView2.setHasFixedSize(true);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(newAdapter2);

        total.setText("the total is  "+Integer.toString(totalest));


        comp = (Button)findViewById(R.id.Complaint);

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, Complaint.class);
                startActivity(intent);
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
                null);
    }

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
