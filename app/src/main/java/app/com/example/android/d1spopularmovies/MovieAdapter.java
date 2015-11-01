package app.com.example.android.d1spopularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by D1 on 8/24/2015.
 */
public class MovieAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Movie[] mThumbUrls;
    public ArrayList<Movie> movieList;


    String baseUrl = "http://image.tmdb.org/t/p/";
    String baseWidth = "w185";

    // Constructor
    public MovieAdapter(Context c,ArrayList<Movie> movieList ){
        this.mContext = c;
        this.mThumbUrls = new Movie[movieList.size()];
        this.mThumbUrls = movieList.toArray(mThumbUrls);


       // Log.i("Movie URL", "Movie 1 url " + mThumbUrls[0]);

    }

    @Override
    public int getCount() {
        //return movieList.size();
        return mThumbUrls.length;
    }

    @Override
    public Object getItem(int position) {


       // return movieList.toString();
        return mThumbUrls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String trueUrl = baseUrl+baseWidth+mThumbUrls[position].getUrlThumbnail();
        View imageView = convertView ;
        Holder holder = null;

        // This block of code sorta works
        // populates imageviews without the xml item
       /*
        Picasso.with(mContext).load(trueUrl).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 300));
        */

        if(imageView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            imageView = inflater.inflate(R.layout.grid_item_movie, parent, false);
            holder = new Holder();
            holder.img = (ImageView) imageView.findViewById(R.id.grid_item_movie_imageview);
            imageView.setTag(holder);
        }else {
            holder = (Holder)imageView.getTag();
        }

        Picasso.with(mContext).load(trueUrl).into(holder.img);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Movie movie = mThumbUrls[position];

                Intent intent = new Intent(mContext,DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, movie);
                mContext.startActivity(intent);
                //Toast.makeText(mContext, "You Clicked "+mThumbUrls[position].getTitle(), Toast.LENGTH_LONG).show();
            }
        });

       // Log.i("WTF",  "What The F*** is Parent? " + parent );
        //Log.i("Movie URL", "I'm in getview!! " + position);
        //Log.i("Movie URL", "This is myURL " + trueUrl);


        return imageView;
    }

    public static class Holder{
        ImageView img;
    }

}
