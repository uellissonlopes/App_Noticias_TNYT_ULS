package com.made.uellisson.desafio_bemvendi_uls.Controle;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.made.uellisson.desafio_bemvendi_uls.dados.Banco_de_Dados;
import com.made.uellisson.desafio_bemvendi_uls.dados.Dados_noticia;
import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;

import java.util.ArrayList;

/**
 * Created by Uellisson on 27/09/2016.
 * Essa Classe e responsavel pela chamada de metodos da classe de dados, gerenciando a comunicacao
 * entre ele da camada de visao (activityes).
 */

public class Controle {

    Banco_de_Dados bd;
    SQLiteDatabase conexao;


    /**
     * Salva todos os objetos de um lista do Noticias
     * @param context
     * @param lista_noticias
     * @return true se for sao com sucesso ou false se tiver algum problema
     */
    public boolean salva_list_noticias(Context context, ArrayList<Noticia> lista_noticias){
        try {
            //cria conexão com db
            bd = new Banco_de_Dados(context);
            conexao = bd.getReadableDatabase();
            bd.onCreate(conexao);

            //salva dados da noticia
            Dados_noticia dados_noticia = new Dados_noticia(conexao);

            if (lista_noticias!=null){
                for (int i=0; i<lista_noticias.size(); i++){

                    dados_noticia.salvar_noticia(lista_noticias.get(i));
                }
            }

            return true;
        }
        //gerenciamento de excessões
        catch (SQLException erro){
            Log.d("Erro: ", "erro salvar: "+erro.getMessage());
            return  false;
        }
    }


    /**
     * Busca o titulo de todas as noticias que estao no banco de dados
     * @param context
     * @return ArrayAdapter<String> titulos
     */
    public ArrayAdapter<String> busca_lista_titulos(Context context){

        ArrayList<String> lista_titulos = null;
        ArrayAdapter<String> titulos_adp = null;

        try {

            //cria conexão com db
            bd = new Banco_de_Dados(context);
            conexao = bd.getReadableDatabase();
            bd.onCreate(conexao);

            //salva dados da noticia
            Dados_noticia dados_noticia = new Dados_noticia(conexao);
            lista_titulos = dados_noticia.busca_titulo_noticias();

            if (lista_titulos!=null){
                titulos_adp = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1);

                for (int i=0; i<lista_titulos.size(); i++){
                    titulos_adp.add(lista_titulos.get(i));
                }

                return titulos_adp;
            }

            else {
                return null;
            }
        }

        //gerenciamento de excessões
        catch (SQLException erro){
            Log.d("Erro: ", "erro salvar: "+erro.getMessage());
            return  null;
        }

    }


    /**
     * Busca por uma noticia usando o id da noticia e o contex da view
     * @param context
     * @param id
     * @return noticia
     */
    public Noticia busca_noticia(Context context, String id){
        Noticia noticia = null;
        try {
            //cria conexão com db
            bd = new Banco_de_Dados(context);
            conexao = bd.getReadableDatabase();
            bd.onCreate(conexao);

            Dados_noticia dados_noticia = new Dados_noticia(conexao);

            if (dados_noticia.busca_titulo_noticias()!=null){
                noticia = dados_noticia.busca_noticia_id(id);
            }

            return noticia;
        }
        //gerenciamento de excessões
        catch (SQLException erro){
            Log.d("Erro: ", "erro salvar: "+erro.getMessage());
            return  null;
        }

    }

}
