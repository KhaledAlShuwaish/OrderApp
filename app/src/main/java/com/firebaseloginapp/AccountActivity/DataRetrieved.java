//package com.firebaseloginapp.AccountActivity;
//
//import android.security.keystore.UserNotAuthenticatedException;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import com.firebaseloginapp.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataRetrieved extends AppCompatActivity {
//
//
//
//    Iterable<DataSnapshot> sdf;
//
//    public RecyclerView recyclerView2;
//    private RecyclerView.Adapter adapter;
//
//    private BranchAdapter branchAdapter;
//    ArrayList<UserInformation> arrayList;
//
//    private DatabaseReference databaseReference;
//    List<UserInformation> userInformationList;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_retrieved);
//
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("new");
//
//
//        recyclerView2 = (RecyclerView) findViewById(R.id.BranchListView);
//        recyclerView2.setHasFixedSize(true);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
//
//        userInformationList = new ArrayList<>();
//
//
//
//        arrayList = new ArrayList<UserInformation>();
//    }
//
//
//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//
//                branchAdapter = new BranchAdapter(arrayList,DataRetrieved.this);
//
//
//                for (DataSnapshot UserSnapshot : dataSnapshot.getChildren()){
//                    UserInformation userInformation = UserSnapshot.getValue(UserInformation.class);
//                    arrayList.add(new UserInformation(userInformation.getBranch1()));
//                    arrayList.add(new UserInformation(userInformation.getBranch2()));
//                    arrayList.add(new UserInformation(userInformation.getBranch3()));
//                    arrayList.add(new UserInformation(userInformation.getBranch4()));
//                    arrayList.add(new UserInformation(userInformation.getBranch5()));
//                    arrayList.add(new UserInformation(userInformation.getBranch6()));
//
//
//
//
//                    branchAdapter = new BranchAdapter(arrayList,DataRetrieved.this);
//
//                }
//
//
//
//                recyclerView2.setAdapter(branchAdapter);
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
