package com.sweatshop.storycal.presentationlayer.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
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
    String month;

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
        EditText title = (EditText)findViewById(R.id.editText_title);
        EditText datePicker = (EditText)findViewById(R.id.singleText);
        EditText postMessage = (EditText)findViewById(R.id.editText_post_message);
        String titleEdit = title.getText().toString();
        String datePickerEdit = datePicker.getText().toString();
        String postMessageEdit = postMessage.getText().toString();
        Toast.makeText(this, titleEdit + "\n" + datePickerEdit + "\n" + postMessageEdit, Toast.LENGTH_SHORT).show();
        if (titleEdit.isEmpty() || datePickerEdit.isEmpty() || postMessageEdit.isEmpty()) {
            Toast.makeText(this, "Fill all Fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(EditPostActivity.this, MainActivity.class);
        intent.putExtra("album", selectedPictures);
        LocalStoreService localStoreService = new LocalStoreService();
        User user = localStoreService.getUserFromLocalStore();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.addPhotoToAlbum(user.getId(), month, "2017", paths.get(0));
        month = "";
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
        calendar.set(Calendar.YEAR, 2017);

        singleBuilder = new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })
                .title("Simple")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        switch (date.getMonth()) {
                            case 0: month = "January"; break;
                            case 1: month = "February"; break;
                            case 2: month = "March"; break;
                            case 3: month = "April"; break;
                            case 4: month = "May"; break;
                            case 5: month = "June";break;
                            case 6: month = "July"; break;
                            case 7: month = "August"; break;
                            case 8: month = "September"; break;
                            case 9: month = "October"; break;
                            case 10: month = "November"; break;
                            case 11: month = "December"; break;
                        }
                        singleText.setText(simpleDateFormat.format(date));
                    }
                });
        singleBuilder.display();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}
