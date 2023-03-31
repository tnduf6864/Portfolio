package tnj.Level;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import tnj.Framework.AppManager;
import tnj.Framework.IState;
import tnj.Framework.SoundManager;
import tnj.Game.BackGround;


public class GameEnd implements IState {
    private BackGround m_background;

    @Override
    public void Destroy() {}

    @Override
    public void Init() {
        SoundManager.getInstance().startSound(1);
        m_background = new BackGround(3);
    }

    @Override
    public void Render(Canvas canvas) {

        //백그라운드 이미지
        m_background.Draw2(canvas);

        //제목(게임클리어 or 게임 종료...)
        Paint p1 = new Paint();
        p1.setTextSize(150);
        p1.setColor(Color.WHITE);
        canvas.drawText("GAME OVER !", 100,200, p1);

        //게임 점수
        Paint p2 = new Paint();
        p2.setTextSize(100);
        p2.setColor(Color.WHITE);
        canvas.drawText("SCORE : "+String.valueOf(AppManager.getInstance().getPlayer().getScore()), 250,700, p2);

        //나가기
        Paint p3 = new Paint();
        p3.setTextSize(50);
        p3.setColor(Color.RED);
        canvas.drawText("나가기" , 900,1700, p3);



        //System.currentTimeMillis()

    }

    @Override
    public void Update() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent evnet) {

        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getX()>=850 && event.getY()>=1650)  {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: {
                //두번 눌러야 나가도록 만듬.
                System.exit(0);
                break;
            }
        }

        }
        return true;
    }






}
