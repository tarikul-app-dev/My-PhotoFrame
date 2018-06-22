package com.tarikul.android.apps.my.myphotoframe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;

public class ShareImageActivity extends AppCompatActivity {
    ImageView shareImageView;
    Button shareImage,startFirstPage ;
    Toolbar toolbar;
    InterstitialAd interstitial;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar_share_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        shareImageView = (ImageView)findViewById(R.id.share_image_view);
        shareImage = (Button)findViewById(R.id.btn_share_pic) ;
        startFirstPage = (Button) findViewById(R.id.btn_first_page);

        interstitial = new InterstitialAd(ShareImageActivity.this);
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



        Intent intent = getIntent();
        final String imageFilePath = intent.getStringExtra("imagePath");
        if(imageFilePath!=null){
            retrieve( imageFilePath);
        }


        //share image
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imageFilePath))); // Set image uri
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share image to.."));
            }
        });

        //Start first page

        startFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent firstPage = new Intent(ShareImageActivity.this,MainActivity.class);
                startActivity(firstPage);
            }
        });

    }

    public void retrieve(String path)
    {
        File imageFile = new File(path);

        if(imageFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(path);

            shareImageView.setImageBitmap(myBitmap);
            Toast.makeText(ShareImageActivity.this, myBitmap.toString(), Toast.LENGTH_LONG).show();

        }
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
