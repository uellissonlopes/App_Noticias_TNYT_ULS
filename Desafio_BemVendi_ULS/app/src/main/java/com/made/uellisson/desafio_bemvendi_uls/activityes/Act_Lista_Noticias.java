package com.made.uellisson.desafio_bemvendi_uls.activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.made.uellisson.desafio_bemvendi_uls.Controle.Controle;
import com.made.uellisson.desafio_bemvendi_uls.R;

/**
 * Esta classe e responsavel por gerenciar a Activity com o listview de noticias,
 * que acessa o XML act_detact_lista_noticias
 */
public class Act_Lista_Noticias extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv_noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_noticias);

        lv_noticias = (ListView) findViewById(R.id.lv_noticias);

        //ArrayAdapter<String> itens = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1);
        Controle ct = new Controle();

        //busca os titulos das noticias e insere na list view, com o setListAdapter
        ArrayAdapter<String> titulos_adp = ct.busca_lista_titulos(this);
        lv_noticias.setAdapter(titulos_adp);


        lv_noticias.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       Integer posicao = i+1;

        // mensagemExibir("Salvou", "Cliente Salvo com Sucesso");
        Intent it = new Intent(this, Act_Detalhes_Noticia.class);
        it.putExtra("posicao_id", posicao.toString());

        startActivity(it);
        finish();

    }
}
