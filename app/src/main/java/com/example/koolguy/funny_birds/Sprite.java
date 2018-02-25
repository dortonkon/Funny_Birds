package com.example.koolguy.funny_birds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koolguy on 09.02.2018.
 */

public class Sprite
{
    private Bitmap bitmap;
    private List<Rect> frames;
    private int frameWidth, frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;
    private int view_width;
    private double x;
    private double y;
    private Rect destination;
    private double velocityX;
    private double velocityY;
    private Game_View game_view;
    private int padding;
    private int track=0;
    public int tr=0;

    public Sprite(double x, double y, double velocityX, double velocityY, Rect initialFrame, Bitmap bitmap,int view_width) {
        this.view_width=view_width;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.bitmap = bitmap;
        this.frames = new ArrayList<Rect>();
        this.frames.add(initialFrame);
        this.bitmap = bitmap;
        this.timeForCurrentFrame = 0.0;
        this.frameTime = 0.1;
        this.currentFrame = 0;
        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
        this.padding = 20;

    }
    public void AddFrame(Rect frame){frames.add(frame);}
    public void update (int ms) {

            if(CheckStatus())x+=40; //4.тут меняет координаты по иксу если есть куда бежать и нажата кнопка,работает по таймеру в game_viev
            currentFrame = (currentFrame + 1) % frames.size();

    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination,  p);

    }
    public void drawRun(Canvas canvas)
    {


    }
    public int getX(){return (int)x;}
    public boolean Run(){if (tr == 1)return true;else return false; } //2.потом здесь чекается бежит ли перс
    public int getTrack(){return track;}
    public boolean CheckStatus()
    {
        if (x+40+frameWidth<Game_View.getView_w()&&Run())return true; //3.Здесь ваще отвечает за движение и за выход за границы.
        else return false;
    }




}