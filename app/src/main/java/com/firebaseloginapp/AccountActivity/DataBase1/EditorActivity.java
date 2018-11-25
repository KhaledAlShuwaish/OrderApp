package com.firebaseloginapp.AccountActivity.DataBase1;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
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

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    private EditText EditName;
    private EditText EditSupplierEmail;

    private static final int Branch_LOADER = 0;

    private Uri BranchUri;
    private boolean BranchHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            BranchHasChanged = true;
            return false;
        }
    };

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Intent intent = getIntent();
        BranchUri = intent.getData();
        getLoaderManager().initLoader(Branch_LOADER, null, this);
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
                Toast.makeText(EditorActivity.this,placeName,Toast.LENGTH_LONG).show();
                EditSupplierEmail.setText(placeName);
            }


            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

    }
    private void saveBranch() {

        String SaveName = EditName.getText().toString().trim();
        String SaveSupplierEmail = EditSupplierEmail.getText().toString().trim();

        if (BranchUri == null && TextUtils.isEmpty(SaveName)  && TextUtils.isEmpty(SaveSupplierEmail)) {
            return;
        }

        if (TextUtils.isEmpty(SaveName) ||TextUtils.isEmpty(SaveSupplierEmail)) {
            return;
        }


        ContentValues values = new ContentValues();

        values.put(BranchEntry.COLUMN_Branch_NAME, SaveName);
        values.put(BranchEntry.COLUMN_Branch_Location, SaveSupplierEmail);

        int rowsAffected = getContentResolver().update(BranchUri, values, null, null);

        if (rowsAffected == 0) {
            Toast.makeText(this, getString(R.string.editor_update_Branch_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.editor_update_Branch_successful),
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


    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(EditorActivity.this,MenuActivity.class);
        String ItemName = EditName.getText().toString().trim();
        String ItemSupplierEmail = EditSupplierEmail.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.action_insert:
                saveBranch();
                startActivity(intent);

                if (TextUtils.isEmpty(ItemName) ||  TextUtils.isEmpty(ItemSupplierEmail)) {
                    Toast.makeText(this, getString(R.string.empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
                return true;
            case android.R.id.home:
                if (!BranchHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                null);    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null || data.getCount() < 1) {
            return;
        }

        if (data.moveToFirst()) {
            int COLUMN_Branch_NAME = data.getColumnIndex(BranchEntry.COLUMN_Branch_NAME);
            int COLUMN_Branch_SupplierEmail = data.getColumnIndex(BranchEntry.COLUMN_Branch_Location);

            String getName = data.getString(COLUMN_Branch_NAME);
            String getSupplierEmail = data.getString(COLUMN_Branch_SupplierEmail);

            EditName.setText(getName);
            EditSupplierEmail.setText(getSupplierEmail);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        EditName.setText("");
        EditSupplierEmail.setText("");


    }
}
