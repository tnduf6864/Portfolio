package tnj.Level;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;


import tnj.Framework.AppManager;
import tnj.Framework.CollisionManager;
import tnj.Framework.IState;
import tnj.Framework.R;
import tnj.Framework.SoundManager;
import tnj.Game.BackGround;
import tnj.Game.Enemy.Enemy;
import tnj.Game.Enemy.Enemy_4;
import tnj.Game.Enemy.Enemy_7;
import tnj.Game.Item.Item;
import tnj.Game.Item.Item_1;
import tnj.Game.Item.Item_2;
import tnj.Game.Missile.Missile;
import tnj.Game.Missile.Missile_Boss;
import tnj.Game.Missile.Missile_Player;
import tnj.Game.Player.Player;
import tnj.Game.StateBar;

public class GameState3 implements IState {
    public ArrayList<Missile_Boss> m_bossmslist = new ArrayList<>();
    protected BackGround m_background;
    public ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>();
    public ArrayList<Missile> m_enemmslist = new ArrayList<Missile>();
    public ArrayList<Item> m_item = new ArrayList<>();
    long lastRegenEnemy = System.currentTimeMillis();
    Paint p1 = new Paint();
    long lastStage = System.currentTimeMillis();

    //player member///////////////////////////////
    public int skillCount = AppManager.getInstance().getBossState2().skillCount; // 남은 스킬횟수를 가져옴
    public boolean isRespawnTime;
    Random randEnem = new Random();
    Random randItem = new Random();
    Random popItem = new Random();

    int clickCount = 0;
    long startTime;
    long duration;

    Timer m_timer = new Timer();



    private StateBar m_heart;
    private StateBar m_pause;
    //////////////////////////////

    public GameState3(){
        AppManager.getInstance().setGameState3 (this);
    }
    @Override
    public void Destroy() {

    }

    @Override
    public void Init() {
        m_background = new BackGround(2);
        p1.setTextSize(80);
        p1.setColor(Color.YELLOW);

        SoundManager.getInstance().startSound(0);
        m_heart = new StateBar(AppManager.getInstance().getBitmap(R.drawable.heart));
        m_pause = new StateBar(AppManager.getInstance().getBitmap(R.drawable.pause));
    }

    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        if(AppManager.getInstance().getPlayer().state == Player.STATE_UNBEATABLE) AppManager.getInstance().getPlayer().m_shield.Draw(canvas);
        for(Missile bossms : m_bossmslist) { bossms.Draw(canvas); }
        if(System.currentTimeMillis() - lastStage<3000)
            canvas.drawText("STAGE 3",400,400,p1);
        AppManager.getInstance().getPlayer().Draw(canvas);
        for(Enemy enem: m_enemlist) enem.Draw(canvas);
        for (Missile enemms : m_enemmslist) { enemms.Draw(canvas); }
        for(Missile_Player pms : AppManager.getInstance().getGameState1().m_pmslist) pms.Draw(canvas);


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
        m_background.Update(GameTime);
        if(AppManager.getInstance().getPlayer().state == Player.STATE_UNBEATABLE) {
            AppManager.getInstance().getPlayer().m_shield.Update
                    (GameTime, AppManager.getInstance().getPlayer().LastSkill, AppManager.getInstance().getPlayer().m_x, AppManager.getInstance().getPlayer().m_y);
            if(GameTime - AppManager.getInstance().getPlayer().LastSkill >= 4000) AppManager.getInstance().getPlayer().state = Player.STATE_NORMAL;
        } else if(AppManager.getInstance().getPlayer().state == Player.STATE_SHOT) {
            if(GameTime - AppManager.getInstance().getPlayer().LastHit >= 500) AppManager.getInstance().getPlayer().state = Player.STATE_NORMAL;
        }

        for( int i=0; i<m_bossmslist.size(); ++i) {
            Missile bossms = m_bossmslist.get(i);
            bossms.Update();
            if(bossms.state == Missile.STATE_OUT) { m_bossmslist.remove(i); }
        }

