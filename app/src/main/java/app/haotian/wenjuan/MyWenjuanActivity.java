package app.haotian.wenjuan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2017-12-3 0003.
 */

public class MyWenjuanActivity extends AppCompatActivity {

    private ListView listView;
    public static List<Question> questionList;
    private int counts;
    private List<String> wenjuan;
    private int index;
    private MyAdapter adapter;
    private LayoutInflater inflater;
    private AssetManager am;

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return wenjuan.size();
        }

        @Override
        public Object getItem(int i) {
            return wenjuan.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        private void editWenjuan(String title){
            questionList.clear();
            index = 0;
            SharedPreferences sp = getSharedPreferences(title,MODE_PRIVATE);
            Map<String,?> map = sp.getAll();
            for(String string : map.keySet()){
                Set<String> set = sp.getStringSet(string,null);
                if(set != null){
                    List<String> list = new ArrayList<>();
                    for (String s:set){
                        list.add(s);
                    }
                    Question question = new Question(index++,string,list);
                    questionList.add(question);
                    if(AddOptionsActivity.questionList != null){
                        AddOptionsActivity.questionList.clear();
                    }
                    if(questionList != null){
                        AddOptionsActivity.questionList = new ArrayList<>();
                        AddOptionsActivity.questionList.addAll(questionList);
                    }
                }
            }


        }

        private void editWenjuan2(String title){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(am.open("wenjuan/"+title +".txt")));
                String line = "";
                while((line = reader.readLine()) != null){
                    String timu = line.split("\\|\\|\\|")[0];
                   // System.out.println("1111111111111111111111" + timu);
                    String options = line.split("\\|\\|\\|")[1];
                    List<String> list = new ArrayList<>();
                    for(String option : options.split("\t")){
                        list.add(option);
                    }
                    Question question = new Question(index++,timu,list);
                    questionList.add(question);
                    if(AddOptionsActivity.questionList != null){
                        AddOptionsActivity.questionList.clear();
                    }

                    if(questionList != null){
                        AddOptionsActivity.questionList = new ArrayList<>();
                        AddOptionsActivity.questionList.addAll(questionList);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private void readWenjuan(String title){
            questionList.clear();
            index = 0;
            SharedPreferences sp = getSharedPreferences(title,MODE_PRIVATE);
            Map<String,?> map = sp.getAll();
            for(String string : map.keySet()){
                Set<String> set = sp.getStringSet(string,null);
                if(set != null){
                    List<String> list = new ArrayList<>();
                    for (String s:set){
                        list.add(s);
                    }
                    Question question = new Question(index++,string,list);
                    questionList.add(question);
                }
            }
            System.out.println("123" + questionList.size());
        }

        private void deleteWenjuan(String title){
            SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Set<String> wenjuans = sp.getStringSet("wenjuans",null);
            Set<String> set = new HashSet<>();
            wenjuans.remove(title);
            System.out.println("--------------" + wenjuans);
            set.addAll(wenjuans);
            editor.putStringSet("wenjuans",null);
            editor.apply();
            editor.putStringSet("wenjuans",set);
            editor.commit();
            wenjuan.remove(wenjuan.indexOf(title));
            this.notifyDataSetChanged();
            SharedPreferences sp1 = getSharedPreferences(title,MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.clear();
            editor1.commit();
        }

        private void readWenjuan2(String title){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(am.open("wenjuan/"+title +".txt")));
                String line = "";
                while((line = reader.readLine()) != null){
                    String timu = line.split("\\|\\|\\|")[0];
                    String options = line.split("\\|\\|\\|")[1];
                    List<String> list = new ArrayList<>();
                    for(String option : options.split("\t")){
                        list.add(option);
                    }
                    Question question = new Question(index++,timu,list);
                    questionList.add(question);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View item = inflater.inflate(R.layout.item_wenjuan,null);
            TextView textView = item.findViewById(R.id.wenjuantitle);
            FancyButton btn_edit = item.findViewById(R.id.edit);
            FancyButton btn_huida = item.findViewById(R.id.dati);
            FancyButton btn_shanchu = item.findViewById(R.id.shanchu);
            textView.setText(wenjuan.get(i));
            final int k = i;
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //Toast.makeText(MyWenjuanActivity.this,"haha",Toast.LENGTH_LONG).show();
                        //questionList.clear();
                        editWenjuan(wenjuan.get(k));
                        Intent intent = new Intent(MyWenjuanActivity.this,AddOptionsActivity.class);
                        intent.putExtra("from","other");
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            btn_huida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //questionList.clear();
                        readWenjuan(wenjuan.get(k));
                        Intent intent = new Intent(MyWenjuanActivity.this,QuestionShowActivity.class);
                        intent.putExtra("flag","exist");
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            btn_shanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteWenjuan(wenjuan.get(k));
                }
            });
            return item;
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwenjuan);
        listView = findViewById(R.id.showwenjuan);
        inflater = LayoutInflater.from(this);
        am = getAssets();
        initDataFromSp();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    private void initData() {
        index = 0;
        questionList = new ArrayList<>();
        try {
            wenjuan = new ArrayList<>();
            counts = 0;
            System.out.println(File.listRoots().toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(am.open("wenjuan/wenjuan_titles.txt")));
            String line = "";
            while((line = reader.readLine()) != null){
                System.out.println(line);
                wenjuan.add(line);
                counts += 1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initDataFromSp(){
        index = 0;
        questionList = new ArrayList<>();
        wenjuan = new ArrayList<>();
        counts = 0;
        SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
        System.out.println(sp);
        Set<String> wenjuans = sp.getStringSet("wenjuans",null);
        if (wenjuans != null) {
            //Toast.makeText(this,sp.toString(),Toast.LENGTH_LONG).show();
            System.out.println(wenjuans.size());
            for (String string : wenjuans) {
                wenjuan.add(string);
                counts += 1;
            }
        }
    }

}
