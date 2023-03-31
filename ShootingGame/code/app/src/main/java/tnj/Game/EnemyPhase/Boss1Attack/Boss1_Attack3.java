package tnj.Game.EnemyPhase.Boss1Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss1_Attack3 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if (System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Vector2 t;
            for (int i = 60; i < 210; i += 10) {
                // 삼각함수를 이용하여 탄환 방향 계산
                t = new Vector2((float) (Math.cos(Math.PI / 180 * i)), (float) (Math.sin(Math.PI / 180 * i)));
                t.normalVector();
                Missile_Boss ms = new Missile_Boss(boss.m_BoundBox.centerX(), boss.m_BoundBox.centerY(), t);
                AppManager.getInstance().getBossState1().m_bossmslist.add(ms);
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) {

    }

}
