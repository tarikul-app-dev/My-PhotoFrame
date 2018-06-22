package com.tarikul.android.apps.my.myphotoframe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Empty on 11/5/2017.
 */

public class ImageAdapter extends BaseAdapter {
    Context mContext;
    Bitmap[] mImageArray ;
    //ArrayList<String> mListPath;

    ImageAdapter(Context context,Bitmap[] imgArray){
        this.mContext=context;
        mImageArray = imgArray;


    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageArray[position];
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        gridView =new View(mContext);

        gridView = inflater.inflate(R.layout.gridview_members, null);
        //final Bitmap path = mImageArray[position];
        ImageView stickerImage = (ImageView)gridView.findViewById(R.id.sticker_item);
        stickerImage.setImageBitmap(mImageArray[position]);
        stickerImage.setScaleType(ImageView.ScaleType.FIT_XY);
        stickerImage.setLayoutParams(new GridView.LayoutParams(300, 300));
        stickerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext,"Image Item Check",Toast.LENGTH_SHORT).show();
                // Sending image id to FullScreenActivity
                Intent i = new Intent(mContext, MultitouchActivity.class);
                // passing array index
                i.putExtra("id", position);
                mContext.startActivity(i);
            }
        });

        return gridView;
    }

}
