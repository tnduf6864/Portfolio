package tnj.Framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import tnj.Game.Player.Player;
import tnj.Level.BossState1;
import tnj.Level.BossState2;
import tnj.Level.BossState3;
import tnj.Level.GameState1;
import tnj.Level.GameState2;
import tnj.Level.GameState3;

// 애플리케이션의 모든것을 관리하는 AppManager
// 각 게임스테이트, 플레이어의 객체를 관리
// set: 저장, get: 인스턴스 반환
public class AppManager {
    private GameView m_gameView;
    private GameState1 m_gameState1;
    private GameState2 m_gameState2;
    private GameState3 m_gameState3;
    private Resources m_resources;
    private BossState1 m_bossState1;
    private BossState3 m_bossState3;
    private BossState2 m_bossState2;
    private Player m_player;

    void setGameView(GameView _gameView) {
        m_gameView = _gameView;
    }
    void setResources(Resources _resources) {
        m_resources = _resources;
    }
    public GameView getGameView() {
        return m_gameView;
    }
    public Resources getResource() {
        return m_resources;
    }

    public void setPlayer(Player _player){ m_player = _player;}
    public Player getPlayer(){return m_player;}

    public void setGameState1(GameState1 _gameState1){m_gameState1 = _gameState1;}
    public GameState1 getGameState1(){return m_gameState1;}
    public void setBossState1(BossState1  _bossState1){m_bossState1 = _bossState1;}
    public BossState1 getBossState1(){return m_bossState1;}

    public void setGameState2(GameState2 _gameState2){m_gameState2 = _gameState2;}
    public GameState2 getGameState2(){return m_gameState2;}
    public void setBossState2(BossState2  _bossState2){m_bossState2 = _bossState2;}
    public BossState2 getBossState2(){return m_bossState2;}

    public void setGameState3(GameState3 _gameState3){m_gameState3 = _gameState3;}
    public GameState3 getGameState3(){return m_gameState3;}
    public void setBossState3(BossState3  _bossState3){m_bossState3 = _bossState3;}
    public BossState3 getBossState3(){return m_bossState3;}

    public Bitmap getBitmap(int r) { // 비트맵 가져오는 메소드
        return BitmapFactory.decodeResource(m_resources, r);
    }

    private static AppManager s_instance;
    public static AppManager getInstance() {
        if(s_instance == null) s_instance = new AppManager();
        return s_instance;
    }
}
