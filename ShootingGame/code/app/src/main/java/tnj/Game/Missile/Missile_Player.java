package tnj.Game.Missile;

import java.util.Timer;
import java.util.TimerTask;

import tnj.Framework.AppManager;
import tnj.Framework.R;

// 플레이어의 미사일
public class Missile_Player extends Missile {
    public int missileType;
    Timer m_timer = new Timer();

    public Missile_Player(float x, float y, int type) {
        super(AppManager.getInstance().getBitmap(R.drawable.missile_3));
        this.setPosition(x, y);
        missileType = type;
    }
    public void Update() {
        super.Update();
        switch (missileType){
            case 1: // 미사일이 위로 발사되는 효과를 준다.
                m_y -= 40;
                break;
            case 2: //미사일이 왼쪽 대각선으로 발사되는 효과
                m_y -= 40;
                m_x -= 20;
                break;
            case 3: //미사일이 오른쪽 대각선으로 발사되는 효과
                m_y -= 40;
                m_x += 20;
                break;
            case 4: //미사일이 왼쪽으로 마름모를 그리며 발사되는 효과
                if(m_y > 500) { m_y -= 40;m_x -= 30; }
                else { m_y -= 40;m_x += 30; }
                break;
            case 5: //미사일이 오른쪽으로 마름모를를 그리며 발사되는 효과
                if(m_y > 500) { m_y -= 50;m_x += 30; }
                else { m_y -= 50;m_x -= 30; }
                break;
            case 6: //3초 뒤 사라지는 미사일(정지상태)
                TimerTask m_task = new TimerTask() {
                    @Override
                    public void run() { m_x += 5000; m_y += 5000; }
                };
                m_timer.schedule(m_task, 3000);
                break;
            case 7: //미사일이 천천히 올라감(player1 스킬을 위한 세팅)
                m_y -= 20;
        }

        m_BoundBox.set((int)m_x, (int)m_y, (int)m_x + 48, (int)m_y + 48);
    }
}
