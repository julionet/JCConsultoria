package br.com.portaljc.jcconsultoria;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.portaljc.jcconsultoria.adapters.PerguntaAdapter;
import br.com.portaljc.jcconsultoria.domains.PerguntasQuestionario;
import br.com.portaljc.jcconsultoria.domains.Questionario;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;

public class PerguntaActivity extends AppCompatActivity {

    TextView textViewQuestionario;
    TextView textViewTipoQuestionario;
    TextView textViewEmpresa;
    TextView textViewSituacao;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;

    PerguntasQuestionario[] _perguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Questionario questionario = (Questionario) getIntent().getSerializableExtra("questionario");

        textViewQuestionario = findViewById(R.id.text_view_questionario_pergunta);
        textViewQuestionario.setText(questionario.getNomeQuestionario());

        textViewTipoQuestionario = findViewById(R.id.text_view_tipo_questionario_pergunta);
        textViewTipoQuestionario.setText(questionario.getTipoQuestionario());

        textViewEmpresa = findViewById(R.id.text_view_empresa_pergunta);
        //textViewEmpresa.setText(questionario.getEmpresa());

        textViewSituacao = findViewById(R.id.text_view_situacao_pergunta);
        //textViewSituacao.setText(questionario.getSituacao());

        recyclerView = findViewById(R.id.recyclerview_pergunta);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.show("Carregando informações...");
        getPerguntas(questionario);
    }

    private void carregarPerguntas(PerguntasQuestionario[] perguntas) {
        recyclerView.setAdapter(new PerguntaAdapter(perguntas, onClickItem()));
    }

    private void getPerguntas(Questionario questionario) {
        progressDialog.hide();
        List<PerguntasQuestionario> perguntas = new ArrayList<>();
        PerguntasQuestionario pergunta = new PerguntasQuestionario();
        pergunta.setDescricaoPergunta("Pergunta 1");
        perguntas.add(pergunta);
        PerguntasQuestionario pergunta1 = new PerguntasQuestionario();
        pergunta1.setDescricaoPergunta("Pergunta 2");
        perguntas.add(pergunta1);
        _perguntas = perguntas.toArray(new PerguntasQuestionario[0]);
        carregarPerguntas(_perguntas);
    }

    private PerguntaAdapter.PerguntaOnClickListener onClickItem() {
        return new PerguntaAdapter.PerguntaOnClickListener() {
            @Override
            public void onClickItem(View view, int i) {

            }
        };
    }
}
