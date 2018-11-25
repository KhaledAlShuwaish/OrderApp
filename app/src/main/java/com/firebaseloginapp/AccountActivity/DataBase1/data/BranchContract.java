package com.firebaseloginapp.AccountActivity.DataBase1.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BranchContract {
    public static final String CONTENT_AUTHORITY = "com.firebaseloginapp.AccountActivity.DataBase1";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_Branch = "DataBase1";

    private BranchContract() {
    }

    public static final class BranchEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_Branch);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Branch;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Branch;
        public static final String TABLE_NAME = "branch";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_Branch_NAME = "Name";
        public static final String COLUMN_Branch_Location = "SupplierEmail";

    }
}
