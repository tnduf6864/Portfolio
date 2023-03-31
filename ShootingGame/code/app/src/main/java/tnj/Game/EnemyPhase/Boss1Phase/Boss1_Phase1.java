package tnj.Game.EnemyPhase.Boss1Phase;

import android.graphics.Rect;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Game.Enemy.Boss_1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack1;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack5;
import tnj.Game.EnemyPhase.IBossPhase;

public class Boss1_Phase1 implements IBossPhase {
    private Boss_1 bs;
    private float accel; // 이동할때 가속도를 넣기위한 멤버변수
    public Boss1_Phase1(Boss_1 boss){
        bs = boss;
        accel = 0.5f;
    }
    @Override
    public void playPhase() {
        if(bs.moveType == bs.MOVE_PATTERN_1){
            bs.attack(2500);
            if(bs.moveStop(5500,true)){
                bs.moveType = bs.MOVE_PATTERN_2;
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_1);
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_2) { //가속이동
            bs.attack(2500);
            bs.speed = accel;
            accel += 0.4;
            if (bs.moveToPos(new Rect(150 - 20, 1200 - 20, 150 + 20, 1200 + 20))) {
                bs.attack(200);
                bs.moveType = bs.MOVE_PATTERN_3;
                accel = 0.5f;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_3) {
            bs.attack(2500);
            bs.speed = accel;
            accel += 0.4;
            if(bs.moveToPos(new Rect(800-20,1100-20,800+20,1100+20))){
                bs.attack(200);
                bs.moveType = bs.MOVE_PATTERN_4;
                bs.speed = 7.0f;
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_4){
            if(bs.moveToPos(new Rect(450-20, 600-20, 450+20, 600+20))){
                bs.attack(200);
                bs.moveType = bs.MOVE_PATTERN_5;
                bs.setAttack(new Boss1_Attack5());
                bs.lastMove = System.currentTimeMillis();
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_2);
            }
        } else if(bs.moveType == bs.MOVE_PATTERN_5){
            if (bs.moveStop(2500, false)) {
                bs.attack(100);
                bs.m_bitmap = AppManager.getInstance().getBitmap(R.drawable.boss1_0);
                if (bs.moveStop(3500, true)) {
                    bs.lastMove = System.currentTimeMillis();
                    bs.setAttack(new Boss1_Attack1());
                    bs.moveType = bs.MOVE_PATTERN_1;
                    bs.changePhase(bs.getPhase2());
                }
            }
        }
        //bs.m_BoundBox.set( (int)bs.m_x, (int)bs.m_y, (int)bs.m_x + 171, (int)bs.m_y + 264);
        bs.m_BoundBox.set( (int)(bs.m_x), (int)(bs.m_y-18f), (int)(bs.m_x+231f), (int)(bs.m_y+282f));
    }
}
