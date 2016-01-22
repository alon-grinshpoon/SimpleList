package com.test.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.myapplication.db.User;
import com.test.myapplication.server.AsyncRequest;
import com.test.myapplication.server.AsyncResponse;
import com.test.myapplication.server.AsyncResult;
import com.test.myapplication.server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AsyncResponse {

    List<User> usersList;
    View rootView;
    Context context;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create fab button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App created by the cool Alon Grinshpoon.\nYou should hire him!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //// Create feed
        // Get Users
       /*   usersList = getUsers();
            AsyncResult asyncResult = new AsyncResult();
            asyncResult.setDataList(usersList);
            handleResult(asyncResult);*/
        AsyncRequest asyncRequest = new AsyncRequest(MainActivity.this);
        asyncRequest.execute(Server.SERVER_ACTION_GET_DATA);
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

    @Override
    public void handleResult(AsyncResult result) {
        // Get Users List
        usersList = result.getDataList();
        // Get List View
        LinearLayout ll = (LinearLayout)findViewById(R.id.content);
        lv = (ListView) ll.findViewById(R.id.list_users);
        // Hide Progress Bar
        ((ProgressBar) ll.findViewById(R.id.progress))
                .setVisibility(View.GONE);
        lv.setVisibility(View.VISIBLE);
        // Create List Adapter
        context = this;
        UsersListAdapter usersListAdapter = new UsersListAdapter(context, usersList);
        lv.setAdapter(usersListAdapter);
    }

    public ArrayList<User> getUsers() throws IOException {

        /** Mock Users **/
        ArrayList<User> usersList = new ArrayList<User>();
        User user;

        user = new User("", "User Name 1", "text text1", "text...1");
        usersList.add(user);
        user = new User("", "User Name 2", "text text2", "text...2");
        usersList.add(user);
        user = new User("", "User Name 3", "text text3", "text...3");
        usersList.add(user);
        user = new User("", "User Name 4", "text text4", "text...4");
        usersList.add(user);
        user = new User("", "User Name 5", "text text5", "text...5");
        usersList.add(user);
        user = new User("", "User Name 6", "text text6", "text...6");
        usersList.add(user);
        user = new User("", "User Name 7", "text text7", "text...7");
        usersList.add(user);
        user = new User("", "User Name 8", "text text8", "text...8");
        usersList.add(user);

        return usersList;
    }

    private class UsersListAdapter extends BaseAdapter {
        private List<User> usersList;
        private LayoutInflater inflater;

        public UsersListAdapter(Context context, List<User> usersList) {
            this.usersList = usersList;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return usersList.size();
        }

        @Override
        public Object getItem(int i) {
            return usersList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.user_list_item, null);

                holder.name = (TextView) convertView
                        .findViewById(R.id.user_item_name);
                holder.userImage = (ImageView) convertView
                        .findViewById(R.id.user_img);

                holder.detail0 = (TextView) convertView
                        .findViewById(R.id.detail_text0);
                holder.detail1 = (TextView) convertView
                        .findViewById(R.id.detail_text1);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // Define Entry
            User user = usersList.get(position);

            holder.name.setText(user.getName());

            holder.userImage.setImageDrawable(getDrawable(R.mipmap.ic_userpic_round));

            holder.detail0.setText(user.getTitle());
            holder.detail0.setTextColor(getResources().getColor(R.color.blue_text));
            holder.detail0.setTextColor(getResources().getColor(R.color.blue_text));
            holder.detail0.setVisibility(View.VISIBLE);

            holder.detail1.setText(user.getEmail());
            holder.detail1.setTextColor(getResources().getColor(R.color.green_text));
            holder.detail1.setVisibility(View.VISIBLE);

            return convertView;
        }

        private Drawable convertToDrawable(String userPic) {
            return null;
        }

        class ViewHolder {
            private TextView name;
            private ImageView userImage;
            private TextView detail0, detail1;
        }

        public class EndlessScrollListener implements AbsListView.OnScrollListener {

            private int visibleThreshold = 5;
            private int currentPage = 0;
            private int previousTotal = 0;
            private boolean loading = true;

            public EndlessScrollListener() {
            }
            public EndlessScrollListener(int visibleThreshold) {
                this.visibleThreshold = visibleThreshold;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                        currentPage++;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {

                    UsersListAdapter usersListAdapter = new UsersListAdapter(context, usersList);
                    lv.setAdapter(usersListAdapter);

                    loading = true;
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        }
    }
}
