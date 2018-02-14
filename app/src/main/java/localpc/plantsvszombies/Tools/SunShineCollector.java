package localpc.plantsvszombies.Tools;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import localpc.plantsvszombies.R;

/**
 * Created by LXL on 2017/5/12.
 */

public class SunShineCollector {

    private int mSunShineNum;
    private TextView mSunNumText;
    private ImageView mSunImage;

    public SunShineCollector(Context context, RelativeLayout layout)
    {
        mSunShineNum = 1000;

        mSunImage = new ImageView(context);
        mSunImage.setImageResource(R.drawable.sunback);
        RelativeLayout.LayoutParams mlp2 = new RelativeLayout.LayoutParams(250, 100);
        mSunImage.setLayoutParams(mlp2);
        mSunImage.setX(0);
        mSunImage.setY(5);
        layout.addView(mSunImage);

        mSunNumText = new TextView(context);
        mSunNumText.setText(Integer.toString(mSunShineNum));
        mSunNumText.setTextColor(Color.BLACK);
        mSunNumText.setTextSize(12);
        mSunNumText.setX(100);
        mSunNumText.setY(30);
        layout.addView(mSunNumText);

    }

    public void setNum(int num)
    {
        mSunShineNum = num;
        mSunNumText.setText(Integer.toString(mSunShineNum));
    }

    public int getNum()
    {
        return mSunShineNum;
    }

    public void changeNum(int change)
    {
        mSunShineNum += change;
        mSunNumText.setText(Integer.toString(mSunShineNum));
    }

    public void bingToFront()
    {
        mSunImage.bringToFront();
        mSunNumText.bringToFront();
    }



}
