package tnj.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class DBView extends View {
    DBHelper m_helper;
    Rect rect;

    public DBView (Context context) {
        super(context);
        //m_helper = new DBHelper(context, "rank.db",null,1);
        //m_helper.onInsert("rank.db",100);
    }

    public boolean onTouchEvent(MotionEvent event) {

        //DB오류로 DB생성 코드 제거
        /*
                SQLiteDatabase db = m_helper.getWritableDatabase();

                // 데이터베이스에 넣을 정보를 테이블 구조에 맞게 구성
                ContentValues row = new ContentValues();
                row.put("name", "안녕"); // name
                row.put("score", "8000"); // score

                // 데이터베이스에 점수를 기록
                db.insert("RankBoard", null, row);

                /*
                // 기록 후 데이터베이스를 닫음
                db.close();
                invalidate(); // 화면 갱신

                SQLiteDatabase db2 = m_helper.getWritableDatabase();
                db2.delete("RankBoard", null, null);
                db2.close();
                invalidate();
                */


        return super.onTouchEvent(event);
    }


    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        invalidate();

        Paint p = new Paint ();
        p.setTextSize(100);
        p.setColor(Color.BLACK);
        canvas.drawText("랭킹", 350 , 200 ,p);


        Paint p2 = new Paint ();
        p2.setTextSize(60);
        p2.setColor(Color.DKGRAY);

        /*
        SQLiteDatabase db2 = m_helper .getReadableDatabase( );
        Cursor cursor = db2.query( "RankBoard", new String[]{ "score" },
        null, null, null, null, null );

         */

        for ( int i=1; i<=10; i++ ) {
            //if ( cursor.moveToNext( ) == false) break;
            //canvas.drawText(i + " : " +cursor.getString(0) + "\n",
            //        50, 400+(100*i), p2 );
            canvas.drawText(i + " : \n",
                    50, 400+(100*i), p2 );

        }

        //p2.setTextAlign(Paint.Align.RIGHT);

        Paint p3 = new Paint ();
        p3.setTextSize(50);
        p3.setColor(Color.RED);

        canvas.drawText("종료",500,1500,p3);
        //canvas.
        //db2.close( );

        invalidate();
    }



    public boolean onKeyDown ( int keyCode, KeyEvent event ) {
        return true;
    }




}
