package fi.jamk.imageviewerapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by K2049 on 18.9.2017.
 */

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {

    // this is done in UI thread, nothing this time
    @Override
    protected void onPreExecute() {
        // show loading progress bar
        progressBar.setVisibility(View.VISIBLE);
    }
    // this is background thread, load image and pass it to onPostExecute
    @Override
    protected Bitmap doInBackground(String... urls) {
        URL imageUrl;
        Bitmap bitmap = null;
        try {
            imageUrl = new URL(urls[0]);
            InputStream in = imageUrl.openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("<<LOADIMAGE>>", e.getMessage());
        }
        return bitmap;
    }

    // this is done in UI thread
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        textView.setText("Image " + (imageIndex + 1) + "/" + images.length);
        // hide loading progress bar
        progressBar.setVisibility(View.INVISIBLE);
    }
}
