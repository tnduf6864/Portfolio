package tnj.Game.EnemyPhase.Boss2Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss2_Attack3 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if (System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for (int i = 0; i <= 5; i++) {
                float rsx = (float)boss.rand.nextInt(1200);
                float rsy = (float)boss.rand.nextInt(200);
                float rex = (float)boss.rand.nextInt(1200);
                float rey = (float)1800;
                t = new Vector2(rex-rsx, rey-rsy);
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(rsx, rsy, t);
                ms.speed = 8.5f;
                AppManager.getInstance().getBossState2().m_bossmslist.add(ms);
            }
        }
    }



    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) {

    }
}
