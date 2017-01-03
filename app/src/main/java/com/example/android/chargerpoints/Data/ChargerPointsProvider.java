package com.example.android.chargerpoints.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.android.chargerpoints.Coupon;

/**
 * Created by Jenna on 12/19/2016.
 */
public class ChargerPointsProvider extends ContentProvider {

    /** URI matcher code for the content URI for the userInfo table */
    private static final int USER_INFO = 100;

    /** URI matcher code for the content URI for a single user in the userInfo table */
    private static final int USER_INFO_ID = 101;

    /** URI matcher code for the content URI for the couponInfo table */
    private static final int COUPON_INFO = 102;

    /** URI matcher code for the content URI for a single coupon in the couponInfo table */
    private static final int COUPON_INFO_ID = 103;

    /** URI matcher code for the content URI for the points table */
    private static final int POINTS_INFO = 104;

    /** URI matcher code for the content URI for a single user in the points table */
    private static final int POINTS_INFO_ID = 105;

    /** URI matcher code for the content URI for the userCouponsInfo table */
    private static final int USER_COUPONS_INFO = 106;

    /** URI matcher code for the content URI for a single user in the userCouponsInfo table */
    private static final int USER_COUPONS_INFO_ID = 107;

    /**Tag for the log messages*/
    public static final String LOG_TAG = ChargerPointsContract.class.getSimpleName();

    private ChargerPointsDbHelper mDbHelper;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.chargerpoints/userInfo" will map to the
        // integer code {@link #USER_INFO}. This URI is used to provide access to MULTIPLE rows
        // of the userInfo table.
        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_USER_INFO, USER_INFO);

        // The content URI of the form "content://com.example.android.chargerpoints/userInfo/#" will map to the
        // integer code {@link #USER_INFO_ID}. This URI is used to provide access to ONE single row
        // of the userInfo table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.chargerpoints/userInfo/3" matches, but
        // "content://com.example.android.chargerpoints/userInfo" (without a number at the end) doesn't match.
        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_USER_INFO + "/#", USER_INFO_ID);

        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_COUPON_INFO, COUPON_INFO);
        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_COUPON_INFO + "/#", COUPON_INFO_ID);

        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_POINTS_INFO, POINTS_INFO);
        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_POINTS_INFO + "/#", POINTS_INFO_ID);

        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_USER_COUPONS_INFO, USER_COUPONS_INFO);
        sUriMatcher.addURI(ChargerPointsContract.CONTENT_AUTHORITY, ChargerPointsContract.PATH_USER_COUPONS_INFO + "/#", USER_COUPONS_INFO_ID);

    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case USER_INFO:
                // For the USER_INFO code, query the userInfo table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the userInfo table.
                cursor = database.query(ChargerPointsContract.UserInfoEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case USER_INFO_ID:
                // For the USER_INFO_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.chargerpoints/userInfo/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = ChargerPointsContract.UserInfoEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the userInfo table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(ChargerPointsContract.UserInfoEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case COUPON_INFO:
                cursor = database.query(ChargerPointsContract.CouponInfoEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case COUPON_INFO_ID:
                selection = ChargerPointsContract.CouponInfoEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(ChargerPointsContract.CouponInfoEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case POINTS_INFO:
                cursor = database.query(ChargerPointsContract.PointsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case POINTS_INFO_ID:
                selection = ChargerPointsContract.PointsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(ChargerPointsContract.PointsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case USER_COUPONS_INFO:
                cursor = database.query(ChargerPointsContract.UserCouponsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case USER_COUPONS_INFO_ID:
                selection = ChargerPointsContract.UserCouponsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(ChargerPointsContract.UserCouponsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USER_INFO:
                return insertUserInfo(uri, contentValues);
            case COUPON_INFO:
                return insertCouponInfo(uri, contentValues);
            case USER_COUPONS_INFO:
                return insertUserCouponsInfo(uri, contentValues);
            case POINTS_INFO:
                return insertPointsInfo(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a user into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertUserInfo(Uri uri, ContentValues values) {

        // Check that the username is not null
        String username = values.getAsString(ChargerPointsContract.UserInfoEntry.COLUMN_USERNAME);
        if (username == null) {
            throw new IllegalArgumentException("Username Required");
        }

        // Check that the password is valid
        String password = values.getAsString(ChargerPointsContract.UserInfoEntry.COLUMN_PASSWORD);
        if (password == null) {
            throw new IllegalArgumentException("Password Required");
        }

        //Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Insert the new user with the given values
        long id = database.insert(ChargerPointsContract.UserInfoEntry.TABLE_NAME, null, values);

        //If the id is -1, then the insertion failed. Log an error and return null
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Insert a coupon into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertCouponInfo(Uri uri, ContentValues values) {

        // Check that the coupon is not null
        Byte coupon = values.getAsByte(ChargerPointsContract.CouponInfoEntry.COLUMN_COUPON);
        if (coupon == null) {
            throw new IllegalArgumentException("Coupon Required");
        }

        //Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Insert the new coupon with the given values
        long id = database.insert(ChargerPointsContract.CouponInfoEntry.TABLE_NAME, null, values);

        //If the id is -1, then the insertion failed. Log an error and return null
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Insert a coupon a user has into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertUserCouponsInfo(Uri uri, ContentValues values) {

        // Check that the userId is not null
        int userId = values.getAsInteger(ChargerPointsContract.UserCouponsEntry._ID);
        if (userId == 0) {
            throw new IllegalArgumentException("User ID required");
        }

        // Check that the name is not null
        int couponId = values.getAsInteger(ChargerPointsContract.UserCouponsEntry.COUPON_ID);
        if (couponId == 0) {
            throw new IllegalArgumentException("Coupon ID required");
        }

        boolean couponRedeemed = values.getAsBoolean(ChargerPointsContract.UserCouponsEntry.COLUMN_REDEEMED);
        if (couponRedeemed == true) {
            throw new IllegalArgumentException("Coupon already redeemed");
        }

        //Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Insert the new pet with the given values
        long id = database.insert(ChargerPointsContract.UserCouponsEntry.TABLE_NAME, null, values);

        //If the id is -1, then the insertion failed. Log an error and return null
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertPointsInfo(Uri uri, ContentValues values) {

        // Check that the name is not null
        int points = values.getAsInteger(ChargerPointsContract.PointsEntry.COLUMN_POINTS);
        if (points == 0) {
            throw new IllegalArgumentException("Points Required");
        }

        //Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //Insert the new pet with the given values
        long id = database.insert(ChargerPointsContract.PointsEntry.TABLE_NAME, null, values);

        //If the id is -1, then the insertion failed. Log an error and return null
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}


