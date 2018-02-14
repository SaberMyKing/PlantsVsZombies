package localpc.plantsvszombies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

import localpc.plantsvszombies.Bullets.Bullet;
import localpc.plantsvszombies.Bullets.SunShine;
import localpc.plantsvszombies.Plants.NutWall;
import localpc.plantsvszombies.Plants.PeaRepeater;
import localpc.plantsvszombies.Plants.PeaShooter;
import localpc.plantsvszombies.Plants.Plant;
import localpc.plantsvszombies.Plants.SunFlower;
import localpc.plantsvszombies.Plants.TallNut;
import localpc.plantsvszombies.Tools.LawnMover;
import localpc.plantsvszombies.Tools.SeedCard;
import localpc.plantsvszombies.Tools.SunShineCollector;
import localpc.plantsvszombies.Tools.ZombieKillCounter;
import localpc.plantsvszombies.Zombies.BucketheadZombie;
import localpc.plantsvszombies.Zombies.ConeheadZombie;
import localpc.plantsvszombies.Zombies.EasyZombie;
import localpc.plantsvszombies.Zombies.Zombie;

/**
 * Created by Xinlei on 2017/5/14.
 *
 * GameObjManager用于将游戏与Activity分离，让此游戏可以在任何合适的Activity上运行
 *
 * OFFSETX OFFSETY ROWSIZE COLSIZE这些常量可能需要手动修改
 *
 * mZombieArrayList为所有的Zombie的容器
 *
 * mPlantArrayList为所有Plant的容器
 *
 * mBulletArrayList为所有Bullet的容器
 *
 * mToolArrayList为所有工具的容器（尽管只是部分的工具）
 *
 * mZombieCollumnList和mPlantCollumnList是与mZombieArrayList和mPlantArrayList相独立的容器序列，按行收纳对象，可以为后续遍历对象的方法提供一些便利
 *
 * mPlantRowCol为整形数组，用于标记植物所在的单元格和植物的类型，初步将植物分类为INPUT, OUTPUT, WALL三种，即生产者，输出攻击者，和防御墙
 *
 *
 *
 *
 */

public class GameObjManager {

    public final static int OFFSETX = 315, OFFSETY = 156, ROWSIZE = 144, COLSIZE = 180;

    private RelativeLayout mRelativeLayout;
    private Context mContext;

    public static ArrayList<Zombie> mZombeArrayList = new ArrayList<Zombie> ();
    public static ArrayList<Plant> mPlantArrayList = new ArrayList<Plant> ();
    public static ArrayList<Bullet> mBulletArrayList = new ArrayList<Bullet> ();

    public static ArrayList<GameObj> mToolArrayList = new ArrayList<GameObj>();


    public static ArrayList[] mZombieCollumnList = new ArrayList[]
            {
                    new ArrayList<Zombie> (), new ArrayList<Zombie> (), new ArrayList<Zombie> (), new ArrayList<Zombie> (), new ArrayList<Zombie> ()
            };

    public static ArrayList[] mPlantCollumnList = new ArrayList[]
            {
                    new ArrayList<Plant> (), new ArrayList<Plant> (), new ArrayList<Plant> (), new ArrayList<Plant> (), new ArrayList<Plant> ()
            };


    public static int[][] mPlantRowCol = new int[9][5];
    public final static int NO_PLANT = 0, OUTPUT_PLANT = 2, INPUT_PLANT = 1, TOOL_PLANT = 3, WALL_PLANT = 4;

    public static ArrayList<Plant> mInputPlantArrayList = new ArrayList<Plant>();
    public static ArrayList<Bullet> mSunShineArrayList = new ArrayList<Bullet>();

    public static SeedCard mSeedCard[] = new SeedCard[5];
    public static LawnMover[] mLawnMover = new LawnMover[5];

    public static int mZombieCounter;

    public static SunShineCollector mSunShineCollector;
    public static ZombieKillCounter mZombieKillCounter;

