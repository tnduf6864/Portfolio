package tnj.Game.Player;

import android.graphics.Bitmap;

import tnj.Framework.AppManager;
import tnj.Framework.GraphicObject;
import tnj.Framework.R;

public class Shield extends GraphicObject {

    public Shield() {
        super(AppManager.getInstance( ).getBitmap(R.drawable.shield));
    }

    // 플레이어 쉴드 위치 조정
    public void Update(long GameTime, long skillTime, float x, float y) {
        this.m_x = x-40;
        this.m_y = y-20;
    }
}
