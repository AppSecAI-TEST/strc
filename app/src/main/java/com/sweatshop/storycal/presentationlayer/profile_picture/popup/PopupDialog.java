package com.sweatshop.storycal.presentationlayer.profile_picture.popup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.edit.date_picker.main_date_picker.dialog.BottomSheetHelper;

/**
 * Created by arTeam on 03/08/2017.
 */

public class PopupDialog extends BaseDialog {
    private Listener listener;
    private BottomSheetHelper bottomSheetHelper;
    private Context context;

    @Nullable
    private String title;
    @Nullable
    private DisplayListener displayListener;

    private PopupDialog(Context context) {
        this(context, false);
    }

    private PopupDialog(Context context, boolean bottomSheet) {
        final int layout = R.layout.popup_list;
        this.context = context;
        this.bottomSheetHelper = new BottomSheetHelper(context, layout);

        this.bottomSheetHelper.setListener(new BottomSheetHelper.Listener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onLoaded(View view) {
                init(view);
            }

            @Override
            public void onClose() {
                PopupDialog.this.onClose();
            }
        });
    }

    private void init(View view) {

        final TextView popupHeader = (TextView) view.findViewById(R.id.popup_header);
        if (popupHeader != null) {
            popupHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okClicked = false;
                }
            });
            popupHeader.setEnabled(false);
            popupHeader.setClickable(false);
        }

        final TextView takePhotoBtn = (TextView) view.findViewById(R.id.take_photo_btn);
        if (takePhotoBtn != null) {
            takePhotoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okClicked = true;
                    context.startActivity(new Intent(context, PopupActivity.class));
                }
            });
        }

        final TextView libPhotoBtn = (TextView) view.findViewById(R.id.lib_photo_btn);
        if (libPhotoBtn != null) {
            libPhotoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okClicked = true;

                }
            });
        }

        final TextView webImportBtn = (TextView) view.findViewById(R.id.web_import_btn);
        if (webImportBtn != null) {
            webImportBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okClicked = true;
                }
            });
        }

        final TextView cancelBtn = (TextView) view.findViewById(R.id.cancel_btn);
        if (cancelBtn != null) {
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okClicked = true;
                    close();
                }
            });
        }
    }

    private void setDisplayListener(DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    public PopupDialog setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    @Override
    public void display() {
        super.display();
        bottomSheetHelper.display();
    }

    @Override
    public void close() {
        super.close();
        bottomSheetHelper.hide();
    }

    public interface Listener {
        void onActionSelected(String action);
    }

    public interface DisplayListener {
        void onDisplayed(String action);
    }

    public static class Builder {
        private final Context context;
        private PopupDialog dialog;

        @Nullable
        private Listener listener;
        @Nullable
        private DisplayListener displayListener;

        @Nullable
        private String title;

        private boolean bottomSheet;

        @ColorInt
        @Nullable
        private Integer backgroundColor = null;

        @ColorInt
        @Nullable
        private Integer mainColor = null;

        @ColorInt
        @Nullable
        private Integer titleTextColor = null;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        public Builder bottomSheet() {
            this.bottomSheet = true;
            return this;
        }

        public Builder listener(@Nullable Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder displayListener(@Nullable DisplayListener displayListener) {
            this.displayListener = displayListener;
            return this;
        }

        public Builder titleTextColor(@NonNull @ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder backgroundColor(@NonNull @ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder mainColor(@NonNull @ColorInt int mainColor) {
            this.mainColor = mainColor;
            return this;
        }

        public PopupDialog build() {
            final PopupDialog dialog = new PopupDialog(context, bottomSheet)
                    .setTitle(title);

            if (mainColor != null) {
                dialog.setMainColor(mainColor);
            }

            if (backgroundColor != null) {
                dialog.setBackgroundColor(backgroundColor);
            }

            if (titleTextColor != null) {
                dialog.setTitleTextColor(titleTextColor);
            }

            if (displayListener != null) {
                dialog.setDisplayListener(displayListener);
            }

            return dialog;
        }

        public void display() {
            dialog = build();
            dialog.display();
        }

        public void close() {
            if (dialog != null) {
                dialog.close();
            }
        }
    }
}
