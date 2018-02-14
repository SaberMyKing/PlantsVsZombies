package localpc.plantsvszombies;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Xinlei on 2017/5/16.
 */

public class ActivityManager {
    private ArrayList<AppCompatActivity> mActivityArray = new ArrayList<AppCompatActivity>();

    public ActivityManager()
    {

    }

    public void add(AppCompatActivity activity)
    {
        mActivityArray.add(activity);
    }
    public AppCompatActivity get(int i)
    {
        return mActivityArray.get(i);
    }
    public void remove(int index)
    {
        mActivityArray.remove(index);
    }

    public void remove(AppCompatActivity activity)
    {
        mActivityArray.remove(activity);
    }

    public void finishAll()
    {
        for(AppCompatActivity activity : mActivityArray)
        {
            activity.finish();
        }
    }




}