    private boolean mShowWinWindow;
    private ImageView mZombieWinImage, mPlantWinImage;
    private Button mRestart, mExit;

    GameObjManager(Context context, RelativeLayout layout)
    {
        mContext = context;
        mRelativeLayout = layout;
    }



    public void newPeaShooter(int x, int y)
    {
        if(mPlantRowCol[x][y] != NO_PLANT)
            return ;

        if(mSunShineCollector.getNum() < 100)
            return ;
        else
            mSunShineCollector.changeNum(-100);

        Plant obj = new PeaShooter(mContext, mRelativeLayout, new Point(x, y));
        mPlantArrayList.add(obj);
        mPlantCollumnList[y].add(obj);
        mPlantRowCol[x][y] = OUTPUT_PLANT;
    }


    public void newSunFlower(int x, int y)
    {
        if(mPlantRowCol[x][y] != NO_PLANT)
            return ;

        if(mSunShineCollector.getNum() < 50)
            return ;
        else
            mSunShineCollector.changeNum(-50);

        Plant obj = new SunFlower(mContext, mRelativeLayout, new Point(x, y));
        mPlantArrayList.add(obj);
        mPlantCollumnList[y].add(obj);
        mPlantRowCol[x][y] = INPUT_PLANT;
        mInputPlantArrayList.add(obj);
    }

    public void newTallNut(int x, int y)
    {
        if(mPlantRowCol[x][y] != NO_PLANT)
            return ;

        if(mSunShineCollector.getNum() < 125)
            return ;
        else
            mSunShineCollector.changeNum(-125);

        Plant obj = new TallNut(mContext, mRelativeLayout, new Point(x, y));
        mPlantArrayList.add(obj);
        mPlantCollumnList[y].add(obj);
        mPlantRowCol[x][y] = WALL_PLANT;
    }

    public void newNutWall(int x, int y)
    {
        if(mPlantRowCol[x][y] != NO_PLANT)
            return ;

        if(mSunShineCollector.getNum() < 50)
            return ;
        else
            mSunShineCollector.changeNum(-50);

        Plant obj = new NutWall(mContext, mRelativeLayout, new Point(x, y));
        mPlantArrayList.add(obj);
        mPlantCollumnList[y].add(obj);
        mPlantRowCol[x][y] = WALL_PLANT;
    }

    public void newPeaRepeater(int x, int y)
    {
        if(mPlantRowCol[x][y] != NO_PLANT)
            return ;

        if(mSunShineCollector.getNum() < 200)
            return ;
        else
            mSunShineCollector.changeNum(-200);

        Plant obj = new PeaRepeater(mContext, mRelativeLayout, new Point(x, y));
        mPlantArrayList.add(obj);
        mPlantCollumnList[y].add(obj);
        mPlantRowCol[x][y] = OUTPUT_PLANT;
    }


    public void newEasyZombie(int col)
    {
        Zombie obj = new EasyZombie(mContext, mRelativeLayout, col);
        mZombeArrayList.add(obj);
        mZombieCollumnList[col].add(obj);
    }

    public void newConeheadZombie(int col)
    {
        Zombie obj = new ConeheadZombie(mContext, mRelativeLayout, col);
        mZombeArrayList.add(obj);
        mZombieCollumnList[col].add(obj);
    }

    public void newBucketheadZombie(int col)
    {
        Zombie obj = new BucketheadZombie(mContext, mRelativeLayout, col);
        mZombeArrayList.add(obj);
        mZombieCollumnList[col].add(obj);
    }

    /**
     * 通过遍历植物，获取p点所在的植物
     * @param p
     * @return 调用特别注意可能返回为null
     */

