package app.haotian.wenjuan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-12-1 0001.
 */

public class Question implements Serializable{
    public String title;
    public int index;
    public List<String> options;
    public Object[] strings;
    public boolean isasscoiated;
    public int asscoiatedToIndex;
    public int asscoiatedFromIndex;
    public int type;
    public Question(int index,String title,List<String> options){
        this.title = title;
        this.index = index;
        this.options = options;
        this.strings = options.toArray();
        this.isasscoiated = false;
        this.asscoiatedToIndex = -1;
        this.asscoiatedFromIndex = -1;
        this.type = 0;
        options.clear();
    }
    public void addOption(String option){
        options.add(option);
    }
    public String getOption(int position){
        return options.get(position);
    }
    public void delOption(int position){
        options.remove(position);
    }
    public void setAsscoiatedItemIndex(int from,int to){
        isasscoiated = true;
        asscoiatedToIndex = to;
        asscoiatedFromIndex = from;
    }
}
