package com.firebaseloginapp.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class Complaint extends AppCompatActivity {

    private ImageView imageReview , UplodeImage;

    private static final int CAMERA_REQUEST_CODE=2;
    private StorageReference mStorage;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    EditText Comment;
    FirebaseUser user;

    private Button sendFB;

    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        mStorage= FirebaseStorage.getInstance().getReference();

        progressDialog= new ProgressDialog(this);



        imageReview=(ImageView)findViewById(R.id.imageView2);

        UplodeImage = (ImageView)findViewById(R.id.imageView);

        UplodeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,CAMERA_REQUEST_CODE);

//                Intent intent= new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });


        Comment =(EditText)findViewById(R.id.commment);


        sendFB=(Button)findViewById(R.id.SendFeedBack);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        sendFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth=FirebaseAuth.getInstance();

                String UserName =Comment.getText().toString().trim();
                FirebaseUser user = firebaseAuth.getCurrentUser();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("comment");
                databaseReference.child(user.getUid()).setValue(UserName);

                Intent intent = new Intent(Complaint.this,MainActivity.class);
                startActivity(intent);
            }
        });

        if (requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            progressDialog.setMessage("Uploading Image...");
            progressDialog.show();

            Uri uri = data.getData();


            firebaseAuth=FirebaseAuth.getInstance();

            FirebaseUser user = firebaseAuth.getCurrentUser();
//
//            String UserName =Comment.getText().toString().trim();
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("comment");
//            databaseReference.child(user.getUid()).setValue(UserName);



            assert uri != null;


            StorageReference filepath = mStorage.child(user.getUid()).child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();

                    imageReview.setImageResource(R.drawable.ic_check_black_24dp);
//                  Uri downloadUri=  taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult();
//                    Picasso.get().load(downloadUri).fit().centerCrop().into(imageReview);
                    Toast.makeText(Complaint.this,"Uploading Finished...",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
