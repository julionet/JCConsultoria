package br.com.portaljc.jcconsultoria;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.domains.PerguntasQuestionario;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;

public class RespostaActivity extends AppCompatActivity {

    TextView textViewQuestionario;
    TextView textViewTipoQuestionario;
    TextView textViewEmpresa;
    TextView textViewPergunta;

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    TextInputLayout textInputLayoutSubresposta;

    EditText editTextSubresposta;

    Button buttonFoto;

    ProgressDialog progressDialog;

    PerguntasQuestionario _pergunta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        textViewQuestionario = findViewById(R.id.text_view_questionario_resposta);
        textViewTipoQuestionario = findViewById(R.id.text_view_tipo_questionario_resposta);
        textViewEmpresa = findViewById(R.id.text_view_empresa_resposta);
        textViewPergunta = findViewById(R.id.text_view_resposta);

        linearLayout1 = findViewById(R.id.linear_layout_resposta1);
        linearLayout2 = findViewById(R.id.linear_layout_resposta2);
        linearLayout3 = findViewById(R.id.linear_layout_resposta3);

        textInputLayoutSubresposta = findViewById(R.id.text_input_layout_subresposta);

        editTextSubresposta = findViewById(R.id.edit_text_subresposta);

        buttonFoto = findViewById(R.id.button_fotos);
    }
}
