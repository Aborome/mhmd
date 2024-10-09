package com.example.chesswithrecview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StoneAdapter.ItemSelected {
    private Button button;
    private TextView textView;
    private RecyclerView recyclerView;
    private StoneAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Stone> stones;
    private int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initImgArray();
        initStonesArray();
        myAdapter=new StoneAdapter(MainActivity.this,stones);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(myAdapter);
            }
        });
    }

    private void initImgArray() {
        images=new int[6];
        images[0]=R.drawable.kos;
        images[1]=R.drawable.chelsea;
        images[2]=R.drawable.manchester;
        images[3]=R.drawable.paris;
        images[4]=R.drawable.tottenham;
        images[5]=R.drawable.tottenham;
    }

    private void initViews() {
        button=findViewById(R.id.btnShowList);
        recyclerView=findViewById(R.id.list);
        textView=findViewById(R.id.txtDetails);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initStonesArray() {
        stones=new ArrayList<>();
        String[] st=getResources().getStringArray(R.array.stones);
        for (String s:st)
              {
            stones.add(new Stone(s));
        }
        for (int i=0;i<images.length;i++
             ) {
            stones.get(i).setImg(images[i]);
        }

    }

    @Override
    public void onItemClicked(int Index) {
        int imageResId=images[Index];
        Intent intent =new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("imageResId",imageResId);
        startActivity(intent);

        String Details[]=getResources().getStringArray(R.array.details);
        String s=(Details[Index]);
        intent.putExtra("ItemId",s);

        textView.setText(Details[Index]);
        startActivity(intent);
    }
}