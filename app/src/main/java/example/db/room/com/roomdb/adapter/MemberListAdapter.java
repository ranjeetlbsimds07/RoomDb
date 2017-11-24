package example.db.room.com.roomdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.db.room.com.roomdb.R;
import example.db.room.com.roomdb.UserListing;
import example.db.room.com.roomdb.callBack.deletMemb;
import example.db.room.com.roomdb.callBack.editMemb;
import example.db.room.com.roomdb.entity.User;

/**
 * Created by Guest User on 11/24/2017.
 */

public class MemberListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    List<User> users;
    deletMemb deletMemb;
    editMemb editMemb;

    public MemberListAdapter(Context context, List<User> users, deletMemb deletMemb, editMemb editMemb) {
        this.context = context;
        this.users = users;
        this.deletMemb =deletMemb;
        this.editMemb =editMemb;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_member_row, null);
        TextView txtMember = (TextView) view.findViewById(R.id.txtMember);
        Button txtDelete = (Button) view.findViewById(R.id.txtDelete);
        Button txtEdit = (Button) view.findViewById(R.id.txtEdit);
        txtMember.setText(users.get(i).getUid() +"===="+users.get(i).getFirstName() +"===="+users.get(i).getLastName()+"==="+users.get(i).getAge());
        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletMemb.deletUser(users.get(i));
            }
        });

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMemb.editUser(users.get(i).getFirstName(),users.get(i).getLastName());
            }
        });
        return view;
    }

}
