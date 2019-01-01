package br.com.portaljc.jcconsultoria.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.domains.Empresa;

public class EmpresaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Empresa[] _empresas;
    private EmpresaOnClickListener _empresaOnClickListener;

    public EmpresaAdapter(Empresa[] empresas, EmpresaOnClickListener empresaOnClickListener) {
        _empresas = empresas;
        _empresaOnClickListener = empresaOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_empresas, parent, false);
        return new EmpresaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final EmpresaAdapter.ViewHolder h = (EmpresaAdapter.ViewHolder)holder;
        final int p = position;

        Empresa empresa = _empresas[position];
        h.textViewEmpresa.setText(empresa.getRazaoSocial());

        if (_empresaOnClickListener != null) {
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _empresaOnClickListener.onClickItem(h.itemView, p);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _empresas.length;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewEmpresa;

        ViewHolder(View view) {
            super(view);

            textViewEmpresa = view.findViewById(R.id.text_view_empresa);
        }
    }

    public interface EmpresaOnClickListener {
        void onClickItem(View view, int i);
    }
}
