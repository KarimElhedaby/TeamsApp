package com.teamapp.teamapp.members.ui;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;
import com.otaliastudios.autocomplete.AutocompletePolicy;
import com.otaliastudios.autocomplete.AutocompletePresenter;
import com.otaliastudios.autocomplete.CharPolicy;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.members.model.Member;
import com.teamapp.teamapp.user.model.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static com.teamapp.teamapp.members.ui.ViewDialog.dialog;


public class AddMember_Activity extends AppCompatActivity {

    Member member;
    private TreeNode mCurrentNode;
    static ArrayList<String> arrayList = new ArrayList<>();
    private Autocomplete mentionsAutocomplete;
    private int nodeCount = 0;
    static List<User> USERS2;
    static User user;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_);

        final TreeView treeView = findViewById(R.id.tree);
        FloatingActionButton addButton = findViewById(R.id.addNode);

        final BaseTreeAdapter adapter =
                new BaseTreeAdapter<ViewHolder>(
                        this, R.layout.node) {
                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(View view) {
                        return new ViewHolder(view);
                    }

                    @Override
                    public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                        viewHolder.nameTV.setText(data.toString());
                    }
                };


        treeView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_allMails();
                if (mCurrentNode != null) {

                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(AddMember_Activity.this,
                            "Mention Member using @");
                    setupMentionsAutocomplete();

                    ViewDialog.dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewDialog.name = ViewDialog.nameET.getText().toString();
                            Get_allMails();
                            if (ViewDialog.name.startsWith("@") != true) {
                                ViewDialog.nameET.setError("please Mention with @");
                            } else {
                                Log.d("id", String.valueOf(UserPresenter.userSelected_id));
                                member = new Member(1, 1, UserPresenter.userSelected_id);
                                addMember();
                                mCurrentNode.addChild(new TreeNode(getNodeText()));
                                dialog.dismiss();
                            }

                        }
                    });

                } else {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(AddMember_Activity.this,
                            "Mention Member using @ ");
                    setupMentionsAutocomplete();
                    Get_allMails();
                    ViewDialog.dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewDialog.name = ViewDialog.nameET.getText().toString();
                            if (ViewDialog.name.startsWith("@") != true) {
                                ViewDialog.nameET.setError("please Mention with @");
                            } else {
                                Log.d("id", String.valueOf(UserPresenter.userSelected_id));
                                member = new Member(1, 1, UserPresenter.userSelected_id);
                                addMember();
                                adapter.setRootNode(new TreeNode(getNodeText()));
                                dialog.dismiss();
                            }
                        }
                    });

                }
            }
        });

        // example tree
//        mCurrentNode = new TreeNode(getNodeText());
//        mCurrentNode.addChild(new TreeNode(getNodeText()));
//        final TreeNode child3 = new TreeNode(getNodeText());
//        child3.addChild(new TreeNode(getNodeText()));
//        final TreeNode child6 = new TreeNode(getNodeText());
//        child6.addChild(new TreeNode(getNodeText()));
//        child6.addChild(new TreeNode(getNodeText()));
//        child3.addChild(child6);
//        mCurrentNode.addChild(child3);
//        final TreeNode child4 = new TreeNode(getNodeText());
//        child4.addChild(new TreeNode(getNodeText()));
//        child4.addChild(new TreeNode(getNodeText()));
//        mCurrentNode.addChild(child4);

//        adapter.setRootNode(mCurrentNode);
        treeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentNode = adapter.getNode(position);
                Snackbar.make(treeView, "Clicked on "
                        + mCurrentNode.getData().toString(), LENGTH_SHORT).show();
            }
        });
    }

    private class ViewHolder {
        TextView nameTV, roleTV;

        ViewHolder(View view) {
            nameTV = view.findViewById(R.id.nameTV);
        }
    }

    private String getNodeText() {
        return ViewDialog.name.replace("@", "");
//        return "Node " + nodeCount++;
    }


    public void Get_allMails() {
        AndroidNetworking.get(" http://team-space.000webhostapp.com/index.php/api/users")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(new User().getClass(),
                        new ParsedRequestListener<List<User>>() {
                            @Override
                            public void onResponse(List<User> response) {
                                users = response;
                                List<User> USERS = users;
                                for (int i = 0; i < users.size(); i++) {
                                    arrayList.add(USERS.get(i).getName());
                                    user = new User(arrayList.get(i));
                                    USERS2 = users;
//                                    Log.d("Recived_users names", "Number of user_mention_row received: " +
//                                            USERS2.toString());

                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Recived_users", anError.toString());

                            }
                        });

    }

    private void setupMentionsAutocomplete() {
        float elevation = 6f;
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        AutocompletePolicy policy = new CharPolicy('@'); // Look for @mentions
        AutocompletePresenter<User> presenter = new UserPresenter(this);
        AutocompleteCallback<User> callback = new AutocompleteCallback<User>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, User item) {
                // Replace query text with the full name.
                int[] range = CharPolicy.getQueryRange(editable);
                if (range == null) return false;
                int start = range[0];
                int end = range[1];
                String replacement = item.getName();
                editable.replace(start, end, replacement);
                // This is better done with regexes and a TextWatcher, due to what happens when
                // the user_mention_row clears some parts of the text. Up to you.
                editable.setSpan(new StyleSpan(Typeface.BOLD), start, start + replacement.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        mentionsAutocomplete = Autocomplete.<User>on(ViewDialog.nameET)
                .with(elevation)
                .with(backgroundDrawable)
                .with(policy)
                .with(presenter)
                .with(callback)
                .build();
    }

    public void addMember() {
        AndroidNetworking.post("http://team-space.000webhostapp.com/index.php/api/members/add")
                .addBodyParameter(member) // posting java object
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Toast.makeText(AddMember_Activity.this,
                                "data send succsefully",
                                Toast.LENGTH_LONG).show();
                        Log.d("Data", member.toString());
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Data", error.toString());
                        Log.d("Data", member.toString());
//                        Utilities.dismissLoadingDialog();

                    }
                });


    }

}
