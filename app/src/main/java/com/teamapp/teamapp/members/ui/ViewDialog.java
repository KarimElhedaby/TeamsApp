package com.teamapp.teamapp.members.ui;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamapp.teamapp.R;

/**
 * Created by karim on 5/1/18.
 */

public class ViewDialog {
    static EditText nameET;
    static Button dialogButton;
    static String name;
    static Dialog dialog;

    public void showDialog(Activity activity, String msg) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("Enter");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        nameET = dialog.findViewById(R.id.nameET);
        TextView text = dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialog.show();
    }
}