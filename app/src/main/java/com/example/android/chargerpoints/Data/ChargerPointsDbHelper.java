package com.example.android.chargerpoints.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.chargerpoints.Data.ChargerPointsContract.CouponInfoEntry;
import com.example.android.chargerpoints.Data.ChargerPointsContract.PointsEntry;
import com.example.android.chargerpoints.Data.ChargerPointsContract.UserCouponsEntry;
import com.example.android.chargerpoints.Data.ChargerPointsContract.UserInfoEntry;

/*Created by Jenna on 12/5/2016.*/
/*Database helper for Charger Points app. Manages database creation and version management.*/
public class ChargerPointsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ChargerPointsDbHelper.class.getSimpleName();

    /* Name of the database file */
    private static final String DATABASE_NAME = "chargerPoints.db";

    /* Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    /*Constructs a new instance of {@link ChargerPointsDbHelper}. @param context of the app */

    public ChargerPointsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*This is called when the database is created for the first time.*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the User Info table
        String SQL_CREATE_USER_INFO_TABLE =  "CREATE TABLE " + UserInfoEntry.TABLE_NAME + " ("
                + UserInfoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserInfoEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + UserInfoEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

        // Create a String that contains the SQL statement to create the Coupon Info table
        String SQL_CREATE_COUPON_INFO_TABLE =  "CREATE TABLE " + CouponInfoEntry.TABLE_NAME + " ("
                + CouponInfoEntry.COUPON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CouponInfoEntry.COLUMN_COUPON + " BLOB NOT NULL);";

        // Create a String that contains the SQL statement to create the Points table
        String SQL_CREATE_POINTS_TABLE =  "CREATE TABLE " + PointsEntry.TABLE_NAME + " ("
                + PointsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PointsEntry.COLUMN_POINTS + " INTEGER NOT NULL);";

        // Create a String that contains the SQL statement to create the UserCoupons table
        String SQL_CREATE_USER_COUPONS_TABLE =  "CREATE TABLE " + UserCouponsEntry.TABLE_NAME + " ("
                + UserCouponsEntry._ID + " INTEGER NOT NULL, "
                + UserCouponsEntry.COUPON_ID + " INTEGER NOT NULL, "
                + UserCouponsEntry.COLUMN_REDEEMED + " BOOLEAN NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_INFO_TABLE);
        db.execSQL(SQL_CREATE_COUPON_INFO_TABLE);
        db.execSQL(SQL_CREATE_POINTS_TABLE);
        db.execSQL(SQL_CREATE_USER_COUPONS_TABLE);
    }

    /*This is called when the database needs to be upgraded.*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}