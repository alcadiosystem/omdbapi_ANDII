/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;

/**
 *
 * @author SUPER
 */
public class Operaciones {
    
    private static boolean estado = false;
    
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
    
    
}
