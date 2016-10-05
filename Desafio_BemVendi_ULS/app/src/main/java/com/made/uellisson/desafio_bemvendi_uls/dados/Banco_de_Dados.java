package com.made.uellisson.desafio_bemvendi_uls.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Uellisson on 27/09/2016.
 *
 * Esta classe e responsavel por criar o banco de dados e a tabela
 */

public class Banco_de_Dados extends SQLiteOpenHelper {

    /**
     * Construtor da classe que recebe um contex como parametos e cria o banco de dados, que tem o nome desafio
     * @param context
     */
    public Banco_de_Dados(Context context) {
        super(context, "desafio", null, 1);

    }

    /**
     * Metodo que cria a tabela de noticias, atraves da execusao do codigo que esta no
     * Script_Noticia, que foi criado para facilitar o entendimento do codigo e possivies
     * modifiicacoes.
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Script_Noticia.abreOuCria_tb_noticia());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
