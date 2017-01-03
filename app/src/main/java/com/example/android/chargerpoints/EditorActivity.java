
package com.example.android.chargerpoints;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.chargerpoints.Data.ChargerPointsContract;
import com.example.android.chargerpoints.Data.ChargerPointsContract.UserInfoEntry;
import com.example.android.chargerpoints.Data.ChargerPointsDbHelper;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the person's username */
    private EditText mUsernameEditText;

    /** EditText field to enter the person's password */
    private EditText mPasswordEditText;

    //Variable for id of user
    private static long idNum;

    public static long getIdNum ()
    {
        return idNum;
    }

    @Override
    //DO THIS LATER
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        // Find all relevant views that we will need to read user input from
        mUsernameEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);

       /*dbHelper = new ChargerPointsDbHelper(this);

        dbHelper.insertCouponInfo("Abbas", 987);

        dbHelper.insertCouponInfo("John", 9877);

        databaseHelper.insertCouponInfo("Michael", 8334);
        */
    }


    /**
     * Get user input from editor and save new user into database.
     */
    private void insertUserInfo() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String usernameString = mUsernameEditText.getText().toString().trim();
        String passwordString = mPasswordEditText.getText().toString().trim();

        // Create a ContentValues object where column names are the keys,
        // and user attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(UserInfoEntry.COLUMN_USERNAME, usernameString);
        values.put(UserInfoEntry.COLUMN_PASSWORD, passwordString);

        // Insert a new user into the provider, returning the content URI for the new user.
        Uri newUri = getContentResolver().insert(UserInfoEntry.USER_INFO_CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with creating account", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast
            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
        }

        //Get user id and add their account to the points table
        ChargerPointsDbHelper mNewDbHelper = new ChargerPointsDbHelper(this);
        // Create and/or open a database to read from it
        SQLiteDatabase db3 = mNewDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {UserInfoEntry._ID};

        // Perform a query on the user info table
        Cursor cursor = db3.query(
                    UserInfoEntry.TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);                 // The sort order

            try {
                //Get the number of users in table and set the id # of the last user to idNum
                int cnt = cursor.getCount();
                idNum = cnt;

                // Insert id into points table
                // Create database helper
                ChargerPointsDbHelper mDbUserHelper = new ChargerPointsDbHelper(this);

                // Gets the database in write mode
                SQLiteDatabase db2 = mDbUserHelper.getWritableDatabase();

                // Create a ContentValues object where column names are the keys,
                // and user attributes from the editor are the values.
                ContentValues userValues = new ContentValues();
                userValues.put(ChargerPointsContract.PointsEntry._ID, idNum);
                userValues.put(ChargerPointsContract.PointsEntry.COLUMN_POINTS, 0);

                // Insert a new row for user in the points, returning if the user was created or not.
                db2.insert(ChargerPointsContract.PointsEntry.TABLE_NAME, null, userValues);
                newUri = getContentResolver().insert(UserInfoEntry.USER_INFO_CONTENT_URI, values);

            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close(); }


    }



    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertNewUser();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
    public void onClickCreateAccount()
    {

    }
}

