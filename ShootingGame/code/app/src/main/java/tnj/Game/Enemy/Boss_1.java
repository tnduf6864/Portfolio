package tnj.Game.Enemy;

import tnj.Framework.AppManager;
import tnj.Framework.R;
import tnj.Framework.Vector2;
import tnj.Game.EnemyPhase.Boss1Attack.Boss1_Attack0;
import tnj.Game.EnemyPhase.Boss1Phase.Boss1_Phase0;
import tnj.Game.EnemyPhase.Boss1Phase.Boss1_Phase1;
import tnj.Game.EnemyPhase.Boss1Phase.Boss1_Phase2;
import tnj.Game.EnemyPhase.Boss1Phase.Boss1_Phase3;
import tnj.Game.EnemyPhase.*;

public class Boss_1 extends Enemy {
    // state패턴을 위한 상태 멤버
    IBossPhase m_state;
    IBossPhase m_idle; // IDLE
    IBossPhase m_phase1;
    IBossPhase m_phase2;
    IBossPhase m_phase3;

    public Boss_1() { // 멤버변수 초기화
        super(AppManager.getInstance().getBitmap(R.drawable.boss1_1)); // 57,88
        this.setPosition(450,-270);
        this.initSpriteData(171, 264, 4, 8); // 3배
        m_BoundBox.set( (int)(m_x), (int)(m_y-18f), (int)(m_x+231f), (int)(m_y+282f));

        m_attack = new Boss1_Attack0(); // 스트레티지 패턴을 이용하기 위한 멤버 m_attack
        m_idle = new Boss1_Phase0(this);
        m_phase1 = new Boss1_Phase1(this);
        m_phase2 = new Boss1_Phase2(this);
        m_phase3 = new Boss1_Phase3(this);
        m_state = m_idle;
        moveType = MOVE_PATTERN_0;

        m_msdir = new Vector2(1f,0);
        hp = 250;
        speed = 2.0f;
        msCnt = 1; // 탄환가지고 있는 개수(특정 attack에서 필요한 변수 입니다.)
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
        if(hp>0) m_state.playPhase(); // hp가 0보다 크면 스테이트(보스phase) 실행
        else { // 보스가 죽으면 폭발 이펙트 생성
            this.m_x -= 120; this.m_y -= 50;
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
