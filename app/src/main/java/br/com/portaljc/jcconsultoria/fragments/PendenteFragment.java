package br.com.portaljc.jcconsultoria.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.adapters.PendenteAdapter;
import br.com.portaljc.jcconsultoria.domains.Questionario;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;

public class PendenteFragment extends Fragment {

    private Context _context;

    Button buttonSincronizar;
    TextView textViewNaoHaSincronizar;
    ImageView imageViewLogo;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;

    public PendenteFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show("Carregando informações...");
        getQuestionarioPendente();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pendente, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_pendente_sincronizar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        textViewNaoHaSincronizar = view.findViewById(R.id.text_view_questionario_sincronizar);
        imageViewLogo = view.findViewById(R.id.image_view_logo_pendente);

        buttonSincronizar = view.findViewById(R.id.button_sincronizar_pendente);
        buttonSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _context = null;
    }

    private void carregarQuestionarios(Questionario[] questionarios) {
        View view = getView();
        if (view != null) {
            textViewNaoHaSincronizar.setVisibility(questionarios.length == 0 ? View.VISIBLE : View.GONE);
            buttonSincronizar.setVisibility(questionarios.length == 0? View.GONE : View.VISIBLE);
            imageViewLogo.setVisibility(questionarios.length == 0 ? View.VISIBLE : View.GONE);
            recyclerView.setAdapter(new PendenteAdapter(questionarios, onClickItem()));
        }
    }

    private void getQuestionarioPendente() {
        progressDialog.hide();
        Questionario questionario = new Questionario();
        questionario.setNomeQuestionario("Questionario 1");
        questionario.setTipoQuestionarioDescricao("Tipo de Questionario");
        //questionario.setEmpresa("Empresa 1");
        //questionario.setData("01/12/2018");
        //questionario.setSituacao("Pendente");
        carregarQuestionarios(new Questionario[]{  });
    }

    private PendenteAdapter.PendenteOnClickListener onClickItem() {
        return new PendenteAdapter.PendenteOnClickListener() {
            @Override
            public void onClickItem(View view, int i) {

            }
        };
    }
}
