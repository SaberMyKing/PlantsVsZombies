package localpc.plantsvszombies.Bullets;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.Plants.Plant;
import localpc.plantsvszombies.Zombies.Zombie;

/**
 * Created by Xinlei on 2017/5/11.
 */

public abstract class Bullet {

    protected Point mPosition = new Point(500, 500);
    protected int mRow, mRowSize, mOffSetX, mWidth;
    protected int mCol, mColSize, mOffSetY, mHeight;

    protected RelativeLayout mRelativeLayout;
    protected Context mContext;
    protected RelativeLayout.LayoutParams mlp;

    protected GifView[] mGifView = new GifView[3];
    protected GifView mGifViewObj = mGifView[0];

    public final static int ALIVE = 0, HIT = 1, DEAD = 2;
    protected int mCurAction = -1;

    protected String mBulletName = "Bullet";

    protected int mSpeed, mHP = 1000, mAttackHarm;
    protected boolean mAliveFlag = false;

    protected Rect mRect;
    protected Point mHitPoint;

    public Bullet(Context context, RelativeLayout layout)
    {
        mContext = context;
        mRelativeLayout = layout;

        mRowSize = 80;
        mColSize = 100;
        mRowSize = mRowSize * 1920 / 1066;
        mColSize = mColSize * 1080 / 600;

        mOffSetX = 0;
        mOffSetY = 0;
        mOffSetX = mOffSetX * 1920 / 1066;
        mOffSetY = mOffSetY * 1080 / 600;

    }


    public void setPosition(int x, int y)
    {
        mPosition.x = x;
        mPosition.y = y;
        //mlp.setMargins(mPosition.x, mPosition.y, 0, 0);
        mGifViewObj.setX(mOffSetX + x);
        mGifViewObj.setY(mOffSetY + y);
    }


    public void setRowCol(int row, int col)
    {
        mRow = row;
        mCol = col;

        setPosition(mOffSetX + row*mRowSize, mOffSetY + col*mColSize);

    }

    public Point getRowCol()
    {
        return new Point(mRow, mCol);
    }

    public Point getPosition()
    {
        return mPosition;
    }

    public GifView getGifViewObj()
    {
        return mGifViewObj;
    }


    public void setAction(int action)
    {
        if(mCurAction == action)
        {
            return ;
        }

        mCurAction = action;

        if(mAliveFlag == true)
        {
            switch(action)
            {
                case ALIVE:
                    aliveAction();
                    break;
                case HIT:
                    hitAction();
                    break;
                case DEAD:
                    deadAction();
                    break;
            }
        }

    }


    public abstract void aliveAction();
    public abstract void hitAction();
    public abstract void deadAction();

    public void requestLayout()
    {
        mGifViewObj.requestLayout();
    }

    public String toString()
    {
        return mBulletName + " (" + mRow + ", " + mCol + ") "
                + " (" + mPosition.x + ", " + mPosition.y + ") \n";
    }

    public int changeHP(int change)
    {
        mHP += change;
        if(mHP <= 0) {
            mHP = 0;
            setAction(DEAD);
        }

        return mHP;
    }

    public void moveForward()
    {
        setAction(ALIVE);
        mPosition.x += mSpeed;
        setPosition(mPosition.x, mPosition.y);
        updateRect(mSpeed, 0);
    }

    public int attack(Zombie zombie)
    {
        setAction(HIT);
        return zombie.changeHP(-mAttackHarm);
    }


    public Rect getRect(){
        return new Rect((int )mGifViewObj.getX(),
                (int )mGifViewObj.getY(),
                (int )(mGifViewObj.getX() + mRowSize),
                (int )(mGifViewObj.getY() + mColSize) );
    }

    public Point getHitPoint()
    {
        return mHitPoint;
    }

    public Point getRowColSize()
    {
        return new Point(mRowSize, mColSize);
    }

    public Point getOffset()
    {
        return new Point(mOffSetX, mOffSetY);
    }

    public void updateRect(int offsetX, int offsetY)
    {
        mRect.left += offsetX;
        mRect.right += offsetX;
        mRect.top += offsetY;
        mRect.bottom += offsetY;

        mHitPoint.x += offsetX;
        mHitPoint.y += offsetY;

    }



}
