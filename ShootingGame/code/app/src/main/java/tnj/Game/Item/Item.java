package tnj.Game.Item;

import android.graphics.Bitmap;
import android.graphics.Rect;
import tnj.Framework.SpriteAnimation;

public class Item extends SpriteAnimation {
    public Rect m_BoundBox = new Rect();

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state =  STATE_NORMAL;

    // 총알 업그레이드 아이템, 스킬아이템을 구분하기 위한 상태변수
    public static final int ITEM_STATE_1 = 0;
    public static final int ITEM_STATE_2 = 1;

    public int movetype;
    public int itemtype;
    public float speed;

    public Item(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move( );
    }

    void Move( ) {
        m_y += speed;
        if(m_y > 1800 || m_x<-200 || m_x>1200) state = STATE_OUT;
    }
}
