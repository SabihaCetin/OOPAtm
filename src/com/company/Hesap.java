package com.company;

public class Hesap {
    private String kulaniciAdi;
    private String parola;
    private int bakiye;

    public Hesap() {

    }

    public String getKulaniciAdi() {
        return kulaniciAdi;
    }

    public void setKulaniciAdi(String kulaniciAdi) {
        this.kulaniciAdi = kulaniciAdi;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        Banka banka = new Banka();
        this.bakiye = banka.getToplamBankaBakiyesi();

    }

    public void parYatir(int tutar) {
        bakiye += tutar;
        System.out.println("Yeni bakiye: " + bakiye);
    }

    public void paraCek(int tutar) {
        if (bakiye - tutar < 0) {
            System.out.println("Yetersiz bakiye");
        } else {
            Banka banka = new Banka();
            banka.adetlerial();//dosyadan alma
            //banka.veriCikartma(tutar);
            System.out.println("Toplam = " + tutar + " TL para çektiniz.");
            System.out.println("Paranız veriliyor, lütfen ATM'nin önünden ayrılmayınız...");
            bakiye -= tutar;
            System.out.println("Yeni bakiyeniz : " + bakiye);
        }
    }
}
