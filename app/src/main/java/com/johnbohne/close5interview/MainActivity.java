package com.johnbohne.close5interview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    private static final String[] URL_LIST = {
            "http://cdn2-www.dogtime.com/assets/uploads/gallery/30-impossibly-cute-puppies/impossibly-cute-puppy-8.jpg",
            "http://cdn3-www.dogtime.com/assets/uploads/gallery/30-impossibly-cute-puppies/impossibly-cute-puppy-15.jpg",
            "https://i.ytimg.com/vi/mRf3-JkwqfU/hqdefault.jpg",
            "http://cdn1-www.dogtime.com/assets/uploads/gallery/30-impossibly-cute-puppies/impossibly-cute-puppy-2.jpg",
            "http://blogs-images.forbes.com/kristintablang/files/2016/02/Uber-Puppies.jpg",
            "http://theprojectheal.org/wp-content/uploads/2016/01/Aaaaaawwwwwwwwww-Sweet-puppies-9415255-1600-1200.jpg",
            "http://cdn.skim.gs/image/upload/v1456344012/msi/Puppy_2_kbhb4a.jpg",
            "https://i.ytimg.com/vi/oGoPUw0YBAg/maxresdefault.jpg",
            "http://media.mnn.com/assets/images/2015/04/puppies-expression.jpg",
            "http://images.r.cruisecritic.com/news/2016/princess-puppies.jpg"

    };
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                gridLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new ImageViewAdapter(this, URL_LIST));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class ImageViewAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final String[] mUrls;
        private Context mContext;

        private static final int EMPTY_VIEW = 10;

        public ImageViewAdapter(Context context, String[] urls) {
            mContext = context;
            mUrls = urls;
        }

        public class EmptyViewHolder extends RecyclerView.ViewHolder {
            public EmptyViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == EMPTY_VIEW) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_empty_view, parent, false);
                return new EmptyViewHolder(view);
            }
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_image, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public int getItemViewType(int position) {
            if (mUrls.length == 0) {
                return EMPTY_VIEW;
            }
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
            final ImageView image = vh.getmImage();

            Picasso.with(mContext)
                    .load(mUrls[position].trim())
                    .into(image);

        }

        @Override
        public int getItemCount() {
            return mUrls.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mImage;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImage = (ImageView) view.findViewById(R.id.image);
            }

            public View getView() {
                return mView;
            }

            public ImageView getmImage() {
                return mImage;
            }
        }
    }


}
