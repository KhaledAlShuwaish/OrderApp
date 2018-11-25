package com.firebaseloginapp.AccountActivity.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebaseloginapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private int count =0;
    private List<list_item> list_items;
    private Context context;
    private List<list_item> list_items2;

    private static ArrayList<list_item> arrayList2;

    private static int Cost=0;

    public MyAdapter(Context context) {
    }

    public MyAdapter(List<list_item> list_items2 , List<list_item> list_items, Context context) {
        this.list_items2 = list_items2;
        this.list_items = list_items;
        this.context = context;
    }

    public MyAdapter(List<list_item> list_items, Context context) {
        this.list_items = list_items;
        this.context = context;

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recycleview1,parent,false);


        return new ViewHolder(v);    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        arrayList2=new ArrayList<list_item>();
        final list_item list_item=list_items.get(position);

        holder.RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count2=0;

                count2=Integer.parseInt(holder.counter.getText().toString());

                count2++;
                int count3=count2;


                list_item current = new list_item(list_items.get(position).getHead(),list_items.get(position).getDisc(),"1");



                arrayList2.add(current);




                int cost =Integer.parseInt(list_items.get(position).getDisc());

                Cost=Cost+cost;
                holder.counter.setText(Integer.toString(count2));


            }

        });




        holder.LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count2=0;
                count2=Integer.parseInt(holder.counter.getText().toString());



                count2--;
                holder.counter.setText(Integer.toString(count2));
            }
        });







        holder.TextViewHeading.setText(list_item.getHead());
        holder.TextViewDisc.setText(list_item.getDisc());




    }

    public  static ArrayList<list_item>  array (){

        return arrayList2 ;
    }



    public static int getCost(){

        return Cost;
    }



    @Override
    public int getItemCount() {
        if (list_items!=null){
            return list_items.size();
        }
        return 0;    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TextViewHeading;
        public TextView TextViewDisc;
        public LinearLayout linearLayout;
//        public CheckBox checkBox;

        public Button RightButton;
        public Button LeftButton;
        public TextView counter;

        public TextView countOrder;


        public ViewHolder(View itemView) {
            super(itemView);

            TextViewHeading=(TextView)itemView.findViewById(R.id.TextViewHeading);
            TextViewDisc=(TextView)itemView.findViewById(R.id.TextViewDisc);
            linearLayout =(LinearLayout)itemView.findViewById(R.id.linearLayout);
//            checkBox = itemView.findViewById(R.id.checkboxxxx);


            counter=(TextView)itemView.findViewById(R.id.counter);
            countOrder=(TextView)itemView.findViewById(R.id.count);

            LeftButton=(Button)itemView.findViewById(R.id.leftB);
            RightButton=(Button)itemView.findViewById(R.id.righB);
        }
    }
}
