package com.made.uellisson.desafio_bemvendi_uls.dados;

import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;

/**
 * Created by Uellisson on 27/09/2016.
 *
 * Esta classe tem por objetivo apresentar os scripts necessarios para fazer operacoes
 * no banco de dados SQLite
 */

public class Script_Noticia {
    /**
     * metodo que retorna uma String, com o codigo necessatio para abrir ou criar a tabela tb_noticia.
     *
     * Criamos um id auto incremento, para facilitar a busca da noticia.
     *
     * @return
     */
    public static String abreOuCria_tb_noticia() {
        String comandoSQL = "CREATE TABLE  IF NOT EXISTS tb_noticia (id INTEGER PRIMARY KEY AUTOINCREMENT, section TEXT, " +
                "subsection TEXT, title TEXT, abstract_nt TEXT, url TEXT);";

        return comandoSQL;
    }

    /**
     * Metodo que recebe como parametro uma Noticia e retorna uma string com o
     * codigo necessario para salvar a noticia no Banco de dados SqLite
     * @param noticia
     * @return
     */
    public static String salvar_Noticia (Noticia noticia){

        String comandoSQL = "INSERT INTO tb_noticia (section, subsection, title, abstract_nt, url) values ('"

                +noticia.getSection()+"','"+noticia.getSubsection()+"','"+noticia.getTitle()
                +"','"+noticia.getAbstract_nt()+"','"+noticia.getUrl()+"')";

        return comandoSQL;
    }
}
