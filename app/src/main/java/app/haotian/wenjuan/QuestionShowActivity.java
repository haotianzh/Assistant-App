package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2017-12-2 0002.
 */

public class QuestionShowActivity extends AppCompatActivity {
    private int totalCounts;
    private TextView textView;
    private boolean FLAG_FIRST_IN;
    private EditText editText;
    private int currentType;
    private Question currentQuestion;
    private LinearLayout layout;
    public static Map<String,String> answer;
    public List<Question> questionList;
    private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String flag = getIntent().getStringExtra("flag");
        this.FLAG_FIRST_IN = true;
        this.questionList = new ArrayList<>();
        if (flag.equals("create")){
            this.questionList.addAll(AddOptionsActivity.questionList);
        }
        if (flag.equals("exist")){
            this.questionList.addAll(MyWenjuanActivity.questionList);
        }
        totalCounts = questionList.size();
        if(totalCounts == 0){
            Toast.makeText(this,"there is no problems!",Toast.LENGTH_LONG).show();
            finish();
        }
        this.setTitle("答题（1/" + totalCounts + ")");
        super.onCreate(savedInstanceState);
        answer = new HashMap<>();
        setContentView(R.layout.activity_questionshow);
        index = -1;
        textView = findViewById(R.id.choose_text);
        editText = findViewById(R.id.my_answer);
        layout = findViewById(R.id.linear1_1);
        next();

    }

    private void next() {

        index += 1;
        if (index == totalCounts){
            Toast.makeText(this,"问卷都填完啦！",Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this,FinalResultActivity.class);
            startActivity(intent);

            return;
        }
        if(layout.getChildCount() != 0){
            layout.removeAllViews();
        }
        currentQuestion = questionList.get(index);
        this.setTitle("答题（"+ (index+1) + "/" + totalCounts + ")");
        currentType = currentQuestion.type;
        final String title = currentQuestion.title;
        Object[] options = currentQuestion.strings;
        textView.setText(title);

        if (currentType == 0){
            findViewById(R.id.linear1).setVisibility(View.VISIBLE);
            findViewById(R.id.linear2).setVisibility(View.GONE);
            int length = currentQuestion.strings.length;
            for(int i=0;i<length;i++){
                FancyButton button = new FancyButton(this,null);
                button.setBorderWidth(2);
                button.setBorderColor(Color.parseColor("#FFFFFF"));
                button.setText((String)options[i]);
                button.setRadius(20);
                button.setTextColor(Color.WHITE);
                button.setTextSize(20);
                button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                final int finalIndex = index;
                final int newi = i;
                final String myanswer = (String)options[i];
                final int from = currentQuestion.asscoiatedFromIndex;
                final int to = currentQuestion.asscoiatedToIndex;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.put(title,myanswer);
                        if (from == newi){
                            index = to-1;
                            next();
                        }else{
                            next();
                        }

                    }
                });
                layout.addView(button);
            }
        }else{
            findViewById(R.id.linear2).setVisibility(View.VISIBLE);
            findViewById(R.id.linear1).setVisibility(View.GONE);
        }


    }


}
