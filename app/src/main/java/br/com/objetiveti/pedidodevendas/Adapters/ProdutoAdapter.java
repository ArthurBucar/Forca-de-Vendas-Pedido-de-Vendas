package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Produto;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/11/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    private List<Produto> pList;
    private LayoutInflater pLayoutInflater;
    private TextView nome;
    private TextView codigoproduto;
    private TextView preco;

    private static MyViewHolder.ItemClickListener itemClickListener;

    public ProdutoAdapter(Context c, List<Produto> l){
        pList = l;
        pLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ProdutoAdapter(String nome, String codigo, double preco, String[] valorUnitario, String[] quantidade, String[] subtotal, boolean se) {
        se = true;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView NomeProduto;
        public TextView CodProduto;
        public TextView PrecoProduto;
//        TextView valor;
//        TextView quantdade;
//        public TextView subtotal;



        public MyViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            NomeProduto = (TextView)itemView.findViewById(R.id.tv_pnomeproduto);
            CodProduto = (TextView)itemView.findViewById(R.id.tv_pcodproduto);
            PrecoProduto = (TextView)itemView.findViewById(R.id.tv_ppreco);
//            valor = (TextView)itemView.findViewById(R.id.tv_03);
//            quantdade = (TextView)itemView.findViewById(R.id.tv_04);
//           subtotal = (TextView)itemView.findViewById(R.id.tv_05);
        }

        public void onClick(View v){
            if(itemClickListener != null) {
//                valor.setVisibility(View.VISIBLE);
//                quantdade.setVisibility(View.VISIBLE);
//                subtotal.setVisibility(View.VISIBLE);
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }

//        public void updateList(Produto produto) {
//            insertItem(produto);
//        }
//
//        // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
//        private void insertItem(Produto produto) {
//        pList.add(produto);
//            notifyItemInserted(getItemCount());
//        }

        public interface ItemClickListener {
            void onItemClick(int position);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = pLayoutInflater.inflate(R.layout.item_produto, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.NomeProduto.setText(pList.get(position).getNomeProduto());
        myViewHolder.CodProduto.setText(pList.get(position).getCodProduto());
        myViewHolder.PrecoProduto.setText(pList.get(position).getPrecoReal());
}

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public void addListItem(Produto p, int position){
        pList.add(p);
        notifyItemInserted(position);
    }

    public void setOnItemClickListener(MyViewHolder.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
