package tnj.Game.Enemy;

import android.media.MediaPlayer;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;

public class Enemy_2 extends Enemy{
    public Enemy_2() {
        super(AppManager.getInstance().getBitmap(R.drawable.enemy22));
        this.setPosition(440,0);
        this.initSpriteData(144,126, 3, 3);
        hp = 2;
        speed = 2.5f;
        m_enemdir = new Vector2(1f,0);
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 144f), (int)(m_y + 126f)); // 186 312

    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
        m_BoundBox.set( (int)m_x, (int)m_y, (int)m_x + 144, (int)m_y + 126);
    }

    @Override
    public void attack() {

    }

}
