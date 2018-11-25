package com.firebaseloginapp.AccountActivity.recycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebaseloginapp.AccountActivity.MainActivity;
import com.firebaseloginapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button button;
    private Button back;

    private MyAdapter adapter;
    private Myadaper2 adapter2;

    private List<list_item> list_items;
    private LinearLayout linearLayout;


    ArrayList<list_item> arrayListFrutes;
    ArrayList<list_item> arrayListVege;

    private Button fru;
    private Button vege;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        imageView=(ImageView)findViewById(R.id.image);

        button=(Button)findViewById(R.id.Button);
        back=(Button)findViewById(R.id.Back);
        back.setVisibility(View.GONE);

        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayout) ;
        fru=(Button)findViewById(R.id.Fruts);
        vege=(Button)findViewById(R.id.Vege);
        recyclerView.setVisibility(View.GONE);
        fru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setVisibility(View.VISIBLE);

                imageView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                adapter=new MyAdapter(arrayListFrutes,OrderActivity.this);

                recyclerView.setAdapter(adapter);
                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });



        vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setVisibility(View.VISIBLE);

                imageView.setVisibility(View.GONE);

                button.setVisibility(View.GONE);

                adapter2= new Myadaper2(arrayListVege,OrderActivity.this);

                recyclerView.setAdapter(adapter2);


                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);


                recyclerView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });





        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list_items = new ArrayList<>();






        arrayListFrutes=new ArrayList<list_item>();
        arrayListFrutes.add(new list_item("برتقال","8","0"));
        arrayListFrutes.add(new list_item("تفاح احمر ","12","0"));
        arrayListFrutes.add(new list_item("تفاح اخضر","11","0"));
        arrayListFrutes.add(new list_item("موز","9","0"));
        arrayListFrutes.add(new list_item("كمثرى","14","0"));
        arrayListFrutes.add(new list_item("كيوي","16","0"));
        arrayListFrutes.add(new list_item("رمان","19","0"));
        arrayListFrutes.add(new list_item("عنب احمر","22","0"));
        arrayListFrutes.add(new list_item("عنب اخضر","19","0"));
        arrayListFrutes.add(new list_item("ليمون","12","0"));
        arrayListFrutes.add(new list_item("ليمون اخضر","12","0"));
        arrayListFrutes.add(new list_item("بخارة","17","0"));
        arrayListFrutes.add(new list_item("خوخ","27","0"));
        arrayListFrutes.add(new list_item("مشمش","28","0"));
        arrayListFrutes.add(new list_item("بطيخ","20","0"));
        arrayListFrutes.add(new list_item("فراولة","34","0"));
        arrayListFrutes.add(new list_item("جوافة","17","0"));
        arrayListFrutes.add(new list_item("اناناس","15","0"));
        arrayListFrutes.add(new list_item("مانجو","19","0"));
        arrayListFrutes.add(new list_item("افوكادو","22","0"));






        arrayListVege=new ArrayList<list_item>();
        arrayListVege.add(new list_item("طماطم","8","0"));
        arrayListFrutes.add(new list_item("بطاطس","7","0"));
        arrayListFrutes.add(new list_item("خيار","8 ","0"));
        arrayListVege.add(new list_item("كوسه","9","0"));
        arrayListVege.add(new list_item("باذنجان","8","0"));
        arrayListVege.add(new list_item("فاصوليا","11","0"));
        arrayListVege.add(new list_item("بصل احمر","5","0"));
        arrayListVege.add(new list_item("جزر","8","0"));
        arrayListVege.add(new list_item("ثوم","8","0"));
        arrayListVege.add(new list_item("فلفل بارد اخضر","6","0"));
        arrayListVege.add(new list_item("فلفل بارد احمر","11","0"));
        arrayListVege.add(new list_item("فلفل حار اخضر ","21","0"));
        arrayListVege.add(new list_item("زنجبيل","11","0"));
        arrayListVege.add(new list_item("بصل ابيض","5","0"));
        arrayListVege.add(new list_item("شمندر","7","0"));
        arrayListVege.add(new list_item("بروكلي","21","0"));







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderActivity.this, Recycle2.class);
                intent.putParcelableArrayListExtra("items",adapter.array());
                intent.putParcelableArrayListExtra("items2",adapter2.array());
                intent.putExtra("Cost2",adapter2.getCost());
                intent.putExtra("Cost1",adapter.getCost());

                startActivity(intent);



            }
        });




    }
}
