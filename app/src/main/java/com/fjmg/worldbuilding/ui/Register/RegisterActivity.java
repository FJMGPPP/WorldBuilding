package com.fjmg.worldbuilding.ui.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.databinding.ActivityRegisterBinding;
import com.fjmg.worldbuilding.ui.login.Event;
import com.fjmg.worldbuilding.ui.login.LoginActivity;
import com.fjmg.worldbuilding.utils.Utils;


public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    private ActivityRegisterBinding binding;
    private RegisterContract.Presenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter = new RegisterPresenter(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtEmail.addTextChangedListener(new RegisterTextWatcher(binding.txtEmail));
        binding.txtPassword.addTextChangedListener(new RegisterTextWatcher(binding.txtPassword));
        binding.txtUserName.addTextChangedListener(new RegisterTextWatcher(binding.txtUserName));
        binding.txtPasswordCheck.addTextChangedListener(new RegisterTextWatcher(binding.txtPasswordCheck));
        binding.btnRegister.setOnClickListener(view ->
        {
            User usuario = new User(binding.txtEmail.getText().toString(),
                                    binding.txtPassword.getText().toString(),
                                    binding.txtPasswordCheck.getText().toString(),
                                    binding.txtUserName.getText().toString()
            );
            registerPresenter.validarSignUp(usuario);
        });
    }

    @Override
    public void onEvent(Event event) {

    }

    @Override
    public void setEmailEmptyError() {
        binding.txtEmail.setError(getString(R.string.errorEmailEmpty));
    }

    @Override
    public void setEmailError() {
        binding.txtEmail.setError(getString(R.string.errorEmailInvalid));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.txtPassword.setError(getString(R.string.errorPasswordInvalid));
    }

    @Override
    public void setMessageDontMatch() {
        binding.txtPasswordCheck.setError(getString(R.string.errorPasswordNoCoindice));
    }

    @Override
    public void setUsernameEmptyError() {
        binding.txtUserName.setError(getString(R.string.errorUsuarioEmpty));
    }

    @Override
    public void setUsernameError()
    {
        binding.txtUserName.setError(getString(R.string.errorUsuarioInvalido));
    }


    @Override
    public void onSuccess(String msg) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFailure(String msg) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    class RegisterTextWatcher implements TextWatcher
    {
        View view;
        public RegisterTextWatcher(View view)
        {
            super();
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId())
            {
                case R.id.txtEmail:
                    validateEmail(((EditText)view).getText().toString());
                    break;
                case R.id.txtPassword:
                    validatePassword(((EditText)view).getText().toString());
                    break;
                case R.id.txtUserName:
                    validateUsername(((EditText)view).getText().toString());
                    break;
                case R.id.txtPasswordCheck:
                    validatePasswordMatch(((EditText)view).getText().toString());
                    break;
            }
        }
        void validateEmail(String email)
        {
            if (email.trim().isEmpty())
            {
                binding.txtEmail.setError(getString(R.string.errorEmailEmpty));
                return;
            }
            if (!Utils.isEmailValid(email))
            {
                binding.txtEmail.setError(getString(R.string.errorEmailInvalid));
                return;
            }
            binding.txtEmail.setError(null);
        }
        void validatePassword(String password)
        {
            if (password.trim().isEmpty())
            {
                binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
                return;
            }
            if (!Utils.isPasswordValid(password))
            {
                binding.txtPassword.setError(getString(R.string.errorPasswordInvalid));
                return;
            }
            binding.txtEmail.setError(null);
        }
        void validatePasswordMatch(String password)
        {
            if (password.trim().isEmpty())
            {
                binding.txtPasswordCheck.setError(getString(R.string.errorPasswordEmpty));
                return;
            }
            if (!binding.txtPasswordCheck.getText().toString().equals(binding.txtPassword.getText().toString()))
            {
                binding.txtPasswordCheck.setError(getString(R.string.errorPasswordNoCoindice));
                return;
            }
            binding.txtEmail.setError(null);
        }
        void validateUsername(String username)
        {
            if (username.trim().isEmpty())
            {
                binding.txtUserName.setError(getString(R.string.errorUsuarioEmpty));
                return;
            }
            if (!Utils.isUsernameValid(username))
            {
                binding.txtUserName.setError(getString(R.string.errorUsuarioInvalido));
                return;
            }
            binding.txtUserName.setError(null);
        }
    }

}
