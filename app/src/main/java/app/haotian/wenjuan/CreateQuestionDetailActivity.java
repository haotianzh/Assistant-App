package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class CreateQuestionDetailActivity extends AppCompatActivity {
    private String TAG = "CreateQuestionDetailActivity";
    private int index;
    private EditText content;
    private List<String> answerList;
    private ListView answerListView;
    private ListAnswersAdapter adapter;
    public static Question question;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_detail);
        index = getIntent().getIntExtra("index",0);
        content = findViewById(R.id.question_content);
        answerListView = findViewById(R.id.list_answers);
        answerList = new ArrayList<>();
        answerList.clear();
        adapter = new ListAnswersAdapter(this,answerList,answerListView);
        answerListView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String string = data.getStringExtra("answer");
        switch(requestCode){
            case 3:
                Log.i(TAG, "onActivityResult: "  + string);
                answerList.add(string);
                System.out.println(answerList);
                adapter = null;
                adapter = new ListAnswersAdapter(this,answerList,answerListView);
                answerListView.setAdapter(adapter);
        }
    }

    public void doPut(View view){
        String title = content.getText().toString().trim();
        Intent intent = new Intent();
        if(title != null && title != ""){
            Log.i(TAG, "doPut: " + index);
            Log.i(TAG, "doPut: " + title);
            Log.i(TAG, "doPut: " + answerList);
            question = new Question(index,title,answerList);
            Intent data = new Intent();
            data.putExtra("question",question);
            this.setResult(0,data);
        }
        finish();
    }
}
