package com.ismailgemalmaz.notlarim;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyNoteContentProvider extends ContentProvider {

    static final String PROVİDER_NAME="com.ismailgemalmaz.notlarim.MyNoteContentProvider";
    static final String URL="content://"+PROVİDER_NAME+"/note";
    static final Uri CONTENT_URI=Uri.parse(URL);

    static final String NAME="name";
    static final String WRİTE_NOTE="writeNote";
    static final String DATE="date";
    static final String IMAGE="image";

    static final UriMatcher URI_MATCHER;
    static final int NOTE=1;

    static {
        URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVİDER_NAME,"note",NOTE);
    }

    private static HashMap<String, String> NOTE_PROJECTİON_MAP;

    //-----------------SQLiteDatabase--------------------------
    private SQLiteDatabase sqLiteDatabase;
    static final String DATABASE_NAME="Note";
    static final String NOTE_TABLE_NAME="note";
    static final int DATABASE_VERSİON=1;
    static final String CREATE_DATABASE_TABLE="CREATE TABLE "+NOTE_TABLE_NAME+"(name TEXT NOT NULL,writeNote TEXT NOT NULL,date INT,image BLOB NOT NULL)";

    private class NoteSqliteDataBaseHelper extends SQLiteOpenHelper{


        public NoteSqliteDataBaseHelper(@Nullable Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSİON);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE İF EXISTS "+NOTE_TABLE_NAME);
            onCreate(db);
        }
    }


    //-----------------SQLiteDatabase-------------------------------

    //CONTENT PROVİDER OVERRİDE METHODS
    @Override
    public boolean onCreate() {
        Context context=getContext();
        NoteSqliteDataBaseHelper noteSqliteDataBaseHelper=new NoteSqliteDataBaseHelper(context);
        sqLiteDatabase=noteSqliteDataBaseHelper.getWritableDatabase();
        return sqLiteDatabase!=null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(NOTE_TABLE_NAME);
        switch (URI_MATCHER.match(uri)){
            case NOTE:
                sqLiteQueryBuilder.setProjectionMap(NOTE_PROJECTİON_MAP);
                break;
            default:
                //
        }

        if(sortOrder!=null&&sortOrder.matches("")){
            sortOrder=NAME;
        }

        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
     long rowId=  sqLiteDatabase.insert(NOTE_TABLE_NAME,"",values);
       if(rowId>0){
           Uri newUri= ContentUris.withAppendedId(CONTENT_URI,rowId);
           getContext().getContentResolver().notifyChange(newUri,null);
       return newUri;
       }

       throw new SQLException("Eror?");

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}