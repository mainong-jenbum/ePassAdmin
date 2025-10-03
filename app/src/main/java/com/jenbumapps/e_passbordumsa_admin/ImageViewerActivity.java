package com.jenbumapps.e_passbordumsa_admin;

import android.os.Bundle;
import android.util.Log;
import android.webkit.URLUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_viewer);

        PhotoView pv = findViewById(R.id.iv_image);

        String url = getIntent().getStringExtra("KEY_IMAGE_URL");
        pv.setZoomable(true);
        Log.d("ImageViewer", url);
        if(URLUtil.isValidUrl(url)) {
            Picasso.get().load(url).into(pv);
        }


    }
}
