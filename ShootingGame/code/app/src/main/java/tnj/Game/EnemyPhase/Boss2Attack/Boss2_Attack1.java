package tnj.Game.EnemyPhase.Boss2Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss2_Attack1 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for(int i=0; i<360; i+=8) {
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(boss.m_BoundBox.centerX(), boss.m_BoundBox.centerY(), t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
        }
    }
    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) { // rangeE - rangeS < 180
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for(int i=rangeS; i<rangeE; i+=10){
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(boss.m_BoundBox.centerX(), boss.m_BoundBox.centerY(), t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
            for(int i= rangeS+180; i<rangeS+180+rangeE; i+=10){
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(boss.m_BoundBox.centerX(), boss.m_BoundBox.centerY(), t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
        }
    }
}
