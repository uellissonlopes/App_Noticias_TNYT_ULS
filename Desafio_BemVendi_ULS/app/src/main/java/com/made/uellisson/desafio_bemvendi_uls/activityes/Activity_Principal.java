package com.made.uellisson.desafio_bemvendi_uls.activityes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.made.uellisson.desafio_bemvendi_uls.Controle.Controle;
import com.made.uellisson.desafio_bemvendi_uls.R;
import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;
import com.made.uellisson.desafio_bemvendi_uls.webService.Processamento_Obj;

import java.util.ArrayList;


/**
 * Sera nossa Actiity Principal, que usaremos para baiaxa as informacoes do webservice
 * e chamada a Act_lista_Noticia
 */
public class Activity_Principal extends AppCompatActivity  {


    private ProgressDialog load;
    Controle ct;

    /**
     * Metodo que cria a tela.
     *
     * Ele e o primiero a iniciar, desse modo carrega o layout da tela e inicializa os componetes.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ct = new Controle();

        //verifica se ja baixamos os dados do webservice, desse modo os dados so sao baixados uma vez
        if (ct.busca_lista_titulos(this)==null){
            GetJson download = new GetJson();

            //Chama Async Task para fazer o downloada
            download.execute();
        }
        else {
            //se ja tiver os dados, apenas mostramos uma mensavem e carregamos a lista de noticias

            mensagemExibir("Os dados do WebService já estão salvos no disco!");

            Intent it = new Intent(this, Act_Lista_Noticias.class);
            startActivity(it);
            finish();

        }

    }

    /**
     * Classe responsavel por fazer toda a manipulacao do webservice
     * em paralelo, para que a aplicacao nao pareca esta travada
     */
    private class GetJson extends AsyncTask<Void, Void, ArrayList<Noticia> > {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(Activity_Principal.this, "Fazendo Download das Notícias...", "Por Favor Aguarde um Momento...");
        }

        @Override
        protected ArrayList<Noticia> doInBackground(Void... params) {
            Processamento_Obj processa = new Processamento_Obj();

            //url do webservice com a devida chave obtida com o proprietario do servico
            String url_ws = "https://api.nytimes.com/svc/topstories/v1/home.json?api-key=223bdd1c164e4484a2c60e0c564bf146";

            return processa.getInformacao(url_ws);
            //return util.getInformacao("https://randomuser.me/api/");
        }

        @Override
        protected void onPostExecute(ArrayList<Noticia> lista_noticias){

            Controle ct = new Controle();

            if (ct.salva_list_noticias(Activity_Principal.this, lista_noticias)){
                mensagemExibir("Dados Salvos com Sucesso!");
            }

            else{
                mensagemExibir("Problema para salvar dados da Noticia!");
            }

            Intent it = new Intent(Activity_Principal.this, Act_Lista_Noticias.class);
            startActivity(it);
            finish();

            //finaliza a barra de progresso
            load.dismiss();
        }
    }

    /**
     * Gerenciador de mensagens que sao exibidas na tela
     */
    private void mensagemExibir(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