    public Plant getPlantByPosition(Point p)
    {
        if(mPlantArrayList.size() == 0)
        {
            return null;
        }

        int col = (p.y - OFFSETY) / COLSIZE;
        if((col >= mPlantCollumnList.length)||(col < 0))
        {
            return null;
        }

        Plant plant;

        for(int i = 0; i < mPlantCollumnList[col].size(); i++)
        {
            plant = (Plant) mPlantCollumnList[col].get(i);
            if(plant.getRect().contains(p.x, p.y))
            {
                Log.d("DebugTag", "Hit the" + plant.toString());
                Log.d("DebugTag", "Rect" + plant.getRect().toString());
                return plant;
            }
        }

        return null;
    }

    /**
     * 通过遍历僵尸，获取p点所在的僵尸
     * @param p
     * @return 调用特别注意可能返回为null
     */

    public Zombie getZombieByPosition(Point p)
    {
        if(mZombeArrayList.size() == 0)
        {
            return null;
        }

        int col = (p.y - OFFSETY) / COLSIZE;
        if((col >= mPlantCollumnList.length)||(col < 0))
        {
            return null;
        }

        Zombie zombie;

        for(int i = 0; i < mZombieCollumnList[col].size(); i++)
        {
            zombie = (Zombie) mZombieCollumnList[col].get(i);
            if(zombie.getRect().contains(p.x, p.y))
            {
                Log.d("DebugTag", "Hit the" + zombie.toString());
                Log.d("DebugTag", "Rect" + zombie.getRect().toString());
                return zombie;
            }
        }

        return null;
    }


    public void outputPlantCheckEnemyTakeAction()
    {
        Bullet bullet;

        for(int i=0; i<mZombieCollumnList.length; i++)
        {
            if(mZombieCollumnList[i].size() != 0)
            {
                for(int j=0; j<mPlantCollumnList[i].size(); j++)
                {
                    Plant plant = (Plant )mPlantCollumnList[i].get(j);
                    /*
                    if((plant.getName().equals("SunFlower"))||(plant.getName().equals("TallNut"))
                            ||(plant.getName().equals("NutWall")))
                            */
                    if(mPlantRowCol[plant.getRowCol().x][plant.getRowCol().y] != OUTPUT_PLANT)
                    {
                        continue;
                    }

                    plant.setAction(Plant.ATTACK);
                    bullet = plant.throwOneBullet();
                    if(bullet != null)
                    {
                        mBulletArrayList.add(bullet);
                    }

                }
            }
            else
            {
                for(int j=0; j<mPlantCollumnList[i].size(); j++)
                {
                    Plant plant = (Plant )mPlantCollumnList[i].get(j);
                    /*
                    if((plant.getName().equals("SunFlower"))||(plant.getName().equals("TallNut"))
                            ||(plant.getName().equals("NutWall")))
                            */
                    if(mPlantRowCol[plant.getRowCol().x][plant.getRowCol().y] != OUTPUT_PLANT)
                    {
                        continue;
                    }

                    plant.setAction(Plant.ALIVE);

                }
            }
        }

    }

    public void zombieCheckEnemyTakeAction()
    {
        Zombie zombie;
        Plant plant;

        for(int i=0; i<mZombeArrayList.size(); i++)
        {
            zombie = mZombeArrayList.get(i);
            plant = getPlantByPosition(zombie.getHitPoint());

            if(plant != null)
            {
                if(zombie.attack(plant) == 0)
                {
                    plant.setAction(Plant.DEAD);
                    mPlantArrayList.remove(plant);
                    mPlantCollumnList[plant.getRowCol().y].remove(plant);
                    if(mPlantRowCol[plant.getRowCol().x][plant.getRowCol().y] == INPUT_PLANT)
                    {
                        mInputPlantArrayList.remove(plant);
                    }
                    mPlantRowCol[plant.getRowCol().x][plant.getRowCol().y] = NO_PLANT;
                }
            }
            else
            {
                zombie.moveForward();
            }

        }
    }

