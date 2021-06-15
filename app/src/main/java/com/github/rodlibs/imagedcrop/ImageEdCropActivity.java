package com.github.rodlibs.imagedcrop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.rodlibs.imagedcrop.util.Constants;
import com.github.rodlibs.imagedcrop.util.ZoomableImageView;
import java.io.ByteArrayOutputStream;

public class ImageEdCropActivity extends AppCompatActivity {

    private ZoomableImageView imgZoom;
    private TextView txtScale;
    private TextView txtRotate;
    private LinearLayout linearColor;
    private int grau;
    private int scale = 300;
    private Bitmap bit;
    private int valueColor;
    private AlertDialog alert;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edcrop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#61C924")));
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#008000"));
        }


        initComponents();
        byte[] byteArray = getIntent().getByteArrayExtra(Constants.IMAGE_BITMAP);
        valueColor = getIntent().getIntExtra(Constants.COLOR_FUND, Color.BLACK);
        boolean editColorFund = getIntent().getBooleanExtra(Constants.EDIT_COLOR_FUND,false);




        bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgZoom.setImageBitmap(bit);
        imgZoom.setBackgroundColor(valueColor);

        if (editColorFund){
            linearColor.setVisibility(View.VISIBLE);
        }
    }




    private void initComponents(){
        imgZoom = (ZoomableImageView)findViewById(R.id.img_crop);
        txtScale = (TextView) findViewById(R.id.txtScale);
        txtRotate = (TextView) findViewById(R.id.txtRotate);
        linearColor = (LinearLayout)findViewById(R.id.linearColor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        else if (item.getItemId() == R.id.CHECKK){
            cropImage();
        }
        return super.onOptionsItemSelected(item);
    }




    private void cropImage(){
        imgZoom.destroyDrawingCache();
        imgZoom.buildDrawingCache (true);
        Bitmap bitmap = imgZoom.getDrawingCache (true);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent it = new Intent();
        it.putExtra(Constants.RETURN_IMAGE_BITMAP, byteArray);
        setResult(RESULT_OK,it);

        finish();
    }



    public void buttonScaleView(View v){
        int height = 0;
        switch (scale){
            case 100:
                scale = scale + 100;
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale, getResources().getDisplayMetrics());
                txtScale.setText("Small");
                break;

            case 200:
                scale = scale + 100;
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale, getResources().getDisplayMetrics());
                txtScale.setText("Medium");
                break;

            case 300:
                scale = scale + 100;
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale, getResources().getDisplayMetrics());
                txtScale.setText("Big");
                break;

            case 400:
                scale = 100;
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scale, getResources().getDisplayMetrics());
                txtScale.setText("Tiny");
                break;

            default:
                height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
                txtScale.setText("Medium");
                break;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(height,height);
        imgZoom.setLayoutParams(params);
    }



    public void buttonRotate(View v){
        if (grau == 360){
            grau = 0;
        }else {
            grau = grau + 90;
            txtRotate.setText((grau == 360) ? "0" : ""+grau);
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(grau);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bit,bit.getWidth(),bit.getHeight(),true);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        imgZoom.setImageBitmap(rotatedBitmap);
    }



    public void buttonColoFund(View v){
        colorPicker();
    }



    private void colorPicker() {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.color_picker, null);

        ((ImageView)view.findViewById(R.id.imgColor1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#ffffff"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#000000"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#0000FF"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#6495ED"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#008000"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#7CFC00"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#FFFF00"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#FFA500"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#FF0000"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor11)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#8B4513"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor13)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#800080"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor14)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#BA55D3"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor16)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#FF1493"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor17)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#F08080"));
                alert.dismiss();
            }
        });
        ((ImageView)view.findViewById(R.id.imgColor18)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgZoom.setBackgroundColor(Color.parseColor("#808080"));
                alert.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Color");
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(view);
        alert = builder.create();
        alert.show();
    }
}