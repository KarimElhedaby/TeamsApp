package com.teamapp.teamapp.profile.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.policy.TimeWindow;
import com.cloudinary.android.policy.UploadPolicy;
import com.cloudinary.utils.ObjectUtils;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.community.model.Community;
import com.teamapp.teamapp.profile.adapter.ProfileFillAdapter;
import com.teamapp.teamapp.profile.model.ProfileData;
import com.teamapp.teamapp.ui.MainActivity;
import com.teamapp.teamapp.utils.DatePickerFragment;
import com.teamapp.teamapp.utils.RecyclerItemTouchHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFillActivity
        extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    @BindView(R.id.profileF_IV)
    ImageView profileF_IV;
    @BindView(R.id.profileF_nameET)
    EditText profileF_name_ET;
    @BindView(R.id.profileF_addressET)
    EditText profileF_Address_ET;
    @BindView(R.id.profileF_phoneET)
    EditText profileF_phone_ET;
    @BindView(R.id.profileF_ReligiousET)
    EditText profileF_Religious_ET;
    @BindView(R.id.profileF_WorkET)
    EditText profileF_work_ET;
    @BindView(R.id.profileF_EducationET)
    EditText profileF_education_ET;

    @BindView(R.id.profileF_dateB)
    Button profileF_date_B;


//    @BindView(R.id.profile_interestsF_ET)
//    EditText profileF_interestes_ET;
//    @BindView(R.id.profile_add_interestsF_B)
//    Button profileF_addinterest_B;
//    @BindView(R.id.profileF_interests_RV)
//    RecyclerView profileF_interest_RV;

    @BindView(R.id.Profile_skillsF_ET)
    EditText profileF_skill_ET;
    @BindView(R.id.profile_add_skillsF_B)
    Button profileF_addskill_B;
    @BindView(R.id.profile_skillsF_RV)
    RecyclerView profileF_skill_RV;
//    @BindView(R.id.card)
//    CardView cardView;

    //    private ProfileFillAdapter interest_adapter;
    //    private List<ProfileData> data_interest_List;
    private ProfileFillAdapter skill_adapter;
    private List<ProfileData> data_skill_list;
    private ProfileData data;


    private String mCurrentPhotoPath;
    private File imageFile;
    private Uri selectedImageUri;
    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fill);
        Toolbar toolbar = findViewById(R.id.toolbar_profile_fill);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
//        data_interest_List = new ArrayList<>();
        data_skill_list = new ArrayList<>();
        MediaManager.init(this);
//        profileF_interest_RV.setLayoutManager
//                (new LinearLayoutManager(this,
//                        LinearLayoutManager.VERTICAL, false));

        profileF_skill_RV.setLayoutManager
                (new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));


//        interest_adapter = new ProfileFillAdapter(
//                data_interest_List, R.layout.profile_recycler_item, this);

        skill_adapter = new ProfileFillAdapter(
                data_skill_list, R.layout.profile_recycler_item, this);

//        profileF_interest_RV.setAdapter(interest_adapter);
        profileF_skill_RV.setAdapter(skill_adapter);
        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback2 =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback2).
                attachToRecyclerView(profileF_skill_RV);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                         int direction, int position) {
        if (viewHolder instanceof ProfileFillAdapter.VH) {


            final ProfileData deletedItem = data_skill_list.get(viewHolder.getAdapterPosition());

            // remove the item from recycler view
            data_skill_list.remove(deletedItem);
            skill_adapter.notifyItemRemoved(position);
            skill_adapter.notifyDataSetChanged();

        }

    }

    @OnClick(R.id.profile_add_skillsF_B)
    void addSkillData() {
        String skill_data = profileF_skill_ET.getText().toString();
        data = new ProfileData(skill_data);
        skill_adapter.addData(data);
        profileF_skill_ET.setText(" ");
    }

    File photoFile;
    Uri photoURI ;
    @OnClick(R.id.profileF_IV)
    void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose)
                .setCancelable(true)
                .setItems(new String[]{getString(R.string.gallery), getString(R.string.camera)},
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    //intent to open any media
                                    Intent i = new Intent(Intent.ACTION_PICK);
                                    i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(i, GALLERY_REQUEST);
                                } else {
                                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                        photoFile = null;
                                        try {
                                            photoFile = createImageFile();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        if (photoFile != null) {
                                             photoURI = FileProvider.getUriForFile(ProfileFillActivity.this,
                                                    "com.teamapp.teamapp.Fileprovider", photoFile);
                                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                                         }
                                    }
                                }
                            }
                        }).show();
    }
