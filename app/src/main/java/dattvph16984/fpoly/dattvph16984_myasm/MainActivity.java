package dattvph16984.fpoly.dattvph16984_myasm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import dattvph16984.fpoly.dattvph16984_myasm.Fragment.KhoanChiFragment;
import dattvph16984.fpoly.dattvph16984_myasm.Fragment.KhoanThuFragment;
import dattvph16984.fpoly.dattvph16984_myasm.Fragment.ThongKeFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(navigationView);

        ThongKeFragment thongKeFragment = new ThongKeFragment();
        replaceFragment(thongKeFragment);
        Toast.makeText(this,"THỐNG KÊ", Toast.LENGTH_SHORT).show();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    private void selectedItemDrawer(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.khoanthu:
                setTitle("KHOẢN THU");
                KhoanThuFragment khoanThufragment = new KhoanThuFragment();
                replaceFragment(khoanThufragment);
                Toast.makeText(this,"Khoản thu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.khoanchi:
                setTitle("KHOẢN CHI");
                KhoanChiFragment khoanChiFragment = new KhoanChiFragment();
                replaceFragment(khoanChiFragment);
                Toast.makeText(this,"Khoản Chi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.thongke:
                setTitle("THỐNG KÊ");
                ThongKeFragment thongKeFragment = new ThongKeFragment();
                replaceFragment(thongKeFragment);
                Toast.makeText(this,"THỐNG KÊ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gioithieu:
                setTitle("GIỚI THIỆU");
                Toast.makeText(MainActivity.this,"GIỚI THIỆU", Toast.LENGTH_SHORT).show();
                break;
            case R.id.doimatkhau:
                setTitle("ĐỔI MẬT KHẨU");
                Toast.makeText(MainActivity.this,"ĐỔI MẬT KHẨU", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

                finish();
                break;
        }
        item.setChecked(true);

        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedItemDrawer(item);
                return true;
            }
        });
    }
}