package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Venda;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/7/2017.
 */

public class VendaAdapter extends RecyclerView.Adapter<VendaAdapter.MyViewHolder>{
    private List<Venda> vList;
    private LayoutInflater vlayoutInflater;

    public VendaAdapter(Context c,  List<Venda> l){
        vList = l;
        vlayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = vlayoutInflater.inflate(R.layout.item_venda, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.nome.setText(vList.get(position).getNome());
        myViewHolder.endereco.setText(vList.get(position).getEndereco());
        myViewHolder.telefone.setText(vList.get(position).getTelefone());
        myViewHolder.valor.setText(vList.get(position).getValorReal());
       // myViewHolder.valor.setText(vList.get(position).getValorTotal());
    }

    @Override
    public int getItemCount() {
        return vList.size();
    }

    public void addListItem(Venda v, int position){
        vList.add(v);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nome;
        public TextView endereco;
        public TextView telefone;
        public TextView valor;
        public TextView total;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.nome);
            endereco = (TextView) itemView.findViewById(R.id.endereco);
            telefone = (TextView) itemView.findViewById(R.id.telefone);
            valor = (TextView) itemView.findViewById(R.id.valor);
            total = (TextView) itemView.findViewById(R.id.valorTotal);
        }
    }
}
