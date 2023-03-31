package tnj.UI;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tnj.Framework.R;

public class MainActivity extends AppCompatActivity {

    TextView Choosetxt;
    TextView Ranktxt;
    TextView Infotxt;

    boolean start = false;
    static boolean startCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Choosetxt = (TextView) findViewById(R.id.Choosetxt);
        Ranktxt = (TextView) findViewById(R.id.Ranktxt);
        Infotxt = (TextView) findViewById(R.id.Infotxt);

    }

    public void ChoosePopupClick (View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivityForResult(intent, 1);
    }


    public void RankPopupClick (View v){
        //데이터 담아서 팝업(액티비티) 호출
        Cursor cursor;
        Intent intent = new Intent(this, RankActivity.class);

        for(int i=1; i<=10; i++) {
            intent.putExtra("data2",
                        i + " : " + "\n" + 
                              2 + " : " + "\n" +
                              3 + " : " + "\n" +
                              4 + " : " + "\n" +
                              5 + " : " + "\n" +
                              6 + " : " + "\n" +
                              7 + " : " + "\n" +
                              8 + " : " + "\n" +
                              9 + " : " + "\n" +
                            10 + " : " + "\n"
            );//
            String data = intent.getStringExtra("data2");
        }
        //intent.putExtra("data2", "1 : " + cursor.getString(1));


        startActivityForResult(intent, 2);
    }


    public void InfoPopupClick (View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("data3", "<게임 방법>\n\n\n"
                + "원하는 캐릭터를 골라 게임을 시작하세요!\n\n"
                + "캐릭터1 : 강력한 단방향 탄환, \n\n"
                + "캐릭터2 : 세갈래 방향 탄환\n     방어막 소환 스킬\n\n"
                + "캐릭터3 : 꺾이는 탄환, 순간이동 스킬\n\n"
                + "화면을 드래그해 움직이세요!: \n\n"
                + "화면을 더블터치해 스킬을 사용하세요!\n\n"
        );
        startActivityForResult(intent, 3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("Rank");
                Ranktxt.setText(result);
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("Info");
                Infotxt.setText(result);
            }
        }

    }

}