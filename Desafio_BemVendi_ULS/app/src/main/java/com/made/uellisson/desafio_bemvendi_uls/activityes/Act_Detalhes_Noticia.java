package com.made.uellisson.desafio_bemvendi_uls.activityes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.made.uellisson.desafio_bemvendi_uls.Controle.Controle;
import com.made.uellisson.desafio_bemvendi_uls.R;
import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;

/**
 * Esta classe e responsavel por gerenciar a Activity com os detalhes da noticia,
 * que acessa o XML act_detalhes_noticia
 */
public class Act_Detalhes_Noticia extends AppCompatActivity implements View.OnClickListener{


    private TextView tv_section;
    private TextView tv_subsection;
    private TextView tv_title;
    private TextView tv_abstract;
    private TextView tv_url;

    private Button bt_voltar;
    String posicao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detalhes_noticia);

        tv_section = (TextView)findViewById(R.id.tv_section);
        tv_subsection = (TextView)findViewById(R.id.tv_subsection);
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_abstract = (TextView)findViewById(R.id.tv_abstract);
        tv_url = (TextView)findViewById(R.id.tv_url);

        bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(this);


        Bundle bdl = getIntent().getExtras();

        posicao = bdl.getString("posicao_id");

        carrega_infor(this, posicao);



    }

    /**
     * Carrega informacao do banco de dados e insere na tela
     * @param context
     */
    public void carrega_infor(Context context, String id){

        Controle ct = new Controle();

       // mensagemExibir(context, id);

        Noticia noticia = ct.busca_noticia(context, id);

        if (noticia!=null){
            tv_section.setText(noticia.getSection());
            tv_subsection.setText(noticia.getSubsection());
            tv_title.setText(noticia.getTitle());
            tv_abstract.setText(noticia.getAbstract_nt());
            tv_url.setText(noticia.getUrl());
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(this, Act_Lista_Noticias.class);

        startActivity(it);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view==bt_voltar){
            Intent it = new Intent(this, Act_Lista_Noticias.class);

            startActivity(it);
            finish();
        }
    }
}
