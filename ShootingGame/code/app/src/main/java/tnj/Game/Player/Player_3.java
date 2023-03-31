package tnj.Game.Player;

import android.util.Log;

import java.util.Timer;
import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.SoundManager;
import tnj.Game.Missile.Missile_Player;

public class Player_3 extends Player {
    public int playerShootLevel = 1;

    public void setPlayerShootLevel(int _playerShootLevel) { playerShootLevel = _playerShootLevel; }

    long LastShoot = System.currentTimeMillis();
    Timer m_timer = new Timer();

    public Player_3() {
        super(AppManager.getInstance().getBitmap(R.drawable.player3));

        this.setPosition(460, 1230);
        this.initSpriteData(90, 150, 6, 4);
    }

    // 플레이어2의 탄환의 타입결정, 아이템1 획득에 따른 탄환 궤도변경
    public void Attack() {
        if(playerShootLevel == 1) {
            if(System.currentTimeMillis() - LastShoot >= 600) {
                LastShoot = System.currentTimeMillis();
                SoundManager.getInstance().play(1);
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 1));
            }
        }
        else if(playerShootLevel == 2) {
            if(System.currentTimeMillis() - LastShoot >= 550) {
                LastShoot = System.currentTimeMillis();
                SoundManager.getInstance().play(1);
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 4));
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 5));
            }
        }
        else if(playerShootLevel == 3) {
            if(System.currentTimeMillis() - LastShoot >= 500) {
                LastShoot = System.currentTimeMillis();
                SoundManager.getInstance().play(1);
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 1));
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 4));
                AppManager.getInstance().getGameState1().m_pmslist.add(new Missile_Player(m_x, m_y - 70, 5));
            }
        }
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set( (int)(m_x+40f), (int)(m_y+77.5f), (int)(m_x+80f), (int)(m_y+117.5f));
        Attack();
    }

    public int getPlayerShootLevel() { return playerShootLevel; }
}
