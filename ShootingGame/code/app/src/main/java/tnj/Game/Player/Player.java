package tnj.Game.Player;

import android.graphics.Bitmap;
import android.graphics.Rect;

import tnj.Framework.SpriteAnimation;

public abstract class Player extends SpriteAnimation {
    public Rect m_BoundBox = new Rect( );
    public Shield m_shield;

    public long LastSkill; // 방어막 지속시간을 위한 변수
    public long LastHit; // 적에게 피격시 0.5초 무적을 구현시키기 위한 변수

    public static final int STATE_NORMAL = 0; // 기본상태
    public static final int STATE_UNBEATABLE = 1; // 방어막상태일때의 상태변수
    public static final int STATE_SHOT = 2; // 적에게 피격시 상태
    public int state = STATE_NORMAL;

    int m_Life = 20;
    int m_score = 0;
    public int playerShootLevel = 1; // 플레이어 탄 레벨. 아이템1을 먹으면 증가
    public Player(Bitmap bitmap){
        super(bitmap);
        m_shield = new Shield();
    }

    public abstract void Attack();
    public void skillShield() { // 쉴드를 생성하기 위한 메소드. 상태변경, 시작시간 저장
        LastSkill = System.currentTimeMillis();
        state = STATE_UNBEATABLE;
    }

    @Override
    public void Update (long gameTime) {
        super.Update(gameTime);
    }

    public int getLife() { return m_Life; }
    public void destroyPlayer() { m_Life--; }
    public int getScore() { return m_score; }
    public void addScore() { m_score+=50; }
    public int getPlayerShootLevel() { return playerShootLevel; }

    public void setPlayerShootLevel(int i){
        playerShootLevel+=i;
    };
}
