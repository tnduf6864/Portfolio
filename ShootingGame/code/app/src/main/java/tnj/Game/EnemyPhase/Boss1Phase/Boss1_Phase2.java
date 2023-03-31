package tnj.Game.EnemyPhase.Boss1Phase;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack2;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack3;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss1_Phase2 implements IBossPhase {
    private Boss_1 bs;
    public Boss1_Phase2(Boss_1 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            bs.attack(2500);
            if(bs.moveStop(6000,true)){
                bs.moveType = bs.MOVE_PATTERN_2;
                bs.setAttack(new Boss1_Attack2());
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_0);
            }

        } else if(bs.moveType == bs.MOVE_PATTERN_2){
            bs.attack(1800);
            if(bs.moveToPos(new Rect(100-20, 1200-20, 100+20, 1200+20))){
                bs.moveType = bs.MOVE_PATTERN_3;
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_1);
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3){
            bs.attack(1800);
            if(bs.m_y<750) bs.setAttack(new Boss1_Attack3());
            if(bs.moveToPos(new Rect(800-20, 100-20, 800+20, 100+20))){
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_0);
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.changePhase(bs.getPhase3());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 171, (int)bs.m_y + 264);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-18f), (int)(bs.m_x+231f), (int)(bs.m_y+282f));
    }
}
