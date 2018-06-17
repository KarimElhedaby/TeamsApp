package com.teamapp.teamapp.community.ui;

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
import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.utils.Utilities;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCommunityActivity extends AppCompatActivity {
    @BindView(R.id.communityNameET)
    EditText communityNameET;
    @BindView(R.id.CommunityDescriptionET)
    EditText CommunityDescriptionET;
    //    @BindView(R.id.communityInviteET)
//    EditText communityInviteET;
    @BindView(R.id.communitySaveB)
    Button communitySaveB;
    Community community;
    String communityName, CommunityDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        ButterKnife.bind(this);
    }

    private void get_EnteredData() {
        communityName = communityNameET.getText().toString().trim();
        CommunityDescription = CommunityDescriptionET.getText().toString().trim();
    }

    @OnClick(R.id.communitySaveB)
    public void createCommunity() {
        get_EnteredData();
        if (communityName.isEmpty()) {
            communityNameET.setError(getString(R.string.enter_name));
        } else if (CommunityDescription.isEmpty()) {
            CommunityDescriptionET.setError(getString(R.string.enterdescription));

        } else {
            Utilities.showLoadingDialog(this, Color.GRAY);

            community = new Community(communityName, CommunityDescription, 1);

            AndroidNetworking.post("http://team-space.000webhostapp.com/index.php/api/community/add")
                    .addBodyParameter(community) // posting java object
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Utilities.dismissLoadingDialog();
                            Toast.makeText(CreateCommunityActivity.this,
                                    "data send succsefully",
                                    Toast.LENGTH_LONG).show();
                            Log.d("Data", community.toString());
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Toast.makeText(CreateCommunityActivity.this,
                                    "data not send ",
                                    Toast.LENGTH_LONG).show();
                            Log.d("Data", error.toString());
                            Utilities.dismissLoadingDialog();

                        }
                    });
//
//            final BaseResponceInterface apiservice = ApiClient.getClient().create(BaseResponceInterface.class);
//            Call<Community> callArray = apiservice.createCommunity(communityName,CommunityDescription,1);
//            callArray.enqueue(new Callback<Community>() {
//                @Override
//                public void onResponse(Call<Community> call, Response<Community> response) {
////                users = response.body() ;
//                    if (response.isSuccessful()) {
//                        Utilities.dismissLoadingDialog();
//                        Log.d("CommunityCreate", "Number of user received: " +
//                                response.body().toString());
//                        Toast.makeText(CreateCommunityActivity.this, response.body().toString(),
//                                Toast.LENGTH_LONG).show();
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<Community> call, Throwable t) {
//                    Log.d("CommunityCreate", "Error of user received: " +
//                            t.toString());
//                    Utilities.dismissLoadingDialog();
//
//                }
//            });
        }
    }
}
