package tnj.Game.Item;

import tnj.Framework.AppManager;
import tnj.Framework.R;

// 스킬횟수를 1증가시키기 위한 아이템2
public class Item_2 extends Item {
    public Item_2() {
        super(AppManager.getInstance().getBitmap(R.drawable.item2));
        this.setPosition(140,0);
        this.initSpriteData(150, 165, 3, 6);
        speed = 3.5f;
        itemtype = Item.ITEM_STATE_2;
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 150f), (int)(m_y+165f));
    }
}
