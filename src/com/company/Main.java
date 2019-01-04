package com.company;

import java.io.*;
import java.util.Scanner;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {


    public static void main(String[] args) {
        ATM atm = new ATM();

        Hesap hesap = new Hesap();
        hesap.setKulaniciAdi("sabiha");
        hesap.setParola("a");

        atm.calis(hesap);

        Banka banka = new Banka();
//banka.adetlerial();

//banka.veriCikartma(855);


        // banka.adetlerial();
        // banka.cekilecekMiktarDizi(385);
        // banka.dosyaGuncelle();
    }

}

