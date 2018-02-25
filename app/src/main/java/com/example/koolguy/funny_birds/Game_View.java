package com.example.koolguy.funny_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by koolguy on 09.02.2018.
 */

public class Game_View extends View {
    private  Canvas canvas1;
    private Sprite playerBird;
    private int view_height,points;
    private static  int view_width;
    private Timer t;
    private int track;
    public Game_View(Context context) {
        super(context);
        points = 0;

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.sprites);

        int w = b.getWidth() / 5;
        int h = b.getHeight() / 2;

        Rect firstFrame = new Rect(0, 0, w, h);
        playerBird = new Sprite(35, 35, 0, 0, firstFrame, b,view_width);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4 ; j++) {


                if (i == 0 && j == 0) continue;
                playerBird.AddFrame(new Rect(j * w, h * i, (j * w) + w, (h * i) + h));

            }


        }
        t = new Timer(Long.MAX_VALUE,150);
        t.start();


    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        view_height=h;
        view_width=w;

    }
    public static int getView_w(){return view_width;}
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(250,127,199,255);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(55);
        canvas.drawText(playerBird.getTrack()+"",view_width-100,70,p);
        playerBird.draw(canvas);


    }
    private  void upgrade()
    {

        playerBird.update(5);
        invalidate();

    }
    public boolean onTouchEvent(MotionEvent e) //1.свитч смотрит что происходит если нажато то меняет переменную следящую за бегом.В идеале ваще там сеттер нужен но мне было лень.
    {
        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                playerBird.tr=1;
                break;
            case MotionEvent.ACTION_UP:
                playerBird.tr=0;
                break;
            default:playerBird.tr=0;break;

        }

        return true;
    }





        class Timer extends CountDownTimer
    {


        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
              upgrade();
        }

        @Override
        public void onFinish() {

        }
    }
}
