package br.com.portaljc.jcconsultoria;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.portaljc.jcconsultoria.domains.RetornoUsuario;
import br.com.portaljc.jcconsultoria.utils.Constants;
import br.com.portaljc.jcconsultoria.utils.KeyStoreUtils;
import br.com.portaljc.jcconsultoria.utils.Preferences;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String login = Preferences.getString(getBaseContext(), Constants.USER_LOGIN);
                if (login.equals("")) {
                    carregarLoginActivity();
                } else {
                    byte[] encryption = Base64.decode(Preferences.getString(getBaseContext(), Constants.PASSWORD_LOGIN), Base64.DEFAULT);
                    byte[] iv = Base64.decode(Preferences.getString(getBaseContext(), Constants.PASSWORD_IV), Base64.DEFAULT);

                    String senha = "";

                    try {
                        KeyStoreUtils keyStoreUtils = new KeyStoreUtils(getBaseContext());
                        senha = keyStoreUtils.decryptData(Constants.KEYSTORE_ALIAS, encryption, iv);
                    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
                            IOException | InvalidKeyException | UnrecoverableEntryException |
                            NoSuchPaddingException | InvalidAlgorithmParameterException |
                            BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }

                    getLogin(login, senha);
                }
            }
        }, 2000);
    }

    private void carregarLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void getLogin(String email, String senha) {
        String url = Constants.URL_WEBAPI_USUARIO + "get/" + email + "/" + senha;
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
               carregarLoginActivity();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();

                    RetornoUsuario retorno = new Gson().fromJson(json, RetornoUsuario.class);
                    if (retorno != null) {
                        if (!retorno.isExecucao()) {
                            Preferences.setString(getBaseContext(), Constants.FRAGMENT_CURRENT, "S");
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            carregarLoginActivity();
                        }
                    } else {
                        carregarLoginActivity();
                    }
                }
            }
        });
    }
}