//load the display image by the image view dimential the full image in file
// but i need only the image size equal the view dimentia

    private void setPic() {
        // Get the dimensions of the View
        int targetW = profileF_IV.getWidth();
        int targetH = profileF_IV.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 1;
        if (targetH != 0 && targetW != 0
                && photoW != 0 && photoH != 0) {
            // Determine how much to scale down the image
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }
        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        profileF_IV.setImageBitmap(bitmap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Path", mCurrentPhotoPath);
        outState.putSerializable("File", imageFile);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPhotoPath = savedInstanceState.getString("Path");
        imageFile = (File) savedInstanceState.getSerializable("File");
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }
//
//    void SendImage (){
//        final Community community = new Community( imageFile );
//        AndroidNetworking.upload(" http://team-space.000webhostapp.com/index.php/api/community/add")
//                .addMultipartFile(String.valueOf(community.getCommunity_picture()),imageFile)
//                .setTag("uploadTest")
//                .setPriority(Priority.HIGH)
//                .build()
//                .setUploadProgressListener(new UploadProgressListener() {
//                    @Override
//                    public void onProgress(long bytesUploaded, long totalBytes) {
//                        // do anything with progress
//                    }
//                })
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        // do anything with response
//                        Log.d("postData", "true send");
////                            Toast.makeText(RegisterActivity.this, error.getMessage(),
////                                    Toast.LENGTH_LONG).show();
////
////                            Log.d("Data", user.toString());
//
//                        Toast.makeText(ProfileFillActivity.this, "data send true",
//                                Toast.LENGTH_LONG).show();
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Log.d("postData", error.getMessage());
////                            Toast.makeText(RegisterActivity.this, error.getMessage(),
////                                    Toast.LENGTH_LONG).show();
////
////                            Log.d("Data", user.toString());
//
//                        Toast.makeText(ProfileFillActivity.this, "data send Errorly",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });

//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            Map config = new HashMap();
            config.put("cloud_name", "dellx1eea");
            config.put("api_key", "218769539232644");
            config.put("api_secret", "aFZL72YMfJQe_YiFnY9kBF1ZNDU");
            Cloudinary cloudinary = new Cloudinary(config);
            try {
                Map uploadResult =  cloudinary.uploader().upload(photoFile.getAbsolutePath(),
                        ObjectUtils.emptyMap());

               String requestId = (String) uploadResult.get("url");

                Toast.makeText(getApplicationContext(),requestId, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                readFileFromSelectedURI();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            setPic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readFileFromSelectedURI();
        } else {
            Toast.makeText(this, R.string.cannot_read_image, Toast.LENGTH_SHORT).show();
        }
    }

    // continue select
    private void readFileFromSelectedURI() {
        Cursor cursor = getContentResolver().query(selectedImageUri, new String[]{MediaStore.Images.Media.DATA},
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String imagePath = cursor.getString(0);
            cursor.close();
            imageFile = new File(imagePath);
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            profileF_IV.setImageBitmap(image);
        }
    }

    @OnClick(R.id.profileF_dateB)
    public void addborn_Date() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        profileF_date_B.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
//            SendImage();
        }
        return super.onOptionsItemSelected(item);
    }

}

