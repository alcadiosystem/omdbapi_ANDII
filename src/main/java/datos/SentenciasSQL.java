/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que prepara las sentencias sql de las operaciones
 * @author SUPER
 */
public class SentenciasSQL {
    
    private static PreparedStatement inseratarSerie;
    
    private static PreparedStatement selectAllSerie;
    
    private static PreparedStatement selectRatings;
    
    private static PreparedStatement insertRating;
    
    
    
    public static PreparedStatement setInsertSerie() throws SQLException{
        inseratarSerie = Conexion.getConexion().prepareStatement("INSERT INTO `omdbapi`.`seire` (`actors`, `awards`, `country`, `director`, `episode`, `genre`, `language`, `metascore`, `plot`, `poster`, `rated`, `released`, `response`, `runtime`, `season`, `title`, `type`, `writer`, `year`, `imdbID`, `imdbRating`, `imdbVotes`, `seriesID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return inseratarSerie;
    }
    
    public static PreparedStatement getSelectAll() throws SQLException{
        selectAllSerie = Conexion.getConexion().prepareStatement("SELECT seire.actors AS AC, seire.awards AS AW, seire.country AS CO, seire.director AS DI, seire.episode AS EP, seire.genre AS GE, seire.`language` AS LA, seire.metascore AS ME, seire.plot AS PL, seire.poster AS PO, seire.rated AS RA, seire.released AS RE, seire.response AS RES, seire.runtime AS RU, seire.season AS SE, seire.title AS TI, seire.type AS TY, seire.writer AS WR, seire.`year` AS YE, seire.imdbID AS IMID, seire.imdbRating AS IMRA, seire.imdbVotes AS IMVO, seire.seriesID AS SER FROM seire");
        return selectAllSerie;
    }
    
    public static PreparedStatement setInsertRating() throws SQLException{
        insertRating = Conexion.getConexion().prepareStatement("INSERT INTO `omdbapi`.`rating` (`imdbID`, `source`, `value`) VALUES (?, ?, ?)");
        return insertRating;
    }
    
    public static PreparedStatement getSelectRating() throws SQLException{
        selectRatings = Conexion.getConexion().prepareStatement("SELECT rating.source, rating.`value` FROM rating WHERE rating.imdbID = ?");
        return selectRatings;
    }
    
}
