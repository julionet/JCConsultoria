package br.com.portaljc.jcconsultoria.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.domains.Questionario;

public class PendenteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Questionario[] _questionarios;
    private PendenteOnClickListener _pendenteOnClickListener;

    public PendenteAdapter(Questionario[] questionarios, PendenteOnClickListener pendenteOnClickListener) {
        _questionarios = questionarios;
        _pendenteOnClickListener = pendenteOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_pendente, parent, false);
        return new PendenteAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final PendenteAdapter.ViewHolder h = (PendenteAdapter.ViewHolder)holder;
        final int p = position;

        Questionario questionario = _questionarios[position];
        h.textViewQuestionario.setText(questionario.getNomeQuestionario());
        h.textViewTipoQuestionario.setText(questionario.getTipoQuestionarioDescricao());
        //h.textViewEmpresa.setText(questionario.getEmpresa());
        //h.textViewData.setText(questionario.getData());

        if (_pendenteOnClickListener != null) {
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _pendenteOnClickListener.onClickItem(h.itemView, p);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _questionarios.length;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewQuestionario;
        TextView textViewTipoQuestionario;
        TextView textViewEmpresa;
        TextView textViewData;

        ViewHolder(View view) {
            super(view);

            textViewQuestionario = view.findViewById(R.id.text_view_questionario_pendente);
            textViewTipoQuestionario = view.findViewById(R.id.text_view_tipo_questionario_pendente);
            textViewEmpresa = view.findViewById(R.id.text_view_empresa_pendente);
            textViewData = view.findViewById(R.id.text_view_data_pendente);
        }
    }

    public interface PendenteOnClickListener {
        void onClickItem(View view, int i);
    }
}
