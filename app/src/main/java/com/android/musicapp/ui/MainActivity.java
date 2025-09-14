package com.android.musicapp.ui;

  import android.os.Bundle;

  import androidx.annotation.Nullable;
  import androidx.appcompat.app.AppCompatActivity;
  import androidx.databinding.DataBindingUtil;

  import com.android.musicapp.R;
  import com.android.musicapp.databinding.ActivityMainBinding;
  import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
     private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //设置ViewPager2适配器
        ViewPagerAdapter adatpter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adatpter);

        //关联ViewPager和TabLayout
        new TabLayoutMediator(binding.tabLayout,binding.viewPager,((tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("首页");
                    break;
                case 1:
                    tab.setText("发现");
                    break;
                case 2:
                    tab.setText("我的");
                    break;
            }})).attach();
    }

      @Override
      protected void onStart() {
          super.onStart();
      }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
      protected void onStop() {
          super.onStop();
      }

      @Override
      protected void onDestroy() {
          super.onDestroy();
      }
  }