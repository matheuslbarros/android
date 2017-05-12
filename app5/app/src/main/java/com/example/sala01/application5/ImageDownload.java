package com.example.sala01.application5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownload extends AsyncTask<ImageView, Void, Bitmap> {

    ImageView imageView = null;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        return downloadImage((String) imageView.getTag());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

    private Bitmap downloadImage(String uri) {
        Bitmap bitmap = null;
        try{
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            if (null != bitmap) {
                return bitmap;
            }
        } catch(Exception e) {

        }
        return bitmap;
    }
}