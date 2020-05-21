package app.azim.opensource254.covidkenya.db;

import android.widget.TextView;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    @Query("SELECT * FROM users WHERE id = 1 ")
    List<User> getUsers();


}


