package app.haotian.wenjuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Question> questions;
    private Context context;

    public ListViewAdapter(Context context, List<Question> questions){
        this.questions = questions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int i) {
        return questions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_question,null);
        TextView questionIndex = item.findViewById(R.id.question_index);
        TextView questionTitle = item.findViewById(R.id.question_title);
        questionIndex.setText(String.valueOf(i+1));
        questionTitle.setText(questions.get(i).title);
        return item;
    }
}
