package com.osamaelsh3rawy.bloodbank3.data.api;

import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.Posts;
import com.osamaelsh3rawy.bloodbank3.data.model.addRemFAV.AddRemFav;
import com.osamaelsh3rawy.bloodbank3.data.model.addRemFAV.AddRemFavData;
import com.osamaelsh3rawy.bloodbank3.data.model.catigoryPost.CatigoryPost;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponse;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponseData;
import com.osamaelsh3rawy.bloodbank3.data.model.donation.Donation;
import com.osamaelsh3rawy.bloodbank3.data.model.login.Login;
import com.osamaelsh3rawy.bloodbank3.data.model.myFevorits.MyFevorits;
import com.osamaelsh3rawy.bloodbank3.data.model.notification.Notification;
import com.osamaelsh3rawy.bloodbank3.data.model.notificationSetting.NotificationSetting;
import com.osamaelsh3rawy.bloodbank3.data.model.rePassward.RePassword;
import com.osamaelsh3rawy.bloodbank3.data.model.register.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServies {

    @GET("cities")
    Call<GeneralResponse> getcity(@Query("governorate_id=1") int governorate_id);

    @GET("governorates")
    Call<GeneralResponse> getGovernorates();

    @GET("blood-types")
    Call<GeneralResponse> getBloodType();


    @GET("posts")
    Call<Posts> getposts(@Query("api_token") String apiTaken,
                         @Query("page") int page);

    @GET("donation-requests")
    Call<Donation> getDonationReq(@Query("api_token") String apiTaken,
                                  @Query("page") int page);

    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("phone") String phone,
                      @Field("password") String pass);

    @POST("signup")
    @FormUrlEncoded
    Call<Register> register(@Field("name") String name,
                            @Field("email") String email,
                            @Field("birth_date") String birth_date,
                            @Field("city_id") int city_id,
                            @Field("phone") String phone,
                            @Field("donation_last_date") String donation_last_date,
                            @Field("password") String pass,
                            @Field("password_confirmation") String pass_confirmation,
                            @Field("blood_type_id") int blood_type_id);
    @POST("profile")
    @FormUrlEncoded
    Call<Register> editClientData(@Field("name") String name,
                                @Field("email") String email,
                                @Field("birth_date") String birth_date,
                                @Field("city_id") int cityId,
                                @Field("phone") String phone,
                                @Field("donation_last_date") String donationLastDate,
                                @Field("password") String password,
                                @Field("password_confirmation") String passwordConfirmation,
                                @Field("blood_type_id") int bloodTypeId,
                                @Field("api_token") String apiToken);


    @POST("reset-password")
    @FormUrlEncoded
    Call<RePassword> reset_password(@Field("phone") String phone);

    @GET("notifications")
    Call<Notification> getNotifications(@Query("api_token") String apiTaken,
                                        @Query("page") int page);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> getNotificationSetting(@Field("api_token") String apiTaken);


    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> setNotificationSetting(@Field("api_token") String apiTaken,
                                                     @Field("governorates[]") List<Integer> goverates,
                                                     @Field("blood_types[]") List<Integer> blood_type);


    @POST("new-password")
    @FormUrlEncoded
    Call<NotificationSetting> newPassword(@Field("password") String password,
                                          @Field("password_confirmation") String password_confirmation,
                                          @Field("pin_code") int pin_code,
                                          @Field("phone") String phone);

    @GET("my-favourites")
    Call<MyFevorits> getMyFevorites(@Query("api_token") String apiTaken);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<AddRemFav> addRemFevorites(@Field("post_id") int post_id,
                                    @Field("api_token") String api_Taken);

    @POST("donation-request")
    @FormUrlEncoded
    Call<Register> addDonation(@Field("api_token") String api_token,
                               @Field("patient_name") String patient_name,
                               @Field("patient_age") int patient_age,
                               @Field("blood_type_id") int blood_type_id,
                               @Field("bags_num") int bags_num,
                               @Field("hospital_name") String hospital_name,
                               @Field("hospital_address") String hospital_address,
                               @Field("city_id") int city_id,
                               @Field("phone") int phone,
                               @Field("notes") String notes,
                               @Field("latitude") float latitude,
                               @Field("longitude") float longitude);

    @GET("posts")
    Call<Posts> searchPosts(@Query("api_token") String apiTaken,
                            @Query("page") int page,
                            @Query("keyword") String keyword,
                            @Query("category_id") int category_id);

    @GET("posts")
    Call<Posts> PostsDetailes(@Query("api_token") String apiTaken,
                              @Query("post_id") int post_id,
                            @Query("page") int page);
    @GET("categories")
    Call<GeneralResponse> catigoryPosts();

    @GET("posts")
    Call<Donation> donationFilter(@Query("api_token") String apiTaken,
                            @Query("blood_type_id") int blood_type_id,
                            @Query("city_id") int city_id,
                            @Query("page") int page);


}