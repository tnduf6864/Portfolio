package tnj.Level;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;

import tnj.Framework.AppManager;
import tnj.Framework.CollisionManager;
import tnj.Framework.IState;
import tnj.Framework.R;
import tnj.Framework.SoundManager;
import tnj.Game.BackGround;
import tnj.Game.Enemy.Boss_2;
import tnj.Game.Missile.Missile;
import tnj.Game.Missile.Missile_Boss;
import tnj.Game.Missile.Missile_Player;
import tnj.Game.Player.Player;
import tnj.Game.StateBar;


public class BossState2 implements IState {
    private Boss_2 m_boss;
    public ArrayList<Missile_Boss> m_bossmslist = new ArrayList<>();
    private BackGround m_background;
    public int skillCount = AppManager.getInstance().getGameState2().skillCount; // 남은 스킬횟수를 가져옴
    Paint p1 = new Paint();

    long lastStage = System.currentTimeMillis();
    public BossState2(){AppManager.getInstance().setBossState2(this);  }

    int clickCount = 0;
    long startTime;
    long duration;

    private StateBar m_heart;
    private StateBar m_pause;

    @Override
    public void Destroy() {}

    @Override
    public void Init() {
        m_boss = new Boss_2();
        //m_background = new BackGround(1);
        m_background =AppManager.getInstance().getGameState2().m_background;
        p1.setTextSize(80);
        p1.setColor(Color.YELLOW);

        SoundManager.getInstance().startSound(1);
        m_heart = new StateBar(AppManager.getInstance().getBitmap(R.drawable.heart));
        m_pause = new StateBar(AppManager.getInstance().getBitmap(R.drawable.pause));
    }
    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        if(AppManager.getInstance().getPlayer().state == Player.STATE_UNBEATABLE) AppManager.getInstance().getPlayer().m_shield.Draw(canvas);
        AppManager.getInstance().getPlayer().Draw(canvas);
        m_boss.Draw(canvas);
        for(Missile_Player pms :AppManager.getInstance().getGameState1(). m_pmslist) pms.Draw(canvas);
        for(Missile bossms : m_bossmslist) { bossms.Draw(canvas); }
        if(System.currentTimeMillis() - lastStage<3000)
            canvas.drawText("WARNING",400,400,p1);

        //상태바
        m_heart.Draw(canvas, 0, 0);


        Paint p2 = new Paint( );
        p2.setTextSize(70);
        //글자 다듬는 코드 (별로중요하지않음)
        p2.setAntiAlias(true);
        p2.setColor(Color. RED);
        canvas.drawText("x  "+String.valueOf(AppManager.getInstance().getPlayer().getLife()), 170,100, p2);

        Paint p = new Paint( );
        p.setTextSize(70);
        //글자 다듬는 코드 (별로중요하지않음)
        p.setAntiAlias(true);
        p.setColor(Color. YELLOW);
        canvas.drawText("SCORE : "+String.valueOf(AppManager.getInstance().getPlayer().getScore()), 400,100, p);

