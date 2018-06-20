package com.github.rodlibs.imagedcrop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.github.rodlibs.imagedcrop.util.Constants;
import java.io.ByteArrayOutputStream;

/**
 * Created by rodLibs on 26/12/17.
 */

public class ImageEdCrop {

    private Intent mCropIntent;

    private ImageEdCrop(Bitmap bit) {
        mCropIntent = new Intent();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        mCropIntent.putExtra(Constants.IMAGE_BITMAP, byteArray);
    }


    public static ImageEdCrop targetBitmap(Bitmap bit) {
        double originalHeight = bit.getHeight();
        double scale = originalHeight / 600;
        int wi = (int) (bit.getWidth() / scale);
        int hey = (int) (bit.getHeight() / scale);
        Bitmap b = Bitmap.createScaledBitmap(bit, wi, hey, true);
        return new ImageEdCrop(b);
    }


    public ImageEdCrop photoBackgroundColor(int color) {
        mCropIntent.putExtra(Constants.COLOR_FUND, color);
        return this;
    }


    public ImageEdCrop changeBackgroundColor(boolean flag) {
        mCropIntent.putExtra(Constants.EDIT_COLOR_FUND,flag);
        return this;
    }



    public void start(Activity activity) {
        mCropIntent.setClass(activity, ImageEdCropActivity.class);
        activity.startActivityForResult(mCropIntent, Constants.REQUEST_IMAGE);
    }
}