    public void lawnMoverCheckEnemyTakeAction()
    {
        Zombie zombie;
        Point pt;


        for(int i=0; i<mLawnMover.length; i++)
        {
            if(mLawnMover[i] == null)
                continue;
            pt = mLawnMover[i].getHitPoint();
            for(int j=0; j<mZombieCollumnList[i].size(); j++)
            {
                zombie = (Zombie )mZombieCollumnList[i].get(j);
                if(zombie.getRect().contains(pt.x, pt.y))
                {
                    mLawnMover[i].setTrigger(true);
                    zombie.setAction(Zombie.DEAD);
                    mZombieCollumnList[i].remove(zombie);
                    mZombeArrayList.remove(zombie);
                    j--;

                    mZombieKillCounter.changeNum(+1);
                }
            }
            if(mLawnMover[i].getPosition().x > 2000)
            {
                mRelativeLayout.removeView(mLawnMover[i].getGifViewObj());
                mLawnMover[i] = null;
            }
            else {
                mLawnMover[i].moveForward();
            }
        }
    }


    public void bulletMoveForward()
    {
        Bullet bullet;
        Zombie zombie;
        for(int i=0; i<mBulletArrayList.size(); i++) {

            bullet = mBulletArrayList.get(i);

            zombie = getZombieByPosition(bullet.getHitPoint());

            if(zombie != null)
            {
                if(bullet.attack(zombie) == 0)
                {
                    zombie.setAction(Zombie.DEAD);
                    mZombeArrayList.remove(zombie);
                    mZombieCollumnList[zombie.getStartCol()].remove(zombie);
                    mZombieKillCounter.changeNum(+1);
                }

                mBulletArrayList.remove(i);
                i--;
            }
            else
            {
                if(bullet.getPosition().x > 2000)
                {
                    mBulletArrayList.remove(bullet);
                    mRelativeLayout.removeView(bullet.getGifViewObj());
                }
                else {
                    bullet.moveForward();
                }
            }

        }
    }

    /**
     * 将实际的坐标点转化为植物单元格坐标
     *
     * @param pt
     * @return 如果pt范围越界，找不到其所在的单元格将返回null，调用时务必注意其可能为null的情况
     */

    public Point turnPositionToRowCol(Point pt)
    {
        double x = (double )(pt.x - OFFSETX)/ROWSIZE;
        double y = (double )(pt.y - OFFSETY)/COLSIZE;

        if((x<0) || (x>=9) || (y<0) || (y>=5))
        {
            return null;
        }

        return new Point((int )x, (int )y);
    }

    public void putTool() {

        mSeedCard[0] = new SeedCard(mContext, mRelativeLayout, new Point(1750, 20 + 0 * 200), R.drawable.card_sunflower, "SunFlower");

        mSeedCard[1] = new SeedCard(mContext, mRelativeLayout, new Point(1750, 20+1*200), R.drawable.card_peashooter, "Peashooter");

        mSeedCard[2] = new SeedCard(mContext, mRelativeLayout, new Point(1750, 20+2*200), R.drawable.card_repeater, "Repeater");

        mSeedCard[3] = new SeedCard(mContext, mRelativeLayout, new Point(1750, 20+3*200), R.drawable.card_wallnut, "NutWall");

        mSeedCard[4] = new SeedCard(mContext, mRelativeLayout, new Point(1750, 20+4*200), R.drawable.card_tallnut, "TallNut");



        for(int i=0; i<mLawnMover.length; i++)
        {
            mLawnMover[i] = new LawnMover(mContext, mRelativeLayout, i);
            mToolArrayList.add(mLawnMover[i]);

        }

        mSunShineCollector = new SunShineCollector(mContext, mRelativeLayout);
        mZombieKillCounter = new ZombieKillCounter(mContext, mRelativeLayout);

        new SunShine(mContext, mRelativeLayout, new Point(500, 500));
    }

