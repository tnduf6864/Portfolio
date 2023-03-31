package tnj.Game.EnemyPhase.Boss1Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss1_Attack4 implements IBossAttackPattern {
    @Override
    // 나선형으로 탄환을 발사하는 공격
    public void attack(Enemy boss, long time) {
        // 특정 시간 간격마다 하나씩 탄을 생성하기 위하여 하나씩 탄을 저장하고 방향 업데이트
        if (System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();
            Missile_Boss ms = new Missile_Boss(boss.m_x, boss.m_y, boss.m_msdir);
            ms.speed = 5.0f;
            AppManager.getInstance().getBossState1().m_bossmslist.add(ms);
            Vector2 t = new Vector2((float) (Math.cos(Math.PI / 180 * (13 * boss.msCnt))), (float) (Math.sin(Math.PI / 180 * (13 * boss.msCnt))));
            t.normalVector();
            boss.m_msdir = t;
            boss.msCnt++;
            if (boss.msCnt == 700) { // 보스가 발사할 탄환 개수 설정
                boss.setAttack(new Boss1_Attack0());
                boss.msCnt=0;
            }
        }
    }

    @Override
    public void attack(Enemy boss, long time, int rangeS, int rangeE) {

    }
}
