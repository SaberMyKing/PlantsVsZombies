package localpc.plantsvszombies.Zombies;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import localpc.plantsvszombies.GameObj;
import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.Plants.Plant;
import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/9.
 */

public abstract class Zombie extends GameObj {

    protected Point mPosition = new Point(500, 500);
    protected int mRow, mRowSize, mOffSetX, mWidth;
    protected int mCol, mColSize, mOffSetY, mHeight;

    protected RelativeLayout mRelativeLayout;
    protected Context mContext;
    protected RelativeLayout.LayoutParams mlp;

    protected GifView[] mGifView = new GifView[3];
    protected GifView mGifViewObj = mGifView[0];

    public final static int MOVE = 0, ATTACK = 1, DEAD = 2;
    protected int mCurAction = -1;

    protected String mZombieName = "Zombie";

    protected int mSpeed, mHP = 1000, mMaxHP = 1000, mAttackHarm;
    protected boolean mAliveFlag = false;

    protected Rect mRect;
    protected Point mHitPoint;

    public Zombie(Context context, RelativeLayout layout)
    {
        mContext = context;
        mRelativeLayout = layout;

        mRowSize = 80;
        mColSize = 100;
        mRowSize = mRowSize * 1920 / 1066;
        mColSize = mColSize * 1080 / 600;

    }


    public void setPosition(int x, int y)
    {
        mPosition.x = x;
        mPosition.y = y;
        //mlp.setMargins(mPosition.x, mPosition.y, 0, 0);
        mGifViewObj.setX(x);
        mGifViewObj.setY(y);
    }


    public void setStartCol(int col)
    {
        mCol = col;

        setPosition(mOffSetX, mOffSetY + col*mColSize);

    }

    public int getStartCol()
    {
        return mCol;
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
                case MOVE:
                    moveAction();
                    break;
                case ATTACK:
                    attackAction();
                    break;
                case DEAD:
                    deadAction();
                    break;
            }
        }

    }


    public abstract void moveAction();
    public abstract void attackAction();
    public abstract void deadAction();


    public void requestLayout()
    {
        mGifViewObj.requestLayout();
    }

    public String getName()
    {
        return mZombieName;
    }

    public String toString()
    {
        return mZombieName + " (" + mPosition.x + ", " + mPosition.y + ") \n";
    }


    /** 仅涉及对象HP的相关参数变化和动作图像，不涉及对象的移除和析构
     *
     * @param change
     * @return 0 clear the zombie in data struct, you need to do it by yourself in main program;
     */
    public int changeHP(int change)
    {
        mHP += change;
        if(mHP <= 0)
        {
            mHP = 0;
            setAction(DEAD);
        }

        return mHP;
    }


    public void moveForward()
    {
        setAction(MOVE);
        mPosition.x -= mSpeed;
        setPosition(mPosition.x, mPosition.y);
        updateRect(-mSpeed, 0);
    }

    public int attack(Plant plant)
    {
        setAction(ATTACK);
        return plant.changeHP(-mAttackHarm);
    }


    public Rect getRect(){

        Log.d("Debug3", "Width = " + mGifViewObj.getGifWidth());
        Log.d("Debug3", "Height = " + mGifViewObj.getGifHeight());

        return new Rect((int )mGifViewObj.getX(),
                (int )mGifViewObj.getY(),
                (int )(mGifViewObj.getX() + mGifViewObj.getGifWidth()),
                (int )(mGifViewObj.getY() + mGifViewObj.getGifHeight()) );

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
