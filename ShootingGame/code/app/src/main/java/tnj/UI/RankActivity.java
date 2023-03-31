package tnj.UI;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import tnj.DB.DBHelper;
import tnj.DB.DBView;
import tnj.Framework.R;


public class RankActivity extends Activity {

    TextView txtText;
    DBHelper m_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rank_activity);

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data2");

        txtText.setText(data);


        setContentView(new DBView(this));
    }


    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //종료 기능 구현
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (event.getX() >= 600 && event.getY() >= 1400) {
                    finish();
                }
                break;
            }

        }
        return false;
    }


}


