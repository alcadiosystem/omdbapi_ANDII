/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.omdbapi;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import datos.Conexion;
import datos.Operaciones;
import datos.Serie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;

/**
 *
 * @author SUPER
 */
public class Prueba {
    
    private static String BASE_URL = "http://www.omdbapi.com/?apikey=77942afd&plot=full&i=";
    
    private static boolean estado = false;       
    
    private static Serie serie;
    
    private static List<Serie> listaSerieURL;
    
    
    public static void main(String[] args) throws IOException, JSONException{
        Scanner sc = new Scanner(System.in);
        String comando = "";
        String parametro = "";
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                 Bienvenido al sistema omdbapi de ACME.                    |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|    Dentro de este sistema podrá realizar las siguientes operaciones:      |");
        System.out.println("|    1) getDatosPeliculas [–d basededatos]                                  |");
        System.out.println("|       'bsade datos' es opcional                                           |");
        System.out.println("|    2) getDatosSeries [–d basededatos]                                     |");
        System.out.println("|       'bsade datos' es requerida                                          |");
        System.out.println("|    3) getPelicula [-n nombre|-a año]                                      |");
        System.out.println("|       'nombre' && 'año' es opcional                                       |");
        System.out.println("|    4) exit                                                                |");
        System.out.println("+---------------------------------------------------------------------------+");
        
        do {            
            System.out.println("+---------------------------------------------------------------------------+");
            System.out.println("|                    Ingrese el comando a ejecutar.                         |");
            System.out.println("+---------------------------------------------------------------------------+");
            comando = sc.nextLine();
            
            switch(comando){
                case "exit":
                    estado = true;
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|                          Chao bambini.                                    |");
                    System.out.println("+---------------------------------------------------------------------------+");
                    break;
                default:
                    System.out.println("Nooo");
                    break;
            }
            
        } while (!estado);
        
        //archivoCSV(leerArchivoCodigo());
        
//        new Conexion().getInstancia("localhost", "omdbapi", "root", "");
//        
//        for (int i = 0; i < datos.length; i++) {
//            System.out.println(getDatosURL(datos[i]));
//        }
    }
    
    /**
     * Funcion que permite obtener los datos de la api
     * @param codigo Codigo de la serie
     * @return boolean
     */
    private static Serie getDatosURL(String codigo){
        
        try {
            
            Resty resty = new Resty();
        
            Gson gson = new Gson();

            JSONObject resultado = resty.json(BASE_URL + codigo).object();

            serie = gson.fromJson(resultado.toString(), Serie.class);
            
            
        } catch (Exception e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|     Error al tratar de obtener los datos.        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println(e.getMessage());
            System.out.println("+--------------------------------------------------+");
        }
        return serie;
    }
        
    private static List<Serie> leerArchivoCodigo() throws FileNotFoundException, IOException{
        
        listaSerieURL = new ArrayList<Serie>();
        
        File codigo = new File("./src/main/java/recurso/hola.txt");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "utf-8"));
        
        String cadena;
        
        while((cadena = br.readLine())!=null){
            
            cadena = cadena.replaceAll("\n", "");
            
            System.out.println(getDatosURL(cadena));
            
            Serie s = getDatosURL(cadena);
            
            if(s.getResponse().equals("False")){
                System.out.println("+--------------------------------------------------+");
                System.out.println("|             Petición no aceptada.                |");
                System.out.println("+--------------------------------------------------+");
                System.out.println("|    Disculpe, pero los datos para el codigo:      |");
                System.out.println("|              " + cadena + "                            |");
                System.out.println("|    No existen o el codigo es incorrecto,         |");
                System.out.println("|    Por favor trate con otro codigo.              |");
                System.out.println("+--------------------------------------------------+");
            }else{
                listaSerieURL.add(s);
            }
        }
        System.out.println("Proceso de obtencion de datos finalizado");
        return listaSerieURL;
    }
        
    private static void archivoCSV(List<Serie> lista) throws IOException{        
        
        String csv = "./src/main/java/recurso/base_datos.csv";
        
        boolean existe = new File(csv).exists();
        
        File fichero = new File("./src/main/java/recurso","base_datos.csv");
        CSVWriter escritura;
        if(!existe){
            if(fichero.createNewFile()){
                escritura = new CSVWriter(new FileWriter(fichero));
                List<String[]> data = toArrayString(lista);
                escritura.writeAll(data);
                escritura.close();
            }
        }else{
            escritura = new CSVWriter(new FileWriter(fichero));
            List<String[]> data = toArrayString(lista);
            escritura.writeAll(data);
            escritura.close();
        }
                
    }        
    
    /***
     * Funcion que permite convertir el array de objeto a un array de string
     * @param lista Serie
     * @return 
     */
    private static List<String[]> toArrayString(List<Serie> lista){
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[]{"Title","Year","Rated","Released","Season","Episode","Runtime","Genre","Director","Writer","Actors","Plot","Language","Country","Awards","Poster","Ratings","Metascore","imdbRating","imdbVotes","imdbID","seriesID","Type","Response" });
        Iterator<Serie> it = lista.iterator();
        while(it.hasNext()){
            Serie s = it.next();
            records.add(new String[] { s.getTitle(),s.getYear(),s.getRated(),s.getReleased(),s.getSeason(),s.getEpisode(),s.getRuntime(),s.getGenre(),s.getDirector(),s.getWriter(),s.getActors(),s.getPlot(),s.getLanguage(),s.getCountry(),s.getAwards(),s.getPoster(),s.getRatings().toString(),s.getMetascore(),s.getImdbRating(),s.getImdbVotes(),s.getImdbID(),s.getSeriesID(),s.getType(),s.getResponse() });
        }
        return records;
    }
    
}
