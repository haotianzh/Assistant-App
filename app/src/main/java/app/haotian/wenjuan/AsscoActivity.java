package app.haotian.wenjuan;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.style.QuoteSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class AsscoActivity extends AppCompatActivity {
    public static Map<String,List<String>> mydata;
    public static String[] parents;
    private boolean FLAG_FIRST_IN;
    private ExpandableListView expandableListView;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_setnext);
        this.setTitle("设置答题关联");
        inflater = LayoutInflater.from(this);
        FLAG_FIRST_IN = true;
        initData();
        expandableListView = findViewById(R.id.expandlist);

        ExpandableListAdapter adapter = new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return mydata.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return mydata.get(parents[i]).size();
            }

            @Override
            public Object getGroup(int i) {
                return mydata.get(parents[i]);
            }

            @Override
            public Object getChild(int i, int i1) {
                return mydata.get(parents[i]).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i*100 + i1;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

                View item = inflater.inflate(R.layout.expand_parent,null);
                TextView text = item.findViewById(R.id.expand_text_parent);
                text.setText("题目"+ (i+1) + ":  " + parents[i]);

                return item;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

                View item = inflater.inflate(R.layout.expand_child,null);
                TextView text = item.findViewById(R.id.expand_text_child);
                text.setText("选项" + (i1+1) + ":  " + mydata.get(parents[i]).get(i1));

                return item;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int i) {

            }

            @Override
            public void onGroupCollapsed(int i) {

            }

            @Override
            public long getCombinedChildId(long l, long l1) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long l) {
                return 0;
            }
        };
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                int par = i;
                int child = i1;
                Intent intent = new Intent(AsscoActivity.this,SetAsscoDialog.class);
                intent.putExtra("parent",par);
                intent.putExtra("child",i1);
                startActivity(intent);
                return true;
            }
        });
    }

    private void initData() {
        int index = 0;
        mydata = new HashMap<>();
        List<Question> questions = AddOptionsActivity.questionList;
        parents = new String[questions.size()];
        for (Question question : questions){
            String title = question.title;
            parents[index++] = title;
            List<String> list = new ArrayList<>();
            list.clear();
            for (Object string : question.strings){
                list.add((String)string);
            }
            mydata.put(title,list);
        }
    }

    public void complete(View view){
            if(AddOptionsActivity.questionList.size() != 0){
                Intent intent = new Intent(this,QuestionShowActivity.class);
                intent.putExtra("flag","create");
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"当前问卷中还没有问题",Toast.LENGTH_SHORT).show();
                return;
            }
    }
}
