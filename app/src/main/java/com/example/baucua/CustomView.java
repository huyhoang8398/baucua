package com.example.baucua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.github.tbouron.shakedetector.library.ShakeDetector;

import java.util.Random;


public class CustomView extends View {
    private Paint paint;
    private Paint paint2;

    int xPos;
    int yPos;

    Bitmap cua;
    Bitmap smallerCua;

    Bitmap ca;
    Bitmap smallerCa;

    Bitmap tom;
    Bitmap smallerTom;

    Bitmap muc;
    Bitmap smallerMuc;

    Bitmap so;
    Bitmap smallerSo;

    Bitmap tomHum;
    Bitmap smallerTomHum;

    public CustomView(Context context) {
        super(context);
        setFocusable(true);
        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.CYAN);

        paint2 = new Paint();
        paint2.setColor(Color.RED);

        cua = BitmapFactory.decodeResource(getResources(), R.mipmap.cua);
        smallerCua = Bitmap.createScaledBitmap(cua, 150, 150, false);

        ca = BitmapFactory.decodeResource(getResources(), R.mipmap.ca);
        smallerCa = Bitmap.createScaledBitmap(ca, 150, 150, false);

        tom = BitmapFactory.decodeResource(getResources(), R.mipmap.tom);
        smallerTom = Bitmap.createScaledBitmap(tom, 150, 150, false);

        tomHum = BitmapFactory.decodeResource(getResources(), R.mipmap.tomhum);
        smallerTomHum = Bitmap.createScaledBitmap(tomHum, 150, 150, false);

        so = BitmapFactory.decodeResource(getResources(), R.mipmap.so);
        smallerSo = Bitmap.createScaledBitmap(so, 150, 150, false);

        muc = BitmapFactory.decodeResource(getResources(), R.mipmap.muc);
        smallerMuc = Bitmap.createScaledBitmap(muc, 150, 150, false);

        ShakeDetector.create(context, new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                hideTopCircle();
                Handler h = new Handler();
                h.postDelayed(() -> {
                    newGame();
                }, 7000);

            }
        });
    }

    public void hideTopCircle() {
        paint2.setColor(Color.TRANSPARENT);
        invalidate();
    }

    public void newGame() {
        Canvas canvas = new Canvas();
        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);

        xPos = (canvas.getWidth() / 2);
        yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        paint2.setColor(Color.RED);
        canvas.drawCircle(xPos, yPos, 300, paint2);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                hideTopCircle();
            case MotionEvent.ACTION_UP:
                Handler h = new Handler();
                h.postDelayed(this::newGame, 7000);
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);

        xPos = (canvas.getWidth() / 2);
        yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(xPos, yPos, 300, paint);

        Bitmap[] images = {smallerTom, smallerMuc, smallerSo, smallerTomHum, smallerCua, smallerCa};
        Random rand = new Random();

        canvas.drawBitmap(images[rand.nextInt(images.length)], xPos + 50, yPos + 50, null);
        canvas.drawBitmap(images[rand.nextInt(images.length)], xPos - 70, yPos - 200, null);
        canvas.drawBitmap(images[rand.nextInt(images.length)], xPos - 200, yPos + 50, null);

        canvas.drawCircle(xPos, yPos, 300, paint2);

    }
}
