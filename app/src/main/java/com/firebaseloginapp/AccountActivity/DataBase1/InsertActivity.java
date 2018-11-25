package com.firebaseloginapp.AccountActivity.DataBase1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.firebaseloginapp.AccountActivity.DataBase1.data.BranchContract.BranchEntry;
import com.firebaseloginapp.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class InsertActivity extends AppCompatActivity {

    private Uri BranchUri;
    private EditText EditName;
    private EditText EditSupplierEmail;
    private boolean BranchHasChanged = false;
    String placeName;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            BranchHasChanged = true;
            return false;
        }
    };

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        EditName = findViewById(R.id.edit_branch_name);
        EditSupplierEmail = findViewById(R.id.edit_branch_SupplierEmail);
        EditName.setOnTouchListener(mTouchListener);
        EditSupplierEmail.setOnTouchListener(mTouchListener);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                Toast.makeText(InsertActivity.this,placeName,Toast.LENGTH_LONG).show();
                EditSupplierEmail.setText(placeName);
            }


            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });




    }
    private void saveBranch() {

        String Name = EditName.getText().toString().trim();
        String SupplierEmail = EditSupplierEmail.getText().toString().trim();

        if (BranchUri == null && TextUtils.isEmpty(Name)  && TextUtils.isEmpty(SupplierEmail)) {
            return;
        }

        if (TextUtils.isEmpty(Name) ||TextUtils.isEmpty(SupplierEmail)) {
            return;
        }



        ContentValues values = new ContentValues();
        values.put(BranchEntry.COLUMN_Branch_NAME, Name);




        values.put(BranchEntry.COLUMN_Branch_Location, SupplierEmail);

        Uri uri = getContentResolver().insert(BranchEntry.CONTENT_URI, values);

        if (uri == null) {
            Toast.makeText(this, getString(R.string.editor_insert_Branch_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.editor_insert_Branch_successful),
                    Toast.LENGTH_SHORT).show();
        }

    }
    public void onBackPressed() {
        if (!BranchHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insertbranch, menu);

        return true;
    }


    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String Item_name = EditName.getText().toString().trim();
        String Item_supplierEmail = EditSupplierEmail.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.action_insert:

                saveBranch();

                if (TextUtils.isEmpty(Item_name) ||  TextUtils.isEmpty(Item_supplierEmail)) {
                    Toast.makeText(this, getString(R.string.empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
                return true;
            case android.R.id.home:
                if (!BranchHasChanged) {
                    NavUtils.navigateUpFromSameTask(InsertActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(InsertActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
