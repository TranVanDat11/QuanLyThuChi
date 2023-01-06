package dattvph16984.fpoly.dattvph16984_myasm.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dattvph16984.fpoly.dattvph16984_myasm.DAO.DaoGiaoDich;
import dattvph16984.fpoly.dattvph16984_myasm.R;
import dattvph16984.fpoly.dattvph16984_myasm.model.GiaoDich;

public class ThongKeFragment extends Fragment {
    private TextView ngayBD, ngayKT, thu, chi, conlai;
    private Button btnShow;
    private DatePickerDialog datePickerDialog;
    private DaoGiaoDich daoGiaoDich;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");

    public ThongKeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        ngayBD = view.findViewById(R.id.tungay);
        ngayKT = view.findViewById(R.id.denngay);
        thu = view.findViewById(R.id.tienThu);
        chi = view.findViewById(R.id.tienChi);
        conlai = view.findViewById(R.id.tienConLai);
        btnShow = view.findViewById(R.id.btnShow);
        daoGiaoDich = new DaoGiaoDich(getActivity());
        //Format dạng tiền
        final NumberFormat fm = new DecimalFormat("#,###");
        final ArrayList<GiaoDich> listThu = daoGiaoDich.getGDtheoTC(0);
        final ArrayList<GiaoDich> listChi = daoGiaoDich.getGDtheoTC(1);
        int tongThu = 0, tongChi = 0;
        for (int i = 0; i < listThu.size(); i++) {
            tongThu += listThu.get(i).getSoTien();
        }
        for (int i = 0; i < listChi.size(); i++) {
            tongChi += Math.abs(listChi.get(i).getSoTien());
        }
        thu.setText(fm.format(tongThu) + " VND");
        chi.setText(fm.format(tongChi) + " VND");
        conlai.setText(fm.format(tongThu - tongChi) + " VND");
        // ----
        ngayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String NgayBD = dayOfMonth + "/" + (month + 1) + "/" + year;
                        ngayBD.setText("Ngày Bắt Đầu: "+NgayBD);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
        //--------
        ngayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String NgayKT = dayOfMonth + "/" + (month + 1) + "/" + year;
                        ngayKT.setText("Ngày Kết Thúc: "+NgayKT);
                    }
                }, y, m, d);
                datePickerDialog.show();
                //Khi nhấn nút show lọc thu chi theo ngày
                btnShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int Thu = 0, Chi = 0;
                        String bd = ngayBD.getText().toString();
                        String kt = ngayKT.getText().toString();

                        //Tính tiền theo ngày
                        for (int i = 0; i < listThu.size(); i++) {
                            try {
                                if (listThu.get(i).getNgayGd().compareTo(dfm.parse(bd)) >= 0 && listThu.get(i).getNgayGd().compareTo(dfm.parse(kt)) <= 0) {
                                    Thu += listThu.get(i).getSoTien();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        for (int i = 0; i < listChi.size(); i++) {
                            try {
                                if (listChi.get(i).getNgayGd().compareTo(dfm.parse(bd)) >= 0 && listChi.get(i).getNgayGd().compareTo(dfm.parse(kt)) <= 0) {
                                    Chi += listChi.get(i).getSoTien();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        thu.setText(fm.format(Thu) + " VND");
                        chi.setText(fm.format(Chi) + " VND");
                        conlai.setText(fm.format(Thu - Chi) + " VND");
                    }
                });
            }
        });
        return view;
    }
}