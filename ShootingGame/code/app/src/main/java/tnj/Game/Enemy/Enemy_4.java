package tnj.Game.Enemy;


import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;
import tnj.Game.Missile.Missile_Enemy2;

public class Enemy_4 extends Enemy{
    public Enemy_4() {
        super(AppManager.getInstance().getBitmap(R.drawable.enemy55));
        this.setPosition(740,0);
        this.initSpriteData(135, 198, 4, 3);
        hp = 8;
        speed = 10f;
        m_enemdir = new Vector2(1f,0);
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 135f), (int)(m_y + 198f));
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 135f), (int)(m_y + 198f));
    }

    @Override
    public void attack() {
        if (System.currentTimeMillis( ) - lastShoot >= 2500) {
            lastShoot = System.currentTimeMillis( );

            AppManager.getInstance( ). getGameState3(). m_enemmslist .add(
                    new Missile_Enemy2( (int)(m_x+174f), (int)(m_y+218f)));
        }
    }
}