        for(int i=0; i<AppManager.getInstance().getGameState1().m_pmslist.size(); i++) {
            Missile_Player pms = AppManager.getInstance().getGameState1().m_pmslist.get(i);
            pms.Update();
            if(pms.state == Missile.STATE_OUT) AppManager.getInstance().getGameState1().m_pmslist.remove(i);
        }
        AppManager.getInstance().getPlayer().Update(GameTime);
        for(int i=0; i<m_enemlist.size(); i++) {
            Enemy enem = m_enemlist.get(i);
            enem.Update(GameTime);
            if(enem.state == Enemy.STATE_OUT) m_enemlist.remove(i);
            if(enem.state == Enemy.STATE_DEAD) {
                if(System.currentTimeMillis() - enem.deadTime >= 1500){
                    enem.state = Enemy.STATE_OUT;
                }
            }
        }
        for ( int i = m_enemmslist .size( )-1; i >= 0; i--) {
            Missile enemms = m_enemmslist .get(i);
            enemms.Update( );
            if ( enemms. state == Missile. STATE_OUT) { m_enemmslist .remove(i); }
        }
        if(System.currentTimeMillis() - lastStage>30000) {
            SoundManager.getInstance().pauseSound(0);
            AppManager.getInstance().getGameView().changeGameState(new BossState3());
        }
        makeEnemy();
        CheckCollision();
        CheckHP();
    }

    public void makeItem(float x, float y) {
        int itemtype = randItem.nextInt(2);
        Item item = null;
        if (itemtype == 0) item = new Item_1( );
        else if (itemtype == 1) item = new Item_2( );
        item.setPosition(x, y);
        item.movetype = randItem.nextInt(2);
        m_item.add(item);
    }
    public void makeDragon(){
        Enemy enem = null;

        enem = new Enemy_4();
        enem.setPosition(randEnem.nextInt(800), randEnem.nextInt(400));
        enem.moveType = 1;
        m_enemlist.add(enem);
    }

    public void makeDragon1(){
        Enemy enem = null;

        enem = new Enemy_7();
        enem.setPosition(randEnem.nextInt(800), randEnem.nextInt(400));
        enem.moveType = randEnem.nextInt(3);
        m_enemlist.add(enem);
    }
    public void makeEnemy() {

        if (System.currentTimeMillis() - lastRegenEnemy >= 3300) {
            lastRegenEnemy = System.currentTimeMillis();

           makeDragon();
           makeDragon1();
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
                    if(duration <= 800 && skillCount > 0) {
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
        for(Missile_Player pms :  AppManager.getInstance().getGameState1().m_pmslist) {
            for(int i=0;i<m_enemlist.size();i++) {
                if(m_enemlist.get(i).state==Enemy.STATE_DEAD) continue;
                if(CollisionManager.CheckBoxToBox(pms.m_BoundBox, m_enemlist.get(i).m_BoundBox)){
                    float enemX = m_enemlist.get(i).getX();
                    float enemY = m_enemlist.get(i).getY();

                    if(popItem.nextInt(10) + 1 == 1)
                        makeItem(enemX, enemY);

                    AppManager.getInstance().getGameState1().m_pmslist.remove(pms);
                    m_enemlist.get(i).destroyEnemy();
                    if(m_enemlist.get(i).hp<=0) {
                        m_enemlist.get(i).dead();
                        SoundManager.getInstance().play(0);
                        AppManager.getInstance().getPlayer().addScore();
                    }
                    return;
                }
            }
        }

        for(int i = 0; i<m_enemlist.size(); i++) {
            if(CollisionManager.CheckBoxToBox(AppManager.getInstance().getPlayer().m_BoundBox, m_enemlist.get(i). m_BoundBox)) {
                m_enemlist.get(i).destroyEnemy();
                if(m_enemlist.get(i).hp<=0) {
                    m_enemlist.remove(i);
                    SoundManager.getInstance().play(0);
                }
                if(AppManager.getInstance().getPlayer().state == Player.STATE_NORMAL) {
                    AppManager.getInstance().getPlayer().state = Player.STATE_SHOT;
                    AppManager.getInstance().getPlayer().LastHit = System.currentTimeMillis();
                    AppManager.getInstance().getPlayer().destroyPlayer();
                }
                else {

                }
            }
        }

        for( int i = 0; i<m_enemmslist.size(); i++) {
            if(CollisionManager.CheckBoxToBox(AppManager.getInstance().getPlayer().m_BoundBox, m_enemmslist.get(i). m_BoundBox)) {
                m_enemmslist.remove(i);
                if(AppManager.getInstance().getPlayer().state == Player.STATE_NORMAL) {
                    AppManager.getInstance().getPlayer().state = Player.STATE_SHOT;
                    AppManager.getInstance().getPlayer().LastHit = System.currentTimeMillis();
                    AppManager.getInstance().getPlayer().destroyPlayer();
                } else {

                }
            }
        }

        for(int i = 0; i < m_item.size(); i++) {
            if(CollisionManager.CheckBoxToBox(m_item.get(i).m_BoundBox, AppManager.getInstance().getPlayer().m_BoundBox)) {
                if(m_item.get(i).itemtype == Item.ITEM_STATE_1){
                    int shootLevel = AppManager.getInstance().getPlayer().getPlayerShootLevel();
                    if(shootLevel < 3)
                        AppManager.getInstance().getPlayer().setPlayerShootLevel(++shootLevel);
                }
                else if(m_item.get(i).itemtype == Item.ITEM_STATE_2){
                    skillCount++;
                }
                m_item.remove(i);
            }
        }
    }

    public void CheckHP() {
        if ( AppManager.getInstance().getPlayer().getLife() <= 0) {
            AppManager.getInstance().getGameView().changeGameState(new GameEnd());
        }
    }

}
