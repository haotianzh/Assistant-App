package app.haotian.wenjuan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createMyForm(View view){
        Intent intent = new Intent(this,AddOptionsActivity.class);
        intent.putExtra("from","main");
        startActivity(intent);
    }

    public void writeQuestion(View view){
        Intent intent = new Intent(this,MyWenjuanActivity.class);
        startActivity(intent);
    }
}
