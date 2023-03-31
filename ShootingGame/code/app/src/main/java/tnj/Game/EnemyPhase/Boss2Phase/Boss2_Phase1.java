package tnj.Game.EnemyPhase.Boss2Phase;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_2;
import tnj.Game.EnemyPhase.Boss2Attack.Boss2_Attack1;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss2_Phase1 implements IBossPhase {
    private Boss_2 bs;
    private int r, cnt=0;
    public Boss2_Phase1(Boss_2 boss){
        bs = boss;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_S){
            bs.attack(1900);
            if(bs.moveStop(2000,true)){
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_1);
                bs.m_currentFrame = 0;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_1) {
            if (bs.moveStop(1500, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_3);
                if (bs.moveStop(3000, true)) {
                    bs.moveType = bs.MOVE_PATTERN_2;
                    bs.m_x = (float)bs.rand.nextInt(650)+150;
                    bs.m_y = (float)bs.rand.nextInt(800)+200;
                    bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_2);
                    bs.m_currentFrame = 0;
                }
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2) {
            if (bs.moveStop(1500, false)) {
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_0);
                if(bs.moveStop(2000, true)){
                    bs.setAttack(new Boss2_Attack1());
                    bs.moveType = bs.MOVE_PATTERN_3;
                    r = bs.rand.nextInt(180);
                }
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3){
            bs.attack(700, r, r+40);
            if(bs.moveStop(2100,true)){
                if(cnt==2) {cnt=0; bs.changePhase(bs.getPhase2());}
                bs.moveType = bs.MOVE_PATTERN_1;
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss2_1);
                bs.m_currentFrame = 0;
                cnt++;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_4){

        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 135, (int)bs.m_y + 153);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-15f), (int)(bs.m_x+175f), (int)(bs.m_y + 170f));
    }
}
