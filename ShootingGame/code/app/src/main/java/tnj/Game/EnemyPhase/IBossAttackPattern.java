package tnj.Game.EnemyPhase;

import tnj.Game.Enemy.Enemy;

// 스트레티지 패턴 인터페이스
public interface IBossAttackPattern {
    // 가변값이 없는 기본공격 메소드
    void attack(Enemy boss, long time);
    // 시간과 범위 또는 랜덤값을 입력할 수 있는 변형 공격을 위한 메소드
    void attack(Enemy boss, long time, int rangeS, int rangeE);
}
