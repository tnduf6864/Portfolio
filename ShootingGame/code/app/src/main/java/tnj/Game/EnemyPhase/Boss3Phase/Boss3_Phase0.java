package tnj.Game.EnemyPhase.Boss3Phase;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack0;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack1;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack2;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack4;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss3_Phase0 implements IBossPhase {
    private Boss_3 bs;

    public Boss3_Phase0(Boss_3 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_0){
            if(bs.moveToPos(new Rect(480-20, 830-20, 480+20, 830+20))){
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.lastMove = System.currentTimeMillis();
                bs.setAttack(new Boss3_Attack4());
                bs.changePhase(bs.getPhase1());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 156, (int)bs.m_y + 216);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15), (int)(bs.m_x + 190f), (int)(bs.m_y + 240f));
    }
}
