package tnj.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import tnj.Framework.GraphicObject;

// 상단바의 비트맵 이미지를 화면에 나타낼때 사용
public class StateBar extends GraphicObject {

    public StateBar(Bitmap bitmap) {
        super(bitmap);
        this.setPosition(0, 0);
    }

    public void Draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap( m_bitmap, x, y, null);
    }
}
