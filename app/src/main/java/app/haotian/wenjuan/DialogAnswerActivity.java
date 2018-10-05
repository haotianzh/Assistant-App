package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class DialogAnswerActivity extends Activity {
    private EditText editText;
    private Button btn_ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_answer);
        editText = findViewById(R.id.xuanxiang);


    }
    public void doClick(View v){
        String result = editText.getText().toString().trim();
        Intent data =  new Intent();
        data.putExtra("answer",result);
        this.setResult(0,data);
        finish();
    }
}
