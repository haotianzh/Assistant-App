package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017-12-4 0004.
 */

public class DialogSaveActivity extends Activity {
    private EditText editText;
    private AssetManager am;
    SharedPreferences sp;
    SharedPreferences.Editor editor_wenjuan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        am = getAssets();
        this.setContentView(R.layout.dialog_save);
        editText = findViewById(R.id.save_text);
    }
    public void doClick(View view){
        String wenjuanName = editText.getText().toString().trim();
        sp = getSharedPreferences("data",MODE_PRIVATE);
        editor_wenjuan = sp.edit();
        Set<String> wenjuans = sp.getStringSet("wenjuans",null);
        if(wenjuans == null){
            Set<String> a = new HashSet<>();
            a.add(wenjuanName);
            editor_wenjuan.putStringSet("wenjuans",a);
        }else{
            Set<String> a = new HashSet<>();
            a.addAll(wenjuans);
            a.add(wenjuanName);
            editor_wenjuan.putStringSet("wenjuans",a);
        }
        editor_wenjuan.apply();
        SharedPreferences.Editor editor = getSharedPreferences(wenjuanName,MODE_PRIVATE).edit();
        Set<String> set = new HashSet<>();
        for(Question question:AddOptionsActivity.questionList){
            String title= question.title;
            for(Object option:question.strings){
                set.add((String)option);
            }
            editor.putStringSet(title,set);
        }
        editor.apply();
        Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
        finish();
    }
}
