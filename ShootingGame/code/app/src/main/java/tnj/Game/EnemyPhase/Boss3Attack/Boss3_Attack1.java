package tnj.Game.EnemyPhase.Boss3Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss3_Attack1 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t1 = new Vector2();
            for(int i=0; i<1100; i+=130){
                t1.x=0; t1.y=1;
                Missile_Boss ms = new Missile_Boss(i, 0, t1);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
            Vector2 t2 = new Vector2();
            for(int i=50; i<1100; i+=130){
                t2.x=0; t2.y=-1;
                Missile_Boss ms = new Missile_Boss(i, 1500, t2);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
            Vector2 t3= new Vector2();
            for(int i=0; i<1750; i+=130){
                t3.x=1; t3.y=0;
                Missile_Boss ms = new Missile_Boss(0, i, t3);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
            Vector2 t4= new Vector2();
            for(int i=50; i<1750; i+=130) {
                t4.x = -1; t4.y = 0;
                Missile_Boss ms = new Missile_Boss(1050, i, t4);
                AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int rd, int none) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            if(rd==0){
                Vector2 t1 = new Vector2();
                for(int i=0; i<1100; i+=130){
                    t1.x=0; t1.y=1;
                    Missile_Boss ms = new Missile_Boss(i, 0, t1);
                    AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
                }
            } else if(rd==1){
                Vector2 t2 = new Vector2();
                for(int i=50; i<1750; i+=130){
                    t2.x=0; t2.y=-1;
                    Missile_Boss ms = new Missile_Boss(i, 1500, t2);
                    AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
                }
            } else if(rd==2){
                Vector2 t3= new Vector2();
                for(int i=0; i<1750; i+=130){
                    t3.x=1; t3.y=0;
                    Missile_Boss ms = new Missile_Boss(0, i, t3);
                    AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
                }
            } else if(rd==3){
                Vector2 t4= new Vector2();
                for(int i=50; i<1750; i+=130) {
                    t4.x = -1; t4.y = 0;
                    Missile_Boss ms = new Missile_Boss(1050, i, t4);
                    AppManager.getInstance().getBossState3().m_bossmslist.add(ms);
                }
            }
        }
    }
}
