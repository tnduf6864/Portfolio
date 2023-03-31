package tnj.Game.Item;

import tnj.Framework.AppManager;
import tnj.Framework.R;

// 총알 업그레이드를 위한 아이템1
public class Item_1 extends Item {
    public Item_1() {
        super(AppManager.getInstance().getBitmap(R.drawable.item1));
        this.setPosition(140,0);

        this.initSpriteData(150, 165, 3, 6);
        speed = 3.5f;
        itemtype = Item.ITEM_STATE_1;
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set( (int)m_x, (int)m_y, (int)(m_x + 150f), (int)(m_y+165f));
    }
}
