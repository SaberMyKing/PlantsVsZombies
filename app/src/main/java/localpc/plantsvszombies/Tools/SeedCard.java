package localpc.plantsvszombies.Tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import localpc.plantsvszombies.GameObj;
import localpc.plantsvszombies.GifView;

/**
 * Created by Xinlei on 2017/5/13.
 *
 * 新建SeedCard对象需要注意String name，name是每个对象唯一的标识
 *
 * 选中的SeedCard的name会被保存在mSelectCardName中，用完后需要手工重置为"null"
 *
 */

public class SeedCard extends GameObj {

    private ImageView mCardImage;

    private String mCardName = "SeedCard";

    public static String mSelectCardName = "null";


    public SeedCard(Context context, RelativeLayout layout, Point p, int imageId, String name)
    {
        mCardImage = new ImageView(context);

        mCardImage.setImageResource(imageId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 200);
        mCardImage.setLayoutParams(lp);
        layout.addView(mCardImage);
        mCardImage.setX(p.x);
        mCardImage.setY(p.y);

        mCardName = name;

        mCardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectCardName = mCardName;
            }
        });

    }

    public  Rect getRect()
    {
        return new Rect(0, 0, 0, 0);

    }

    public  Point getHitPoint()
    {
        return new Point(0, 0);
    }


    public ImageView getImageView()
    {
        return mCardImage;
    }





}
