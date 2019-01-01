package br.com.portaljc.jcconsultoria.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.portaljc.jcconsultoria.QuestionarioActivity;
import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.adapters.EmpresaAdapter;
import br.com.portaljc.jcconsultoria.domains.Empresa;
import br.com.portaljc.jcconsultoria.utils.Constants;
import br.com.portaljc.jcconsultoria.utils.Preferences;
import br.com.portaljc.jcconsultoria.utils.ProgressDialog;

public class EmpresaFragment extends Fragment {

    Context _context;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    Empresa[] _empresas;

    public EmpresaFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();

        Preferences.setInteger(_context, Constants.EMPRESA_SELECIONADA, 0);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show("Carregando informações...");
        getEmpresas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_empresa);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

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

    private void carregarEmpresas(Empresa[] empresas) {
        View view = getView();
        if (view != null) {
            recyclerView.setAdapter(new EmpresaAdapter(empresas, onClickItem()));
        }
    }

    private void getEmpresas() {
        progressDialog.hide();

        List<Empresa> empresas = new ArrayList<>();
        Empresa empresa = new Empresa();
        empresa.setId(1);
        empresa.setRazaoSocial("Empresa 1");
        empresas.add(empresa);
        empresa = new Empresa();
        empresa.setId(2);
        empresa.setRazaoSocial("Empresa 2");
        empresas.add(empresa);
        _empresas = empresas.toArray(new Empresa[0]);
        carregarEmpresas(_empresas);
    }

    private EmpresaAdapter.EmpresaOnClickListener onClickItem() {
        return new EmpresaAdapter.EmpresaOnClickListener() {
            @Override
            public void onClickItem(View view, int i) {
                Preferences.setLong(_context, Constants.EMPRESA_SELECIONADA, _empresas[i].getId());
                Intent intent = new Intent(getActivity(), QuestionarioActivity.class);
                startActivity(intent);
            }
        };
    }
}
