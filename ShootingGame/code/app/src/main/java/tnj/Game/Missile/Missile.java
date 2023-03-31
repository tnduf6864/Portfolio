package tnj.Game.Missile;

import android.graphics.Bitmap;
import android.graphics.Rect;

import tnj.Framework.GraphicObject;
import tnj.Framework.Vector2;

public class Missile extends GraphicObject {
    public Rect m_BoundBox = new Rect();
    public Vector2 m_dir;
    public float speed = 15.0f;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    public Missile(Bitmap bitmap) {
        super(bitmap);
    }
    public void setDirection(Vector2 dir) {
        m_dir = dir;
    }
    public void Update() {
        if(m_y < -200) state = STATE_OUT;
    }
}
