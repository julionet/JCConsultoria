package br.com.portaljc.jcconsultoria.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.domains.Questionario;

public class QuestionarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Questionario[] _questionarios;
    private QuestionarioOnClickListener _questionarioOnClickListener;

    public QuestionarioAdapter(Questionario[] questionarios, QuestionarioOnClickListener questionarioOnClickListener) {
        _questionarios = questionarios;
        _questionarioOnClickListener = questionarioOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_questionario, parent, false);
        return new QuestionarioAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final QuestionarioAdapter.ViewHolder h = (QuestionarioAdapter.ViewHolder)holder;
        final int p = position;

        Questionario questionario = _questionarios[position];
        h.textViewQuestionario.setText(questionario.getNomeQuestionario());
        h.textViewTipoQuestionario.setText(questionario.getTipoQuestionarioDescricao());
        //h.textViewSituacao.setText(questionario.getSituacao());

        if (_questionarioOnClickListener != null) {
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _questionarioOnClickListener.onClickItem(h.itemView, p);
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
        TextView textViewSituacao;

        ViewHolder(View view) {
            super(view);

            textViewQuestionario = view.findViewById(R.id.text_view_nome_questionario);
            textViewTipoQuestionario = view.findViewById(R.id.text_view_tipo_questionario_questionario);
            textViewSituacao = view.findViewById(R.id.text_view_situacao_questionario);
        }
    }

    public interface QuestionarioOnClickListener {
        void onClickItem(View view, int i);
    }
}
