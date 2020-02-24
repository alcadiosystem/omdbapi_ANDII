/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author SUPER
 */
public class SentenciasSQL {
    
    private static PreparedStatement inseratarSerie;
    
    private static PreparedStatement selectAllSerie;
    
    private static PreparedStatement selectByName;
    
    private static PreparedStatement selectByYear;
    
    private static PreparedStatement selectByNamYear;
    
    public static PreparedStatement setInsertSerie() throws SQLException{
        inseratarSerie = Conexion.getConexion().prepareStatement("INSERT INTO `omdbapi`.`seire` (`actors`, `awards`, `country`, `director`, `episode`, `genre`, `language`, `metascore`, `plot`, `poster`, `rated`, `released`, `response`, `runtime`, `season`, `title`, `type`, `writer`, `year`, `imdbID`, `imdbRating`, `imdbVotes`, `seriesID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return inseratarSerie;
    }
    
    public static PreparedStatement getSelectAll() throws SQLException{
        selectAllSerie = Conexion.getConexion().prepareStatement("SELECT seire.imdbID AS ID, seire.title AS NOM FROM seire");
        return selectAllSerie;
    }
    
    public static PreparedStatement getSelectByName() throws SQLException{
        selectByName = Conexion.getConexion().prepareStatement("SELECT * FROM seire WHERE title LIKE ?");
        return selectByName;
    }
    
    public static PreparedStatement getSelectByYear() throws SQLException{
        selectByYear = Conexion.getConexion().prepareStatement("SELECT * FROM seire WHERE year LIKE ?");
        return selectByYear;
    }
    
    public static PreparedStatement getSelectByNamYear() throws SQLException{
        selectByNamYear = Conexion.getConexion().prepareStatement("SELECT * FROM seire WHERE seire.`year` = ? AND seire.title = ?");
        return selectByNamYear;
    }
    
}
