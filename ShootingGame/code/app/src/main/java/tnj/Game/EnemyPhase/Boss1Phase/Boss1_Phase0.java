package tnj.Game.EnemyPhase.Boss1Phase;

import android.graphics.Rect;
import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack1;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss1_Phase0 implements IBossPhase {
    private Boss_1 bs;
    public Boss1_Phase0(Boss_1 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_0){
            if(bs.moveToPos(new Rect(450-20, 600-20, 450+20, 600+20))){
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.lastMove = System.currentTimeMillis();
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_0);
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_1) {
            if (bs.moveStop(3000, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_2);
                if (bs.moveStop(6000, false)) {
                    bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_0);
                    if (bs.moveStop(7500, true)) {
                        bs.moveType = bs.MOVE_PATTERN_1;
                        bs.lastMove = System.currentTimeMillis();
                        bs.setAttack(new Boss1_Attack1());
                        bs.changePhase(bs.getPhase1());
                    }
                }
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 171, (int)bs.m_y + 264);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-18f), (int)(bs.m_x+231f), (int)(bs.m_y+282f));
    }

}
