package dattvph16984.fpoly.dattvph16984_myasm.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dattvph16984.fpoly.dattvph16984_myasm.Fragment.TabKhoanChiFragment;
import dattvph16984.fpoly.dattvph16984_myasm.Fragment.TabKhoanThuFragment;
import dattvph16984.fpoly.dattvph16984_myasm.Fragment.TabLoaiChiFragment;
import dattvph16984.fpoly.dattvph16984_myasm.Fragment.TabLoaiThuFragment;

public class KhoanChi_ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numberTab = 2;
    public KhoanChi_ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabKhoanChiFragment tab_khoanChi = new TabKhoanChiFragment();
                return tab_khoanChi;
            case 1:
                TabLoaiChiFragment tab_loaiChi = new TabLoaiChiFragment();
                return tab_loaiChi;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberTab;
    }
}
