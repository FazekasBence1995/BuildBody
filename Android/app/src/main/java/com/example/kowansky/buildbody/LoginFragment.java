package com.example.kowansky.buildbody;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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

    private Button regButton;
    OnLoginListener loginListener;

    private TextInputEditText email;
    private TextInputEditText password;
    private Button loginButton;

    public interface OnLoginListener{
        public void registerPerformed();
        public void loginPerformed(String email);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        regButton = view.findViewById(R.id.registrationButton);
        email = view.findViewById(R.id.emailLoginTextInputEditText);
        password = view.findViewById(R.id.passwordLoginTextInputEditText);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void loginPerformed(){
        String _email = email.getText().toString();
        String _password = password.getText().toString();

        LoginUserDto loginUserDto = new LoginUserDto();

        Call<LoginData> call = MainActivity.apiInterface.performUserLogin(loginUserDto);

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if(response.isSuccessful()){
                    LoginData loginData = response.body();
                    PrefConfig prefConfig = new PrefConfig(getContext());
                    prefConfig.writeAccesToken(loginData.Token);

                    //átmenni welcomefragment
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {

            }
        });
    }
}
