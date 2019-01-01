package br.com.portaljc.jcconsultoria;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.portaljc.jcconsultoria.controllers.LoginController;
import br.com.portaljc.jcconsultoria.domains.RetornoUsuario;
import br.com.portaljc.jcconsultoria.domains.Usuario;
import br.com.portaljc.jcconsultoria.utils.Constants;
import br.com.portaljc.jcconsultoria.utils.KeyStoreUtils;
import br.com.portaljc.jcconsultoria.utils.KeyboardUtils;
import br.com.portaljc.jcconsultoria.utils.MessageBox;
import br.com.portaljc.jcconsultoria.utils.NetworkUtils;
import br.com.portaljc.jcconsultoria.utils.Preferences;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextSenha;
    Button buttonAcessar;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        editTextEmail = findViewById(R.id.edit_text_email_login);

        editTextSenha = findViewById(R.id.edit_text_senha_login);
        editTextSenha.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    return validarAcesso();
                }
                return false;
            }
        });

        buttonAcessar = findViewById(R.id.button_login);
        buttonAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAcesso();
            }
        });
    }

    private boolean validarAcesso() {
        KeyboardUtils.hide(LoginActivity.this.getCurrentFocus(), getBaseContext());

        if (NetworkUtils.isNetworkAvailable(getBaseContext())) {
            String mensagem = LoginController.validarLogin(editTextEmail.getText().toString(), editTextSenha.getText().toString());
            if (mensagem.equals("")) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.show(getResources().getString(R.string.carregando_informacoes));
                getLogin(editTextEmail.getText().toString(), editTextSenha.getText().toString());
                return true;
            } else {
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            exibirMensagem(getResources().getString(R.string.dispositivo_sem_internet));
            return false;
        }
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
                if (progressDialog != null)
                    progressDialog.hide();
                exibirMensagem(getResources().getString(R.string.dispositivo_sem_internet));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (progressDialog != null)
                    progressDialog.hide();

                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();

                    RetornoUsuario retorno = new Gson().fromJson(json, RetornoUsuario.class);
                    if (retorno != null) {
                        if (!retorno.isExecucao()) {
                            salvarAcesso(editTextEmail.getText().toString(), editTextSenha.getText().toString(),
                                    null);
                        } else {
                            exibirMensagem(retorno.getMensagemRetorno());
                        }
                    } else {
                        exibirMensagem("Falha ao tentar obter dados do usuário!");
                    }
                }
            }
        });
    }

    private void carregarMainActivity() {
        Preferences.setString(getBaseContext(), Constants.FRAGMENT_CURRENT, "S");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void salvarAcesso(String email, String senha, Usuario usuario) {
        try {
            KeyStoreUtils keyStoreUtils = new KeyStoreUtils(getBaseContext());
            keyStoreUtils.encryptText(Constants.KEYSTORE_ALIAS, senha);

            byte[] password = keyStoreUtils.getEncryption();
            byte[] iv = keyStoreUtils.getIv();

            String password64 = Base64.encodeToString(password, Base64.DEFAULT);
            String iv64 = Base64.encodeToString(iv, Base64.DEFAULT);

            Preferences.setString(getBaseContext(), Constants.USER_LOGIN, email);
            Preferences.setString(getBaseContext(), Constants.PASSWORD_LOGIN, password64);
            Preferences.setString(getBaseContext(), Constants.PASSWORD_IV, iv64);

            GlobalSingleton.get_instance().setUsuarioLogado(usuario);

            carregarMainActivity();
        } catch ( NoSuchAlgorithmException | NoSuchProviderException | IOException |
                NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException |
                IllegalBlockSizeException | BadPaddingException | CertificateException |
                KeyStoreException e) {
            exibirMensagem("Falha ao tentar salvar informações de acesso do usuário, tente novamente!");
        }
    }

    private void exibirMensagem(final String mensagem) {
        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageBox.warning(LoginActivity.this, "Atenção", mensagem);
            }
        });
    }
}
