package tnj.Game.Enemy;


import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.Random;

import tnj.Framework.AppManager;
import tnj.Framework.CollisionManager;
import tnj.Framework.R;
import tnj.Framework.SpriteAnimation;
import tnj.Framework.Vector2;
import tnj.Game.EnemyPhase.IBossAttackPattern;

public abstract class Enemy extends SpriteAnimation {
    public Rect m_BoundBox = new Rect(); // 충돌박스
    public IBossAttackPattern m_attack; // 스트레티지 패턴
    public Random rand = new Random();

    public long deadTime;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_DEAD = 2;
    public static final int STATE_OUT = 1;
    public int state =  STATE_NORMAL;

    // 상태(phase)마다 움직임의 여러패턴상태를 나타내기 위한 패턴의 상태변수
    public static final int MOVE_PATTERN_0 = 0; // Idle상태
    public static final int MOVE_PATTERN_1 = 1;
    public static final int MOVE_PATTERN_2 = 2;
    public static final int MOVE_PATTERN_3 = 3;
    public static final int MOVE_PATTERN_4 = 4;
    public static final int MOVE_PATTERN_5 = 5;
    public static final int MOVE_PATTERN_C = 6; // curve상태
    public static final int MOVE_PATTERN_S = 7;

    public Vector2 m_msdir; // 미사일 방향
    public Vector2 m_enemdir;
    public float m_enemangle = 0f;
    public int moveType;
    public int hp;
    public int msCnt;
    public float speed = 5.0f;
    public long lastShoot = System.currentTimeMillis();
    public long lastMove = System.currentTimeMillis();

    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }
    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
    }
    public void setAttack(IBossAttackPattern _attack) { m_attack = _attack; }
    public void attack(){};

    void Move( ) { // 일반적 움직이는 기본 로직
        if( moveType == MOVE_PATTERN_1) {
            moveToPos(AppManager.getInstance().getPlayer().m_BoundBox);
        }
        else if(moveType == MOVE_PATTERN_2) {
            if( m_y <= 200 ) m_y += speed;// 중간지점까지 기본속도
            else m_y += speed * 4; // 중간지점 이후 빠른속도
        }
        else if( moveType == MOVE_PATTERN_3) {
            lastMove = System.currentTimeMillis();
            if(m_x<400) {
                if (moveCurve(1, 500,true)){
                    moveType = MOVE_PATTERN_2;
                }
              }else {
                if (moveCurve(-1, 500,true)) {
                    moveType = MOVE_PATTERN_2;
                }
            }
        }else if (moveType == MOVE_PATTERN_S){
        }
        if(m_y > 1800 || m_x<-200 || m_x>1200) state = STATE_OUT;
    }

    // 적의 움직임의 기본 패턴을 메소드로 선언

    // 특정 시간동안 멈춰있도록 하는 메소드
    public boolean moveStop(long time, boolean chk){ // 멈출시간, lastMove업데이트 여부(default: 1)
        if(System.currentTimeMillis() - lastMove >= time) {
            if(chk)lastMove = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    // 특정 각도로와 시간만큼 곡선운동을 하도록 하는 메소드
    public Boolean moveCurve(float angle, long time, boolean chk){ // 각도, 시간, lastMove업데이트 여부(default:1 )
        if(System.currentTimeMillis() - lastMove >= time) {
            if(chk) lastMove = System.currentTimeMillis();
            return true;
        }
        m_enemangle = (m_enemangle + angle)%360;
        m_enemdir.x = (float)(Math.cos(Math.PI / 180 * m_enemangle));
        m_enemdir.y = (float)(Math.sin(Math.PI / 180 * m_enemangle));
        m_enemdir.normalVector();
        m_x += speed*m_enemdir.x;
        m_y += speed*m_enemdir.y;
        return false;
    }
    // 특정 좌표로 이동하도록 하는 메소드
    public boolean moveToPos(Rect dest){ //직선운동, dest: 목적지
        m_enemdir = new Vector2((float)(dest.centerX())-m_x, (float)(dest.centerY())-m_y);
        m_enemdir.normalVector();
        m_x += speed*m_enemdir.x;
        m_y += speed*m_enemdir.y;
        if(CollisionManager.CheckBoxToBox(m_BoundBox, dest)){
            moveType = MOVE_PATTERN_0;
            return true;
        }
        return false;
    }
    // 보스가 죽었을경우 이펙트를 나타내기 위한 메소드
    public void dead(){
        this.m_currentFrame = 0;
        m_bitmap = AppManager.getInstance().getBitmap(R.drawable.explosion);
        this.initSpriteData(198, 186 , 4,6);
        this.state = STATE_DEAD;
        deadTime = System.currentTimeMillis();
    }
    // 피격시 hp하나씩 감소
    public void destroyEnemy() { hp--; }

}
