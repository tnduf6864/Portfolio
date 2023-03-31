package tnj.Framework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import tnj.Game.Player.Player;
import tnj.Level.BossState1;
import tnj.Level.BossState2;
import tnj.Level.BossState3;
import tnj.Level.GameState1;
import tnj.Level.GameState2;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameViewThread m_thread;
    private IState m_state;
    private Player m_player;

    public GameView(Context context){
        super(context);
        setFocusable(true); // 키 입력처리를 받기 위해서
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        SoundManager.getInstance().Init(context);
        SoundManager.getInstance().play(0);
        changeGameState(new GameState1());
        getHolder().addCallback(this);
        m_thread = new GameViewThread(getHolder(), this);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        m_state.Render(canvas);
    }
    public void Update() {
        m_state.Update();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return m_state.onKeyDown(keyCode,event);
    }

    public static boolean CheckPause=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (event.getX() >= 950 && event.getY() <= 200) {
                    if (CheckPause == false)
                        CheckPause = true;
                    else
                        CheckPause = false;
                }
                break;
            }
        }


        return m_state.onTouchEvent(event);
    }

    public void changeGameState(IState _state) {
        if(m_state != null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }
    public Player changePlayer(Player _player){

        m_player = _player;

        return m_player;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { // 뷰가 변경될 때 호출

    }
    @Override
    public void surfaceCreated(SurfaceHolder arg0) { // 뷰가 생성될 때 호출
        m_thread.setRunning(true); // 스레드를 실행 상태로 만든다.
        m_thread.start(); // 스레드 실행

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) { // 뷰가 종료될 때 호출
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry) {
            try{
                // 스레드를 중지시킨다.
                m_thread.join();
                retry = false;
            } catch(InterruptedException e) {
                // 스레드가 종료되도록 계속 시도
            }
        }
    }

}
