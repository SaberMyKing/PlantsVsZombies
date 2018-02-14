package localpc.plantsvszombies.Plants;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.Bullets.PeaShooterBullet;
import localpc.plantsvszombies.Bullets.SunShine;
import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/7.
 */

public class SunFlower extends Plant{

    public SunFlower(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 63;
        mHeight = 72;
        mHeight = mHeight * mRowSize / mWidth;
        mWidth = mRowSize;
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.sun_flower);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setRowCol(p.x, p.y);

        mPlantName = "SunFlower";
        mAliveFlag = true;

        mMaxHP = 100;
        mHP = mMaxHP;
        mBulletReloadTime = 22;
        mBulletReload = mBulletReloadTime;
        mBulletMaxNumber = 1;
        mBulletNumber = mBulletMaxNumber;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.right, mRect.centerY());

        //Log.d("DebugTag", this + "pt = " + mHitPoint);

    }


    public void aliveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.sun_flower);
    }

    public void attackAction()
    {
        mGifViewObj.setMovieResource(R.drawable.sun_flower);
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


    public Bullet throwOneBullet()
    {
        if(mBulletReload > 0)
        {
            mBulletReload --;
            return null;
        }
        else
        {
            if(mBulletNumber > 0)
            {
                mBulletNumber --;
                return new SunShine(mContext, mRelativeLayout, mHitPoint);
            }
            else
            {
                mBulletReload = mBulletReloadTime;
                mBulletNumber = mBulletMaxNumber;
                return null;
            }

        }
    }

}
