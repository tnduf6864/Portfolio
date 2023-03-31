package tnj.Framework;

public class Vector2 {
    public float x, y;
    public Vector2(){} // 벡터 생성자1
    public Vector2(float x_, float y_){ x = x_; y = y_; } // 벡터 생성자2 float형
    public void setPos(float x_, float y_){ this.x = x_; this.y = y_;} // 벡터 x,y 업데이트
    public boolean operatorEqual(Vector2 rhs){ return x == rhs.x && y == rhs.y; } // 벡터 == 연산
    public boolean operatorInequal(Vector2 rhs){ return x != rhs.x ? x < rhs.x : y < rhs.y; } // 벡터 x,y에따른 크기 반환 ( '<' 연산 )
    public Vector2 operatorAdd(Vector2 rhs){ return new Vector2(x + rhs.x, y + rhs.y); } // 벡터 덧셈
    public Vector2 operatorSub(Vector2 rhs){ return new Vector2(x - rhs.x, y - rhs.y); } // 벡터 뺄셈
    public void operatorMulti(float rhs) { this.x = x*rhs; this.y = y*rhs; } // 벡터 곱연산
    public float length(){ return (float)Math.sqrt(Math.pow(x,2)+ Math.pow(y,2)); } // 벡터 길이
    public float dot(Vector2 rhs){ return this.x * rhs.x + this.y * rhs.y; } // 벡터 내적
    public float cross(Vector2 rhs) { return this.x * rhs.y - rhs.x * this.y; } // 벡터 외적
    public void normalVector(){
        float size = this.length();
        if(size==0) {this.x=0; this.y=0;}
        this.x = x/size;
        this.y = y/size;
    }
}
