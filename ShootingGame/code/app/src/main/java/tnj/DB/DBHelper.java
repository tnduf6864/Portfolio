package tnj.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ( "CREATE TABLE RankBoard(score String NOT NULL);");

        db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("score", "100"); // score
        // 데이터베이스에 점수를 기록
        db.insert("RankBoard", null, row);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onInsert(SQLiteDatabase db, int i) {
        SQLiteDatabase db2 = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("score", i); // score
        // 데이터베이스에 점수를 기록
        db2.insert("RankBoard", null, row);
        db2.close();
    }

    public void onDelete(SQLiteDatabase db) {
        SQLiteDatabase db3 =getWritableDatabase();
        db3.delete("RankBoard", null, null);
        db3.close();
    }
}