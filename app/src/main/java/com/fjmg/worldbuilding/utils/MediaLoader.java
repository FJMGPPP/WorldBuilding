package com.fjmg.worldbuilding.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fjmg.worldbuilding.R;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

import java.io.File;

public class MediaLoader implements AlbumLoader {

    @Override
    public void load(ImageView imageView, AlbumFile albumFile) {
        load(imageView, albumFile.getPath());
    }

    @Override
    public void load(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}