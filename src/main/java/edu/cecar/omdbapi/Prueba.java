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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static boolean isDB = true;
    
    private static final String rutaCSV = "./src/main/java/recurso/base_datos.csv";
    
    private static Serie serie;
    
    private static List<Serie> listaSerieURL;
    
    private static List<String> listaCSVExtraida = new ArrayList<String>();
    
    
    public static void main(String[] args) throws IOException, JSONException{
        //instancia de nueva conexion a base de datos
        new Conexion().getInstancia("localhost", "omdbapi", "root", "");
        //pruebaArchivoCodigo();
        String[] arg = analizar("getDatosPeliculas [-d d]");
        
        System.out.println("Tamaño = " + arg.length);
        for (int i = 0; i < arg.length; i++) {
            System.out.println("Elemneto => " + arg[i]);
        }
        
        String test = "getDatosPeliculas [–d d]";
        test = test.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        System.out.println(test);
        
        Scanner sc = new Scanner(System.in);
        String comando = "";
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
            System.out.println("Comando ==> " + comando);
            int rpta = comandoUsuario(comando);
            
            switch(rpta){
                case 0:
                    estado = true;
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|                          Chao bambini.                                    |");
                    System.out.println("+---------------------------------------------------------------------------+");
                    break;
                case 1:
                    getDatosPeliculas(analizar(comando));
                    break;
                case 2:
                    System.out.println("getDatosSeries");
                    break;
                case 3:
                    System.out.println("getPelicula");
                    break;
                default:
                    System.out.println("Nooo");
                    break;
            }
            
        } while (!estado);
                
    }
    
    /**
     * Funcion que permite obtener los datos de la api
     * @param codigo Codigo de la serie
     * @return boolean
     */
    private static Serie getDatosURL(String codigo){
        
        try {
            //Intnacia de la libreria resty que dijo
            Resty resty = new Resty();
            //Esta es la libreria de google para manejo de json
            Gson gson = new Gson();
            //Obtenemos el objeto json desde la api
            JSONObject resultado = resty.json(BASE_URL + codigo).object();
            //Mediante la libreria de ggogle gson convertimos el objeto json en objeto de clase serie
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
        
    /***
     * Funcion que permite leer el archivo de los comandos
     * @return List<Serie>
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static List<Serie> leerArchivoCodigo() throws FileNotFoundException, IOException{
        
        listaSerieURL = new ArrayList<Serie>();
        /**
         * El archivo hola.txt contiene un pequeño contenido de los codigos
         * Los archivos codigos y series son los que pasasate y generan ese problemita
         * El archivo serie2 es el nuevo que copie el contenido y pege hablar con medez de eso
         * ojo borrar este comentario
         */
        File codigo = new File("./src/main/java/recurso/hola.txt");
        //Por si las moscas establecemos la codificacion utf-8
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "utf-8"));
        
        String cadena;
        
        while((cadena = br.readLine())!=null){
            
            cadena = cadena.replaceAll("\n", "");
            //Llamamos al metodo que obtiene los datos desde la api
            Serie s = getDatosURL(cadena);
            
            System.out.println("Mouestro => " + s.toString());
            
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
    
    
    private static void pruebaArchivoCodigo() throws FileNotFoundException, IOException{
                
        
        File codigo = new File("./src/main/java/recurso/series.txt");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "utf-8"));
        
        String cadena;
        
        while((cadena = br.readLine())!=null){
            cadena = cadena.replaceAll("^\\\\s*","");
            
            System.out.println("Codigo => " + cadena.replaceAll("\\s","")); //Muestra el problema de lectura
            
//            Serie s = getDatosURL(cadena);   este muestra el error de la api
//            System.out.println("Mouestro => " + s.toString());
        }        
        
    }
    
    
    /**
     * Control para las operaciones del menu
     * @param cmd
     * @return 
     */
    private static int comandoUsuario(String cmd){                
        
        cmd = cmd.toLowerCase();
        
        int res = 0;
        if(cmd.contains("getdatospeliculas")){
            res = 1;
        }else if(cmd.contains("getdatosseries")){
            res = 2;
        }else if(cmd.contains("getpelicula")){
            res = 3;
        }else if(cmd.contains("exit")){
            res = 0;
        }
        return res;
    }
    
     /***
     * Funcion que permite crear y/o escribir un archivo csv
     * @param lista
     * @throws IOException 
     */
    private static void archivoCSV(List<Serie> lista) throws IOException{               
        //Establecemos el archivo
        File fichero = new File(rutaCSV);
        //La libreria opencsv la instanciamos para la creacion de los archivos
        CSVWriter csv;
        //verificamos si existe
        if(!fichero.exists()){
            //Si no existe lo creamos
            if(fichero.createNewFile()){
                //Lo abrimos para escribirlo
                csv = new CSVWriter(new FileWriter(fichero));
                //El formato csv es un formato de datos especial, para eso lo convertimos asi
                List<String[]> data = toArrayString(lista);
                //Lo mandamos a escribir
                csv.writeAll(data);
                //Cerramos
                csv.close();
            }
        }else{
            //Jajajajay lo mismo de arriba x2
            csv = new CSVWriter(new FileWriter(fichero));
            List<String[]> data = toArrayString(lista);
            csv.writeAll(data);
            csv.close();
        }
                
    }        
    
    /***
     * Funcion que permite convertir el array de objeto a un array de string
     * @param lista Serie
     * @return 
     */
    private static List<String[]> toArrayString(List<Serie> lista){
        //Este metodo lo proporciona la pagina asi que no problem ellos te dicen que lo uses
        //Preguntar al mendez por que el primer parametro lo guarda si problemas y el reto le agrega "" en el archivo
        List<String[]> records = new ArrayList<String[]>();
        //Aqui hacemos la convercion de los datos
        Iterator<Serie> it = lista.iterator();
        while(it.hasNext()){
            //Y aqui tambien
            Serie s = it.next();
            records.add(
                    new String[] {
                        "{","Title=" + s.getTitle(),"Year=" + s.getYear(),"Rated=" + s.getRated(),"Released=" + s.getReleased(),"Season=" + s.getSeason(),"Episode=" + s.getEpisode(),"Runtime=" + s.getRuntime(),"Genre=" + s.getGenre(),"Director=" + s.getDirector()," Writer=" + s.getWriter(),"Actors=" + s.getActors(),"Plot=" + s.getPlot(),"Language=" + s.getLanguage(),"Country=" + s.getCountry() ,"Awards=" + s.getAwards() ,"Poster=" + s.getPoster() ,"Ratings=" + s.getRatings() ,"Metascore=" + s.getMetascore() ,"imdbRating=" + s.getImdbRating() ,"imdbVotes=" + s.getImdbVotes(), "imdbID=" + s.getImdbID() ,"seriesID=" + s.getSeriesID(), "Type=" + s.getType(), "Response=" + s.getResponse(), "}"
                    }
            );
        }
        return records;
    }
    
    
    /**
     * Permite leer un archivo csv
     */
    private static void leerCSV(){
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(rutaCSV));
            while((line = br.readLine()) != null){
                listaCSVExtraida.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Funcion que permite buscar los elementos en base a lo pasado por parametros
     * @param buscar 
     */
    private static void buscarElementoCSV(String buscar){
        String csvSplitBy = ",";
        for (String res : listaCSVExtraida) {
            res = res.toLowerCase();
            if(res.contains(buscar)){
                String[] datos = res.split(csvSplitBy);
                System.out.println("Mostrar => " + res);
            }
        }
    }
        
    
    /***
     * Funcion que permite mostrar por consola las operaciones pertinente al metodo
     * @param comando
     * @param parametro
     * @param aux 
     */
    private static void getPelicula(String comando, String parametro, int aux){
        
        if(aux == 0){
            String csvSplitBy = ",";
            for (String res : listaCSVExtraida) {
                res = res.toLowerCase();
                String[] datos = res.split(csvSplitBy);
                System.out.println("Mostrar => " + datos[0]);
            }
        }else if(comando.equals("-b")){
            
        }else if(comando.equals("-a")){
            
        }
    }
    
    /***
     * Funcion que permite insertar en base de datos las series
     * @param comando
     * @param parametro 
     */
    private static void getDatosSeries(String comando,String parametro){
        
    }
    
    /***
     * Funcion que permite guardar en base datos o archivo csv lo obtenido por 
     * la url api
     * @param comando
     * @param parametro
     * @param aux 
     */
    private static void getDatosPeliculas(String[] cmd){
        System.out.println("Tamaño " + cmd.length);
        if(cmd.length == 3){
            String comando = cmd[1].toString();
            String base_datos = cmd[2];
            if(comando.contains("-d")){
                if(base_datos.contains("omdbapi")){
                    isDB = true;
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|                       Proceso Iniciado.                                   |");
                    System.out.println("+---------------------------------------------------------------------------+");
                    
                    
                    
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|                    Base de datos completa.                                |");
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|                       Terminado el proceso.                               |");
                    System.out.println("+---------------------------------------------------------------------------+");
                }else{
                    System.out.println("+---------------------------------------------------------------------------+");
                    System.out.println("|           El nombre de la base de datos es incorrecta.                    |");
                    System.out.println("+---------------------------------------------------------------------------+");
                }
            }else{
                System.out.println("+---------------------------------------------------------------------------+");
                System.out.println("|              El comando ingresado no es el correcto.                      |");
                System.out.println("+---------------------------------------------------------------------------+");
            }
        }else if(cmd.length == 2){
            String comando = cmd[1].toString();
            if(comando.contains("-d")){
                isDB = false;
                System.out.println("+---------------------------------------------------------------------------+");
                System.out.println("|        No se especifico una base de datos, se creara un archivo csv       |");
                System.out.println("+---------------------------------------------------------------------------+");
            }else{
                System.out.println("+---------------------------------------------------------------------------+");
                System.out.println("|              El comando ingresado no es el correcto.                      |");
                System.out.println("+---------------------------------------------------------------------------+");
            }
        }else if(cmd.length == 1 || cmd.length == 0){
            System.out.println("+---------------------------------------------------------------------------+");
            System.out.println("|              El comando ingresado no es el correcto.                      |");
            System.out.println("+---------------------------------------------------------------------------+");
        }
    }
    
    
    
    
    private static String[] analizar(String cmd){
        
        cmd = cmd.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        
        String[] args = cmd.split(" ");
            
        return args;
    }
    
}
