package com.sweatshop.storycal.presentationlayer.face_detection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.sweatshop.storycal.R;

public class FaceDetectionActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.faceimage);
        btnProgress = (Button)findViewById(R.id.faceDetection_btn);

        final Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.f6);
        imageView.setImageBitmap(myBitmap);
        final Paint rectPaint = new Paint();
        rectPaint.setStrokeWidth(15);
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.STROKE);

        final Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(myBitmap,0,0,null);

        btnProgress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setMode(FaceDetector.ACCURATE_MODE)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(FaceDetectionActivity.this,"Face Detector could not be set up on your device", Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face> sparseArray = faceDetector.detect(frame);

                for(int i=0;i< sparseArray.size(); i++){
                    Face face = sparseArray.valueAt(i);
                    float x1=face.getPosition().x;
                    float y1 = face.getPosition().y;
                    float x2 = x1+face.getWidth();
                    float y2=y1+face.getWidth();
                    RectF rectf = new RectF(x1,y1,x2,y2);
                    canvas.drawRoundRect(rectf,2,2,rectPaint);
                }
                imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

            }
        });


    }


    @Override
    public void onBackPressed() {
        this.finish();
    }


}
