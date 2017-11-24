package example.db.room.com.roomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import example.db.room.com.roomdb.database.AppDatabase;
import example.db.room.com.roomdb.entity.User;

public class MainActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText etAge;
    private Button Btn;
    private Button btnMemberListing;
    private AppDatabase appDatabase;
    private int age;
    private  User updateUserModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        etAge = (EditText) findViewById(R.id.etAge);
        Btn = (Button) findViewById(R.id.Btn);
        btnMemberListing = (Button) findViewById(R.id.btnMemberListing);


        appDatabase = AppDatabase.getAppDatabase(MainActivity.this);
        btnMemberListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserListing.class));
            }
        });




        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                age = Integer.parseInt(etAge.getText().toString().trim());;

                final User user= new User();
                user.setFirstName(fName.getText().toString().trim());
                user.setLastName(lName.getText().toString().trim());
                user.setAge(age);

                if(updateUserModel == null  ) {
                    appDatabase.userDao().insertAll(user);
                }else{
                    user.setUid(updateUserModel.getUid());
                    appDatabase.userDao().updateUsers(user);
                }

                startActivity(new Intent(MainActivity.this, UserListing.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //User value = bundle.getSerializableExtra("USERMODEL");
            updateUserModel = (User) getIntent().getSerializableExtra("USERMODEL");
            fName.setText(updateUserModel.getFirstName());
            lName.setText(updateUserModel.getLastName());
            etAge.setText(updateUserModel.getAge()+"");

        }

    }
}
