package com.example.haider.tictactoe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Haider on 3/28/2017.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    public Integer[] mThumbIds = {
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray
    };

    public Integer[] newThumbIds = {
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray, R.drawable.emptygray,
            R.drawable.emptygray
    };
    public GridAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(180, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(4, 4, 4, 4);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


}
