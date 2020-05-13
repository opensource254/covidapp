package app.azim.opensource254.covidkenya.activities.FirebaseChat;

import androidx.appcompat.app.AppCompatActivity;
import app.azim.opensource254.covidkenya.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UsersActivity extends AppCompatActivity {

        ListView usersList;
        TextView noUsersText;
        ArrayList<String> al = new ArrayList<>();
        int totalUsers = 0;
        ProgressDialog pd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_users);

            usersList = findViewById(R.id.usersList);
            noUsersText = findViewById(R.id.noUsersText);

            pd = new ProgressDialog(UsersActivity.this);
            pd.setMessage("Loading...");
            pd.show();

            String url = "https://covappkenya.firebaseio.com/users.json";

            StringRequest request = new StringRequest(Request.Method.GET, url, s -> doOnSuccess(s), volleyError -> System.out.println("" + volleyError));

            RequestQueue rQueue = Volley.newRequestQueue(UsersActivity.this);
            rQueue.add(request);

            usersList.setOnItemClickListener((parent, view, position, id) -> {
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(UsersActivity.this, ChatActivity.class));
            });
        }

        public void doOnSuccess(String s){
            try {
                JSONObject obj = new JSONObject(s);

                Iterator i = obj.keys();
                String key = "";

                while(i.hasNext()){
                    key = i.next().toString();

                    if(!key.equals(UserDetails.username)) {
                        al.add(key);
                    }

                    totalUsers++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(totalUsers <=1){
                noUsersText.setVisibility(View.VISIBLE);
                usersList.setVisibility(View.GONE);
            }
            else{
                noUsersText.setVisibility(View.GONE);
                usersList.setVisibility(View.VISIBLE);
                usersList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al));
            }

            pd.dismiss();
        }
    }

