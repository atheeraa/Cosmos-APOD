package com.example.favor.cosmos;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<list>> {

public static final String URL = "https://api.nasa.gov/planetary/apod?api_key=t59T7J6yjULjNCqugFhbaPMIzwtjdex9L6BvGT9g";

        private MyAdapter mAdapter;
        private static final String LOG_TAG = MainActivity.class.getName();
        private static final int LOADER_ID = 1;

       TextView testTextView;
       @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                 final  ImageView  image = findViewById(R.id.bg);
                 //  animate();
                // Get a reference to the LoaderManager, in order to interact with loaders.
                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                        LoaderManager loaderManager = getLoaderManager();
                        loaderManager.initLoader(LOADER_ID, null, this);
                } else {

                }}
                public void animate(){
               CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
               View bottomSheet = (View) coordinatorLayout.findViewById(R.id.BS);
                        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);


                        final Animation shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink);

                /*  behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                        @Override
                        public void onStateChanged(@NonNull View bottomSheet, int newState) {

                                switch (newState) {
                                        case BottomSheetBehavior.STATE_DRAGGING:
                                                image.startAnimation(shrinkAnimation);
                                                break;
                                }
                        }

                        @Override
                        public void onSlide(View view, float v) {

                        }
                });*/


        }

                        @Override
        public Loader<List<list>> onCreateLoader(int i, Bundle bundle) {
                Uri baseUri = Uri.parse(URL);
                Uri.Builder uriBuilder = baseUri.buildUpon();

                String url = uriBuilder.toString();
                Log.i(LOG_TAG, "the url called is **** : " + url);
                return new CLoader(this, uriBuilder.toString());
        }




        @Override
        public void onLoadFinished(Loader<List<list>> loader, List<list> data) {
                  if (data != null && !data.isEmpty()) {

                        for (int ii = 0; ii < data.size();ii++ ) {

                                testTextView = (TextView) findViewById(R.id.title);
                                testTextView.setText(data.get(ii).getTitle());

                                testTextView = (TextView) findViewById(R.id.desc);
                                testTextView.setText(data.get(ii).getExplanation());

                                ImageView image = findViewById(R.id.bg);

                                Picasso.get().load(data.get(ii).getUrl()).into(image);


                }

        }}

        @Override
        public void onLoaderReset(Loader<List<list>> loader) {
                mAdapter.clear();
        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {
                //
        }


}