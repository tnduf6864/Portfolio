package tnj.Game.EnemyPhase.Boss2Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss2_Attack2 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for(int i=-10; i<=100; i+=20) { // 100, 100
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(100, 100, t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
            for(int i=80; i<=190; i+=20) { // 865, 100
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(910, 100, t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
            for(int i=170; i<=280; i+=20) { // 865, 1300
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(910,1650, t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
            for(int i=260; i<=370; i+=20) { // 100,1300
                t = new Vector2((float)(Math.cos(Math.PI / 180 * i)), (float)(Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(100,1650, t);
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) {

    }
}
