package br.com.portaljc.jcconsultoria.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.portaljc.jcconsultoria.R;
import br.com.portaljc.jcconsultoria.domains.PerguntasQuestionario;

public class PerguntaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PerguntasQuestionario[] _perguntas;
    private PerguntaAdapter.PerguntaOnClickListener _perguntaOnClickListener;

    public PerguntaAdapter(PerguntasQuestionario[] perguntas, PerguntaAdapter.PerguntaOnClickListener perguntaOnClickListener) {
        _perguntas = perguntas;
        _perguntaOnClickListener = perguntaOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_pergunta, parent, false);
        return new PerguntaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final PerguntaAdapter.ViewHolder h = (PerguntaAdapter.ViewHolder)holder;
        final int p = position;

        PerguntasQuestionario pergunta = _perguntas[position];
        h.textViewPergunta.setText(pergunta.getDescricaoPergunta());
        //h.textViewResposta.setText(pergunta.getResposta());

        if (_perguntaOnClickListener != null) {
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _perguntaOnClickListener.onClickItem(h.itemView, p);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _perguntas.length;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPergunta;
        TextView textViewResposta;

        ViewHolder(View view) {
            super(view);

            textViewPergunta = view.findViewById(R.id.text_view_descricao_pergunta);
            textViewResposta = view.findViewById(R.id.text_view_resposta_pergunta);
        }
    }

    public interface PerguntaOnClickListener {
        void onClickItem(View view, int i);
    }
}
