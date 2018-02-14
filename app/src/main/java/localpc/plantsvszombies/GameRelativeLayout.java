package localpc.plantsvszombies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.Plants.Plant;
import localpc.plantsvszombies.Zombies.Zombie;

/**
 * Created by Xinlei on 2017/5/11.
 */

public class GameRelativeLayout extends RelativeLayout {

    public GameRelativeLayout(Context context)
    {
        super(context);

        setWillNotDraw(false);
    }

    public Point getTouchPt()
    {
        return mTouchPt;
    }

    private static int INVALID_ID = -1;
    private int mActivePointerId = INVALID_ID;
    private Point mTouchPt = new Point(0, 0);

    private Runnable mOnActionDown = new Runnable() {
        @Override
        public void run() {

        }
    };

    public void setOnActionDown(Runnable runnable)
    {
        mOnActionDown = runnable;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                int index = event.getActionIndex();
                mActivePointerId = event.getPointerId(index);
                mTouchPt.x = (int )event.getRawX();
                mTouchPt.y = (int )event.getRawY();
                mOnActionDown.run();

                Log.d("DebugTag", "ACTION_DOWN " + mTouchPt);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                index = event.findPointerIndex(mActivePointerId);
                mTouchPt.x = (int )event.getRawX();
                mTouchPt.y = (int )event.getRawY();

                Log.d("DebugTag", "ACTION_MOVE " + mTouchPt);

                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_ID;
                mTouchPt.x = 0;
                mTouchPt.y = 0;
                break;
        }
        return true;
    }


    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Rect rect;
        Point pt;
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        Paint hitPtPaint = new Paint();
        hitPtPaint.setColor(Color.BLUE);

        Zombie zombie;
        for(int i=0; i<GameObjManager.mZombeArrayList.size(); i++) {

            zombie = GameObjManager.mZombeArrayList.get(i);
            rect = zombie.getRect();
            pt = zombie.getHitPoint();

            canvas.drawRect(rect, paint);
            canvas.drawCircle(pt.x, pt.y, 10, hitPtPaint);
        }

        //Log.d("DebugTag", GameActivity.mPlantArrayList.get(5) + "pt = " + GameActivity.mPlantArrayList.get(5).getHitPoint());

        Plant plant;
        for(int i=0; i<GameObjManager.mPlantArrayList.size(); i++) {

            plant = GameObjManager.mPlantArrayList.get(i);
            rect = plant.getRect();
            pt = plant.getHitPoint();

            canvas.drawRect(rect, paint);
            canvas.drawCircle(pt.x, pt.y, 10, hitPtPaint);
        }


        Bullet bullet;
        for(int i=0; i<GameObjManager.mBulletArrayList.size(); i++) {

            bullet = GameObjManager.mBulletArrayList.get(i);
            rect = bullet.getRect();
            pt = bullet.getHitPoint();

            canvas.drawCircle(pt.x, pt.y, 10, hitPtPaint);
        }

        GameObj tool;
        for(int i=0; i<GameObjManager.mToolArrayList.size(); i++) {

            tool = GameObjManager.mToolArrayList.get(i);
            rect = tool.getRect();
            pt = tool.getHitPoint();

            canvas.drawRect(rect, paint);
            canvas.drawCircle(pt.x, pt.y, 10, hitPtPaint);
        }



    }

}
