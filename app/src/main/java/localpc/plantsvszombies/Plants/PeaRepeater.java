package localpc.plantsvszombies.Plants;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.Bullets.PeaShooterBullet;
import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by LXL on 2017/5/12.
 */

public class PeaRepeater extends Plant {

    public PeaRepeater(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 63;
        mHeight = 70;
        mHeight = mHeight * mRowSize / mWidth;
        mWidth = mRowSize;
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.repeater);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setRowCol(p.x, p.y);

        mPlantName = "PeaShooter";
        mAliveFlag = true;

        mMaxHP = 100;
        mHP = mMaxHP;
        mBulletReloadTime = 10;
        mBulletReload = mBulletReloadTime;
        mBulletMaxNumber = 2;
        mBulletNumber = mBulletMaxNumber;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.right - 20, mRect.centerY() - 45);

        //Log.d("DebugTag", this + "pt = " + mHitPoint);

    }

    public void aliveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.repeater);
    }

    public void attackAction()
    {
        mGifViewObj.setMovieResource(R.drawable.repeater);
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
                return new PeaShooterBullet(mContext, mRelativeLayout, mHitPoint);
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
