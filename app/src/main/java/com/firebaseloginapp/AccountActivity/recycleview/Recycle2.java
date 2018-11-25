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

import com.firebaseloginapp.AccountActivity.DataBase1.BranchCursorAdapter2;
import com.firebaseloginapp.AccountActivity.MainActivity;
import com.firebaseloginapp.R;

import java.util.List;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;

public class Recycle2 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

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

    TextView total;
    public Button DonBuy;

    private static final int BRANCH_LOADER = 0;
    BranchCursorAdapter2 mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle2);
        DonBuy=(Button)findViewById(R.id.DoneBuy);
        ListView BranchListView = findViewById(R.id.list3);
        mCursorAdapter = new BranchCursorAdapter2(this, null);
        BranchListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(BRANCH_LOADER, null, this);



        BranchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(view.getContext(), BranchEntry.COLUMN_Branch_NAME.toString(),Toast.LENGTH_SHORT).show();
            }
        });





        Intent pastIntent = getIntent();
        list_items = pastIntent.getParcelableArrayListExtra("items");
        list_items2=pastIntent.getParcelableArrayListExtra("items2");



        cost1= MyAdapter.getCost();
        cost2=Myadaper2.getCost();

        total =(TextView)findViewById(R.id.cost);

        int totalest = cost1+cost2;


        newAdapter=new newAdapter(list_items,this);
        newAdapter2=new newAdapter(list_items2,this);



        recyclerView3 = (RecyclerView)findViewById(R.id.recycleView3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(newAdapter);


        recyclerView2 = (RecyclerView)findViewById(R.id.recycleView2);
        recyclerView2.setHasFixedSize(true);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(newAdapter2);

        total.setText("the total is  "+Integer.toString(totalest));




        DonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Recycle2.this,MainActivity.class);
                startActivity(i);
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
