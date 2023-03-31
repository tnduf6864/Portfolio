package tnj.Framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
    public Bitmap m_bitmap;
    public float m_x;
    public float m_y;

    public GraphicObject(Bitmap bitmap){
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
    }
    // 좌표설정
    public void setPosition(float x, float y) {
        m_x = x;
        m_y = y;
    }
    // 이미지 그림
    public void Draw(Canvas canvas) {

        canvas.drawBitmap( m_bitmap, m_x, m_y, null);
    }
    // X, Y 각좌표반환
    public float getX( ) { return m_x; }
    public float getY( ) { return m_y; }
}
