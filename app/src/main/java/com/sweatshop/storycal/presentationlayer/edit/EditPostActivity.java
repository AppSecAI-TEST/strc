package com.sweatshop.storycal.presentationlayer.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.edit.date_picker.main_date_picker.SingleDateAndTimePicker;
import com.sweatshop.storycal.presentationlayer.edit.date_picker.main_date_picker.dialog.SingleDateAndTimePickerDialog;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPostActivity extends AppCompatActivity {

    @BindView(R.id.singleText)
    TextView singleText;

    SimpleDateFormat simpleDateFormat;
    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateOnlyFormat;
    ArrayList<String> paths;
    SingleDateAndTimePickerDialog.Builder singleBuilder;
    ArrayList<Bitmap> selectedPictures;
    ImageView editedPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        setUpActionBar();
        ButterKnife.bind(this);

        this.simpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());
        this.simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
        this.simpleDateOnlyFormat = new SimpleDateFormat("EEE d MMM", Locale.getDefault());

        selectedPictures = (ArrayList<Bitmap>) getIntent().getSerializableExtra("selectedPictures");
        paths = (ArrayList<String>) getIntent().getSerializableExtra("paths");
        Toast.makeText(this, paths.get(2), Toast.LENGTH_SHORT).show();
        setPicture();
    }

    private void setPicture() {
        editedPic = (ImageView) findViewById(R.id.edit_post_pick);
        Bitmap bitmap = selectedPictures.get(0);
        editedPic.setImageBitmap(bitmap);
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_signup);
        View view =getSupportActionBar().getCustomView();

        TextView leftActionBtn= (TextView)view.findViewById(R.id.action_left_signup);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        TextView rightActionBtn= (TextView)view.findViewById(R.id.action_right_signup);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishAlbum();
            }
        });
    }


    public void publishAlbum() {
        Intent intent = new Intent(EditPostActivity.this, MainActivity.class);
        intent.putExtra("album", selectedPictures);
        startActivity(intent);
    }

    public void cancel() { }

    @Override
    protected void onPause() {
        super.onPause();
        if (singleBuilder != null)
            singleBuilder.close();
    }

    @OnClick(R.id.singleLayout)
    public void simpleClicked() {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 2017);
        final Date minDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 5);
        final Date maxDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 2);
        final Date defaultDate = calendar.getTime();

        singleBuilder = new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
//              .displayHours(false)
//              .displayMinutes(false)
                .defaultDate(defaultDate)
                .minDateRange(minDate)
                .maxDateRange(maxDate)

                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })
                .title("Simple")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        singleText.setText(simpleDateFormat.format(date));
                    }
                });
        singleBuilder.display();
    }

}
