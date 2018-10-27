package com.example.kowansky.buildbody;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextInputEditText email;
    private TextInputEditText password;

    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private Button loginButton;
    private Button regButton;

    OnLoginListener loginListener;

    public interface OnLoginListener{
        public void registerPerformed();
        public void loginPerformed();
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        regButton = view.findViewById(R.id.registrationButton);
        email = view.findViewById(R.id.emailLoginTextInputEditText);
        password = view.findViewById(R.id.passwordLoginTextInputEditText);
        emailTextInputLayout = view.findViewById(R.id.emailLoginTextInputLayout);
        passwordTextInputLayout = view.findViewById(R.id.passwordLoginTextInputLayout);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginValidator()){
                    performUserLogin();
                }
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginListener.registerPerformed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginListener = (OnLoginListener) activity;
    }

    public void performUserLogin(){
        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();

        LoginUserDto loginUserDto = new LoginUserDto(emailLogin, passwordLogin);

        Call<LoginData> call = LoginActivity.apiInterface.performUserLogin(loginUserDto);

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if(response.isSuccessful()){
                    LoginData loginData = response.body();
                    PrefConfig prefConfig = new PrefConfig(getContext());
                    prefConfig.writeAccesToken(loginData.Token);
                    loginListener.loginPerformed();
                }
                else if(response.code()==401){
                    ValidationError validationError = ErrorUtil.parseError(response);
                    if(validationError.getAttributeName().equals("emailEmpty")){
                        emailTextInputLayout.setError(validationError.getError());
                    }
                    else if(validationError.getAttributeName().equals("emailPassword")){
                        passwordTextInputLayout.setError(validationError.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean loginValidator(){
        emailTextInputLayout.setError("");
        passwordTextInputLayout.setError("");

        boolean succes = true;

        if(email.getText().toString().equals("")){
            emailTextInputLayout.setError(getString(R.string.emailEmpty));
            succes = false;
        }
        else if(!isEmailValid(email.getText().toString())){
            emailTextInputLayout.setError(getString(R.string.emailFault));
            succes = false;
        }

        if(password.getText().toString().equals("")) {
            passwordTextInputLayout.setError(getString(R.string.passwordEmpty));
            succes = false;
        }
        return succes;
    }
}
