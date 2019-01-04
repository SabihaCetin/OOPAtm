package com.company;

import java.io.*;
import java.util.Scanner;


public class Banka {
    // maksimum çekim tutarı 1000 tl
    //eğer girilen tutar 200 ün katıysa o katı kadar 200 tl verilir eger 200ler bitmişse 100ler  verilir
    //5tl-20adet 100
    //10tl-20    100
    //20tl-30     100
    //50tl - 50     200
    //100tl- 50
    //200tl-50
    //100 ve

    private int toplamBankaBakiyesi;
    private int besTLMiktari;
    private int onTLMiktari;
    private int yirmiTLMiktari;
    private int elliTLMiktari;
    private int yuzTLMiktari;
    private int ikiyuzTLMiktari;

    // her banknottan kaç adet olduğunu tutan dizi
    int[] adetler = new int[6];

    public void adetlerial() {

        // okunacak dosya
        String csvFile = "dosya.txt";

        BufferedReader br = null;

        // dosyadan okunan satırı tutan değişken
        String line = "";
        // satırı böleceğimiz karakter
        String cvsSplitBy = ",";
        try {

            // buffered reader nesnesi
            br = new BufferedReader(new FileReader(csvFile));
            // adetler indexi
            int i = 0;


            // dosya okuma
            // satır kalmayıncaya kadar oku
            while ((line = br.readLine()) != null) {

                // okuduğun satırı virgül ile bölüp
                String[] degerler = line.split(cvsSplitBy);

                // ikinci elemanları int convert edip adetler dizisinbe atanır
                adetler[i] = Integer.parseInt(degerler[1]);
                i++;

            }

            besTLMiktari = adetler[0];
            onTLMiktari = adetler[1];
            yirmiTLMiktari = adetler[2];
            elliTLMiktari = adetler[3];
            yuzTLMiktari = adetler[4];
            ikiyuzTLMiktari = adetler[5];

           /* for (int j =0;j<6;j++){
                System.out.println("adetler : " + j + " " + adetler[j]);

            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cekilecekMiktarDizi(int tutar) {


  /*  for (int i =0;i<6;i++){
        System.out.println("adetler : " + i + " " + adetler[i]);

    }*/
        //tutar= çekilmek istenen para miktarı


        int[] harcananAdetler = new int[6];

        int eldeKalan = tutar;
        //tutarın 200 e bölümünden kalan sayı = mod200
        int mod200 = tutar % 200;
        //tutarın içindeki 200 tl miktarı adeti. yani 200 ün kaç katı
        int kat200 = (tutar - mod200) / 200;
        if (eldeKalan != 0 && kat200 != 0) {
            if (ikiyuzTLMiktari > 0) {
                if (ikiyuzTLMiktari < kat200) {// ikiyuztl miktarı bankada asıl olan paranın adeti YETMİYORSA yapılacak işlemler
                    eldeKalan = tutar - ikiyuzTLMiktari * 200;//tutar - var olan 200lein sayısıçarpı 200 = elde kalan tutardır

                    harcananAdetler[5] = ikiyuzTLMiktari;//harcanan adetler dizisi yeni oluşmaya başlar  ve tutarın içindeki 200 adetleri
                    System.out.println(ikiyuzTLMiktari + "x" + 200 + "=" + ikiyuzTLMiktari * 200);
                } else if (ikiyuzTLMiktari >= kat200) {
                    eldeKalan = tutar - kat200 * 200;
                    harcananAdetler[5] = kat200;//kat200 kadar bir harcama olacagından ...
                    System.out.println(kat200 + "x" + 200 + "=" + kat200 * 200);


                }

            }
        }
        int mod100 = eldeKalan % 100;
        int kat100 = (eldeKalan - mod100) / 100;
        if (eldeKalan != 0 && kat100 != 0) {
            if (yuzTLMiktari > 0) {
                if (yuzTLMiktari < kat100) {
                    eldeKalan = eldeKalan - yuzTLMiktari * 100;
                    harcananAdetler[4] = yuzTLMiktari;
                    System.out.println(yuzTLMiktari + "x" + 100 + "=" + yuzTLMiktari * 100);


                } else if (yuzTLMiktari >= kat100) {
                    eldeKalan -= kat100 * 100;
                    harcananAdetler[4] = kat100;
                    System.out.println(kat100 + "x" + 100 + "=" + kat100 * 100);


                }

            }
        }
        int mod50 = eldeKalan % 50;
        int kat50 = (eldeKalan - mod50) / 50;
        if (eldeKalan != 0 && kat50 != 0) {
            if (elliTLMiktari > 0) {
                if (elliTLMiktari < kat50) {
                    eldeKalan = eldeKalan - elliTLMiktari * 50;
                    harcananAdetler[3] = elliTLMiktari;
                    System.out.println(elliTLMiktari + "x" + 50 + "=" + elliTLMiktari * 50);


                } else if (elliTLMiktari >= kat50) {
                    eldeKalan -= kat50 * 50;
                    harcananAdetler[3] = kat50;
                    System.out.println(kat50 + "x" + 50 + "=" + kat50 * 50);


                }

            }
        }

        int mod20 = eldeKalan % 20;
        int kat20 = (eldeKalan - mod20) / 20;
        if (eldeKalan != 0 && kat20 != 0) {// burada ------------------------------
            if (yirmiTLMiktari > 0) {
                if (yirmiTLMiktari < kat20) {
                    eldeKalan = eldeKalan - yirmiTLMiktari * 20;
                    harcananAdetler[2] = yirmiTLMiktari;
                    System.out.println(yirmiTLMiktari + "x" + 20 + "=" + yirmiTLMiktari * 20);


                } else if (yirmiTLMiktari >= kat20) {
                    eldeKalan -= kat20 * 20;
                    harcananAdetler[2] = kat20;
                    System.out.println(kat20 + "x" + 20 + "=" + kat20 * 20);


                }

            }
        }

        int mod10 = eldeKalan % 10;
        int kat10 = (eldeKalan - mod10) / 10;
        if (eldeKalan != 0 && kat10 != 0) {
            if (onTLMiktari > 0) {
                if (onTLMiktari < kat10) {
                    eldeKalan = eldeKalan - onTLMiktari * 10;
                    harcananAdetler[1] = onTLMiktari;
                    System.out.println(onTLMiktari + "x" + 10 + "=" + onTLMiktari * 10);


                } else if (onTLMiktari >= kat10) {
                    eldeKalan -= kat10 * 10;
                    harcananAdetler[1] = kat10;
                    System.out.println(kat10 + "x" + 10 + "=" + kat10 * 10);


                }

            }
        }
        int mod5 = eldeKalan % 5;
        int kat5 = (eldeKalan - mod5) / 5;
        if (eldeKalan != 0) {
            if (besTLMiktari > 0) {
                if (besTLMiktari < kat5) {
                    eldeKalan = eldeKalan - besTLMiktari * 5;
                    harcananAdetler[0] = besTLMiktari;
                    System.out.println(besTLMiktari + "x" + 5 + "=" + besTLMiktari * 5);


                } else if (besTLMiktari >= kat5) {
                    eldeKalan -= kat5 * 5;
                    harcananAdetler[0] = kat5;
                    System.out.println(kat5 + "x" + 5 + "=" + kat5 * 5);


                }

            }
        }
        System.out.println("Toplam = " + tutar + " tl ");
        for (int i = 0; i < 6; i++) {
            adetler[i] -= harcananAdetler[i];
        }


        /*for (int i = 0; i < 6; i++) {
            System.out.println("adetler : " + i + " " + adetler[i]);

        }*/

    }

    public void dosyaGuncelle() {
        try {
            File file = new File("dosya.txt");

            String str = "5," + adetler[0] + "\n" +
                    "10," + adetler[1] + "\n" +
                    "20," + adetler[2] + "\n" +
                    "50," + adetler[3] + "\n" +
                    "100," + adetler[4] + "\n" +
                    "200," + adetler[5];


            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false
            );
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(str);
            bWriter.close();
            System.out.println("Paranızı alt bölmeden alabilirsiniz.---\n Anasayfaya yönlendiriliyorsunuz...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


  /*  public void veriCikartma(int tutar) {

        while (true) {


            int mod200 = tutar % 200;

            int mod100 = tutar % 100;
            int mod50 = tutar % 50;
            int mod20 = tutar % 20;
            int mod10 = tutar % 10;
            int mod5 = tutar % 5;

            int kat200 = (tutar - mod200) / 200;
            int kat100 = (tutar - mod100) / 100;
            int kat50 = (tutar - mod50) / 50;
            int kat20 = (tutar - mod20) / 20;
            int kat10 = (tutar - mod10) / 10;
            int kat5 = (tutar - mod5) / 5;
            if (tutar <= 1000 && tutar > 0 && tutar % 5 == 0) {
                if ((kat200 >= 1 && (ikiyuzTLMiktari > 0))) {

                    ikiyuzTLMiktari -= kat200;// 200 tl adetlerinden tutarda kaç ikiyuz varsaonu düştük.
                    System.out.println(kat200 + "x" + 200 + "TL =" + kat200 * 200);
                    tutar -= kat200 * 200;


                } else if (((kat200) <= 0 && (kat100) >= 1 && yuzTLMiktari > 0 && kat100 <= yuzTLMiktari) || (kat200 > 0 && kat100 >= 1 && yuzTLMiktari > 0 && kat100 <= yuzTLMiktari)) {

                    yuzTLMiktari -= kat100;//kalan yuztl adedi
                    System.out.println(kat100 + "x" + 100 + "TL =" + kat100 * 100);
                    tutar -= kat100 * 100;

                } else if (((kat200) <= 0 && (kat100) <= 0 && (kat50) >= 1 && elliTLMiktari > 0 && kat50 <= elliTLMiktari) || (kat200 > 0 && kat100 > 0 && kat50 >= 1 && elliTLMiktari > 0 && kat50 <= elliTLMiktari)) {
                    elliTLMiktari -= kat50;
                    System.out.println(kat50 + "x" + 50 + "TL =" + kat50 * 50);
                    tutar -= kat50 * 50;

                } else if (((kat200) <= 0 && (kat100) <= 0 && (kat50) <= 0 && (kat20) >= 1 && yirmiTLMiktari > 0 && kat20 <= yirmiTLMiktari) || ((kat200 > 0 && kat100 > 0 && kat50 > 0 && kat20 >= 1 && yuzTLMiktari > 0 && kat20 <= yirmiTLMiktari))) {
                    yirmiTLMiktari -= kat20;
                    System.out.println(kat20 + "x" + 20 + "TL =" + kat20 * 20);
                    tutar -= kat20 * 20;
                } else if ((kat200) <= 0 && ((kat100) <= 0 && (kat50) <= 0 && (kat20) <= 0 && (kat10) >= 1 && onTLMiktari > 0 && kat10 <= onTLMiktari) || ((kat200 > 0 && kat100 > 0 && kat50 > 0) || kat20 > 0 && kat10 >= 1 && onTLMiktari > 0 && kat10 <= onTLMiktari)) {
                    onTLMiktari -= kat10;
                    System.out.println(kat10 + "x" + 10 + "TL =" + kat10 * 10);
                    tutar -= kat10 * 10;
                    parasilme(tutar, kat10);
                } else if (((kat200) <= 0 && (kat100) <= 0 && (kat50) <= 0 && (kat20) <= 0 && (kat10) <= 0 && (kat5) >= 1 && besTLMiktari > 0 && kat5 <= besTLMiktari) || (((kat200 > 0 && kat100 > 0 && kat50 > 0 && kat20 > 0 && kat10 > 0) || kat5 >= 1 && besTLMiktari > 0 && kat5 <= besTLMiktari))) {
                    besTLMiktari -= kat5;
                    System.out.println(kat5 + "x" + 5 + "TL=" + kat5 * 5);
                    tutar -= kat5 * 5;

                    parasilme(5, kat5);

                }


            } else if (tutar % 5 != 0) {
                System.out.println("5 TL ve katlarını çekebilirsiniz. ");
                break;
            } else if (tutar > 1000) {
                System.out.println("Günlük maksimum çekim miktarı : 1000 TL'dir");
                break;

            }

        }

    }*/


    public Banka() {

        adetlerial();

    }

    public Banka(int toplamBankaBakiyesi) {
        this.toplamBankaBakiyesi = toplamBankaBakiyesi;
    }

    public void paraekleme(int para, int adet) {


        if (para == 5) {
            besTLMiktari += adet;
            setBesTLMiktari(besTLMiktari);
        } else if (para == 10) {
            onTLMiktari += adet;
        } else if (para == 20) {
            yirmiTLMiktari += adet;
        } else if (para == 50) {
            elliTLMiktari += adet;
        } else if (para == 100) {
            yirmiTLMiktari += adet;
        } else if (para == 200) {
            ikiyuzTLMiktari += adet;
        }

    }

    /*public void parasilme(int para, int adet) {
        if (para == 5) {
            besTLMiktari -= adet;
            setBesTLMiktari(besTLMiktari);
        } else if (para == 10) {
            onTLMiktari -= adet;
        } else if (para == 20) {
            yirmiTLMiktari -= adet;
        } else if (para == 50) {
            elliTLMiktari -= adet;
        } else if (para == 100) {
            yirmiTLMiktari -= adet;
        } else if (para == 200) {
            ikiyuzTLMiktari += adet;
        }

    }*/

    public int getToplamBankaBakiyesi() {
        return toplamBankaBakiyesi = (5 * besTLMiktari) + (10 * onTLMiktari) + (20 * yirmiTLMiktari) + (50 * elliTLMiktari) + (100 * yirmiTLMiktari) + (200 * ikiyuzTLMiktari);
    }

    public void setToplamBankaBakiyesi(int toplamBankaBakiyesi) {
        this.toplamBankaBakiyesi = toplamBankaBakiyesi;
    }


    public int getBesTLMiktari() {
        return besTLMiktari;
    }

    public void setBesTLMiktari(int besTLMiktari) {
        this.besTLMiktari = besTLMiktari;
    }

    public int getOnTLMiktari() {
        return onTLMiktari;
    }

    public void setOnTLMiktari(int onTLMiktari) {
        this.onTLMiktari = onTLMiktari;
    }

    public int getYirmiTLMiktari() {
        return yirmiTLMiktari;
    }

    public void setYirmiTLMiktari(int yirmiTLMiktari) {
        this.yirmiTLMiktari = yirmiTLMiktari;
    }

    public int getElliTLMiktari() {
        return elliTLMiktari;
    }

    public void setElliTLMiktari(int elliTLMiktari) {
        this.elliTLMiktari = elliTLMiktari;
    }

    public int getYuzTLMiktari() {
        return yuzTLMiktari;
    }

    public void setYuzTLMiktari(int yuzTLMiktari) {
        this.yuzTLMiktari = yuzTLMiktari;
    }

    public int getIkiyuzTLMiktari() {
        return ikiyuzTLMiktari;
    }

    public void setIkiyuzTLMiktari(int ikiyuzTLMiktari) {
        this.ikiyuzTLMiktari = ikiyuzTLMiktari;
    }
}
