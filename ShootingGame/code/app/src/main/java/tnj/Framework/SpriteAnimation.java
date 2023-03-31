package tnj.Framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class SpriteAnimation extends GraphicObject {
    public Rect m_rect; // 그려줄 사각영역
    private int m_fps; // 초당프레임
    private int m_iFrames; // 프레임개수
    public int m_currentFrame; // 최근프레임
    private long m_frameTimer;
    public int m_spriteWidth;
    public int m_spriteHeight;

    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);
        // 멤버 변수 초기화
        m_rect= new Rect(0, 0, 0, 0);
        m_frameTimer = 0;
        m_currentFrame = 0;
    }

    public void initSpriteData(int _width, int _height, int _fps, int iFrame) {
        // 기본 정보 설정
        m_spriteWidth = _width;
        m_spriteHeight = _height;
        m_rect.top = 0;     m_rect.bottom = m_spriteHeight;
        m_rect.left= 0;     m_rect.right = m_spriteWidth;
        m_fps = 1000/_fps; // 밀리초 단위 프레임
        m_iFrames = iFrame;
    }

    @Override
    public void Draw(Canvas canvas) {
        Rect dest = new Rect((int)m_x, (int)m_y, (int)m_x + m_spriteWidth, (int)m_y + m_spriteHeight);
        canvas.drawBitmap(m_bitmap, m_rect, dest, null);
    }

    public void Update(long gameTime) {
        if(gameTime > m_frameTimer + m_fps) {
            m_frameTimer = gameTime;
            m_currentFrame += 1;
            // 프레임의 순환
            if(m_currentFrame >= m_iFrames) m_currentFrame= 0;
        }
        m_rect.left = m_currentFrame * m_spriteWidth;
        m_rect.right = m_rect.left + m_spriteWidth;
    }
}
