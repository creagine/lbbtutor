package com.creaginetech.lbbtutor.Model;

public class Jadwal {

    String idSiswa, idTutor, namaSiswa, jurusan, grade, harga, tutor, hari, jam, tanggal, ruang, pertemuan;

    public Jadwal() {
    }

    public Jadwal(String idSiswa, String idTutor, String namaSiswa, String jurusan, String grade,
                  String harga, String tutor, String hari, String jam, String tanggal, String ruang,
                  String pertemuan) {

        this.idSiswa = idSiswa;
        this.idTutor = idTutor;
        this.namaSiswa = namaSiswa;
        this.jurusan = jurusan;
        this.grade = grade;
        this.harga = harga;
        this.tutor = tutor;
        this.hari = hari;
        this.jam = jam;
        this.tanggal = tanggal;
        this.ruang = ruang;
        this.pertemuan = pertemuan;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(String pertemuan) {
        this.pertemuan = pertemuan;
    }

    public String getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(String idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }
}
