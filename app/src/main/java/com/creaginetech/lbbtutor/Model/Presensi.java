package com.creaginetech.lbbtutor.Model;

public class Presensi {

    String namaSiswa, namaTutor, bulan, pekan, tanggalPresensi, materi, keterangan;

    public Presensi() {
    }

    public Presensi(String namaSiswa, String namaTutor, String bulan, String pekan, String tanggalPresensi, String materi, String keterangan) {
        this.namaSiswa = namaSiswa;
        this.namaTutor = namaTutor;
        this.bulan = bulan;
        this.pekan = pekan;
        this.tanggalPresensi = tanggalPresensi;
        this.materi = materi;
        this.keterangan = keterangan;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getNamaTutor() {
        return namaTutor;
    }

    public void setNamaTutor(String namaTutor) {
        this.namaTutor = namaTutor;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getPekan() {
        return pekan;
    }

    public void setPekan(String pekan) {
        this.pekan = pekan;
    }

    public String getTanggalPresensi() {
        return tanggalPresensi;
    }

    public void setTanggalPresensi(String tanggalPresensi) {
        this.tanggalPresensi = tanggalPresensi;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
