package com.example.haider.tictactoe;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignIn_Activity extends AppCompatActivity {
    Button anonymousbtn;
    CallbackManager callbackManager;
    LoginButton loginButton;
    ProfileTracker profileTracker;
    String firstname="",lastname="",middlename="",full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(AccessToken.getCurrentAccessToken() != null){
//
//            Intent intt = new Intent(SignIn_Activity.this,MainActivity.class);
//            startActivity(intt);
//        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_in_);


        disconnectFromFacebook();
        anonymousbtn = (Button) findViewById(R.id.anaonymous);


        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {


                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
//                                    email_id=object.getString("email");
//                                    gender=object.getString("gender");
                                    full_name = object.getString("name");
                                    long fb_id = object.getLong("id"); //use this for logout

                                    Toast.makeText(SignIn_Activity.this, "Logged in As : " + full_name, Toast.LENGTH_SHORT).show();

                                    Intent inte = new Intent(SignIn_Activity.this, LevelSelection.class);
                                    inte.putExtra("fbName",full_name);
                                    startActivity(inte);
//                                    //Start new activity or use this info in your project.
//                                    Intent i=new Intent(FacebookLogin.this, LoginSuccess.class);
//                                    i.putExtra("type","facebook");
//                                    i.putExtra("facebook_id",facebook_id);
//                                    i.putExtra("f_name",f_name);
//                                    i.putExtra("m_name",m_name);
//                                    i.putExtra("l_name",l_name);
//                                    i.putExtra("full_name",full_name);
//                                    i.putExtra("profile_image",profile_image);
//                                    i.putExtra("email_id",email_id);
//                                    i.putExtra("gender",gender);
//
//                                    progress.dismiss();
//                                    startActivity(i);
//                                    finish();
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    //  e.printStackTrace();
                                }

                            }

                        });

                request.executeAsync();

//                Intent inte = new Intent(SignIn_Activity.this, LevelSelection.class);
//                inte.putExtra("fbName",full_name);
//                startActivity(inte);
            }






            @Override
            public void onCancel() {
                Intent inte = new Intent(SignIn_Activity.this, LevelSelection.class);
                startActivity(inte);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignIn_Activity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

        });




        anonymousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(SignIn_Activity.this,GameType.class);
                inte.putExtra("fbName","Anonymous");
                startActivity(inte);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }



//    private void setProfileToView(JSONObject jsonObject) {
//        try {
//            email = jsonObject.getString("email");
//            gender = jsonObject.getString("gender");
//            profilename = jsonObject.getString("name");
//
////            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
////            profilePictureView.setProfileId(jsonObject.getString("id"));
////            infoLayout.setVisibility(View.VISIBLE);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


}
