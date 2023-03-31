package tnj.Game.Missile;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.CollisionManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;

// 일반적 2 미사일
public class Missile_Enemy2 extends Missile {
    long lastMissile = System.currentTimeMillis();
    public Vector2 m_msdir; // 미사일 방향


    public Missile_Enemy2(float x, float y) {
        super(AppManager.getInstance( ).getBitmap(R.drawable.missile_2));
        this.setPosition(x, y);
    }
    public void Update( ) {
        //미사일이 아래로 발사되는 효과
        if(System.currentTimeMillis() - lastMissile >=8000) state = STATE_OUT;
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 80f), (int)(m_y + 80f));
        moveToPos(AppManager.getInstance().getPlayer().m_BoundBox);

    }
    public boolean moveToPos(Rect dest){ //직선운동, dest: 목적지
        m_msdir = new Vector2((float)(dest.centerX())-m_x, (float)(dest.centerY())-m_y);
        m_msdir.normalVector();
        m_x += speed*m_msdir.x;
        m_y += speed*m_msdir.y;
        if(CollisionManager.CheckBoxToBox(m_BoundBox, dest)){

            return true;
        }
        return false;
    }
}
