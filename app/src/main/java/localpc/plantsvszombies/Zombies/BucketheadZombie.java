package localpc.plantsvszombies.Zombies;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/14.
 */

public class BucketheadZombie extends Zombie {


    public BucketheadZombie(Context context, RelativeLayout layout, int column)
    {
        super(context, layout);

        mWidth = 85;
        mHeight = 120;
        mWidth = (int )(mWidth * 3.5);
        mHeight = (int )(mHeight * 2);
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);


        mOffSetX = 944;
        mOffSetY = 45;
        mOffSetX = mOffSetX * 1920 / 1066;
        mOffSetY = mOffSetY * 1080 / 600;

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.buckethead_zombie);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);


        setAction(MOVE);

        setStartCol(column);

        mZombieName = "BucketheadZombie";
        mAliveFlag = true;

        mSpeed = 10;
        mMaxHP = 750;
        mHP = mMaxHP;
        mAttackHarm = 20;

        mRect = super.getRect();

        mHitPoint = new Point(mRect.left, mRect.centerY());

        updateRect(130, 80);

        //Log.d("DebugTag", mRect.toString());


    }


    public void moveAction()
    {
        mGifViewObj.setMovieResource(R.drawable.buckethead_zombie);
    }

    public void attackAction()
    {
        mGifViewObj.setMovieResource(R.drawable.buckethead_zombie_attack);
    }

    public void deadAction()
    {
        mGifViewObj.playOnce(R.drawable.zombie_lost_head);

        mGifViewObj.nextPlayOnce(new Runnable() {
            @Override
            public void run() {
                mGifViewObj.setMovieResource(R.drawable.zombie_die);
            }
        });

        mGifViewObj.nextPlayOnce(new Runnable() {
            @Override
            public void run() {
                mRelativeLayout.removeView(mGifViewObj);
            }
        });

        mAliveFlag = false;
    }

    @Override
    public Rect getRect()
    {
        return mRect;
    }


}
