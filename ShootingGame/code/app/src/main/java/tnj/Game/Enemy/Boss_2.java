package tnj.Game.Enemy;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;
import tnj.Game.EnemyPhase.Boss2Attack.Boss2_Attack0;
import tnj.Game.EnemyPhase.Boss2Phase.Boss2_Phase0;
import tnj.Game.EnemyPhase.Boss2Phase.Boss2_Phase1;
import tnj.Game.EnemyPhase.Boss2Phase.Boss2_Phase2;
import tnj.Game.EnemyPhase.Boss2Phase.Boss2_Phase3;
import tnj.Game.EnemyPhase.IBossPhase;

// 스테이트, 스트레티지 패턴을 이용하여 Boss_1의 설명과 동일합니다.
public class Boss_2 extends Enemy{
    // 스테이트, 스트레티지 패턴을 이용하여 Boss_1의 설명과 동일합니다.
    IBossPhase m_state;
    IBossPhase m_idle; // IDLE
    IBossPhase m_phase1;
    IBossPhase m_phase2;
    IBossPhase m_phase3;

    public Boss_2() { // 스테이트, 스트레티지 패턴을 이용하여 Boss_1의 설명과 동일합니다.
        super(AppManager.getInstance().getBitmap(R.drawable.boss2_0)); // 45x51
        this.setPosition(480,-170); // (480, -170): default
        this.initSpriteData(135, 153, 5, 8); // 3배
        m_BoundBox.set( (int)(m_x), (int)(m_y-15f), (int)(m_x+175f), (int)(m_y + 170f));

        m_attack = new Boss2_Attack0();
        m_idle = new Boss2_Phase0(this);
        m_phase1 = new Boss2_Phase1(this);
        m_phase2 = new Boss2_Phase2(this);
        m_phase3 = new Boss2_Phase3(this);
        m_state = m_idle;
        moveType = MOVE_PATTERN_0;

        m_msdir = new Vector2(1f,0);
        hp = 270;
        speed = 2.5f;
        msCnt = 0;
    }
    public IBossPhase getPhase0() { return m_idle; }
    public IBossPhase getPhase1() { return m_phase1; }
    public IBossPhase getPhase2() { return m_phase2; }
    public IBossPhase getPhase3() { return m_phase3; }
    public void changePhase(IBossPhase _state) { m_state = _state; }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        if(this.state == STATE_DEAD) {
            if(System.currentTimeMillis() - deadTime >= 2000){
                state = STATE_OUT;
            }
            return;
        }
        if(hp>0) m_state.playPhase();
        else {
            this.m_x -= 150; this.m_y -= 50;
            this.m_currentFrame = 0;
            m_bitmap = AppManager.getInstance().getBitmap(R.drawable.effect);
            this.initSpriteData(420, 282 , 4,9);
            this.state = STATE_DEAD;
            deadTime = System.currentTimeMillis();
        }
    }
    public void attack(long time) {
        m_attack.attack(this, time);
    }
    public void attack(long time, int rangeS, int rangeE) { m_attack.attack(this, time, rangeS, rangeE); }
}
