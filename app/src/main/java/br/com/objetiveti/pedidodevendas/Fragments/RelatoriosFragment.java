package br.com.objetiveti.pedidodevendas.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import br.com.objetiveti.pedidodevendas.Effects.ColorChange;
import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Dao.DaoMetaDia;
import br.com.objetiveti.pedidodevendas.Dao.DaoPedido;
import br.com.objetiveti.pedidodevendas.Dao.DaoTotalVendas;
import br.com.objetiveti.pedidodevendas.Models.Venda;
import br.com.objetiveti.pedidodevendas.R;

public class RelatoriosFragment extends Fragment implements OnChartGestureListener {
    Venda venda = new Venda();
    DaoTotalVendas daoTotalVendas = new DaoTotalVendas();
    private static final int TIMER_RUNTIME = 30000;
    BarChart barChart;
    String ret1 = "";
    String ret2 = "";
    String ret3 = "";
    String teste;
    protected ProgressBar mProgressBar;
    public TextView primeiroValor;
    public TextView segundoValor;
    int diaCorrente = 0;
    int diasUteis = 251;
    int totalDeVendas = 10000;
    float warrant;
    float projecao;
    String diaDaSemana = "";
    int desconto = 0;

    int fevereiro = 18;
    int marco = 21;
    int abril = 21;
    int maio = 20;
    int Junho = 21;
    int Julho = 22;
    int Agosto = 23;
    int Setembro = 19;
    int Outubro = 22;
    int Novembro = 20;
    int Dezembro = 19;

    int fevereiroUtil = 19;
    int marcoUtil = 21;
    int abrilUtil = 21;
    int maioUtil = 21;
    int junhoUtil = 21;
    int julhoUtil = 22;
    int agostoUtil = 23;
    int setembroUtil = 19;
    int outubroUtil = 22;
    int novembroUtil = 20;
    int dezembroUtil = 19;

    int[] fevereiroInvalid = new int[10];
    int[] marcoInvalid = new int[13];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RelatoriosFragment() {
        // Required empty public constructor
    }

    public RelatoriosFragment(float result) {
        warrant = result;
    }

