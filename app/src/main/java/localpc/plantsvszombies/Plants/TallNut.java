package localpc.plantsvszombies.Plants;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/11.
 */

public class TallNut extends Plant {

    public TallNut(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 63;
        mHeight = 72;
        mHeight = mHeight * mRowSize / mWidth;
        mWidth = mRowSize;
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mOffSetX = 175;
        mOffSetY = 56;
        mOffSetX = mOffSetX * 1920 / 1066;
        mOffSetY = mOffSetY * 1080 / 600;

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.tall_nut);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setRowCol(p.x, p.y);

        mPlantName = "TallNut";
        mAliveFlag = true;

        mMaxHP = 10000;
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
        mGifViewObj.setMovieResource(R.drawable.tall_nut);
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
        mGifViewObj.setMovieResource(R.drawable.tall_nut_littlehurt);
    }

    public void injuredAction()
    {
        mGifViewObj.setMovieResource(R.drawable.tall_nut_injured);
    }


    @Override
    public Rect getRect()
    {
        return mRect;
    }

}
