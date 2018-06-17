package com.teamapp.teamapp.group.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.group.model.Group;
import com.teamapp.teamapp.utils.Utilities;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGroup_Activity extends AppCompatActivity {
    @BindView(R.id.groupNameET)
    EditText groupNameET;
    @BindView(R.id.groupDescriptionET)
    EditText groupDescriptionET;
    @BindView(R.id.groupSaveB)
    Button groupSaveB;

    Group group;

    String groupName, groupDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_);
        ButterKnife.bind(this);
    }


    private void get_EnteredData() {
        groupName = groupNameET.getText().toString().trim();
        groupDescription = groupDescriptionET.getText().toString().trim();
    }

    @OnClick(R.id.groupSaveB)
    public void createCommunity() {
        get_EnteredData();
        if (groupName.isEmpty()) {
            groupNameET.setError(getString(R.string.enter_name));
        } else if (groupDescription.isEmpty()) {
            groupDescriptionET.setError(getString(R.string.enterdescription));

        } else {

            Utilities.showLoadingDialog(this, Color.GRAY);
            group = new Group(groupName, groupDescription, "0", 0, 1);

            AndroidNetworking.post("http://team-space.000webhostapp.com/index.php/api/groups/add")
                    .addBodyParameter(group) // posting java object
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Utilities.dismissLoadingDialog();
                            Toast.makeText(CreateGroup_Activity.this,
                                    "data send succsefully",
                                    Toast.LENGTH_LONG).show();
                            Log.d("Data", group.toString());
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error

                            Log.d("Data", error.toString());
                            Log.d("Data", group.toString());
                            Utilities.dismissLoadingDialog();

                        }
                    });


        }
    }
}
