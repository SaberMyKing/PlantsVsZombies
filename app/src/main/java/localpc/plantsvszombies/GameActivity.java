package localpc.plantsvszombies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.Zombies.Zombie;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private GameRelativeLayout mRelativeLayout;

    private Timer mTimer;
    TimerTask updateTask, lowSpeedTask, veryLowTask, sunShineDropTask, mZombieTask, mPlantTask, mBulletTask;

    private Point mScreenSize = new Point(1024, 1080);

    GameObjManager mGameObjManager;

    public final static int UPDATE = 0, GAMECLOCKEVENT = 1, VERYLOWEVENT = 2, SUNSHINE_DROP = 3, ZOMBIE_ACTION = 4, PLANT_ACTION = 5, BULLET_ACTION = 6;

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.what)
            {
                case UPDATE:
                    mGameObjManager.updateViewLayout();
                    mRelativeLayout.requestLayout();
                    break;
                case GAMECLOCKEVENT:
                    mGameObjManager.lawnMoverCheckEnemyTakeAction();
                    break;
                case VERYLOWEVENT:
                    mGameObjManager.zombieGenerator();

                    if(mGameObjManager.checPlantkWin())
                    {
                        veryLowTask.cancel();
                        sunShineDropTask.cancel();
                        mZombieTask.cancel();
                        mPlantTask.cancel();

                        mGameObjManager.plantWinWindow();
                        break;
                    }
                    if(mGameObjManager.checkZombieWin())
                    {
                        veryLowTask.cancel();
                        sunShineDropTask.cancel();
                        mZombieTask.cancel();
                        mPlantTask.cancel();

                        mGameObjManager.zombieWinWindow();
                    }
                    break;
                case SUNSHINE_DROP:
                    mGameObjManager.sunShineGenerator();
                    break;

                case PLANT_ACTION:
                    mGameObjManager.outputPlantCheckEnemyTakeAction();
                    mGameObjManager.inputPlantTakeAction();
                    break;

                case ZOMBIE_ACTION:
                    mGameObjManager.zombieCheckEnemyTakeAction();
                    break;

                case BULLET_ACTION:
                    mGameObjManager.bulletMoveForward();
                    break;


            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        mRelativeLayout = new GameRelativeLayout(this);
        mGameObjManager = new GameObjManager(this, mRelativeLayout);
        setContentView(mRelativeLayout);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(mScreenSize);
        Log.d("DebugTag", "ScreenSize = " + mScreenSize.toString());
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(1920, 1080);//(mScreenSize.x, mScreenSize.y);

        ImageView bgImage = new ImageView(this);
        bgImage.setImageResource(R.drawable.bg_pic);
        mRelativeLayout.addView(bgImage, lp1);
//        bgImage.setVisibility(View.INVISIBLE);

        mGameObjManager.putTool();

        mRelativeLayout.setOnActionDown(new Runnable() {
            @Override
            public void run() {
                Point pt = mRelativeLayout.getTouchPt();
                mGameObjManager.createPlantByCard(pt);
            }
        });

        mGameObjManager.updateViewLayout();


        mTimer = new Timer(true);
        updateTask = new TimerTask()
        {
            public void run()
            {
                mHandler.sendEmptyMessage(UPDATE);
            }
        };

        lowSpeedTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(GAMECLOCKEVENT);
            }
        };

        veryLowTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(VERYLOWEVENT);
            }
        };

        sunShineDropTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(SUNSHINE_DROP);
            }
        };

        mZombieTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(ZOMBIE_ACTION);
            }
        };

        mPlantTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(PLANT_ACTION);
            }
        };

        mBulletTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(BULLET_ACTION);
            }
        };

        mTimer.schedule(updateTask, 0, 100);
        mTimer.schedule(lowSpeedTask, 0, 100);
        mTimer.schedule(veryLowTask, 3000, 1000);
        mTimer.schedule(sunShineDropTask, 2000, 5000);
        mTimer.schedule(mZombieTask, 0, 250);
        mTimer.schedule(mPlantTask, 0, 250);
        mTimer.schedule(mBulletTask, 0, 100);

        MainActivity.mActivityManager.add(this);


    }
}







