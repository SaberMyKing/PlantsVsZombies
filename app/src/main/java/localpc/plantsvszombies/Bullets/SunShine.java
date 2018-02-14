package localpc.plantsvszombies.Bullets;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GameObjManager;
import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/14.
 */

public class SunShine extends Bullet {

    public SunShine(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 35;
        mHeight = 35;
        mWidth = (int )(mWidth * 3.5);
        mHeight = (int )(mHeight * 2);
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mOffSetX -= 60;
        mOffSetY -= 40;

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.sun);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setPosition(p.x, p.y);

        mBulletName = "SunShine";
        mAliveFlag = true;

        mSpeed = 20;
        mHP = 50;
        mAttackHarm = 40;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.centerX(), mRect.top-20);

        Log.d("DebugTag", this + "pt = " + mHitPoint);

        mGifViewObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameObjManager.mSunShineCollector.changeNum(mHP);
                setAction(HIT);

            }
        });

    }

    public void aliveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.sun);
    }

    public void hitAction()
    {
        mGifViewObj.setMovieResource(R.drawable.sun);
        deadAction();
    }

    public void deadAction()
    {
        mRelativeLayout.removeView(mGifViewObj);
        mAliveFlag = false;

    }


    @Override
    public Rect getRect()
    {
        return mRect;
    }




}
