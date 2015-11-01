package app.com.example.android.d1spopularmovies;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivityFragment extends Fragment {

    int count_em = 0;
    private MovieAdapter mMovieAdapter = null;
    GridView gridView = null;
    ArrayList<String> movieList = null;
    ArrayList<Movie> myMovieList = new ArrayList<Movie>();
    View rootView = null;
    String sortMovies="vote_average.desc"; // vote_average.desc or popularity.desc
    boolean prefChange = false;
    String APIKEY = "fc4ecf6be846cd6edcddb660fdd2f272";



    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Here", "I'm in onCreate" );
        // Create some dummy data for the GridView.
       /* String[] data = {
                "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                "/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg",
                "/6PgpI2Uj4s1solkGWaYXP5QgO1I.jpg",
                "/xxOKDTQbQo7h1h7TyrQIW7u8KcJ.jpg",
                "/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
                "/s5uMY8ooGRZOL0oe4sIvnlTsYQO.jpg",
                "/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg"
        };

        myMovieList.add(new Movie("Mad Max: Fury Road", "An apocalyptic story set in the furthest reaches of our planet," +
                " in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for" +
                " the necessities of life. Within this world exist two rebels on the run who just might be able to" +
                " restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following" +
                " the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a" +
                " woman who believes her path to survival may be achieved if she can make it" +
                " across the desert back to her childhood homeland", "2015-05-15", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg", "7.8"));
*/

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
       // sortMovies = prefs.getString(getString(R.string.pref_sort_key),
           //     getString(R.string.pref_sort_default));

       // String FsortMovies;

       //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
       //sortMovies = preferences.getString("sort_key","popularity.desc");


       // Log.i("SORT", "WTF IS IN HERE?! " + sortMovies);


        //update(sortMovies);



        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //new MoviesAsynTask().execute("popularity.desc");


        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sortMovies = preferences.getString("sort_key","popularity.desc");
        update(sortMovies);

        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                prefChange = true;
            }
        };

        preferences.registerOnSharedPreferenceChangeListener(listener);






        //movieList = new ArrayList<String>(Arrays.asList(data));



        //Log.i("MY JSON STRING", "This ish is long... " + jSondData);
        //Log.i("After", "I set gridView Adapter ");
        return rootView;
    }

    public void update(String sort){
        new MoviesAsynTask().execute(sort);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("Here", "I'm in onResume");

        if(prefChange) {
            myMovieList.clear();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            sortMovies = preferences.getString("sort_key", "popularity.desc");
            update(sortMovies);
            prefChange = false;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Here", "I'm in onStart");
        // The activity is about to become visible.
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("Here", "I'm in onPause");
        // The activity is about to become visible.
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Here", "I'm in onStop");
        // The activity is about to become visible.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.i("Here", "I'm in onDestroy");
    }



    public class MoviesAsynTask extends AsyncTask<String, Void, String[]>
    {

        private final String LOG_TAG = MoviesAsynTask.class.getSimpleName();


        private void getMovieDataFromJson(String movieJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String MOVIE_LIST = "results";
            final String MOVIE_TITLE = "original_title";
            final String MOVIE_PLOT = "overview";
            final String MOVIE_DATE = "release_date";
            final String MOVIE_POSTER = "poster_path";
            final String MOVIE_RATING = "vote_average";

            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");



            for (int i = 0; i < movieArray.length(); i++) {
                Movie movie = new Movie();

                JSONObject jRealObject = movieArray.getJSONObject(i);

                movie.setTitle(jRealObject.getString("original_title"));
                movie.setPlot(jRealObject.getString("overview"));
                movie.setReleaseDate(jRealObject.getString("release_date"));
                movie.setUrlThumbnail(jRealObject.getString("poster_path"));
                movie.setRating(jRealObject.getString("vote_average"));



              //  count_em++;
              //  Log.i("My COUNTER", "iterate i " + i);
                myMovieList.add(movie);

               /* Log.i("JSon Testing", "My movie title is: " + movie.getTitle());
                Log.i("JSon Testing", "My movie date is: " + movie.getReleaseDate());
                Log.i("JSon Testing", "My movie rating is: " + movie.getRating());
                Log.i("JSon Testing", "My movie URL is: " + movie.getUrlThumbnail());
                Log.i("JSon Testing", "My movie overview is: " + movie.getPlot());
                */


            }




        }






        @Override
        protected String[] doInBackground(String... params) {


            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            String movieJsonStr = null;

            String format = "json";
            String units = "metric";
            int numDays = 7;

            try {

                final String MOVIE_BASE_URL =
                        "http://api.themoviedb.org/3/discover/movie?sort_by=";
                final String QUERY_PARAM = "q";
                final String API_KEY= "&api_key=" + APIKEY;


                String tmp = MOVIE_BASE_URL + sortMovies + API_KEY;

                URL url = new URL(tmp);
                Log.i(LOG_TAG, "Built URI " + url);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                movieJsonStr = buffer.toString();

                Log.v(LOG_TAG, "Movie string: " + movieJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                getMovieDataFromJson(movieJsonStr, numDays);




            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            gridView = (GridView) rootView.findViewById(R.id.gridview_movies);
            mMovieAdapter = new MovieAdapter(getActivity(), myMovieList);
            gridView.setAdapter(mMovieAdapter);
        }
    }
}