    // TODO: Rename and change types and number of parameters
    public static RelatoriosFragment newInstance(String param1, String param2) {
        RelatoriosFragment fragment = new RelatoriosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Relatórios");
    }

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_relatorios, container,
                false);
        primeiroValor = (TextView)rootView.findViewById(R.id.primeiroVALOR);
        segundoValor = (TextView)rootView.findViewById(R.id.segundoVALOR);
        barChart = (BarChart) rootView.findViewById(R.id.bargrahp);
        View view =  inflater.inflate(R.layout.fragment_relatorios, container, false);

        DBMain banco = new DBMain(getContext());
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        double recebe = 0;
        Cursor c = db.rawQuery("select TotalPedido from Pedido_Venda_Cabecalho",null);
        if(c.moveToFirst()){
            do{
                double numSub = c.getDouble(c.getColumnIndex("TotalPedido"));
                recebe = recebe + numSub;
            }while(c.moveToNext());
        }
        c.close();
        db.close();

        DaoMetaDia daoMetaDia1 = new DaoMetaDia();
        teste = String.valueOf(daoMetaDia1.getMetaDia(getContext(), 5));
        float recebeTotal = Float.parseFloat(String.valueOf(teste));

        DaoPedido daoPedido = new DaoPedido();
        final double timer = 6000;

        String direita1 = " ";
        direita1 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recebeTotal);

        String esquerda1 = " ";
        esquerda1 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recebe);

        Log.e("lgodaw1", direita1);
        Log.e("lgodaw1", esquerda1);

        final float valorCorrente = Float.parseFloat((teste));

        primeiroValor.setText("" + esquerda1);
        segundoValor.setText("" + direita1);

        mProgressBar = (ProgressBar)rootView.findViewById(R.id.metaDiaRelatorio);
        final int progress = (int) (mProgressBar.getMax() * recebe / valorCorrente);
        mProgressBar.setProgress(progress);


        final ArrayList<BarEntry> entries = new ArrayList<>();
        DaoMetaDia daoMetaDia = new DaoMetaDia();
        //{3, 4, 10, 11, 13, 17, 18, 24, 25, 30, 31};
        fevereiroInvalid[0] = 3;
        fevereiroInvalid[1] = 4;
        fevereiroInvalid[2] = 10;
        fevereiroInvalid[3] = 11;
        fevereiroInvalid[4] = 13;
        fevereiroInvalid[5] = 17;
        fevereiroInvalid[6] = 18;
        fevereiroInvalid[7] = 24;
        fevereiroInvalid[8] = 25;

        float vendaDiaria = 10;

        GregorianCalendar calendar = new GregorianCalendar();
        int diaChavoso = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mesChavoso = +1 + calendar.get(GregorianCalendar.MONTH);

        Date d = new Date();
        Calendar calen = new GregorianCalendar();
        calen.setTime(d);
        String nome = "";
        int dia = calen.get(calen.DAY_OF_WEEK);
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo";break;
            case Calendar.MONDAY: nome = "Segunda";break;
            case Calendar.TUESDAY: nome = "Terça";break;
            case Calendar.WEDNESDAY: nome = "Quarta";break;
            case Calendar.THURSDAY: nome = "Quinta";break;
            case Calendar.FRIDAY: nome = "Sexta";break;
            case Calendar.SATURDAY: nome = "sábado";break;
        }

        if (diaChavoso == 25 && mesChavoso == 3){
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 30 && mesChavoso == 3){
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 21 && mesChavoso == 4){
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 1 && mesChavoso == 5) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 31 && mesChavoso == 5) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 7 && mesChavoso == 9) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 12  && mesChavoso == 10) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 2 && mesChavoso == 11) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 15 && mesChavoso == 11) {
            diasUteis = diasUteis -1;
        } else if (diaChavoso == 25 && mesChavoso == 12) {
            diasUteis = diasUteis -1;
        }

        if (weekDay(Calendar.getInstance()).equals("Monday")){
            diaDaSemana = "SEG";
        } else if (weekDay(Calendar.getInstance()).equals("Tuesday")) {
            diaDaSemana = "TER";
        } else if (weekDay(Calendar.getInstance()).equals("Wednesday")) {
            diaDaSemana = "QUA";
        } else if (weekDay(Calendar.getInstance()).equals("Thursday")) {
            diaDaSemana = "QUI";
        } else if (weekDay(Calendar.getInstance()).equals("Friday")) {
            diaDaSemana = "SEX";
        } else if (weekDay(Calendar.getInstance()).equals("Saturday")) {
            diaDaSemana = "SAB";
        } else if (weekDay(Calendar.getInstance()).equals("Sunday")) {
            diaDaSemana = "DOM";
        }

        if (nome == "Segunda"){
            if (mesChavoso == 2 && diaChavoso == 13){
                diaCorrente = diaChavoso - 1;
            }
           diaCorrente = diaChavoso;
        }
        else if (nome == "Terça") {
            if (mesChavoso == 3 && diaChavoso == 25){
                diaCorrente = diaChavoso - 1;
            } else if (mesChavoso == 3 && diaChavoso == 30){
                diaCorrente = diaChavoso - 1;
            }
            diaCorrente = diaChavoso;
        }
        else if (nome == "Quarta") {
            if (mesChavoso == 4 && diaChavoso == 21){
                diaCorrente = diaChavoso - 1;
            }
            diaCorrente = diaChavoso;
        }
        else if (nome == "Quinta") {
            if (mesChavoso == 4 && diaChavoso == 31){
                diaCorrente = diaChavoso - 1;
            }
            diaCorrente = diaChavoso;
        }
        else if (nome == "Sexta") {
            if (mesChavoso == 10 && diaChavoso == 12){
                diaCorrente = diaChavoso - 1;
            }
            diaCorrente = diaChavoso;
        } else if (nome == "sábado") {

            diaCorrente = diaChavoso - 1;
        }
        else if (nome == "Domingo") {
            diaCorrente = diaChavoso - 1;
        }
        float chavao;
        DaoTotalVendas daoTotalVendas1 = new DaoTotalVendas();
        chavao = (daoTotalVendas1.getTotalVendasRelatorio(getContext(), 5));

       warrant = chavao / diaCorrente;
        Log.e("daosjdkaw", String.valueOf(chavao));
                switch(mesChavoso) {
            case 0:
                break;
            case 1:
               // Toast.makeText(getContext(),ret2, Toast.LENGTH_SHORT).show();
                break;
            case 2:
               projecao = fevereiroUtil * vendaDiaria;
                break;
            case 3:
                projecao = marcoUtil * vendaDiaria;
                break;
            case 4:
                projecao = abrilUtil * vendaDiaria;
                break;
            case 5:
                projecao = maioUtil * vendaDiaria;
                break;
            case 6:
                projecao = junhoUtil * vendaDiaria;
                break;
            case 7:
                projecao = julhoUtil * vendaDiaria;
                break;
            case 8:
                projecao = agostoUtil * vendaDiaria;
                break;
            case 9:
                projecao = setembroUtil * vendaDiaria;
                break;
            case 10:
                projecao = outubroUtil * vendaDiaria;
                break;
            case 11:
                projecao = novembroUtil * vendaDiaria;
                break;
            case 12:
                projecao = dezembroUtil * vendaDiaria;
                break;
            default:
                Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
        }

        Log.e("testeChavao", String.valueOf(chavao));
        Log.e("testeChavao", String.valueOf(diaCorrente));
        Log.e("testeChavao", String.valueOf(warrant));
        float meta = daoMetaDia.getMetaMensal(getContext(), 5);
        final float v1 = meta;
        final float v2 = chavao / diaCorrente;
        final float v3 = projecao;
        entries.add(new BarEntry(v1, 0));
        entries.add(new BarEntry(v2, 1));
        entries.add(new BarEntry(v3, 2));

        Log.e("logRelatorioMeta", String.valueOf(v1));
        Log.e("logRelatorioVendaDiaria", String.valueOf(v2));
        Log.e("logRelatorioProjecao", String.valueOf(v3));

