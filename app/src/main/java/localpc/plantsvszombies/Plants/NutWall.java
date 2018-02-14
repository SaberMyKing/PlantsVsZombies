package localpc.plantsvszombies.Plants;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by LXL on 2017/5/12.
 */

public class NutWall extends Plant {

    public NutWall(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 63;
        mHeight = 72;
        mHeight = mHeight * mRowSize / mWidth;
        mWidth = mRowSize;
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.wall_nut);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setRowCol(p.x, p.y);

        mPlantName = "NutWall";
        mAliveFlag = true;

        mMaxHP = 5000;
        mHP = mMaxHP;
        mBulletReloadTime = 10;
        mBulletReload = mBulletReloadTime;
        mBulletMaxNumber = 1;
        mBulletNumber = mBulletMaxNumber;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.right, mRect.centerY());

        //Log.d("DebugTag", this + "pt = " + mHitPoint);

    }


    public void aliveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.wall_nut);
    }

    public void attackAction()
    {

    }

    public void deadAction()
    {
        mRelativeLayout.removeView(mGifViewObj);
        mAliveFlag = false;
    }

    public void littleHurtAction()
    {
        mGifViewObj.setMovieResource(R.drawable.wall_nut_littlehurt);
    }

    public void injuredAction()
    {
        mGifViewObj.setMovieResource(R.drawable.wall_nut_injured);
    }


    @Override
    public Rect getRect()
    {
        return mRect;
    }

}
