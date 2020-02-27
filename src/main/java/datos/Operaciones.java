/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que ejecuta las operaciones con la base de datos
 * @author SUPER
 */
public class Operaciones {
    
    private static boolean estado = false;
    
    /**
     * Funcion que permite insertar una pelicula y/o serie en la base de datos
     * @param s
     * @return 
     */
    public static boolean setInsertSerie(Serie s){
        try {
            PreparedStatement st = SentenciasSQL.setInsertSerie();
            
            st.setString(1, s.getActors());
            st.setString(2, s.getAwards());
            st.setString(3, s.getCountry());
            st.setString(4, s.getDirector());
            st.setString(5, s.getEpisode());
            st.setString(6, s.getGenre());
            st.setString(7, s.getLanguage());
            st.setString(8, s.getMetascore());
            st.setString(9, s.getPlot());
            st.setString(10, s.getPoster());
            st.setString(11, s.getRated());
            st.setString(12, s.getReleased());
            st.setString(13, s.getResponse());
            st.setString(14, s.getRuntime());
            st.setString(15, s.getSeason());
            st.setString(16, s.getTitle());
            st.setString(17, s.getType());
            st.setString(18, s.getWriter());
            st.setString(19, s.getYear());
            st.setString(20, s.getImdbID());
            st.setString(21, s.getImdbRating());
            st.setString(22, s.getImdbVotes());
            st.setString(23, s.getSeriesID());
            
            int n = st.executeUpdate();
            
            if(n != 0){
                estado = true;
            }else{
                estado = false;
            }
            
        } catch (Exception e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Error al tratar de insertar los datos.       |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Codigo de error=>"+s.getImdbID()+".       |");
            System.out.println(e.getMessage());
            System.out.println("+--------------------------------------------------+");
        }
        return estado;
    }
    
    /***
     * Funcion que permite insertar un rating
     * @param s
     * @return 
     */
    public static boolean setInsertRating(String imdbRating, String source, String value){
        try {
            PreparedStatement st = SentenciasSQL.setInsertRating();
                        
            st.setString(1, imdbRating);
            st.setString(2, source);
            st.setString(3, value);
            
            int n = st.executeUpdate();
            
            if(n != 0){
                estado = true;
            }else{
                estado = false;
            }
            
        } catch (Exception e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Error al tratar de insertar los datos.       |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Codigo de error=>"+imdbRating+".       |");
            System.out.println(e.getMessage());
            System.out.println("+--------------------------------------------------+");
        }
        return estado;
    }
    
    /***
     * 
     * @param id
     * @return 
     */
    public static ArrayList<Serie> getSerieDatos(){
        
        ArrayList<Serie> lista = new ArrayList<>();
        try {

            PreparedStatement stat = SentenciasSQL.getSelectAll();
            
            ResultSet rs = stat.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Serie s = new Serie();
                    s.setActors(rs.getString("AC"));                    
                    s.setAwards(rs.getString("AW"));
                    s.setCountry(rs.getString("CO"));
                    s.setDirector(rs.getString("DI"));
                    s.setEpisode(rs.getString("EP"));
                    s.setGenre(rs.getString("GE"));
                    s.setLanguage(rs.getString("LA"));
                    s.setMetascore(rs.getString("ME"));
                    s.setPlot(rs.getString("PL"));
                    s.setPoster(rs.getString("PO"));
                    s.setRated(rs.getString("RA"));
                    s.setReleased(rs.getString("RE"));
                    s.setResponse(rs.getString("RES"));
                    s.setRuntime(rs.getString("RU"));
                    s.setSeason(rs.getString("SE"));
                    s.setTitle(rs.getString("TI"));
                    s.setType(rs.getString("TY"));
                    s.setWriter(rs.getString("WR"));
                    s.setYear(rs.getString("YE"));
                    s.setImdbID(rs.getString("IMID"));
                    s.setImdbRating(rs.getString("IMRA"));
                    s.setImdbVotes(rs.getString("IMVO"));
                    s.setSeriesID(rs.getString("SER"));
                    
                    s.setRatings(getRating(s.getImdbID()));
                    
                    lista.add(s);
                }
            }

        } catch (Exception e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Error al tratar de traer los datos.          |");
            System.out.println("+--------------------------------------------------+");
            System.out.println(e.getMessage());
            System.out.println("+--------------------------------------------------+");

        }
        
        return lista;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private static List<Rating> getRating(String id){        
        List<Rating> lista = new ArrayList<>();
        try {

            PreparedStatement stat = SentenciasSQL.getSelectRating();
            stat.setString(1, id);
            
            ResultSet rs = stat.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Rating ra = new Rating();
                    ra.setSource(rs.getString("source"));
                    ra.setValue(rs.getString("value"));
                    lista.add(ra);
                }
            }

        } catch (Exception e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Error al tratar de traer los datos.          |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Codigo de error=>"+id+".       |");
            System.out.println(e.getMessage());
            System.out.println("+--------------------------------------------------+");

        }
        return lista;
    }
}
