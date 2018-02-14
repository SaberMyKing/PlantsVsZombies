package localpc.plantsvszombies.Plants;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.Bullets.PeaShooterBullet;
import localpc.plantsvszombies.GameObj;
import localpc.plantsvszombies.GifView;

/**
 * Created by Xinlei on 2017/5/9.
 */

public abstract class Plant extends GameObj {

    protected Point mPosition = new Point(500, 500);
    protected int mRow, mRowSize, mOffSetX, mWidth;
    protected int mCol, mColSize, mOffSetY, mHeight;

    protected RelativeLayout mRelativeLayout;
    protected Context mContext;
    protected RelativeLayout.LayoutParams mlp;

    protected GifView[] mGifView = new GifView[3];
    protected GifView mGifViewObj = mGifView[0];
    
    public final static int ALIVE = 0, ATTACK = 1, DEAD = 2, LITTE_HURT = 3, INJURED = 4;
    protected int mCurAction = -1;
    
    protected String mPlantName = "Plant";

    protected int mHP = 1000, mMaxHP = 1000;
    protected boolean mAliveFlag = false;

    protected Rect mRect;
    protected Point mHitPoint;

    protected int mBulletReload = 10, mBulletReloadTime = 10, mBulletNumber = 1, mBulletMaxNumber = 1;

    public Plant(Context context, RelativeLayout layout)
    {
        mContext = context;
        mRelativeLayout = layout;

        mRowSize = 80;
        mColSize = 100;
        mRowSize = mRowSize * 1920 / 1066;
        mColSize = mColSize * 1080 / 600;

        mOffSetX = 175;
        mOffSetY = 87;
        mOffSetX = mOffSetX * 1920 / 1066;
        mOffSetY = mOffSetY * 1080 / 600;

    }


    public void setPosition(int x, int y)
    {
        mPosition.x = x;
        mPosition.y = y;
        //mlp.setMargins(mPosition.x, mPosition.y, 0, 0);
        mGifViewObj.setX(x);
        mGifViewObj.setY(y);
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


    /**setAction仅为设置对象的动作图像，在View中刷新出来
     *
     * 实际动作的位移或者攻击行为的相关数据变化，需要另行调用其他方法才能完成
     *
     * @param action
     */
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
                case ATTACK:
                    attackAction();
                    break;
                case DEAD:
                    deadAction();
                    break;
                case LITTE_HURT:
                    littleHurtAction();
                    break;
                case INJURED:
                    injuredAction();
                    break;

            }
        }

    }


    public abstract void aliveAction();
    public abstract void attackAction();
    public abstract void deadAction();
    public void littleHurtAction(){}
    public void injuredAction(){}

    public void requestLayout()
    {
        mGifViewObj.requestLayout();
    }

    public String getName()
    {
        return mPlantName;
    }

    public String toString()
    {
        return mPlantName + " (" + mRow + ", " + mCol + ") "
                + " (" + mPosition.x + ", " + mPosition.y + ") \n";
    }

    public int changeHP(int change)
    {
        mHP += change;

        if(mHP > mMaxHP*0.6)
        {
            setAction(ALIVE);
        }
        else if(mHP > mMaxHP*0.3)
        {
            setAction(LITTE_HURT);
        }
        else if(mHP > 0)
        {
            setAction(INJURED);
        }
        else if(mHP == 0)
        {
            setAction(DEAD);
        }

        if(mHP <= 0) {
            mHP = 0;
        }

        return mHP;
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


    public Bullet throwOneBullet()
    {
        return null;
    }

}
