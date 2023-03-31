package tnj.Game.EnemyPhase.Boss3Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss3_Attack3 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t= new Vector2();
            for(int i=200; i<1250; i+=100){
                t.x=1; t.y=0;
                Missile_Boss ms = new Missile_Boss(0, i, t);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }

            Vector2 t2= new Vector2();
            for(int i=1400; i<1750; i+=100){
                t2.x=1; t2.y=0;
                Missile_Boss ms = new Missile_Boss(0, i, t2);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int k, int none) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();

            Vector2 t= new Vector2();
            for(int i=1150-k; i>200; i-=100){
                t.x=1; t.y=0;
                Missile_Boss ms = new Missile_Boss(0, i, t);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }

            Vector2 t2= new Vector2();
            for(int i=1450-k; i<1750; i+=100){
                t2.x=1; t2.y=0;
                Missile_Boss ms = new Missile_Boss(0, i, t2);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
        }
    }
}
