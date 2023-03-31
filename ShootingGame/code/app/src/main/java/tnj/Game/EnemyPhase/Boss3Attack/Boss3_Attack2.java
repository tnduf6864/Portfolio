package tnj.Game.EnemyPhase.Boss3Attack;

import tnj.Framework.AppManager;
import tnj.Framework.Vector2;
import tnj.Game.Enemy.Enemy;
import tnj.Game.EnemyPhase.IBossAttackPattern;
import tnj.Game.Missile.Missile_Boss;

public class Boss3_Attack2 implements IBossAttackPattern {
    @Override
    public void attack(Enemy boss, long time) {
        if(System.currentTimeMillis() - boss.lastShoot >= time) {
            boss.lastShoot = System.currentTimeMillis();

            Vector2 t1 = new Vector2();
            t1.x=1; t1.y=2;
            Missile_Boss ms11 = new Missile_Boss(0, 0, t1);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms11);
            Missile_Boss ms12 = new Missile_Boss(50, 0, t1);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms12);
            Missile_Boss ms13 = new Missile_Boss(0, 50, t1);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms13);

            Vector2 t2 = new Vector2();
            t2.x=-1; t2.y=2;
            Missile_Boss ms21 = new Missile_Boss(1100, 0, t2);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms21);
            Missile_Boss ms22 = new Missile_Boss(1100, 50, t2);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms22);
            Missile_Boss ms23 = new Missile_Boss(1050, 0, t2);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms23);

            Vector2 t3 = new Vector2();
            t3.x=-1; t3.y=-2;
            Missile_Boss ms31 = new Missile_Boss(1100, 1750, t3);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms31);
            Missile_Boss ms32 = new Missile_Boss(1050, 1750, t3);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms32);
            Missile_Boss ms33 = new Missile_Boss(1100, 1700, t3);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms33);

            Vector2 t4 = new Vector2();
            t4.x=1; t4.y=-2;
            Missile_Boss ms41 = new Missile_Boss(0, 1700, t4);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms41);
            Missile_Boss ms42 = new Missile_Boss(50, 1700, t4);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms42);
            Missile_Boss ms43 = new Missile_Boss(0, 1650, t4);
            AppManager.getInstance().getBossState3().m_bossmslist.add(ms43);
        }
    }

    @Override
    public void attack(Enemy boss, long time, int none1, int none2) { // reverse
        boss.lastShoot = System.currentTimeMillis();

        Vector2 t1 = new Vector2();
        t1.x=2; t1.y=1;
        Missile_Boss ms11 = new Missile_Boss(0, 0, t1);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms11);
        Missile_Boss ms12 = new Missile_Boss(50, 0, t1);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms12);
        Missile_Boss ms13 = new Missile_Boss(0, 50, t1);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms13);

        Vector2 t2 = new Vector2();
        t2.x=-2; t2.y=1;
        Missile_Boss ms21 = new Missile_Boss(1100, 0, t2);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms21);
        Missile_Boss ms22 = new Missile_Boss(1100, 50, t2);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms22);
        Missile_Boss ms23 = new Missile_Boss(1050, 0, t2);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms23);

        Vector2 t3 = new Vector2();
        t3.x=-2; t3.y=-1;
        Missile_Boss ms31 = new Missile_Boss(1100, 1550, t3);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms31);
        Missile_Boss ms32 = new Missile_Boss(1050, 1550, t3);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms32);
        Missile_Boss ms33 = new Missile_Boss(1100, 1500, t3);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms33);

        Vector2 t4 = new Vector2();
        t4.x=2; t4.y=-1;
        Missile_Boss ms41 = new Missile_Boss(0, 1550, t4);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms41);
        Missile_Boss ms42 = new Missile_Boss(50, 1550, t4);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms42);
        Missile_Boss ms43 = new Missile_Boss(0, 1500, t4);
        AppManager.getInstance().getBossState3().m_bossmslist.add(ms43);
    }
}