        m_pause.Draw(canvas, 950, 0);
        p.setColor(Color. GREEN);
        canvas.drawText("Skill : "+String.valueOf(skillCount), 20,1750, p);
    }
    @Override
    public void Update() {
        long GameTime= System.currentTimeMillis();
        m_boss.Update(GameTime);
        AppManager.getInstance().getPlayer().Update(GameTime);
        m_background.Update(GameTime);

        if(AppManager.getInstance().getPlayer().state == Player.STATE_UNBEATABLE) {
            AppManager.getInstance().getPlayer().m_shield.Update
                    (GameTime, AppManager.getInstance().getPlayer().LastSkill, AppManager.getInstance().getPlayer().m_x, AppManager.getInstance().getPlayer().m_y);
            if(GameTime - AppManager.getInstance().getPlayer().LastSkill >= 4000) AppManager.getInstance().getPlayer().state = Player.STATE_NORMAL;
        } else if(AppManager.getInstance().getPlayer().state == Player.STATE_SHOT) {
            if(GameTime - AppManager.getInstance().getPlayer().LastHit >= 500) AppManager.getInstance().getPlayer().state = Player.STATE_NORMAL;
        }

        for( int i=0; i<m_bossmslist.size(); i++) {
            Missile bossms = m_bossmslist.get(i);
            bossms.Update();
            if(bossms.state == Missile.STATE_OUT) { m_bossmslist.remove(i); }
        }
        for(int i=0; i<AppManager.getInstance().getGameState1().m_pmslist.size(); i++) {
            Missile_Player pms = AppManager.getInstance().getGameState1().m_pmslist.get(i);
            pms.Update();
            if(pms.state == Missile.STATE_OUT) AppManager.getInstance().getGameState1().m_pmslist.remove(i);
        }
        CheckCollision();
        CheckHP();
        if(m_boss.state == m_boss.STATE_OUT){
            for(int x=0;x<10;x++)
                AppManager.getInstance().getPlayer().addScore();
            SoundManager.getInstance().pauseSound(1);
            AppManager.getInstance().getGameView().changeGameState(new GameState3());

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent evnet) {
        // 키 입력에 따른 플레이어 이동
        float x = AppManager.getInstance().getPlayer().getX( );
        float y = AppManager.getInstance().getPlayer().getY( );

        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {// 왼쪽
            AppManager.getInstance().getPlayer().setPosition(x - 15, y);

        }
        if(keyCode == KeyEvent. KEYCODE_DPAD_RIGHT) { // 오른쪽
            AppManager.getInstance().getPlayer().setPosition(x + 15, y);

        }
        if(keyCode == KeyEvent. KEYCODE_DPAD_UP) { // 위
            AppManager.getInstance().getPlayer().setPosition(x, y - 15);
        }
        if(keyCode == KeyEvent. KEYCODE_DPAD_DOWN) { // 아래
            AppManager.getInstance().getPlayer().setPosition(x, y + 15);
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float px =  AppManager.getInstance().getPlayer().getX( );
        float py =  AppManager.getInstance().getPlayer().getY( );

        float cx=px,cy=py;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cx = event.getX();
                cy = event.getY();
                break;

            case MotionEvent.ACTION_MOVE: {
                float mx = event.getX();
                float my = event.getY();
                float X = mx -cx, Y = my - cy;

                if (Math.abs(X) >= 30 || Math.abs(Y) >= 30) {
                    if (X >= 30) X = 50;
                    if (Y >= 30) Y = 50;
                    if (X <= -30) X = -50;
                    if (Y <= -30) Y = -50;
                }
                AppManager.getInstance().getPlayer().setPosition((int) (cx + X), (int) (cy + Y));
            }


        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                clickCount++;

                if(clickCount == 1) {
                    startTime = System.currentTimeMillis();
                }

                else if(clickCount == 2) {
                    duration = System.currentTimeMillis() - startTime;
                    if(duration <= 1000 && skillCount > 0) {
                        AppManager.getInstance().getPlayer().skillShield();
                        skillCount--;
                        clickCount = 0;
                        duration = 0;
                    } else {
                        clickCount = 0;
                        startTime = System.currentTimeMillis();
                    }
                }
                break;
        }
        return true;
    }

    public void CheckCollision() {
        for(Missile_Player pms :AppManager.getInstance().getGameState1(). m_pmslist) {

            if(CollisionManager.CheckBoxToBox(pms.m_BoundBox, m_boss.m_BoundBox)){
                AppManager.getInstance().getGameState1().m_pmslist.remove(pms);
                m_boss.destroyEnemy();
                return;
            }

        }

        if(CollisionManager.CheckBoxToBox(AppManager.getInstance().getPlayer().m_BoundBox, m_boss. m_BoundBox)) {
            if(AppManager.getInstance().getPlayer().state == Player.STATE_NORMAL) {
                AppManager.getInstance().getPlayer().state = Player.STATE_SHOT;
                AppManager.getInstance().getPlayer().LastHit = System.currentTimeMillis();
                AppManager.getInstance().getPlayer().destroyPlayer();
            }
            else {

            }
        }

        for( int i = 0; i<m_bossmslist.size(); i++) {
            if(CollisionManager.CheckBoxToBox(AppManager.getInstance().getPlayer().m_BoundBox, m_bossmslist.get(i). m_BoundBox)) {
                m_bossmslist.remove(i);
                if(AppManager.getInstance().getPlayer().state == Player.STATE_NORMAL) {
                    AppManager.getInstance().getPlayer().state = Player.STATE_SHOT;
                    AppManager.getInstance().getPlayer().LastHit = System.currentTimeMillis();
                    AppManager.getInstance().getPlayer().destroyPlayer();
                } else {

                }
            }
        }
    }


    public void CheckHP() {
        if (AppManager.getInstance().getPlayer().getLife() <= 0) {
            AppManager.getInstance().getGameView().changeGameState(new GameEnd());
        }
    }

}

