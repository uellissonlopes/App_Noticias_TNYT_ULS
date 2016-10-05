package com.made.uellisson.desafio_bemvendi_uls.modelo;

/**
 * Created by Uellisson on 27/09/2016.
 *
 * Classe Noticia que sera o modelo da nossa noticia, especificanto todos
 * os argurmentos de uma noticia, seu contrutor e metodos acesso.
 */
public class Noticia {

    //Definicao dos atributos da classe noticia
    private String section;
    private String subsection;
    private String title;
    private String abstract_nt;
    private String url;

    /**
     * Construtor da Classe Noticia, com todos os seus 05 parametros:
     * @param section
     * @param subsection
     * @param title
     * @param abstract_nt
     * @param url
     *
     * Este metodo e utizado sempre que queremos criar um objeto do tipo Noticia.
     *
     */
    public Noticia(String section, String subsection, String title, String abstract_nt, String url) {
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this.abstract_nt = abstract_nt;
        this.url = url;
    }

    /**
     * Metodo get, usado para recuperamos o conteudo dos atributos do
     * objeto noticia.
     *
     * Nesse sentido existe um para cada atributo da classe, como pode-se observar abaixo.
     *
      * @return section
     */
    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract_nt() {
        return abstract_nt;
    }

    public String getUrl() {
        return url;
    }

    public void setAbstract_nt(String abstract_nt) {
        this.abstract_nt = abstract_nt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
