package com.example.asmm.model;

public class NhaPhanPhoi {
    private int MaNPP;
    private String TenNPP;


    public NhaPhanPhoi(int MaNPP, String TenNPP ) {
        MaNPP = MaNPP;
        TenNPP = TenNPP;

    }

    public NhaPhanPhoi() {
    }

    public int getMaNPP() {
        return MaNPP;
    }

    public void setMaNPP(int maNPP) {
        MaNPP = maNPP;
    }

    public String getTenNPP() {
        return TenNPP;
    }

    public void setTenNPP(String tieuDe) {
        TenNPP = TenNPP;
    }

}



