package tnj.Game.EnemyPhase.Boss3Phase;

import android.graphics.Rect;

import tnj.Game.Enemy.Boss_3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack0;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack1;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack2;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack3;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss3_Phase2 implements IBossPhase {
    private Boss_3 bs;
    public Boss3_Phase2(Boss_3 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            bs.attack(2000);
            if(bs.moveStop(2900,true)){
                bs.moveType = bs.MOVE_PATTERN_2;
                bs.setAttack(new Boss3_Attack1());
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2){
            bs.attack(3000);
            if(bs.moveStop(6000,true)){
                bs.moveType = bs.MOVE_PATTERN_3;
                bs.lastMove = System.currentTimeMillis();
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3){
            bs.attack(2000, bs.rand.nextInt(4), 0);
            if(bs.moveToPos(new Rect(480-20, 50-20, 480+20, 50+20))){
                bs.setAttack(new Boss3_Attack1());
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.setAttack(new Boss3_Attack3());
                bs.lastMove = System.currentTimeMillis();
                bs.changePhase(bs.getPhase3());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 156, (int)bs.m_y + 216);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15), (int)(bs.m_x + 190f), (int)(bs.m_y + 240f));
    }
}
