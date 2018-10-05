package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-12-2 0002.
 */

public class SetAsscoDialog extends Activity {
    private EditText editText;
    int par;
    int child;
    private  int totalCounts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setassco);
        editText = findViewById(R.id.to);
        totalCounts = AddOptionsActivity.questionList.size();
        Intent data = getIntent();
        par = data.getIntExtra("parent",-1);
        child = data.getIntExtra("child",-1);

    }

    public void submit(View view){
        String toText = editText.getText().toString().trim();
        if (toText != null && !toText.equals("")){
            int to = Integer.valueOf(toText)-1;
            if (to+1 > totalCounts){
                Toast.makeText(this,"没有此题目",Toast.LENGTH_SHORT).show();
            }else{
                AddOptionsActivity.questionList.get(par).setAsscoiatedItemIndex(child,to);
                Toast.makeText(this,"添加关联成功！",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
