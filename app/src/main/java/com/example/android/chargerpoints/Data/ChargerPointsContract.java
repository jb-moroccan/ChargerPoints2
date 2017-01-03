package com.example.android.chargerpoints.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/* Created by Jenna on 12/2/2016 */

public final class ChargerPointsContract {
        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
    private ChargerPointsContract() {}
        /* Inner class that defines constant values for the ChargerPoints database


    /* The "Content authority" is a name for the entire content provider, similar to the
    * relationship between a domain name and its website.  A convenient string to use for the
    * content authority is the package name for the app, which is guaranteed to be unique on the
    * device. */
        public final static String CONTENT_AUTHORITY = "com.example.android.chargerpoints";

    /* Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    * the content provider.*/
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /* Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.chargerpoints/userInfo/ is a valid path for
     * looking at userInfo data. content://com.example.android.chargerpoints/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".*/
        public static final String PATH_USER_INFO = "userInfo";
        public static final String PATH_COUPON_INFO = "couponInfo";
        public static final String PATH_POINTS_INFO = "pointsInfo";
        public static final String PATH_USER_COUPONS_INFO = "userCouponsInfo";

        // * Each entry in the table represents a single person.*/
        public static final class UserInfoEntry implements BaseColumns {

            /** The content URI to access the userInfo data in the provider */
            public static final Uri USER_INFO_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USER_INFO);

            /* The MIME type of the {@link #USER_INFO_CONTENT_URI} for a list of users*/
            public static final String USER_INFO_CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_INFO;

            /*The MIME type of the {@link #USER_INFO_CONTENT_LIST_TYPE} for a single user.*/
            public static final String USER_INFO_CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_INFO;

            /** Name of database table for user info */
            public final static String TABLE_NAME = "userInfo";

            /**Unique ID number for the user. Type: INTEGER*/
            public final static String _ID = BaseColumns._ID;

            /** Name of the person. Type: TEXT*/
            public final static String COLUMN_USERNAME ="username";

            /** Password of the person.Type: TEXT*/
            public final static String COLUMN_PASSWORD = "password";
        }

        /* Each entry in the table represents a single coupon.*/
        public static final class CouponInfoEntry implements BaseColumns {

            /** The content URI to access the coupon data in the provider */
            public static final Uri COUPON_INFO_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_COUPON_INFO);

            /* The MIME type of the {@link #USER_INFO_CONTENT_URI} for a list of coupons*/
            public static final String COUPON_INFO_CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUPON_INFO;

            /*The MIME type of the {@link #USER_INFO_CONTENT_LIST_TYPE} for a single coupon.*/
            public static final String COUPON_INFO_CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUPON_INFO;

            /** Name of database table for coupon info */
            public final static String TABLE_NAME = "couponInfo";

            /**Unique ID number for the coupon. Type: INTEGER*/
            public final static String COUPON_ID = BaseColumns._ID;

            /** Individual coupon as is. Type: BLOB*/
            public final static String COLUMN_COUPON ="coupon";
        }

        /* Each entry in the table represents a person and the amount of points they have.*/
        public static final class PointsEntry implements BaseColumns {

            /** The content URI to access the points data in the provider */
            public static final Uri POINTS_INFO_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_POINTS_INFO);

            /* The MIME type of the {@link #POINTS_INFO_CONTENT_URI} for a list of users' points*/
            public static final String POINTS_INFO_CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POINTS_INFO;

            /*The MIME type of the {@link #POINTS_INFO_CONTENT_LIST_TYPE} for a single user's point.*/
            public static final String POINTS_INFO_CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POINTS_INFO;

            /** Name of database table for user points info */
            public final static String TABLE_NAME = "pointsInfo";

            /**Unique ID number for the person. Type: INTEGER*/
            public final static String _ID = BaseColumns._ID;

            /** Number of points user has. Type: INTEGER*/
            public final static String COLUMN_POINTS ="points";
        }

        /* Each entry in the table represents a person and a coupon they have.*/
        public static final class UserCouponsEntry implements BaseColumns {

            /** The content URI to access the userCoupons data in the provider */
            public static final Uri USER_COUPONS_INFO_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USER_COUPONS_INFO);

            /* The MIME type of the {@link #USER_COUPONS_INFO_CONTENT_URI} for a list of coupons users have*/
            public static final String USER_COUPONS_INFO_CONTENT_LIST_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_COUPONS_INFO;

            /*The MIME type of the {@link #USER_COUPONS_INFO_CONTENT_LIST_TYPE} for a single coupon a single user has.*/
            public static final String USER_COUPONS_INFO_CONTENT_ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_COUPONS_INFO;

            /** Name of database table for the coupons a user has*/
            public final static String TABLE_NAME = "userCouponsInfo";

            /**ID number of the user. Type: INTEGER*/
            public final static String _ID = "userID";

            /**ID number of the coupon. Type: INTEGER*/
            public final static String COUPON_ID = "couponID";

            /**Coupon redeemed or not. Type: BOOLEAN*/
            public final static String COLUMN_REDEEMED = "redeemed";
        }
    }
