package com.example.mauro.movilvisionglosariolenguajeseas.model;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Mauro on 23/10/2018.
 */

public class AdminSQLiteOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "vocabulario.db";
    private static final int DATABASE_VERSION = 1;
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
    }


}
