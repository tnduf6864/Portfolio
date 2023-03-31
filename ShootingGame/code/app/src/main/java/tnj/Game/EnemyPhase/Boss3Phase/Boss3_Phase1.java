package tnj.Game.EnemyPhase.Boss3Phase;

import tnj.Game.Enemy.Boss_3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack2;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack4;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss3_Phase1 implements IBossPhase {
    private Boss_3 bs;
    private int k=0;
    private int a=5;
    public Boss3_Phase1(Boss_3 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            bs.attack(1500);
            if(bs.moveStop(2900,true)){
                bs.moveType = bs.MOVE_PATTERN_2;
                bs.setAttack(new Boss3_Attack2());
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2){
            bs.attack(100);
            if(bs.moveStop(3000,true)){
                bs.moveType = bs.MOVE_PATTERN_3;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3){
            bs.attack(1000, 0, 0);
            if(bs.moveStop(3000,true)){
                bs.setAttack(new Boss3_Attack4());
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.lastMove = System.currentTimeMillis();
                bs.changePhase(bs.getPhase2());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 156, (int)bs.m_y + 216);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15), (int)(bs.m_x + 190f), (int)(bs.m_y + 240f));
    }
}
