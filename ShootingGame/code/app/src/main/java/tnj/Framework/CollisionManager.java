package tnj.Framework;

import android.graphics.Rect;
import java.util.ArrayList;

// 충돌처리는 정확하지만 충돌 BoundBox의 멤버가 int형으로 선언되어 있기때문에
// 충돌경계가 깔끔하게 보이지 않을 수 있습니다.

public class CollisionManager {
    // CCW알고리즘. 실수형계산이 이루어지므로 앱실론값으로 0을 판단
    // 점p가 (직선)ab에 대하여 시계방향인지, 반시계방향인지 또는 직선위에 있는지를 판단
    // 시간복잡도는 O(1)로 단순계산만 포함되어 있습니다.
    public static int ccw(Vector2 p, Vector2 a, Vector2 b) {
        float ret = (a.operatorSub(p)).cross(b.operatorSub(p));
        if ((ret < 0.000000001 && ret>0) || (ret > -0.000000001 && ret<0)) return 0;
        if (ret==0) return 0;
        else if(ret < 0) return -1;
        else return 1;
    }

    // (선분)과 (선분)의 교차점을 판단하는 sementIntersects 메소드
    // 한점에서 만날때, 무수히 많은 점에서 만날때, 만나지 않을때 모두 판단 가능합니다.
    // 시간복잡도는 O(1)로 단순계산과 한번의 조건문이 포함되어 있습니다.
    public static int segmentIntersects(Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        if (ccw(a, b, c) == 0 && ccw(a, b, d) == 0) {
            if (b.operatorInequal(a)) {
                float tx=a.x, ty=a.y;
                a.x = b.x; a.y = b.y;
                b.x = tx; b.y = ty;
            }
            if (d.operatorInequal(c)) {
                float tx=c.x, ty=c.y;
                c.x = d.x; c.y = d.y;
                d.x = tx; d.y = ty;
            }
            if (b.operatorInequal(c) || d.operatorInequal(a)) return 0;
            else return 1;
        } else {
            int ab = ccw(a, b, c) * ccw(a, b, d);
            int cd = ccw(c, d, a) * ccw(c, d, b);
            if(ab <= 0 && cd <= 0) return 1;
            else return 0;
        }
    }

    // 정확한 충돌처리 판단을 위하여 사각형rt1과 rt2의 서로의 4개의 변에 대하여 교차점을 판단
    // O(1)의 시간복잡도를 가집니다.
    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2) {
        ArrayList<Vector2> sq = new ArrayList<>();
        ArrayList<Vector2> line = new ArrayList<>();
        sq.add(new Vector2(_rt1.left, _rt1.top));
        sq.add(new Vector2(_rt1.right, _rt1.top));
        sq.add(new Vector2(_rt1.right, _rt1.bottom));
        sq.add(new Vector2(_rt1.left, _rt1.bottom));
        line.add(new Vector2(_rt2.left, _rt2.top));
        line.add(new Vector2(_rt2.right, _rt2.top));
        line.add(new Vector2(_rt2.right, _rt2.bottom));
        line.add(new Vector2(_rt2.left, _rt2.bottom));

        //
        for (int j = 0; j < 4; ++j) {
            boolean ans1 = false;
            boolean ans2 = true;
            for(int i=0; i<4; ++i) {
                Vector2 a = line.get(j);
                Vector2 b = line.get((j+1)%4);
                Vector2 p = sq.get(i);
                Vector2 q = sq.get((i + 1) % 4);
                if(segmentIntersects(a, b, p, q)!=0) ans1 = true;
                if(!(ccw(a,p,q)==1 && ccw(b,p,q)==1)) ans2 = false;
            }
            if(ans1 || ans2) return true;
        } return false;
    }
}