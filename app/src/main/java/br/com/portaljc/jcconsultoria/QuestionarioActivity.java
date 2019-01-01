package br.com.portaljc.jcconsultoria;

import android.content.Intent;
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

import br.com.portaljc.jcconsultoria.adapters.QuestionarioAdapter;
import br.com.portaljc.jcconsultoria.domains.Empresa;
import br.com.portaljc.jcconsultoria.domains.Questionario;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;

public class QuestionarioActivity extends AppCompatActivity {

    TextView textViewEmpresa;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Questionario[] _questionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Empresa empresa = (Empresa) getIntent().getSerializableExtra("empresa");

        if (empresa != null) {
            textViewEmpresa = findViewById(R.id.text_view_empresa_questionario);
            textViewEmpresa.setText(empresa.getRazaoSocial());
        }

        recyclerView = findViewById(R.id.recyclerview_questionario);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.show("Carregando informações...");
        getQuestionarios(empresa);
    }

    private void carregarQuestionarios(Questionario[] questionarios) {
        recyclerView.setAdapter(new QuestionarioAdapter(questionarios, onClickItem()));
    }

    private void getQuestionarios(Empresa empresa) {
        progressDialog.hide();

        List<Questionario> questionarios = new ArrayList<>();
        Questionario questionario = new Questionario();
        questionario.setNomeQuestionario("Questionario 1");
        questionario.setTipoQuestionarioDescricao("Tipo de Questionario");
        //questionario.setEmpresa("Empresa 1");
        //questionario.setSituacao("Pendente");
        questionarios.add(questionario);
        _questionarios = questionarios.toArray(new Questionario[0]);
        carregarQuestionarios(_questionarios);
    }

    private QuestionarioAdapter.QuestionarioOnClickListener onClickItem() {
        return new QuestionarioAdapter.QuestionarioOnClickListener() {
            @Override
            public void onClickItem(View view, int i) {
                Intent intent = new Intent(QuestionarioActivity.this, PerguntaActivity.class);
                intent.putExtra("questionario", _questionarios[i]);
                startActivity(intent);
            }
        };
    }
}
