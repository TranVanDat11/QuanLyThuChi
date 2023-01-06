package dattvph16984.fpoly.dattvph16984_myasm.model;

import androidx.annotation.NonNull;

public class ThuChi {
    private int maKhoan;
    private String tenKhoan;
    //loaiKhoan = true sẽ là thu, false là chi
    private int loaiKhoan;

    public ThuChi() {
    }

    public ThuChi(int maKhoan, String tenKhoan, int loaiKhoan) {
        this.maKhoan = maKhoan;
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public int getMaKhoan() {
        return maKhoan;
    }

    public void setMaKhoan(int maKhoan) {
        this.maKhoan = maKhoan;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public int isLoaiKhoan() {
        return loaiKhoan;
    }

    public void setLoaiKhoan(int loaiKhoan) {
        this.loaiKhoan = loaiKhoan;
    }

    @NonNull
    @Override
    public String toString() {
        return tenKhoan;
    }
}
