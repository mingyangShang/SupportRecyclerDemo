package com.smy.demo.supportrecyclerdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by starkshang on 2015/10/29.
 */
public class DemoDBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "demo_db";

    public static final String TABLE_NAME = "demo_table";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public DemoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DemoDBHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + AGE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion,
                            int newVersion) {
        // 空实现，勿删！！因为基类该方法会抛异常
    }

    public boolean insertUser(final User user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,user.name);
        cv.put(AGE,user.age);
        return database.insert(TABLE_NAME,null,cv)>0;
    }

    public boolean deleteUserById(final String id){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, "_id=", new String[]{id}) > 0;
    }

    public boolean deleteAll(){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME,null,null) > 0;
    }

    public Cursor queryAll(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME,null);
        return cursor;
    }

}