    public void createPlantByCard(Point pt)
    {
        if(SeedCard.mSelectCardName.equals("null"))
        {
            Log.d("DebugTag", "No Select Card");
            return ;
        }


        pt = turnPositionToRowCol(pt);

        if(pt == null)
        {
            Log.d("DebugTag", "Touch Pt = null");
            return ;
        }

        if(SeedCard.mSelectCardName.equals("SunFlower"))
        {
            newSunFlower(pt.x, pt.y);
            SeedCard.mSelectCardName = "null";
            return ;
        }

        if(SeedCard.mSelectCardName.equals("Peashooter"))
        {
            newPeaShooter(pt.x, pt.y);
            SeedCard.mSelectCardName = "null";
            return ;
        }
        if(SeedCard.mSelectCardName.equals("Repeater"))
        {
            newPeaRepeater(pt.x, pt.y);
            SeedCard.mSelectCardName = "null";
            return ;
        }
        if(SeedCard.mSelectCardName.equals("NutWall"))
        {
            newNutWall(pt.x, pt.y);
            SeedCard.mSelectCardName = "null";
            return ;
        }
        if(SeedCard.mSelectCardName.equals("TallNut"))
        {
            newTallNut(pt.x, pt.y);
            SeedCard.mSelectCardName = "null";
            return ;
        }




    }

    public void zombieGenerator()
    {
        Random random = new Random();

        int difficulty = random.nextInt(100);
        int col = random.nextInt(5);

        mZombieCounter++;

        if(difficulty <= 60)
        {
            newEasyZombie(col);
            return;
        }

        if(difficulty <= 85)
        {
            newConeheadZombie(col);
            return;
        }

        if(difficulty >= 90)
        {
            newBucketheadZombie(col);
            return;
        }

    }

    public boolean checPlantkWin()
    {
        Zombie zombie;

        if(mZombieKillCounter.getNum() >= 100)
        {
            Log.d("DebugTag4", "Plant Win");
            for(int i=0; i<mZombeArrayList.size(); i++)
            {
                zombie = mZombeArrayList.get(i);
                zombie.setAction(Zombie.DEAD);
                mZombeArrayList.remove(zombie);
                mZombieCollumnList[zombie.getStartCol()].remove(zombie);

            }

            return true;
        }

        return false;


    }

    public boolean checkZombieWin()
    {

        Zombie zombie;

        for(int i=0; i<mZombeArrayList.size(); i++)
        {
            zombie = mZombeArrayList.get(i);


            if(zombie.getPosition().x < -20)
            {
                Log.d("DebugTag4", "Zombie Win");
                return true;
            }
        }

        return  false;

    }


    /**遍历所有对象，调整它们在RelativeLayout上的View对象的叠放顺序
     *
     * UI Thread中每次刷新View之前必须调用
     *
     */

    public void updateViewLayout()
    {
        Zombie zombie;

        for(int i=0; i<mZombieCollumnList.length; i++)
        {
            for(int j=0; j<mZombieCollumnList[i].size(); j++)
            {
                zombie = (Zombie) mZombieCollumnList[i].get(j);
                zombie.getGifViewObj().bringToFront();
            }
        }

        Plant plant;

        for(int i=0; i<mPlantCollumnList.length; i++)
        {
            for(int j=0; j<mPlantCollumnList[i].size(); j++)
            {
                plant = (Plant) mPlantCollumnList[i].get(j);
                plant.getGifViewObj().bringToFront();
            }
        }

        for(int i=0; i<mSeedCard.length; i++)
        {
            mSeedCard[i].getImageView().bringToFront();
        }

        mZombieKillCounter.bingToFront();
        mSunShineCollector.bingToFront();

        for(int i=0; i<mSunShineArrayList.size(); i++)
        {
            mSunShineArrayList.get(i).getGifViewObj().bringToFront();
        }

        if(mShowWinWindow)                  /**游戏结束时，出现胜利者，需要显示胜利的画面，这里将胜利的画面的对象置于顶层**/
        {
            if(mZombieWinImage == null)
                mPlantWinImage.bringToFront();
            else
                mZombieWinImage.bringToFront();

            mRestart.bringToFront();
            mExit.bringToFront();
        }

    }


