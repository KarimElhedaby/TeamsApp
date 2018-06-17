package com.teamapp.teamapp.community.ui;

import com.teamapp.teamapp.community.model.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by karim pc on 1/24/2018.
 */

public interface BaseResponceInterface {

    @POST("community/add")
    @FormUrlEncoded
    Call<Community> createCommunity(@Field("Community_name") String Community_name,
                                    @Field("Community_description") String Community_description,
                                    @Field("Users_User_id") int Users_User_id);
}



