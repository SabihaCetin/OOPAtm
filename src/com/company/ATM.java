package com.company;

import java.io.*;
import java.io.FileWriter;


import java.util.Scanner;

public class ATM {


    public static Banka banka = new Banka();

    public static void veriEkle(int para, int adet) {
        try {

            banka.paraekleme(para, adet);
            File file = new File("dosya.txt");

            String str = "5 ," + banka.getBesTLMiktari() + "\n" +
                    "10 ," + banka.getOnTLMiktari() + "\n" +
                    "20 ," + banka.getYirmiTLMiktari() + "\n" +
                    "50 ," + banka.getElliTLMiktari() + "\n" +
                    "100 ," + banka.getYuzTLMiktari() + "\n" +
                    "200 ," + banka.getIkiyuzTLMiktari() + "\n";


            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false
            );
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(str);
            bWriter.close();
            System.out.println("---Paranız kaydedilmiştir.---");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void veriSil(int para) {
        try {

            File file = new File("dosya.txt");
            banka.parasilme(para,0);

            String str = "5 ," + banka.getBesTLMiktari() + "\n" +
                    "10 ," + banka.getOnTLMiktari() + "\n" +
                    "20 ," + banka.getYirmiTLMiktari() + "\n" +
                    "50 ," + banka.getElliTLMiktari() + "\n" +
                    "100 ," + banka.getYuzTLMiktari() + "\n" +
                    "200 ," + banka.getIkiyuzTLMiktari() + "\n";


            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false
            );
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(str);
            bWriter.close();
            System.out.println("---Paranız kaydedilmiştir.---");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void verileriGoster() {
        try {
            File file = new File("dosya.txt");
            FileReader fileReader = new FileReader(file);
            String line;

            BufferedReader br = new BufferedReader(fileReader);

            while ((line = br.readLine()) != null) {

                System.out.println(line);

            }

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void calis(Hesap hesap) {

        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bankamıza hosgeldınız.....");
        System.out.println("Kullanıcı girisi --------");
        int girisHakki = 3;
        while (true) {
            if (login.login(hesap)) {
                System.out.println("Hoşgeldiniz...");
                break;
            } else if (!login.login(hesap)) {
                girisHakki -= 1;

                System.out.println("Yanlıs giriş, kalan giris hakkı :" + girisHakki);

            }
            if (girisHakki == 0) {
                System.out.println("Giris hakkınız bitti !");
                return;
            }
        }

        if (hesap.getKulaniciAdi().equals("sabiha")) {
            while (true) {


                System.out.println("İşlemler:\n" + "1:Bakiyeni gör\n" + "2:Para ekle\n" + "3:Para çek");
                int secim = scanner.nextInt();
                scanner.nextLine();
                boolean cıkıs = false;
                String istek;

                while (true) {
                    if (secim == 1) {
                        System.out.println("Hesap bakiyeniz : " + banka.getToplamBankaBakiyesi());
                        verileriGoster();
                        System.out.println("Çıkış (Ana menü) -> q ");
                        istek = scanner.nextLine();
                        if (istek.equals("q")) {
                            break;
                        } else {
                            System.out.println("Yanlış bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                            break;
                        }
                    } else if (secim == 2) {
                        while (true) {

                            System.out.println("Eklemek istediğiniz para türünü giriniz (5TL, 10TL, 20Tl, 50TL, 100TL, 200TL) :");

                            int para = scanner.nextInt();
                            if (para == 5 || para == 10 || para == 20 || para == 50 || para == 100 || para == 200) {


                                System.out.println("Kaç adet " + para + "TL ekleyeceksiniz ? ");
                                int adet = scanner.nextInt();
                                scanner.nextLine();
                                int tutar = para * adet;
                                hesap.parYatir(tutar);
                                veriEkle(para, adet);
                                System.out.println("Tekrar para eklemek ister misiniz ?  Evet -> e , Çıkış(Ana menü) -> q");
                                istek = scanner.nextLine();
                            } else {
                                System.out.println("Girdiğiniz tutar hatalıdır.Sadece 5TL, 10TL, 20Tl, 50TL, 100TL, 200TL seçebilirsiniz. ");
                                continue;
                            }

                            if (istek.equals("e")) {
                                continue;

                            } else if (istek.equals("q")) {
                                break;
                            } else {
                                System.out.println("Hatalı bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                                break;

                            }


                        }
                        break;

                    } else if (secim == 3) {
                        System.out.println("Çekmek istediğiniz miktarı giriniz.");
                        int cekimtutari = scanner.nextInt();
                        // hesap.paraCek(cekimtutari);
                        if (cekimtutari > 1000) {
                            System.out.println("Günlük çekim tutarını aştınız.Maksimum 1000 tl çekebilirsiniz. ");
                        } else {
                            if (cekimtutari % 5 != 0) {
                                System.out.println("Sadece 5TL ve katlarını çekebilirsiniz!\n");
                            } else {
                                banka.adetlerial();
                                banka.cekilecekMiktarDizi(cekimtutari);
                                banka.dosyaGuncelle(); // veriSilmeler
                                break;
                            }
                        }


                    } else {
                        System.out.println("Yanlış bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                        break;

                    }
                }
            }
        } else if (hesap.getKulaniciAdi().equals("bankaci")) {
            while (true) {


                System.out.println("İşlemler:\n" + "1:Verileri gör\n" + "2:Veri ekle");
                int secim = scanner.nextInt();
                scanner.nextLine();

                String istek;

                while (true) {
                    if (secim == 1) {
                        verileriGoster();
                        System.out.println("Çıkış (Ana menü) -> q ");
                        istek = scanner.nextLine();
                        if (istek.equals("q")) {
                            break;
                        } else {
                            System.out.println("Yanlış bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                            break;
                        }
                    } else if (secim == 2) {
                        while (true) {
                            System.out.println("Eklemek istediğiniz para türünü giriniz (5TL, 10TL, 20Tl, 50TL, 100TL, 200TL) :");
                            int para = scanner.nextInt();
                            System.out.println("Kaç adet " + para + "TL ekleyeceksiniz ? ");
                            int adet = scanner.nextInt();
                            scanner.nextLine();
                            veriEkle(para, adet);
                            System.out.println("Tekrar para eklemek ister misiniz ?  Evet -> e , Çıkış(Ana menü) -> q");
                            istek = scanner.nextLine();


                            if (istek.equals("e")) {
                                continue;

                            } else if (istek.equals("q")) {
                                break;
                            } else {
                                System.out.println("Hatalı bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                                break;

                            }


                        }
                        break;

                    } else {
                        System.out.println("Yanlış bir işlem yaptınız.\nAnasayfaya yönlendiriliyorsunuz!\n   ...   ");
                        break;

                    }

                }

            }

        }
    }

}