    /** 生产者抛出阳光
     *
     */
    public void inputPlantTakeAction()
    {
        Bullet obj;
        for(int i=0; i<mInputPlantArrayList.size(); i++)
        {
            obj = mInputPlantArrayList.get(i).throwOneBullet();
            if(obj != null)
                mSunShineArrayList.add(obj);
        }
    }

    /** 随机下落自然生成的阳光
     *
     */

    public void sunShineGenerator()
    {

        Random random = new Random();
        int x = random.nextInt(9*ROWSIZE) + OFFSETX;
        int y = random.nextInt(5)*COLSIZE + (int)(OFFSETY*1.5);

        mSunShineArrayList.add(new SunShine(mContext, mRelativeLayout, new Point(x, y)));


    }



    public void plantWinWindow()
    {
        mShowWinWindow = true;

        mPlantWinImage = new ImageView(mContext);
        mPlantWinImage.setImageResource(R.drawable.trophy);
        RelativeLayout.LayoutParams winlp = new RelativeLayout.LayoutParams(600, 450);
        mPlantWinImage.setLayoutParams(winlp);
        mPlantWinImage.setX(600);
        mPlantWinImage.setY(400);
        mRelativeLayout.addView(mPlantWinImage);

        winWindowButtons();
    }

    public void zombieWinWindow()
    {
        mShowWinWindow = true;

        mZombieWinImage = new ImageView(mContext);
        mZombieWinImage.setImageResource(R.drawable.zombie_win);
        RelativeLayout.LayoutParams winlp = new RelativeLayout.LayoutParams(1600, 700);
        mZombieWinImage.setLayoutParams(winlp);
        mZombieWinImage.setX(200);
        mZombieWinImage.setY(200);
        mRelativeLayout.addView(mZombieWinImage);

        winWindowButtons();

    }

    public void winWindowButtons()
    {
        mRestart = new Button(mContext);
        mRestart.setWidth(50);
        mRestart.setX(630);
        mRestart.setY(850);
        mRestart.setText("重新开始");
        mRestart.setTextSize(11);
        mRestart.setTextColor(Color.WHITE);
        mRestart.setBackgroundColor(Color.BLACK);
        mRelativeLayout.addView(mRestart);

        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent intent = new Intent(mContext, MainActivity.class);
                MainActivity.mActivityManager.get(1).startActivity(intent);
                MainActivity.mActivityManager.get(1).finish();
                MainActivity.mActivityManager.remove(1);
*/
                resetGame();


            }
        });

        mExit = new Button(mContext);
        mExit.setWidth(50);
        mExit.setX(1030);
        mExit.setY(850);
        mExit.setText("退出游戏");
        mExit.setTextSize(11);
        mExit.setTextColor(Color.WHITE);
        mExit.setBackgroundColor(Color.BLACK);
        mRelativeLayout.addView(mExit);

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mActivityManager.finishAll();
            }
        });
    }



    public void resetGame()
    {
        mZombeArrayList.clear();

        for(int i=0; i<mZombieCollumnList.length; i++)
        {
            mZombieCollumnList[i].clear();
        }

        mZombeArrayList.clear();

        for(int i=0; i<mZombieCollumnList.length; i++)
        {
            mZombieCollumnList[i].clear();
        }

        mBulletArrayList.clear();
        mToolArrayList.clear();

        mPlantRowCol = new int[9][5];
        mInputPlantArrayList.clear();
        mSunShineArrayList.clear();

        //mLawnMover = new LawnMover[5];

        mZombieCounter = 0;

        mSunShineCollector.setNum(1000);
        mZombieKillCounter.setNum(0);

        mShowWinWindow = false;

        mZombieWinImage = null;
        mPlantWinImage = null;
        mRestart = null;
        mExit = null;

    }

}
