package com.example.android.chargerpoints;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.chargerpoints.Data.ChargerPointsContract;
import com.example.android.chargerpoints.Data.ChargerPointsContract.CouponInfoEntry;
import com.example.android.chargerpoints.Data.ChargerPointsContract.PointsEntry;
import com.example.android.chargerpoints.Data.ChargerPointsContract.UserInfoEntry;
/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        displayPointsInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayPointsInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = { PointsEntry._ID,
                PointsEntry.COLUMN_POINTS};

        //Perform a query on the provider using the ContentResolver
        //Use the {@link PointsEntry#CONTENT_URI} to access the pet data
        Cursor cursor = getContentResolver().query(
                PointsEntry.POINTS_INFO_CONTENT_URI, // The content URI of the words table
                projection, //The columns to return for each row
                null, // Selection criteria
                null, // Selection criteria
                null); // The sort order for the returned rows

        TextView displayView = (TextView) findViewById(R.id.pts);

        try {
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PointsEntry._ID);
            int pointsColumnIndex = cursor.getColumnIndex(PointsEntry.COLUMN_POINTS);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                long currentID = cursor.getLong(idColumnIndex);
                int currentPoints = cursor.getInt(pointsColumnIndex);

                if (currentID == EditorActivity.getIdNum())
                    displayView.setText("POINTS: " + currentPoints);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        // Insert a new row for Toto into the provider using the ContentResolver
        // Use the {@link PetEntry#CONTENT_URI} to indicate that we want to insert
        // into the pets database table.
        // Receive the new content URI that will allow us to access Toto's data in the future.
        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
