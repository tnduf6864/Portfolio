package tnj.Game.Enemy;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;
import tnj.Game.Missile.Missile_Enemy;

public class Enemy_5 extends Enemy{
    public Enemy_5() {
        super(AppManager.getInstance().getBitmap(R.drawable.bat));
        this.setPosition(740,0);
        this.initSpriteData(96, 96, 1, 4);
        hp = 1;
        speed = 5f;
        m_enemdir = new Vector2(1f,0);
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        attack();
        Move();
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 96f), (int)(m_y + 96f)); // 186 312
    }

    @Override
    public void attack() {
        if (System.currentTimeMillis( ) - lastShoot >= 3500) {
            lastShoot = System.currentTimeMillis( );

            AppManager.getInstance( ). getGameState2(). m_enemmslist .add(
                    new Missile_Enemy( m_x, m_y));
        }
    }


}