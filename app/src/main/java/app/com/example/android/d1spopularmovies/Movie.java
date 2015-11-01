package app.com.example.android.d1spopularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

/**
 * Created by D1 on 8/24/2015.
 */
public class Movie implements Parcelable {


    private String title;
    private String plot;
    private String rating;
    private String releaseDate;
    private String urlThumbnail;

    public Movie(){

    }


    public Movie( String title, String plot, String myDate, String urlThumbnail,String rating ){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        this.title= title;
        this.plot = plot;
        this.releaseDate= myDate;
        this.urlThumbnail = urlThumbnail;
        this.rating = rating;
    }

    public Movie(Parcel input){
        title = input.readString();
        plot = input.readString();
        releaseDate = input.readString();
        urlThumbnail = input.readString();
        rating = input.readString();
    }

    @Override
    public int describeContents(){
        //L.m("describes contents of movie");
        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.title);
        dest.writeString(this.plot);
        dest.writeString(this.releaseDate);
        dest.writeString(this.urlThumbnail);
        dest.writeString(this.rating);

    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getUrlThumbnail() {
        return urlThumbnail;
    }
    public String getTitle(){
        return title;
    }
    public String getPlot(){
        return plot;
    }
    public String getRating(){
        return rating;
    }
    public String getReleaseDate(){
        return releaseDate;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setPlot(String plot){
        this.plot = plot;
    }
    public  void setRating(String rating){
        this.rating = rating;
    }
    public void setUrlThumbnail(String urlThumbnail){
        this.urlThumbnail = urlThumbnail;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

}
