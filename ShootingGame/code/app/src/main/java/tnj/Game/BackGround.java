package tnj.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import tnj.Framework.AppManager;
import tnj.Framework.GraphicObject;
import tnj.Framework.R;

public class BackGround extends GraphicObject {
    protected static final float SCROLL_SPEED = 1.8f;
    public float m_scroll = -960 + 480;

    public Bitmap m_layer2;
    protected static final float SCROLL_SPEED_2 = 0.8f;
    protected float m_scroll_2 = -960 + 480;

    public BackGround(int backtype) {
        super( null);

        if(backtype == 0)
            m_bitmap = AppManager.getInstance().getBitmap(R.drawable.level1);
        else if(backtype == 1)
            m_bitmap= AppManager.getInstance().getBitmap(R.drawable.level2);
        else if(backtype ==2 )
            m_bitmap= AppManager.getInstance().getBitmap(R.drawable.level3);
        else if(backtype ==3 )
            m_bitmap= AppManager.getInstance().getBitmap(R.drawable.back_end);
        else if(backtype ==4 )
            m_bitmap= AppManager.getInstance().getBitmap(R.drawable.back_clear3);
        if(backtype == 0)
            m_layer2= AppManager.getInstance().getBitmap(R.drawable.layer1);
        else if(backtype == 1)
            m_layer2= AppManager.getInstance().getBitmap(R.drawable.layer2);
        else if(backtype ==2 )
            m_layer2= AppManager.getInstance().getBitmap(R.drawable.layer3);

        setPosition(0, (int)m_scroll);
    }
    public void Update(long GameTime) {
        m_scroll = m_scroll + SCROLL_SPEED;
        if(m_scroll>=0) m_scroll =0;
        setPosition(0, (int)m_scroll);
        m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
        if(m_scroll_2>=0) m_scroll_2 = 0;
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
        canvas.drawBitmap(m_layer2, m_x, m_scroll_2, null);
    }


    //게임 종료시 나타나는 화면엔 레이어가 불필요해 추가로 만듬.
    public void Draw2(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }
}