//        for(int i = 0; i < fevereiroInvalid.length; i++){
//            Log.v("testeSsd","Numero : " + fevereiroInvalid[i] + " Dia :" + diaChavoso );
//            if (fevereiroInvalid[i] == diaChavoso);{
//                Log.e("logLoopTeste", "FAIL");
//            }
//        }

//        switch(mesChavoso) {
//            case 0:
//                break;
//            case 1:
//                Toast.makeText(getContext(),ret2, Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//               warrant = totalDeVendas/fevereiro;
//               Log.e("foajpfs", String.valueOf(warrant));
//                break;
//            case 3:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 4:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 5:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 6:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 7:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 8:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 9:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 10:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 11:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            case 12:
//                Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
//        }


        //        if (weekDay(Calendar.getInstance()).equals("Monday")){
//            diaCorrente = 2;
//            Toast.makeText(getContext(),"Hojé é Segunda" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Tuesday")) {
//            diaCorrente = 3;
//            Toast.makeText(getContext(),"Hojé é Terça" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Wednesday")) {
//            diaCorrente = 4;
//            Toast.makeText(getContext(),"Hojé é Quarta" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Thursday")) {
//            diaCorrente = 5;
//            Toast.makeText(getContext(),"Hojé é Quinta" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Friday")) {
//            diaCorrente = 6;
//            Toast.makeText(getContext(),"Hojé é Sexta" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Saturday")) {
//            diaCorrente = 7;
//            Toast.makeText(getContext(),"Hojé é Sábado" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        } else if (weekDay(Calendar.getInstance()).equals("Sunday")) {
//            diaCorrente = 1;
//            Toast.makeText(getContext(),"Hojé é Domingo" + "e dia:"+diaCorrente, Toast.LENGTH_SHORT).show();
//        }


        Legend l = barChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setTextColor(Color.BLACK);
        l.setTextSize(14f);
        l.setXEntrySpace(5f);
        l.setYEntrySpace(5f);

        l.setCustom(ColorChange.Relatorio_Color, new String[] { "Meta", "Média Diária", "Projeção" });

        //BarDataSet dataset = new BarDataSet(entries, "# Indicativos");
        BarDataSet dataset = new BarDataSet(entries, null);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        labels.add("");

        BarDataSet set = new BarDataSet(entries, "BarDataSet");


        ret1 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(v1);
        ret2 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(v2);
        ret3 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(v3);

        barChart.setOnChartValueSelectedListener( new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                switch(e.getXIndex()) {
                    case 0:
                        Toast.makeText(getContext(),ret1, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),ret2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(),ret3, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                }

                    //Toast.makeText(getContext(), "R$" + String.valueOf(e.getXIndex()), Toast.LENGTH_SHORT).show();
//                    if ((e.getXIndex()) == 0){
//                        Toast.makeText(getContext(), "R$ 5000,00" + e.getXIndex(), Toast.LENGTH_SHORT).show();
//                    } else if ((e.getXIndex()) == 1){
//                        Toast.makeText(getContext(), "R$ 953,00" + e.getXIndex(), Toast.LENGTH_SHORT).show();
//                    } else {
//                    Toast.makeText(getContext(), "R$ 12000,00" + e.getXIndex(), Toast.LENGTH_SHORT).show();
//                }
            }

            public void updateProgress(final int timePassed){
                if (null != mProgressBar){
                    final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
                    mProgressBar.setProgress(progress);
                }
            }

            @Override
            public void onNothingSelected() {

            }


        });

//        barChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), RelatoriosActivity.class);
//                intent.putExtra("Lista", "Vendas");
//                startActivity(intent);
//            }
//        });

//        barChart.setOnChartValueSelectedListener ( new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                Toast.makeText(getContext(), "My Nigga !", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });

        /* for create Grouped Bar chart
        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(4f, 0));
        group1.add(new BarEntry(8f, 1));
        group1.add(new BarEntry(6f, 2));
        group1.add(new BarEntry(12f, 3));
        group1.add(new BarEntry(18f, 4));
        group1.add(new BarEntry(9f, 5));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(6f, 0));
        group2.add(new BarEntry(7f, 1));
        group2.add(new BarEntry(8f, 2));
        group2.add(new BarEntry(12f, 3));
        group2.add(new BarEntry(15f, 4));
        group2.add(new BarEntry(10f, 5));

        BarDataSet barDataSet1 = new BarDataSet(group1, "Group 1");
        //barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(group2, "Group 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<BarDataSet> dataset = new ArrayList<>();
        dataset.add(barDataSet1);
        dataset.add(barDataSet2);
        */

        BarData data = new BarData(labels, dataset);
        dataset.setColors(ColorChange.Relatorio_Color);
        data.setHighlightEnabled(true); // allow highlighting for DataSet
        barChart.setData(data);
        barChart.animateY(1300);

        barChart.setOnChartGestureListener(this);
        // Inflate the layout for this fragment
        return rootView;
        //inflater.inflate(R.layout.fragment_relatorios, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //Toast.makeText(context, "Fragment Relatorios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END");
        barChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
