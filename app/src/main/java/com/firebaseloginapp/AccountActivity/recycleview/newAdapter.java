package com.firebaseloginapp.AccountActivity.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebaseloginapp.R;

import java.util.List;

public class newAdapter extends RecyclerView.Adapter<newAdapter.ViewHolder> {

    private List<list_item> list_items;
    private Context context;

    public newAdapter(List<list_item> list_items, Context context) {
        this.list_items = list_items;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recycleview2,parent,false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final list_item list_item=list_items.get(position);

        holder.TextViewHeading.setText(list_item.getHead());
        holder.TextViewDisc.setText(list_item.getDisc()+" ريال");
        holder.count.setText(list_item.getCount()+"x");
    }

    @Override
    public int getItemCount() {
        if (list_items!=null){
            return list_items.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextViewHeading;
        public TextView TextViewDisc;
        public TextView count;

        public ViewHolder(View itemView) {
            super(itemView);
            count=(TextView)itemView.findViewById(R.id.count);
            TextViewHeading=(TextView)itemView.findViewById(R.id.TextViewHeading);
            TextViewDisc=(TextView)itemView.findViewById(R.id.TextViewDisc);
        }
    }
}
