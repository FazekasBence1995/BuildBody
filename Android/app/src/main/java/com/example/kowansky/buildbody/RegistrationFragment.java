package com.example.kowansky.buildbody;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText age;
    private TextInputEditText weight;
    private TextInputEditText height;

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private TextInputLayout ageTextInputLayout;
    private TextInputLayout weightTextInputLayout;
    private TextInputLayout heightTextInputLayout;

    private CheckBox male;
    private CheckBox female;
    private CheckBox low;
    private CheckBox medium;
    private CheckBox high;

    private Button apply;
    private Button cancel;

    OnRegistrationListener registrationListener;

    public interface OnRegistrationListener{
        public void applyPerformed();
        public void cancelPerformed();
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        name = view.findViewById(R.id.nameRegisterTextInputEditText);
        email = view.findViewById(R.id.emailRegisterTextInputEditText);
        password = view.findViewById(R.id.passwordRegisterTextInputEditText);
        age = view.findViewById(R.id.ageRegisterTextInputEditText);
        weight = view.findViewById(R.id.weightRegisterTextInputEditText);
        height = view.findViewById(R.id.heightRegisterTextInputEditText);

        nameTextInputLayout = view.findViewById(R.id.nameRegisterTextInputLayout);
        emailTextInputLayout = view.findViewById(R.id.emailRegisterTextInputLayout);
        passwordTextInputLayout = view.findViewById(R.id.passwordRegisterTextInputLayout);
        ageTextInputLayout = view.findViewById(R.id.ageRegisterTextInputLayout);
        weightTextInputLayout = view.findViewById(R.id.weightRegisterTextInputLayout);
        heightTextInputLayout = view.findViewById(R.id.heightRegisterTextInputLayout);

        male = view.findViewById(R.id.maleCheckbox);
        female = view.findViewById(R.id.femaleCheckbox);
        low = view.findViewById(R.id.lowCheckbox);
        medium = view.findViewById(R.id.mediumCheckbox);
        high = view.findViewById(R.id.highCheckbox);

        apply = view.findViewById(R.id.applyButton);
        cancel = view.findViewById(R.id.cancelButton);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    female.setChecked(false);
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    male.setChecked(false);
                }
            }
        });

        low.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    medium.setChecked(false);
                    high.setChecked(false);
                }
            }
        });

        medium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    low.setChecked(false);
                    high.setChecked(false);
                }
            }
        });

        high.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    medium.setChecked(false);
                    low.setChecked(false);
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registrationValidator()){
                    performRegistration();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationListener.cancelPerformed();
            }
        });
        return view;
    }

    public void performRegistration(){
        String nameHelper = name.getText().toString();
        String emailHelper = email.getText().toString();
        String passwordHelper = password.getText().toString();
        String ageHelper = age.getText().toString();
        String weightHelper = weight.getText().toString();
        String heightHelper = height.getText().toString();

        String genderHelper = "";
        String activityLevelHelper = "";

        if(male.isChecked()){
            genderHelper = male.getText().toString();
        }

        if(female.isChecked()){
            genderHelper = female.getText().toString();
        }

        if(low.isChecked()){
            activityLevelHelper = low.getText().toString();
        }

        if(medium.isChecked()){
            activityLevelHelper = medium.getText().toString();
        }

        if(high.isChecked()){
            activityLevelHelper = high.getText().toString();
        }

        User user = new User(nameHelper, emailHelper, passwordHelper, genderHelper, activityLevelHelper, ageHelper, weightHelper, heightHelper);

        Call<User> call = MainActivity.apiInterface.performRegistration(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    registrationListener.applyPerformed();
                }
                else if(response.code()==403){
                    ValidationError validationError = ErrorUtil.parseError(response);
                    if(validationError.getAttributeName().equals("email")){
                        emailTextInputLayout.setError(validationError.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //error json feldolgozás
                t.printStackTrace();
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        registrationListener = (OnRegistrationListener) activity;
    }

    public boolean checkInteger(String string){
        boolean isInteger;
        try {
            int num = Integer.parseInt(string);
            isInteger = true;
        } catch (NumberFormatException e) {
            isInteger = false;
        }
        return  isInteger;
    }

    public boolean registrationValidator(){
        nameTextInputLayout.setError("");
        emailTextInputLayout.setError("");
        passwordTextInputLayout.setError("");
        ageTextInputLayout.setError("");
        weightTextInputLayout.setError("");
        heightTextInputLayout.setError("");

        boolean succes = true;

        if(name.getText().toString().equals("")){
            nameTextInputLayout.setError("A név nem lehet üres!");
            succes = false;
        }

        if(email.getText().toString().equals("")){
            emailTextInputLayout.setError("Az email nem lehet üres!");
            succes = false;
        }
        else if(!isEmailValid(email.getText().toString())){
            emailTextInputLayout.setError("Nem jó email formátum!");
            succes = false;
        }

        if(password.getText().toString().equals("")) {
            passwordTextInputLayout.setError("A jelszó nem lehet üres!");
            succes = false;
        }

        if(age.getText().toString().equals("")){
            ageTextInputLayout.setError("Az életkor nem lehet üres!");
            succes = false;
        }
        else if(!checkInteger(age.getText().toString())){
            ageTextInputLayout.setError("Az életkort nem egész számban adta meg!");
            succes = false;
        }

        if(weight.getText().toString().equals("")){
            weightTextInputLayout.setError("A súly nem lehet üres!");
            succes = false;
        }
        else if(!checkInteger(weight.getText().toString())){
            weightTextInputLayout.setError("A súlyt nem egész számban adta meg!");
            succes = false;
        }

        if(height.getText().toString().equals("")){
            heightTextInputLayout.setError("A magasság nem lehet üres!");
            succes = false;
        }
        else if(!checkInteger(height.getText().toString())){
            heightTextInputLayout.setError("A masasságot nem egész számban adta meg!");
            succes = false;
        }
        return succes;
    }
}