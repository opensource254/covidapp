package app.azim.opensource254.covidkenya.activities.FirebaseChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.db.User;
import app.azim.opensource254.covidkenya.db.UserAppDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RegisterActivity extends AppCompatActivity {

        EditText username, password;
        Button registerButton;
        String muser, pass;
        TextView login,register;

        public  static UserAppDatabase muserAppDatabase;



        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            muserAppDatabase = Room.databaseBuilder(getApplicationContext(),UserAppDatabase.class,"userdb").build();

          //  username = findViewById(R.id.username);
            register = findViewById(R.id.txt_register);
            password = findViewById(R.id.password);
            registerButton = findViewById(R.id.registerButton);
            login = findViewById(R.id.login);

            Firebase.setAndroidContext(this);


            muser = (RandomUtil.unique());

            //setting user object
            //seting a unique user


            AsyncTask.execute(() -> {


                //check is user exists is exists move to login avtivity if not create new user
                int  id  =  1;
                User user = new User();
                user.setId(id);
                user.setName(muser);


               // muserAppDatabase.userDao().addUser(user);


                List <User> users =  muserAppDatabase.userDao().getUsers();

                for(User usr: users){
                    String name = usr.getName();


                     if(( name == null)){

                         register.setText("no user");


                     }
                        else {

                          register.setText(name);
                           // Toast.makeText(this,"user exists  "+name+"",Toast.LENGTH_LONG).show();

                       }

                    }




            });


          //  register.setText();


          //  Toast.makeText(this,"user added successfully  "+muser+"",Toast.LENGTH_LONG).show();

            login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

            registerButton.setOnClickListener(v -> {

               // register.setText(RandomUtil.unique());
                pass = password.getText().toString();

                if (muser.equals("")) {
                    username.setError(" cant proceed");
                } else if (pass.equals("")) {
                    password.setError("can't be blank");
                    password.setError("at least 5 characters long");
                } else {
                    final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://covappkenya.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, s -> {
                        Firebase reference = new Firebase("https://covappkenya.firebaseio.com/users");

                        if (s.equals("null")) {
                            reference.child(muser).child("password").setValue(pass);
                            Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                JSONObject obj = new JSONObject(s);

                                if (!obj.has(muser)) {
                                    reference.child(muser).child("password").setValue(pass);
                                    Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        pd.dismiss();
                    }, volleyError -> {
                        System.out.println("" + volleyError);
                        pd.dismiss();
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(RegisterActivity.this);
                    rQueue.add(request);
                }
            });
        }


        //class to implement random unique id for the users
    public static class RandomUtil {

        private static volatile SecureRandom numberGenerator = null;
        private static final long MSB = 0x8000000000000000L;

        static String unique() {
            SecureRandom ng = numberGenerator;
            if (ng == null) {
                numberGenerator = ng = new SecureRandom();
            }

            return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
        }
    }











}

