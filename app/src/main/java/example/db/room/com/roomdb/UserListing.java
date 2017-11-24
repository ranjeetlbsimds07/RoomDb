package example.db.room.com.roomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import example.db.room.com.roomdb.adapter.MemberListAdapter;
import example.db.room.com.roomdb.callBack.deletMemb;
import example.db.room.com.roomdb.callBack.editMemb;
import example.db.room.com.roomdb.database.AppDatabase;
import example.db.room.com.roomdb.entity.User;

public class UserListing extends AppCompatActivity implements deletMemb, editMemb{

    private ListView lvUserList;
    private MemberListAdapter memberListAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_listing);
        lvUserList = (ListView) findViewById(R.id.lvUserList);

        appDatabase = AppDatabase.getAppDatabase(UserListing.this);
        //get All Users
        List<User> users = appDatabase.userDao().getAll();
        memberListAdapter = new MemberListAdapter(this, users, (deletMemb) UserListing.this, (editMemb) UserListing.this);
        lvUserList.setAdapter(memberListAdapter);

    }

    @Override
    public void deletUser(User user) {

        //delete member from db
        appDatabase.userDao().delete(user);
        List<User> users = appDatabase.userDao().getAll();
        memberListAdapter = new MemberListAdapter(this, users, (deletMemb) UserListing.this, (editMemb) UserListing.this);
        lvUserList.setAdapter(memberListAdapter);


    }

    @Override
    public void editUser(String fname, String lName) {

        User user = appDatabase.userDao().findByName(fname,lName);
        startActivity(new Intent(UserListing.this, MainActivity.class).putExtra("USERMODEL",user));
        finish();

    }
}
