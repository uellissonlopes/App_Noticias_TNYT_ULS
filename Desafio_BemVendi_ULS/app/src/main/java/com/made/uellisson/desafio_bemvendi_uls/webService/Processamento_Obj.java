package com.made.uellisson.desafio_bemvendi_uls.webService;

import android.util.Log;

import com.made.uellisson.desafio_bemvendi_uls.modelo.Noticia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Uellisson on 27/09/2016.
 */
public class Processamento_Obj {



    /**
     * Metodo que analisa e processa o arquivo json em formato String,
     * passado como parametro e retorna um objeto Noticia.
     *
     * @param json_string
     * @return Noticia
     */
    private ArrayList<Noticia> processa_Json(String json_string){

        ArrayList<Noticia> lista_noticias;
        Noticia noticia = null;

        JSONObject jsonObj = null;
        JSONArray array = null;
        JSONObject objArray = null;

        try {
            jsonObj = new JSONObject(json_string);
            array = jsonObj.getJSONArray("results");
            //objArray = array.getJSONObject(1);
            lista_noticias = new ArrayList<Noticia>();

            for (int i=0; i<array.length(); i++){
                objArray = array.getJSONObject(i);

                String section = objArray.getString("section");
                String subsection = objArray.getString("subsection");
                String title = objArray.getString("title");
                String abstract_nt = objArray.getString("abstract");
                String url = objArray.getString("url");

                noticia = new Noticia(section, subsection, title, abstract_nt, url);

                lista_noticias.add(noticia);

            }


            return lista_noticias;
        }

        catch (JSONException e){
            e.printStackTrace();
            Log.d("Erro", "erro null "+e.getMessage());
            return null;
        }

    }

    /**
     * Metodo que retorna as noticias, que foram trazidas no objeto json atraves do metodo getJSON_do_WS,
     * da classe Conexao_WS e processada pelo metodo processa_Json, desta classe.
     *
     * @param url
     * @return noticias
     */
    public ArrayList<Noticia> getInformacao(String url){
        String json;
        ArrayList<Noticia> noticia;
        json = Conexao_WS.getJSON_do_WS(url);
        Log.i("Resultado", json);

        noticia = processa_Json(json);

        return noticia;
    }
}
