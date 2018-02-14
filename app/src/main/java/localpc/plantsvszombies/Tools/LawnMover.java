package localpc.plantsvszombies.Tools;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.GameObj;
import localpc.plantsvszombies.GifView;
import localpc.plantsvszombies.R;

/**
 * Created by LXL on 2017/5/12.
 */

public class LawnMover extends GameObj {

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

    protected String mToolName = "Tool";

    protected int mSpeed, mHP = 1000, mMaxHP = 1000;
    protected boolean mAliveFlag = false;

    protected Rect mRect;
    protected Point mHitPoint;

    protected boolean mTrigger = false;

    public LawnMover(Context context, RelativeLayout layout, int column)
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

        mWidth = 150;
        mHeight = 150;
        //mHeight = mHeight * mRowSize / mWidth;
        //mWidth = mRowSize;
        mlp = new RelativeLayout.LayoutParams(mWidth, mHeight);

        mGifView[0] = new GifView(context);
        mGifView[0].setMovieResource(R.drawable.lawn_mower);
        mGifView[0].setLayoutParams(mlp);

        mGifViewObj = mGifView[0];

        mRelativeLayout.addView(mGifViewObj);

        //setAction(ALIVE);

        setStartCol(column);

        mToolName = "Lawn Mover";
        mAliveFlag = true;

        mSpeed = 25;
        mMaxHP = 10000;
        mHP = mMaxHP;
        mTrigger = false;

        mRect = getRect();

        mHitPoint = new Point(mRect.right, mRect.centerY());

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

        setPosition(mOffSetX - mRowSize, mOffSetY + col*mColSize);

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


    public  void aliveAction()
    {

    }
    public  void attackAction()
    {

    }
    public  void deadAction()
    {

    }
    public void littleHurtAction(){}
    public void injuredAction(){}

    public void requestLayout()
    {
        mGifViewObj.requestLayout();
    }

    public String getName()
    {
        return mToolName;
    }

    public String toString()
    {
        return mToolName + " (" + mRow + ", " + mCol + ") "
                + " (" + mPosition.x + ", " + mPosition.y + ") \n";
    }

    public void moveForward()
    {
        if(mTrigger == false)
            return ;

        mPosition.x += mSpeed;
        setPosition(mPosition.x, mPosition.y);
        updateRect(mSpeed, 0);
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
    public void setTrigger(boolean flag)
    {
        mTrigger = flag;
    }

    public boolean getTriggerFlag()
    {
        return mTrigger;
    }

}