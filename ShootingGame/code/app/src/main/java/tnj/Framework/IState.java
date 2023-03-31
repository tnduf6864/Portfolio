package tnj.Framework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
    public void Destroy(); // 상태가소멸될때
    public void Init(); // 상태가생성되었을때
    public void Render(Canvas canvas); // 그려야할것들
    public void Update(); // 지속적으로수행할것들
    public boolean onKeyDown(int keyCode, KeyEvent evnet); // 키입력처리
    public boolean onTouchEvent(MotionEvent event); // 키입력처리
}
