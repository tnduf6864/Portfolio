package tnj.Game.EnemyPhase.Boss2Phase;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_2;
import tnj.Game.EnemyPhase.Boss2Attack.Boss2_Attack3;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss2_Phase2 implements IBossPhase {
    private Boss_2 bs;
    public Boss2_Phase2(Boss_2 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1) {
            if (bs.moveStop(1500, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_3);
                if (bs.moveStop(3000, true)) {
                    bs.moveType = bs.MOVE_PATTERN_2;
                    bs.m_x = 480f;
                    bs.m_y = 230f;
                    bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_2);
                    bs.m_currentFrame = 0;
                }
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2) {
            if (bs.moveStop(1500, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_0);
                if(bs.moveStop(2000, true)){
                    bs.setAttack(new Boss2_Attack3());
                    bs.moveType = bs.MOVE_PATTERN_3;
                }
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3){
            bs.attack(600);
            if(bs.moveStop(6600,true)){
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_1);
                bs.m_currentFrame = 0;
                bs.changePhase(bs.getPhase3());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 135, (int)bs.m_y + 153);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15f), (int)(bs.m_x+175f), (int)(bs.m_y + 170f));
    }
}
