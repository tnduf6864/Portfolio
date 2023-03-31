package tnj.Game.Missile;


import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;

// 보스 미사일
public class Missile_Boss extends Missile {
    public Missile_Boss(float x, float y, Vector2 dir) {
        super(AppManager.getInstance( ).getBitmap(R.drawable.missile_1));
        this.setPosition(x, y);
        this.setDirection(dir);
    }
    public void Update() {
        m_x += (m_dir.x*speed);
        m_y += (m_dir.y*speed);
        if(m_y > 1800 || m_y<-150 || m_x<-150 || m_x>1200) state = STATE_OUT;
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 48f), (int)(m_y + 48f));
    }

}
