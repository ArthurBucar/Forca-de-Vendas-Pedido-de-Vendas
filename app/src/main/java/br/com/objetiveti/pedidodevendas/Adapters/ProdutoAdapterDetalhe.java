package br.com.objetiveti.pedidodevendas.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Activities.AcClienteSelecionado;
import br.com.objetiveti.pedidodevendas.Activities.AcListaProdutosValores;
import br.com.objetiveti.pedidodevendas.Models.Produto;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/11/2017.
 */

public class ProdutoAdapterDetalhe extends RecyclerView.Adapter<ProdutoAdapterDetalhe.MyViewHolder>{
    public Produto produto = new Produto();
    private List<Produto> pList;
    private LayoutInflater pLayoutInflater;
    public int contador = 1;
    private TextView nome;
    private TextView codigoproduto;
    private TextView preco;
    private View context;
    private int position;

    String ret = "";
    AcClienteSelecionado ac_cliente_selecionado = new AcClienteSelecionado();
    public AcListaProdutosValores acLista_produtos_valores = new AcListaProdutosValores();
    private Context cc;
// private static MyViewHolder.ItemClickListener itemClickListener;

    public ProdutoAdapterDetalhe(Context c, List<Produto> l){
        pList = l;
        pLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int delete(int position) {
        pList.remove(position);
        notifyItemRemoved(position);
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        public TextView NomeProduto;
        public TextView CodProduto;
        public TextView PrecoProduto;
        public TextView untario;
        public TextView qtdProdutos;
        public Button BntLixo;
        public double recebePreco;
        private String recebeB;

        public MyViewHolder(final View itemView) {
            super(itemView);
            final Dialog dialogEdit = new Dialog(itemView.getContext());
            dialogEdit.setContentView(R.layout.diaglog_item1);
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(final View view) {
////                   Toast.makeText(itemView.getContext(), getAdapterPosition(), Toast.LENGTH_SHORT).show();
////                    final Dialog dialogEdit = new Dialog(view.getContext());
////                    final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(view.getContext());
//
////                    alertDialog.setTitle("Deseja Deletar ou Editar esse Item ? ");
////                    alertDialog.setMessage("Se sim, clicar em 'DELETAR' ou 'EDITAR'");
////                    alertDialog.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            delete(position);
////                            dialogEdit.setContentView(R.layout.diaglog_item1);
////                            final TextView txtNome = dialogEdit.findViewById(R.id.dNomeProduto);
////                            final TextView txtCodigo = dialogEdit.findViewById(R.id.dCodProduto);
////                            final TextView txtPreco = dialogEdit.findViewById(R.id.dPrecoProduto);
////                            final TextView txtQuantidade = dialogEdit.findViewById(R.id.quantidade);
////                            final TextView txtSubtotal = dialogEdit.findViewById(R.id.dPrecoProduto02);
////
////                            final String nome = NomeProduto.getText().toString();
////                            final String codigo = CodProduto.getText().toString();
////                            String preco = PrecoProduto.getText().toString();
////                            txtNome.setText(nome);
////                            txtCodigo.setText(codigo);
////                            txtPreco.setText(preco);
////
////                            Button bnt_pedido_ok = dialogEdit.findViewById(R.id.bnt_ok_pedido);
////                            final Button mais1 = dialogEdit.findViewById(R.id.bnt_mais);
////                            final Button menos1 = dialogEdit.findViewById(R.id.bnt_menos);
////                            Button mais10 = dialogEdit.findViewById(R.id.bnt_maismais);
////                            Button menos10 = dialogEdit.findViewById(R.id.bnt_menosmenos);
////                            Button mais100 = dialogEdit.findViewById(R.id.bnt_maismaismais);
////                            Button menos100 = dialogEdit.findViewById(R.id.bnt_menosmenosmenos);
////
////                            mais1.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    contador = contador+1;
////                                    txtQuantidade.setText(""+contador);
////                                    String teste = untario.getText().toString().replace("R$","");
////                                    txtSubtotal.setText("R$"+ teste);
////                                    double valor1 = Double.parseDouble(teste.replace(',','.'));
////                                    ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
////                                    txtSubtotal.setText(""+ret);
////                                }
////                            });
////
////                            menos1.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    if (contador > 0) {
////                                        contador--;
////                                        txtQuantidade.setText("" + contador);
////                                        String teste = untario.getText().toString().replace("R$", "");
////                                        txtSubtotal.setText("R$" + teste);
////                                        double valor1 = Double.parseDouble(teste.replace(',', '.'));
////                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
////                                        txtSubtotal.setText("" + ret);
////                                    }
////                                }
////                            });
////
////                            mais10.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    contador = contador+10;
////                                    txtQuantidade.setText(""+contador);
////                                    String teste = untario.getText().toString().replace("R$","");
////                                    txtSubtotal.setText("R$"+ teste);
////                                    double valor1 = Double.parseDouble(teste.replace(',','.'));
////                                    ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
////                                    txtSubtotal.setText(""+ret);
////                                }
////                            });
////
////                            menos10.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    if (contador > 0) {
////                                        contador = contador -10;
////                                        txtQuantidade.setText("" + contador);
////                                        String teste = untario.getText().toString().replace("R$", "");
////                                        txtSubtotal.setText("R$" + teste);
////                                        double valor1 = Double.parseDouble(teste.replace(',', '.'));
////                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
////                                        txtSubtotal.setText("" + ret);
////                                    }
////                                }
////                            });
////
////                            mais100.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    contador = contador+100;
////                                    txtQuantidade.setText(""+contador);
////                                    String teste = untario.getText().toString().replace("R$","");
////                                    txtSubtotal.setText("R$"+ teste);
////                                    double valor1 = Double.parseDouble(teste.replace(',','.'));
////                                    ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
////                                    txtSubtotal.setText(""+ret);
////                                }
////                            });
////
////                            menos100.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    if (contador > 0) {
////                                        contador = contador -100;
////                                        txtQuantidade.setText("" + contador);
////                                        String teste = untario.getText().toString().replace("R$", "");
////                                        txtSubtotal.setText("R$" + teste);
////                                        double valor1 = Double.parseDouble(teste.replace(',', '.'));
////                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
////                                        txtSubtotal.setText("" + ret);
////                                    }
////                                }
////                            });
////
////                            bnt_pedido_ok.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    Produto prod = new Produto();
////                                    String teste = untario.getText().toString().replace("R$", "");
////                                    double valor1 = Double.parseDouble(teste.replace(',','.'));
////                                    prod.setNomeProduto(txtNome.getText().toString());
////                                    prod.setCodProduto(txtCodigo.getText().toString());
////                                    prod.setPreco(valor1);
////                                    prod.setQtd(contador);
////                                    Toast.makeText(view.getContext(), "Item Editado com sucesso", Toast.LENGTH_SHORT).show();
////                                    add(prod);
////                                    tela_cliente_selecionado.atualizaProduto();
////                                    dialogEdit.dismiss();
////                                }
////                            });
////                            dialogEdit.show();
////                        }
////                    });
////                    alertDialog.setNegativeButton("DELETAR", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int position) {
////                            Toast.makeText(view.getContext(), "Item Deletado com sucesso", Toast.LENGTH_SHORT).show();
////                            delete(getAdapterPosition());
////                        }
////                    });
////
////                    android.app.AlertDialog dialogbuilder = alertDialog.create();
////                    dialogbuilder.show();
//
//                    return false;
//                }
//            });

            NomeProduto = (TextView)itemView.findViewById(R.id.tv_pnomeproduto);
            CodProduto = (TextView)itemView.findViewById(R.id.tv_pcodproduto);
            PrecoProduto = (TextView)itemView.findViewById(R.id.tv_ppreco);
            untario = (TextView)itemView.findViewById(R.id.tv_valor_unit);
            qtdProdutos = (TextView)itemView.findViewById(R.id.tv_descricaoProduto);
            BntLixo = (Button)itemView.findViewById(R.id.bnt_deletar);

            BntLixo.setOnClickListener(this); //button onclick listener
        }

        @Override
        public void onClick(final View view) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private static ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = pLayoutInflater.inflate(R.layout.item_produto_detalhe, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.NomeProduto.setText(pList.get(position).getNomeProduto());
        myViewHolder.CodProduto.setText(pList.get(position).getCodProduto());
       // myViewHolder.PrecoProduto.setText( " "+ (pList.get(position).getPreco() * pList.get(position).getQtd()));
        myViewHolder.PrecoProduto.setText(pList.get(position).getPrecoReal1());
        myViewHolder.untario.setText(pList.get(position).getPrecoReal());
        myViewHolder.qtdProdutos.setText(pList.get(position).getQtd() +  "");
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public void add(Produto produto){
        if (pList.contains(produto)){
            Toast.makeText(pLayoutInflater.getContext(), "Produto já adicionado", Toast.LENGTH_SHORT).show();
        }else{
            pList.add(position, produto);
            notifyDataSetChanged();
        }
    }
}

//              Snackbar.make(v, "Item Deletado", Snackbar.LENGTH_LONG)
//                      .setAction("Action", null).show();


//    public void setOnItemClickListener(MyViewHolder.ItemClickListener itemClickListener){
//        this.itemClickListener = itemClickListener;
//    }


//        public void onClick(View v){
//            if(itemClickListener != null) {
//                itemClickListener.onItemClick(getAdapterPosition());
//            }
//        }


//        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "Item Deletado " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//            delete(getAdapterPosition()); //calls the method above to delete
//        }

//        public interface ItemClickListener {
//            void onItemClick(int position);
//        }


