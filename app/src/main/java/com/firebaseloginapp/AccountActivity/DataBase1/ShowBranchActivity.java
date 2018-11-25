package com.firebaseloginapp.AccountActivity.DataBase1;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;
import com.firebaseloginapp.R;

public class ShowBranchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView EditName;

    private TextView EditSupplierEmail;


    private Button edit;
    private Button delete;

    private Uri BranchUri;

    private static final int Branch_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_branch);

        Intent i = getIntent();
        BranchUri = i.getData();

        getLoaderManager().initLoader(Branch_LOADER, null, this);



        delete = findViewById(R.id.Delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });


        edit = findViewById(R.id.Edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowBranchActivity.this, EditorActivity.class);

                intent.setData(BranchUri);

                startActivity(intent);
            }
        });


        EditName = findViewById(R.id.nameShow);
        EditSupplierEmail = findViewById(R.id.supplierEmailShow);







    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Case_Edit:
                EditBranchs();
                finish();
                return true;
            case R.id.Case_Delete:
                showDeleteConfirmationDialog();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    private void EditBranchs() {
        Intent intent = new Intent(ShowBranchActivity.this, EditorActivity.class);
        intent.setData(BranchUri);

        startActivity(intent);
    }


    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteBranch();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private void deleteBranch() {
        if (BranchUri != null) {
            int rowsDeleted = getContentResolver().delete(BranchUri, null, null);

            Intent i = new Intent(ShowBranchActivity.this, MenuActivity.class);
            startActivity(i);
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_Branch_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_Branch_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }





    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BranchEntry._ID,
                BranchEntry.COLUMN_Branch_NAME,
                BranchEntry.COLUMN_Branch_Location};

        return new CursorLoader(this,
                BranchUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }


        if (cursor.moveToFirst()) {
            int COLUMN_Branch_NAME = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_NAME);
            int COLUMN_Branch_SupplierEmail = cursor.getColumnIndex(BranchEntry.COLUMN_Branch_Location);


            String get_name = cursor.getString(COLUMN_Branch_NAME);
            String get_SEmail = cursor.getString(COLUMN_Branch_SupplierEmail);


            EditName.setText(get_name);
            EditSupplierEmail.setText(get_SEmail);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        EditName.setText("");
        EditSupplierEmail.setText("");
    }
}
