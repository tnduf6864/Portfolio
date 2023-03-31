package tnj.Game.EnemyPhase.Boss1Phase;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack4;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss1_Phase3 implements IBossPhase {
    private Boss_1 bs;
    public Boss1_Phase3(Boss_1 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            bs.attack(2000);
            if(bs.moveToPos(new Rect(450-20, 600-20, 450+20, 600+20))){
                bs.setAttack(new Boss1_Attack4());
                bs.lastMove = System.currentTimeMillis();
                bs.moveType = bs.MOVE_PATTERN_C;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_C){
            bs.attack(5);
            if(bs.moveCurve(50,7000,true)){
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_2);
                bs.setAttack(new Boss1_Attack1());
                bs.msCnt = 0;
                bs.lastMove = System.currentTimeMillis();
                bs.moveType = bs.MOVE_PATTERN_S;
                bs.msCnt=0;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_S){
            if(bs.moveStop(1500,true)){
                bs.lastMove = System.currentTimeMillis();
                bs.moveType = bs.MOVE_PATTERN_1;
                int i = bs.rand.nextInt(3)+1;
                if(i==1)bs.changePhase(bs.getPhase1());
                else if(i==2)bs.changePhase(bs.getPhase2());
                else if(i==3)bs.changePhase(bs.getPhase3());
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 171, (int)bs.m_y + 264);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-18f), (int)(bs.m_x+231f), (int)(bs.m_y+282f));
    }
}
