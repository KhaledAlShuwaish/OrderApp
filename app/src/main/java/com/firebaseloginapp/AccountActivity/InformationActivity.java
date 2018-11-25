package com.firebaseloginapp.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebaseloginapp.AccountActivity.DataBase1.MenuActivity;
import com.firebaseloginapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{


    static FirebaseUser user2;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;
    private EditText name,phonN;
//            Branch1,Branch2,Branch3,Branch4,Branch5,Branch6;
    private Button send;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();

            startActivity(new Intent(this, MenuActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();
//
        user2=firebaseAuth.getCurrentUser();
//        Branch1=(EditText)findViewById(R.id.Branch1);
//        Branch2=(EditText)findViewById(R.id.Branch2);
//        Branch3=(EditText)findViewById(R.id.Branch3);
//        Branch4=(EditText)findViewById(R.id.Branch4);
//        Branch5=(EditText)findViewById(R.id.Branch5);
//        Branch6=(EditText)findViewById(R.id.Branch6);



        databaseReference = FirebaseDatabase.getInstance().getReference("new");
        name=(EditText)findViewById(R.id.name);
        phonN=(EditText)findViewById(R.id.phoneN);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(this);


    }

    private void saveUserInformation(){

        String UserName =name.getText().toString().trim();
        String UserPhoneN= phonN.getText().toString().trim();
//        String Branch11= Branch1.getText().toString().trim();
//        String Branch22= Branch2.getText().toString().trim();
//        String Branch33= Branch3.getText().toString().trim();
//        String Branch44= Branch4.getText().toString().trim();
//        String Branch55= Branch5.getText().toString().trim();
//        String Branch66= Branch6.getText().toString().trim();



        if (UserName.equals("")||UserPhoneN.equals("")){
            Toast.makeText(this, "Please Enter your name and Phone number",Toast.LENGTH_LONG).show();

        }else {
            UserInformation  userInformation= new UserInformation(UserName,UserPhoneN);


            user =firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);


            Toast.makeText(this,"Information Saved",Toast.LENGTH_LONG).show();

            startActivity(new Intent(this, MainActivity.class));


        }

    }

    public static FirebaseUser user(){
        return  user2;
    }
    @Override
    public void onClick(View v) {

        if (v == send ){


                saveUserInformation();





        }



    }





}
