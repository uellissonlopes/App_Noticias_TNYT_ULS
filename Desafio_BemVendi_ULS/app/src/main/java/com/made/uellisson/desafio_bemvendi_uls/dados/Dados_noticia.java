package com.made.uellisson.desafio_bemvendi_uls.dados;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;

import java.util.ArrayList;

/**
 * Created by Uellisson on 27/09/2016.
 *
 * Classe responsavel por fazer as operacoes de insercao e busca no banco de dados.
 * utiliza metodos das classes Banco_de_dados e Script_Noticia
 */
public class Dados_noticia {

    private SQLiteDatabase conexao;

    public Dados_noticia(SQLiteDatabase conexao){
        this.conexao = conexao;

    }

    /**
     * Salva a noticia passada como parametro
     * @param noticia
     * @return
     */
    public String salvar_noticia (Noticia noticia){

        //retira a aspas simples para nao ter problema ao salvar no banco de dados
        noticia.setSection(noticia.getSection().replace("\'", " "));
        noticia.setSubsection(noticia.getSubsection().replace("\'", " "));
        noticia.setTitle(noticia.getTitle().replace("\'", " "));
        noticia.setAbstract_nt(noticia.getAbstract_nt().replace("\'", " "));

        String comando_salvar = Script_Noticia.salvar_Noticia(noticia);

        conexao.execSQL(comando_salvar);

        return comando_salvar;
    }

    /**
     * Busca uma lista com o titulo de todas as noticias salvas no banco de dados
     * @return
     */
    public ArrayList<String> busca_titulo_noticias(){
        ArrayList<String> titulos = null;

        Cursor cursor = null;

        //armazena todos os dados no cursor
        cursor = conexao.query("tb_noticia", null, null, null, null, null, null);

        if(cursor.getCount()>0){
            titulos = new ArrayList<String>();

            cursor.moveToFirst();
            do {

                //o titule e a quarta colouna da tebela, como comca de 0 é representada pelo 3
                String title = cursor.getString(3);

                titulos.add(title);
            }
            while (cursor.moveToNext());
        }

        return titulos;
    }



    /**
     * Retorna uma noticia, que tem o id passadao como parametro
     * @param id
     * @return
     */
    public Noticia busca_noticia_id(String id){
        Noticia noticia = null;
        Cursor cursor = null;

        //armazena todos os dados no curso, ordenados pelo titulo
        cursor = conexao.query("tb_noticia", null, null, null, null, null, null);


        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {



                if (String.valueOf(cursor.getInt(0)).equalsIgnoreCase(id)){
                    String section = cursor.getString(1);
                    String subsection = cursor.getString(2);
                    String title = cursor.getString(3);
                    String abstract_nt = cursor.getString(4);
                    String url = cursor.getString(5);

                    noticia = new Noticia(section, subsection, title, abstract_nt, url);

                }

            }
            while (cursor.moveToNext());
        }

        return noticia;
    }

}
