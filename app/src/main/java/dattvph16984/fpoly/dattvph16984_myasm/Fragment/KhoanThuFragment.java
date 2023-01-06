package dattvph16984.fpoly.dattvph16984_myasm.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

import dattvph16984.fpoly.dattvph16984_myasm.Adapter.KhoanThu_ViewPagerAdapter;
import dattvph16984.fpoly.dattvph16984_myasm.R;


public class KhoanThuFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    View view;
    public KhoanThuFragment(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        viewPager=view.findViewById(R.id.viewpager_khoanthu);
        tabLayout=view.findViewById(R.id.tablayout_khoanthu);

        tabLayout.addTab(tabLayout.newTab().setText("KHOẢN THU"));
        tabLayout.addTab(tabLayout.newTab().setText("LOẠI THU"));

        KhoanThu_ViewPagerAdapter adapter = new KhoanThu_ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
}