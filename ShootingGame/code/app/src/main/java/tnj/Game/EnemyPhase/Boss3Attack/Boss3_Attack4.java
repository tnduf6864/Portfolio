package tnj.Game.EnemyPhase.Boss3Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss3_Attack4 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if (System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for (int i = 0; i < 360; i += 45) {
                t = new Vector2((float) (Math.cos(Math.PI / 180 * i)), (float) (Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                t.operatorMulti(20);
                Missile_Boss ms = new Missile_Boss(boss.m_BoundBox.centerX()+t.x, boss.m_BoundBox.centerY()+t.y, new Vector2(0,1));
                ms.speed = 20f;
                AppManager.getInstance().getBossState1().m_bossmslist.add(ms);
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) {

    }
}
