package com.tarikul.android.apps.my.myphotoframe;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class SecondActivity extends AppCompatActivity {
    Toolbar toolbar;


    String[] Frames = {"frm01.png","frm02.png","frm03.png","frm04.png","frm05.png","frm06.png","frm07.png","frm08.png","frm09.png",
            "frm10.png","frm11.png","frm12.png","frm13.png","frm14.png",
            "frm15.png","frm16.png","frm17.png","frm18.png","frm19.png","frm20.png","frm21.png","frm22.png","frm23.png","frm24.png","frm25.png"};
    private GridView gridview=null;
    static Bitmap[] mThumbIds;
    private ArrayList<File> fl = new ArrayList<File>() ;
    ImageAdapter imageAdapter = null;
    InterstitialAd interstitial;
    private AdView mAdView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar) findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        interstitial = new InterstitialAd(SecondActivity.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.add_unit_id));

        //Locate the Banner Ad in activity_main.xml
        AdView adView = (AdView) this.findViewById(R.id.banner_AdView);

        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder().build();

        // Add a test device to show Test Ads

        requestNewInterstitial();

        // Load ads into Banner Ads
        adView.loadAd(adRequest);

        // Load ads into Interstitial Ads
        interstitial.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }


        });


        gridview = (GridView)findViewById(R.id.gridviewframes);

        mThumbIds = new Bitmap[25];
        try
        {
            //these images are stored in the root of "assets"
            mThumbIds[0] =getBitmapFromAsset("frm01.png");
            mThumbIds[1] =getBitmapFromAsset("frm02.png");
            mThumbIds[2] =getBitmapFromAsset("frm03.png");
            mThumbIds[3] =getBitmapFromAsset("frm04.png");
            mThumbIds[4] =getBitmapFromAsset("frm05.png");
            mThumbIds[5] =getBitmapFromAsset("frm06.png");
            mThumbIds[6] =getBitmapFromAsset("frm07.png");
            mThumbIds[7] =getBitmapFromAsset("frm08.png");
            mThumbIds[8] =getBitmapFromAsset("frm09.png");
            mThumbIds[9] =getBitmapFromAsset("frm10.png");
            mThumbIds[10] =getBitmapFromAsset("frm11.png");
            mThumbIds[11] =getBitmapFromAsset("frm12.png");
            mThumbIds[12] =getBitmapFromAsset("frm13.png");
            mThumbIds[13] =getBitmapFromAsset("frm14.png");
            mThumbIds[14] =getBitmapFromAsset("frm15.png");
            mThumbIds[15] =getBitmapFromAsset("frm16.png");
            mThumbIds[16] =getBitmapFromAsset("frm17.png");
            mThumbIds[17] =getBitmapFromAsset("frm18.png");
            mThumbIds[18] =getBitmapFromAsset("frm19.png");
            mThumbIds[19] =getBitmapFromAsset("frm20.png");
            mThumbIds[20] =getBitmapFromAsset("frm21.png");
            mThumbIds[21] =getBitmapFromAsset("frm22.png");
            mThumbIds[22] =getBitmapFromAsset("frm23.png");
            mThumbIds[23] =getBitmapFromAsset("frm24.png");
            mThumbIds[24] =getBitmapFromAsset("frm25.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        gridview.setAdapter(new ImageAdapter(SecondActivity.this,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path="";
                if(fl!=null){
                    if(position<fl.size()){
                        path = fl.get(position).getPath();
                    }else{
                        path = "frame/" + Frames[position-fl.size()];
                    }
                }else{
                    path = "frame/" + Frames[position];
                }
                if(path != ""){
//                    Intent intent = new Intent(SecondActivity.this, FrameFitting.class);
//                    intent.putExtra("framepath", path) ;
//                    startActivity(intent);
//                    if(inter.isLoaded()){
//                        inter.show();
//                    }
                    //finish();
                }else{
                    Toast.makeText(SecondActivity.this, "try again", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        in = assetManager.open("frame/" + strName);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();

        return bitmap;

    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        interstitial.loadAd(adRequest);
    }
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
