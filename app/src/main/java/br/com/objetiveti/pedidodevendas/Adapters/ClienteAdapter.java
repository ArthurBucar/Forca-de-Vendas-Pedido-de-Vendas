package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Activities.AcClienteSelecionado;
import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/4/2017.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder>{
    private List<Cliente> cList;
    private LayoutInflater cLayoutInflater;
    int ver = 0;
    Double cor;
    float codigo;
    public ClienteAdapter(Context c, List<Cliente> l){
        cList = l;
        cLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = cLayoutInflater.inflate(R.layout.item_cliente, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        codigo = (float) cList.get(position).getCodigo();
        cor = Double.valueOf(cList.get(position).getLimCredito());
        myViewHolder.cNome.setText(cList.get(position).getcNome());

        myViewHolder.ccc.setText(String.valueOf(codigo));
        myViewHolder.ddd.setText( String.valueOf(cor));

        if (cor == 0){
            myViewHolder.cStatus.setTextColor(Color.parseColor("#8C7853"));
            myViewHolder.cStatus.setText(("Sem limite disponível"));
        } else if (cor < 200){
            myViewHolder.cStatus.setTextColor(Color.parseColor("#03a9f4"));
            myViewHolder.cStatus.setText(("Disponível"));
        } else {
            myViewHolder.cStatus.setTextColor(Color.parseColor("#F00000"));
            myViewHolder.cStatus.setText(("Bloqueado"));
        }
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public void addListItem(Cliente c, int position){
        cList.add(c);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView cNome;
        public TextView cStatus;
        public TextView ccc;
        public TextView ddd;
        public MyViewHolder(final View itemView) {
            super(itemView);
            cNome = (TextView) itemView.findViewById(R.id.cnome);
            cStatus = (TextView) itemView.findViewById(R.id.cstatus);
            ccc = (TextView) itemView.findViewById(R.id.ccc);
            ddd = (TextView) itemView.findViewById(R.id.ddd);
            ccc.setVisibility(View.INVISIBLE);
            ddd.setVisibility(View.INVISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cStatus.getText() == "Bloqueado" || cStatus.getText() == "Sem limite disponível"){
                        Log.e("teste", String.valueOf(ver));
                        Toast.makeText(view.getContext(), "Cliente indisponível!!!\nPor favor entre em contato com sua unidade de gestão de pessoas para realizar o desbloqueio.", Toast.LENGTH_LONG).show();
                    } else {
                        int adapterPosition = MyViewHolder .this.getAdapterPosition();
                        int codioooo = (int) cList.get(getAdapterPosition()).getCodigo();
                        Log.e("Ui", String.valueOf(codioooo));
                        Log.e("logCodigo", String.valueOf(adapterPosition+1));
                        Context context = view.getContext();
                        Intent intent = new Intent(context, AcClienteSelecionado.class);
                        intent.putExtra("nome", cNome.getText());
                        Log.i("nome", String.valueOf(cNome));
                        intent.putExtra("status", cStatus.getText());
                        intent.putExtra("limteDeCredito", ddd.getText());
                        intent.putExtra("codigo", adapterPosition);
                        intent.putExtra("codioooo", codioooo);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}