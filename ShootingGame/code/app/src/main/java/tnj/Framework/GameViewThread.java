package tnj.Framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.logging.Level;

import tnj.Level.GameState1;

public class GameViewThread extends Thread {
    // 접근을 위한 멤버변수
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;
    // 스레드 실행상태 멤버변수
    private boolean m_run = false;
    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView) {
        m_surfaceHolder = surfaceHolder;
        m_gameView = gameView;
    }
    public void setRunning(boolean run) {
        m_run = run;
    }
    @Override
    public void run() {
        Canvas _canvas;
        while(m_run) {
            _canvas = null;
            //일시정지 버튼이 클릭되면 스레드 일시정지
            if (GameView.CheckPause == false) {
                try { // SurfaceHolder를 통해 Surface에 접근해서 가져옴
                    m_gameView.Update();
                    _canvas = m_surfaceHolder.lockCanvas(null);
                    synchronized (m_surfaceHolder) {
                        m_gameView.onDraw(_canvas); // 그림을 그림
                    }
                } finally {
                    if (_canvas != null)
                        m_surfaceHolder.unlockCanvasAndPost(_canvas); // Surface를 화면에 표시함
                }
            }
        }
    }
}
