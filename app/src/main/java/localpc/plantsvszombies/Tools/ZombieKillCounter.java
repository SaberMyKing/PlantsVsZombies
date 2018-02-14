package localpc.plantsvszombies.Tools;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import localpc.plantsvszombies.R;

/**
 * Created by Xinlei on 2017/5/14.
 */

public class ZombieKillCounter {

    private int mZombieKillNum;
    private TextView mNumText;
    private ImageView mZombieKill;

    public ZombieKillCounter(Context context, RelativeLayout layout)
    {
        mZombieKill = new ImageView(context);
        mZombieKill.setImageResource(R.drawable.zombie_head);
        RelativeLayout.LayoutParams mlp4 = new RelativeLayout.LayoutParams(200, 100);
        mZombieKill.setLayoutParams(mlp4);
        layout.addView(mZombieKill);
        mZombieKill.setX(300);
        mZombieKill.setY(0);

        mNumText = new TextView(context);
        mNumText.setText(Integer.toString(mZombieKillNum));
        mNumText.setTextColor(Color.BLACK);
        mNumText.setTextSize(12);
        mNumText.setX(420);
        mNumText.setY(30);
        layout.addView(mNumText);

    }

    public void setNum(int num)
    {
        mZombieKillNum = num;
        mNumText.setText(Integer.toString(mZombieKillNum));
    }

    public int getNum()
    {
        return mZombieKillNum;
    }

    public void changeNum(int change)
    {
        mZombieKillNum += change;
        mNumText.setText(Integer.toString(mZombieKillNum));
    }


    public void bingToFront()
    {
        mZombieKill.bringToFront();
        mNumText.bringToFront();
    }



}
