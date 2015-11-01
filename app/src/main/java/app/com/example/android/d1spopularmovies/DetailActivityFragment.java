package app.com.example.android.d1spopularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    String baseUrl = "http://image.tmdb.org/t/p/";
    String baseWidth = "w185";

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();

        if(intent !=null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            Movie movie = intent.getParcelableExtra(Intent.EXTRA_TEXT);

            String trueUrl = baseUrl+baseWidth+movie.getUrlThumbnail();

            TextView txtTemp;

            ImageView imgTemp;

            imgTemp = ((ImageView)rootView.findViewById(R.id.movie_poster_imageview));
            Picasso.with(getActivity()).load(trueUrl).into(imgTemp);

            txtTemp = ((TextView)rootView.findViewById(R.id.movie_title_textview));
            txtTemp.setText(movie.getTitle());

            txtTemp = ((TextView)rootView.findViewById(R.id.movie_rating_textview));
            txtTemp.setText("Rated:"+"\n"+movie.getRating()+"/10");

            txtTemp = ((TextView)rootView.findViewById(R.id.movie_release_date_textview));
            txtTemp.setText("Released:"+"\n"+movie.getReleaseDate());

            txtTemp = ((TextView)rootView.findViewById(R.id.movie_plot_textview));
            txtTemp.setMovementMethod(new ScrollingMovementMethod());
            txtTemp.setText(movie.getPlot());


            //(ImageView)rootView.findViewById((R.id.movie_poster_imageview)).set
        }

        return rootView;
    }


}
