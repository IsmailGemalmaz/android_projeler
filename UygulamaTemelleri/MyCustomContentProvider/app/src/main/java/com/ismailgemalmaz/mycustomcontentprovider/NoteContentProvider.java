package com.ismailgemalmaz.mycustomcontentprovider;

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
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URI;
import java.util.HashMap;

public class NoteContentProvider extends ContentProvider {

    //content://com.ismailgemalmaz.mycustomcontentprovider.NoteContentProvider/note
    static final String PROVİDER_NAME = "com.ismailgemalmaz.mycustomcontentprovider.NoteContentProvider";
    static final String URL = "content://" + PROVİDER_NAME + "/note";
    static final Uri CONTENT_URI = Uri.parse(URL);//verileri kaydettiğim yol url uri uri=gösterim şkeli

    static final String NAME = "name";
    static final String İMAGE = "image";//tabodaki bölümlerin referansı

    static final UriMatcher URI_MATCHER;//uri eşleştirici
    static final int NOTE = 1;//buda note tablosunu temsil ediyor url mizdeki notuda temsil ediyor tabiki

    //bu static bu sınıftan herhangi bir değişkene ulaşılğında gel bu static i çalıştır
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVİDER_NAME, "note", NOTE);
    }

    private static HashMap<String, String> NOTE_PROJECTİON_MAP;

    //----------------------------------SQLiteDataBase------------------------------------------

    private SQLiteDatabase sqLiteDatabase;
    static final String DATABASE_NAME = "Note";
    static final String NOTE_TABLE_NAME = "note";
    static final int DATABASE_VERSİON = 1;
    static final String CREATE_DATABASE_TABLE = "CREATE TABLE " + NOTE_TABLE_NAME + "VALUE (name TEXT NOT NULL,image BLOB NOT NULL) ;";

    private static class NoteDataBaseHelper extends SQLiteOpenHelper {

        public NoteDataBaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSİON);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DATABASE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE_NAME);
            onCreate(db);
        }
    }


//----------------------------------SQLiteDataBase------------------------------------------

    // >>>>>>>CONTENT PROVİDER OVERRİDE METHODS<<<<<<<<<

    @Override
    public boolean onCreate() {
        Context context = getContext();
        NoteDataBaseHelper noteDataBaseHelper = new NoteDataBaseHelper(context);//database helper sınıfndan nesne oluşturup bu neseneyi sqllitedatabase değişjkenine atadık
        sqLiteDatabase = noteDataBaseHelper.getWritableDatabase();
        return sqLiteDatabase != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();//sorgu yapmamız için yardımcı bir sınıf metod
        sqLiteQueryBuilder.setTables(NOTE_TABLE_NAME);//hangi tabloda sorgu yapcağımızı söylediğimiz yer SQLitequerybuildir yardımı ile
        //kendi uri imiz ile verilen uri eşleştirmeye çalışacağız
        switch (URI_MATCHER.match(uri)) {
            case NOTE:
                sqLiteQueryBuilder.setProjectionMap(NOTE_PROJECTİON_MAP);
                break;
            default:
                //
        }//kendi cursorümüzü oloşturacağız bizden istendiği için bunları yaptık

        //neye göre dizileceği
        if (sortOrder == null || sortOrder.matches("")) {
            sortOrder = NAME;//name göre dizilecek
        }

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);//kendi sorgumuzu oluşturduk
        cursor.setNotificationUri(getContext().getContentResolver(), uri);//uri da herhangi bir değişiklik oloşursa query bununla haberdar olacak
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
        long rowId = sqLiteDatabase.insert(NOTE_TABLE_NAME, "", values);//tabloya veri ekldik long a eşitledik
        if (rowId > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, rowId);//bizim urimiza sonuna ekleme yapıcak
            getContext().getContentResolver().notifyChange(newUri, null);//yine urimiza ekleme olduğunu gözlemliyecek
            return newUri;
        }

        throw new SQLException("ERORO!");//hata fırlatacak
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
            int rowCount=0;
            switch (URI_MATCHER.match(uri)){
                case NOTE :
                 rowCount=sqLiteDatabase.delete(NOTE_TABLE_NAME,selection,selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("uri eşleşmedi");
            }
            getContext().getContentResolver().notifyChange(uri,null);
        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount=0;
        switch (URI_MATCHER.match(uri)){
            case NOTE:
                sqLiteDatabase.update(NOTE_TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("uri eşleşmedi");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowCount;
    }
}
