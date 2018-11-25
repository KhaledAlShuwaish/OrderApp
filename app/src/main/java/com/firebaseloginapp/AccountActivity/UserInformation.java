package com.firebaseloginapp.AccountActivity;

import android.widget.EditText;

public class UserInformation {

    private String UserName;
    private String UserPhoneN;



    public UserInformation(){

    }
    public UserInformation(String userName, String userPhoneN, EditText branch1, EditText branch2, EditText branch3, EditText branch4, EditText branch5, EditText branch6){

    }

    public UserInformation(String userName, String userPhoneN) {
        UserName = userName;
        UserPhoneN = userPhoneN;

    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhoneN() {
        return UserPhoneN;
    }

    public void setUserPhoneN(String userPhoneN) {
        UserPhoneN = userPhoneN;
    }


}
