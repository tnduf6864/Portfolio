package tnj.Game.EnemyPhase.Boss3Phase;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack0;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack1;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack3;
import tnj.Game.EnemyPhase.Boss3Attack.Boss3_Attack4;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss3_Phase3 implements IBossPhase {
    private Boss_3 bs;
    private int k=0;
    private int a=5;
    public Boss3_Phase3(Boss_3 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            if(bs.moveStop(2500,true)){
                bs.moveType = bs.MOVE_PATTERN_2;
                bs.setAttack(new Boss3_Attack3());
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2){
            bs.attack(130,k,0);
            k+=a;
            if(k>400) a*=-1;
            if(a<0 && k<100) a*=-1;
            if(bs.moveStop(8000,true)){
                bs.lastMove = System.currentTimeMillis();
                bs.moveType = bs.MOVE_PATTERN_1;
                int i = bs.rand.nextInt(3) + 1;
                if (i == 1) bs.changePhase(bs.getPhase1());
                else if (i == 2) bs.changePhase(bs.getPhase2());
                bs.setAttack(new Boss3_Attack4());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 156, (int)bs.m_y + 216);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15), (int)(bs.m_x + 190f), (int)(bs.m_y + 240f));
    }
}
