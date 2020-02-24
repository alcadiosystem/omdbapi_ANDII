/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SUPER
 */
public class Serie {
    
    
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Season;
    private String Episode;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private List<Rating> Ratings = null;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String seriesID;
    private String Type;
    private String Response;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String Season) {
        this.Season = Season;
    }

    public String getEpisode() {
        return Episode;
    }

    public void setEpisode(String Episode) {
        this.Episode = Episode;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String Awards) {
        this.Awards = Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Rating> Ratings) {
        this.Ratings = Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String Metascore) {
        this.Metascore = Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "Serie{" + "Title=" + Title + ", Year=" + Year + ", Rated=" + Rated + ", Released=" + Released + ", Season=" + Season + ", Episode=" + Episode + ", Runtime=" + Runtime + ", Genre=" + Genre + ", Director=" + Director + ", Writer=" + Writer + ", Actors=" + Actors + ", Plot=" + Plot + ", Language=" + Language + ", Country=" + Country + ", Awards=" + Awards + ", Poster=" + Poster + ", Ratings=" + Ratings + ", Metascore=" + Metascore + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + ", imdbID=" + imdbID + ", seriesID=" + seriesID + ", Type=" + Type + ", Response=" + Response + ", additionalProperties=" + additionalProperties + '}';
    }

    
    
}
