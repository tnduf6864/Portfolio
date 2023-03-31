package tnj.Game.EnemyPhase.Boss2Phase;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_2;
import tnj.Game.EnemyPhase.Boss2Attack.Boss2_Attack1;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss2_Phase0 implements IBossPhase {
    private Boss_2 bs;
    public Boss2_Phase0(Boss_2 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_0){
            if(bs.moveToPos(new Rect(480-20, 230-20, 480+20, 230+20))){
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.lastMove = System.currentTimeMillis();
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_1);
                bs.m_currentFrame = 0;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_1) {
            if (bs.moveStop(1500, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_3);
                if (bs.moveStop(3000, true)) {
                    bs.moveType = bs.MOVE_PATTERN_2;
                    bs.m_x = 480f;
                    bs.m_y = 630f;
                    bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_2);
                    bs.m_currentFrame = 0;
                }
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2) {
            if (bs.moveStop(1500, true)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_0);
                bs.moveType = bs.MOVE_PATTERN_S;
                bs.setAttack(new Boss2_Attack1());
                bs.changePhase(bs.getPhase1());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 135, (int)bs.m_y + 153);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15f), (int)(bs.m_x+175f), (int)(bs.m_y + 170f));
    }
}
