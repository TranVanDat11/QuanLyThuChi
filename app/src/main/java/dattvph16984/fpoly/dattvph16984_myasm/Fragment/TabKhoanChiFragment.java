package dattvph16984.fpoly.dattvph16984_myasm.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import dattvph16984.fpoly.dattvph16984_myasm.Adapter.KhoanChiAdapter;
import dattvph16984.fpoly.dattvph16984_myasm.DAO.DaoGiaoDich;
import dattvph16984.fpoly.dattvph16984_myasm.DAO.DaoThuChi;
import dattvph16984.fpoly.dattvph16984_myasm.R;
import dattvph16984.fpoly.dattvph16984_myasm.model.GiaoDich;
import dattvph16984.fpoly.dattvph16984_myasm.model.ThuChi;

public class TabKhoanChiFragment extends Fragment {
    View view;
    private RecyclerView rcv;
    private ArrayList<GiaoDich> list = new ArrayList<>();
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    private DaoGiaoDich daoGiaoDich;
    private DaoThuChi daoThuChi;
    private DatePickerDialog datePickerDialog;
    private ArrayList<ThuChi> listTC = new ArrayList<>();
    KhoanChiAdapter adapter;
    FloatingActionButton addBtn;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(list, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
    public TabKhoanChiFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_khoan_chi, container, false);
        rcv = view.findViewById(R.id.rcv_tabKhoanChi);
        addBtn = view.findViewById(R.id.fl_add_khoan_chi);
        daoGiaoDich = new DaoGiaoDich(getActivity());

        list = daoGiaoDich.getGDtheoTC(1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        adapter = new KhoanChiAdapter(getActivity(), R.layout.item_rvc, list);
        rcv.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(dividerItemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcv);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_khoan_thuchi);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                final TextView moTaGd = dialog.findViewById(R.id.them_mota_gd);
                final TextView ngayGd = dialog.findViewById(R.id.them_ngay_gd);
                final TextView tienGd = dialog.findViewById(R.id.them_tien_gd);
                final Spinner spLoaiGd = dialog.findViewById(R.id.spLoaiGd);
                final TextView title = dialog.findViewById(R.id.titleThemKhoan);
                final Button xoa = dialog.findViewById(R.id.xoaTextGD);
                final Button them = dialog.findViewById(R.id.btnThemGD);
                daoThuChi = new DaoThuChi(getActivity());
                listTC = daoThuChi.getThuChi(1);
                //Set ti??u ?????
                title.setText("TH??M KHO???N CHI");
                //Khi nh???n ng??y hi???n l??n l???a ch??? ng??y
                ngayGd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        int d = calendar.get(Calendar.DAY_OF_MONTH);
                        int m = calendar.get(Calendar.MONTH);
                        int y = calendar.get(Calendar.YEAR);
                        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                final String NgayGD = dayOfMonth + "/" + (month + 1) + "/" + year;
                                ngayGd.setText(NgayGD);
                            }
                        }, y, m, d);
                        datePickerDialog.show();
                    }
                });

                        //????? d??? li???u v??o spinner
                        final ArrayAdapter sp = new ArrayAdapter(getActivity(), R.layout.item_spinner, listTC);
                        spLoaiGd.setAdapter(sp);

                        //Khi nh???n n??t x??a
                        xoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        //Khi nh???n n??t Th??m
                        them.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "Th??m", Toast.LENGTH_SHORT).show();
                                String mota = moTaGd.getText().toString();
                                String ngay = ngayGd.getText().toString();
                                String tien = tienGd.getText().toString();
                                ThuChi tc = (ThuChi) spLoaiGd.getSelectedItem();
                                int ma = tc.getMaKhoan();
                                //Check l???i

                                if (mota.isEmpty() && ngay.isEmpty() && tien.isEmpty()) {
                                    Toast.makeText(getActivity(), "C??c tr?????ng kh??ng ???????c ????? tr???ng!", Toast.LENGTH_SHORT).show();
                                } else {
                                    try {
                                        GiaoDich gd = new GiaoDich(0, mota, dfm.parse(ngay), Integer.parseInt(tien), ma);

                                        if (daoGiaoDich.themGD(gd) == true) {
                                            list.clear();
                                            list.addAll(daoGiaoDich.getGDtheoTC(1));
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "Th??m th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        } else {
                                            Toast.makeText(getActivity(), "Th??m th???t b???i!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }

                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}