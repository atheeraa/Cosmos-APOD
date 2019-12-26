package com.example.favor.cosmos;
import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

public class CLoader extends AsyncTaskLoader<List<list>> {
    private String mUrl;

    public CLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<list> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<list> news = Query.fetchData(mUrl);
        return news;
    }
}