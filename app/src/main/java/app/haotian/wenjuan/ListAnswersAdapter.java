package app.haotian.wenjuan;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class ListAnswersAdapter extends BaseAdapter {
    private String TAG = "ListAnswersAdapter";
    private CreateQuestionDetailActivity context;
    private List<String> answresList;
    private int counts;
    private ListAnswersAdapter me;
    private ListView answerListView;
    public ListAnswersAdapter(CreateQuestionDetailActivity context, List<String> answresList, ListView answerListView){
        this.context = context;
        this.answresList = answresList;
        this.counts = answresList.size() + 1;
        this.answerListView = answerListView;
        me = this;
        Log.i(TAG, "ListAnswersAdapter: " + this.counts);
    }
    @Override
    public int getCount() {
        return answresList.size()+1;
    }

    @Override
    public Object getItem(int i) {
        if (i != counts-1){
            return answresList.get(i);
        }else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return 1000 + i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int k = i;
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = null;
        if (i == counts-1){
            item = inflater.inflate(R.layout.activity_addanswer,null);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,DialogAnswerActivity.class);
                    context.startActivityForResult(intent,3);
                }
            });

        }else{
            item = inflater.inflate(R.layout.activity_answer,null);
            TextView daan = item.findViewById(R.id.daan);
            FancyButton button = item.findViewById(R.id.delete_answer);
            Log.i(TAG, "getView: " + answresList);
            daan.setText(answresList.get(i));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answresList.remove(k);
                    Log.i(TAG, "onClick: " + answresList);
                    ListAnswersAdapter adapter = new ListAnswersAdapter(context,answresList,answerListView);
                    answerListView.setAdapter(adapter);
                }
            });
        }
        return item;
    }
}
