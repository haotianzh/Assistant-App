package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class AddOptionsActivity extends AppCompatActivity {
    private String TAG = "AddOptionsActivity";
    private FancyButton btn_add;
    private EditText editText;
    private LinearLayout scrollView;
    private ListView listView;
    private ListViewAdapter adapter;
    private int index;
    public static List<Question> questionList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoptions);
        btn_add = findViewById(R.id.add);
        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.save_text);
        if(questionList == null){
            questionList = new ArrayList<>();
            questionList.clear();
        }
        String from = getIntent().getStringExtra("from");
        switch (from){
            case "main":
                questionList.clear();
                break;
            case "other":
                break;
            default:
                break;
        }

        adapter = new ListViewAdapter(this,questionList);
        listView.setAdapter(adapter);
        index = 0;
    }

    public void addNewQuestion(View view){
        Log.i(TAG, "addNewQuestion: ");
        Intent intent = new Intent(this,CreateQuestionDetailActivity.class);
        intent.putExtra("index",++index);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            Question question = (Question) data.getSerializableExtra("question");
            questionList.add(question);
            adapter.notifyDataSetChanged();
            return;
        }
        Toast.makeText(this,"还没有添加问题",Toast.LENGTH_SHORT).show();
    }

    public void next(View view){
        Intent intent = new Intent(this,AsscoActivity.class);
        startActivity(intent);
    }

    public void save(View view){
        Intent intent = new Intent(this,DialogSaveActivity.class);
        startActivity(intent);
    }
}
