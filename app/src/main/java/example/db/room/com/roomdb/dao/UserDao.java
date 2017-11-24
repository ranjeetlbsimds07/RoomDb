package example.db.room.com.roomdb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.db.room.com.roomdb.entity.User;

/**
 * Created by Guest User on 11/24/2017.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();
    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);
    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User users);

    @Delete
    void delete(User user);
    @Update
     void updateUsers(User user);
}
