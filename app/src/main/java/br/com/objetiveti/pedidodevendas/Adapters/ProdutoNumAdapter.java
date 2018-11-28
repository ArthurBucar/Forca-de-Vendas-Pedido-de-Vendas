package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Produto_Num;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/13/2017.
 */

public class ProdutoNumAdapter extends RecyclerView.Adapter<ProdutoNumAdapter.MyViewHolder>{
    private List<Produto_Num> pList;
    private LayoutInflater pLayoutInflater;

    public ProdutoNumAdapter(Context c, List<Produto_Num> p){
        pList = p;
        pLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = pLayoutInflater.inflate(R.layout.item_produto_num,viewGroup, false);

        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.tv_valor.setText(pList.get(position).getValorUnitarioProduto());
        myViewHolder.tv_quantidade.setText(pList.get(position).getQuantidadeProduto());
        myViewHolder.tv_subtotal.setText(pList.get(position).getSubtotalProduto());
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public void addListItem(Produto_Num pn, int position){
        pList.add(pn);
        notifyItemInserted(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_valor;
        public TextView tv_quantidade;
        public TextView tv_subtotal;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_valor = (TextView) itemView.findViewById(R.id.tv_valor);
            tv_quantidade = (TextView) itemView.findViewById(R.id.tv_quantidade);
            tv_subtotal = (TextView) itemView.findViewById(R.id.tv_subtotal);
        }
    }
}
