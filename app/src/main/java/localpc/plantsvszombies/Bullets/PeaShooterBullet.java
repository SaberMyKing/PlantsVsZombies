package localpc.plantsvszombies.Bullets;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/11.
 */

public class PeaShooterBullet extends Bullet {

    public PeaShooterBullet(Context context, RelativeLayout layout, Point p)
    {
        super(context, layout);

        mWidth = 24;
        mHeight = 24;
        mWidth = (int )(mWidth * 3.5);
        mHeight = (int )(mHeight * 2);
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mOffSetX -= 60;
        mOffSetY -= 40;

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.peashooter_bullet);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        setAction(ALIVE);

        setPosition(p.x, p.y);

        mBulletName = "PeaShooter";
        mAliveFlag = true;

        mSpeed = 20;
        mHP = 1000;
        mAttackHarm = 40;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.right - 70, mRect.centerY() - 62);

        Log.d("DebugTag", this + "pt = " + mHitPoint);

    }

    public void aliveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.peashooter_bullet);
    }

    public void hitAction()
    {
        mGifViewObj.setMovieResource(R.drawable.peashooter_bullet);
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
