package tnj.Game.Missile;

import tnj.Framework.AppManager;
import tnj.Framework.R;

// 일반적 미사일
public class Missile_Enemy extends Missile {
    public Missile_Enemy(float x, float y) {
        super(AppManager.getInstance( ).getBitmap(R.drawable.missile_2));
        this.setPosition(x, y);
    }
    public void Update( ) {
        //미사일이 아래로 발사되는 효과
        m_y += 25;
        if(m_y > 1800) state = STATE_OUT;
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 80f), (int)(m_y + 80f));
    }
}
