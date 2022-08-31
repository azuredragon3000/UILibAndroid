package com.myapp.mylibrary.ngontinh;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.myapp.mylibrary.DB.DatabaseNgonTinh;
import com.myapp.mylibrary.boitinhyeu.ModelDanhNgon;
import com.myapp.mylibrary.databinding.ActivityNgontinhBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityNgonTinh extends AppCompatActivity {

    List<ModelDanhNgon> modelDanhNgonList = new ArrayList<>();
    private final Handler mHandler = new Handler();
    List<ModelDanhNgon> ngonList = new ArrayList<>();
    ActivityNgontinhBinding binding;

    public  String DB_PATH= "/data/data/com.myapp.mylibrary/databases/";
    public  String DATABASE_BOIPHUONGDONG = "datatonghop.db";
    public  String DATABASE_NAMSINH = "tuvitrondoi.sqlite";
    public  String DATABASE_BOITINHYEUPD = "boiphuongdong_bty.db";
    public  String DATABASE_BOITINHYEUCHD = "cunghoangdao.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNgontinhBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DatabaseNgonTinh databaseNgonTinh = DatabaseNgonTinh.getInstance(getApplication(),DB_PATH,DATABASE_BOIPHUONGDONG);
        //modelDanhNgonList = getDanhNgon();//((SubApp) getApplication()).getDatabaseNgonTinh().getDanhNgon();
        modelDanhNgonList = databaseNgonTinh.getDanhNgon();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.danhNgonRclView.setLayoutManager(manager);
        binding.danhNgonRclView.setHasFixedSize(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        loadData(0, 100);
        final AdapterDanhNgon adapter = new AdapterDanhNgon(this, ngonList, binding.danhNgonRclView);
        binding.danhNgonRclView.setAdapter(adapter);


        adapter.setOnLoadMoreListener(() -> {
            if (ngonList.size() <= modelDanhNgonList.size()) {
                ngonList.add(null);
                adapter.notifyItemInserted(ngonList.size() - 1);
                mHandler.postDelayed(() -> {
                    ngonList.remove(ngonList.size() - 1);
                    adapter.notifyItemRemoved(ngonList.size());
                    int start = ngonList.size();
                    int end = start + 100;
                    loadData(start, end);
                    adapter.setLoaded();
                }, 1000);
            } else {
                adapter.setLoaded();
            }
        });
    }

    //protected List<ModelDanhNgon> getDanhNgon();

    private void loadData(int start, int end) {
        for (int i = start; i < end; i++) {
            ngonList.add(new ModelDanhNgon(modelDanhNgonList.get(i).getId(),
                    modelDanhNgonList.get(i).getContent(), modelDanhNgonList.get(i).getAuthor()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}