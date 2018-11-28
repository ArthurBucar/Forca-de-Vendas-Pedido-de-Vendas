package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Pedido;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/2/2017.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {
    private List<Pedido> pList;
    private LayoutInflater pLayoutInflater;
    public Context context;
    private ArrayList<Pedido> data;
    private int previousPosition = 0;
    public PedidoAdapter(Context c, List<Pedido> p){
        pList = p;
        pLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = pLayoutInflater.inflate(R.layout.item_pedido,viewGroup, false);

        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        myViewHolder.nNome.setText(pList.get(position).getnNome());
        myViewHolder.dataPed.setText((CharSequence) pList.get(position).getDataPed());
        myViewHolder.numPed.setText(pList.get(position).getNumPed());
        myViewHolder.numPrePed.setText(pList.get(position).getNumPrePed());
        myViewHolder.numSub.setText(pList.get(position).getNumSubReal());


    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public void onClick(View v){}

    public void addListItem(Pedido o, int position){
        pList.add(o);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nNome;
        public TextView dataPed;
        public TextView numPed;
        public TextView numPrePed;
        public TextView numSub;


        public MyViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(view.getContext());

                    alertDialog.setTitle("Deseja Deletar esse Pedido ? ");
                    alertDialog.setMessage("Se sim, clicar em 'Deletar'");
                    alertDialog.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(view.getContext(), "Deleção do pedido cancelada.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            delete(getAdapterPosition());
                            Toast.makeText(view.getContext(), "Pedido deletado com sucesso", Toast.LENGTH_SHORT).show();
                        }
                    });

                    android.app.AlertDialog dialogbuilder = alertDialog.create();
                    dialogbuilder.show();

                    return false;
                }
            });

            nNome = (TextView) itemView.findViewById(R.id.tv_nome);
            dataPed = (TextView) itemView.findViewById(R.id.dataPed);
            numPed = (TextView) itemView.findViewById(R.id.numPed);
            numPrePed = (TextView) itemView.findViewById(R.id.numPrePed);
            numSub = (TextView) itemView.findViewById(R.id.numSub);
        }

        public int delete(int position) {
            pList.remove(position);
            notifyItemRemoved(position);
            return position;
        }
    }



    public void setFilter(ArrayList<Pedido> newList){
        pList = new ArrayList<>();
        pList.addAll(newList);
        notifyDataSetChanged();

    }
}
// myViewHolder.tv_subTotals.setText(pList.get(position).getsubTotal());