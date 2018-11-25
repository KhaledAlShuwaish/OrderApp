//package com.firebaseloginapp.AccountActivity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.firebaseloginapp.R;
//
//import java.util.List;
//
//public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {
//
//
//    private  Context context;
//    private List<UserInformation> userInformationsList;
//
//
//
//    public  BranchAdapter(List<UserInformation> userInformations, Context context){
//        this.userInformationsList = userInformations;
//        this.context=context;
//    }
//
//
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_view,parent,false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        UserInformation userInformationlist = userInformationsList.get(position);
//        holder.Bunch1.setText(userInformationlist.getBranch1());
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return userInformationsList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView Bunch1;
//
//
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            Bunch1=(TextView)itemView.findViewById(R.id.BunchRet1);
//
//        }
//    }
//}
//
