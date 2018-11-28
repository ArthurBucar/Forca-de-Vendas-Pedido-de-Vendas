package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Activities.AcNovoCliente;
import br.com.objetiveti.pedidodevendas.Models.Clientec;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/4/2017.
 */

public class ClienteAdapterc extends RecyclerView.Adapter<ClienteAdapterc.MyViewHolder>{
    private List<Clientec> cList;
    private LayoutInflater cLayoutInflater;
    int ver = 0;
    Double cor;
    float codigo;
    String cnpj;
    String razaoSocial;
    String endereco;
    String bairro;
    String cidade;
    String CEP;
    String UF;
    String Telefone;
    String Contato;
    String Email;
    String tipoDePessoa;
    public ClienteAdapterc(Context c, List<Clientec> l){
        cList = l;
        cLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ClienteAdapterc.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = cLayoutInflater.inflate(R.layout.item_cliente, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        codigo = (float) cList.get(position).getCodigo();
        cor = Double.valueOf(cList.get(position).getLimCredito());

        myViewHolder.cNome.setText(cList.get(position).getcNome());
        myViewHolder.cStatus.setText( String.valueOf(cor));

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

    public void addListItem(Clientec c, int position){
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
                    if (cStatus.getText() == "Bloqueado"){
                        Toast.makeText(view.getContext(), "Cliente Bloqueado!!!\nPor favor entre em contato com sua unidade de gestão de pessoas para realizar o desbloqueio.", Toast.LENGTH_LONG).show();
                    } else if (cStatus.getText() == "Sem limite disponível"){
                        Toast.makeText(view.getContext(), "Cliente Indisponível!!!\nPor favor entre em contato com sua unidade de gestão de pessoas para realizar o desbloqueio.", Toast.LENGTH_LONG).show();
                    }
                        else {
                        int adapterPosition = MyViewHolder .this.getAdapterPosition();
                        cnpj = cList.get(adapterPosition).getCPF_CNPJ();
                        razaoSocial = cList.get(adapterPosition).getRazaoSocial();
                        endereco = cList.get(adapterPosition).getEndereco();
                        bairro = cList.get(adapterPosition).getBairro();
                        cidade = cList.get(adapterPosition).getCidade();
                        CEP = cList.get(adapterPosition).getCEP();
                        UF = cList.get(adapterPosition).getUF();
                        Telefone = cList.get(adapterPosition).getTelefone();
                        Contato = cList.get(adapterPosition).getContato();
                        Email = cList.get(adapterPosition).getEmail();
                        tipoDePessoa = cList.get(adapterPosition).getTipoPessoa();

                        Context context = view.getContext();
                        Intent intent = new Intent(context, AcNovoCliente.class);
                        intent.putExtra("nome", cNome.getText());
                        intent.putExtra("status", cStatus.getText());
                        intent.putExtra("limteDeCredito", ddd.getText());
                        intent.putExtra("cnpj_cpf", cnpj);
                        intent.putExtra("razaoSocial", razaoSocial);
                        intent.putExtra("endereco", endereco);
                        intent.putExtra("bairro", bairro);
                        intent.putExtra("cidade", cidade);
                        intent.putExtra("cep", CEP);
                        intent.putExtra("uf", UF);
                        intent.putExtra("telefone", Telefone);
                        intent.putExtra("contato", Contato);
                        intent.putExtra("email", Email);
                        intent.putExtra("tipoDePessoa", tipoDePessoa);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}