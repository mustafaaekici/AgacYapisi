/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilariagacproje2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author msemi
 */
class BSTDugum
{
    public int sayi;
    public BSTDugum sagCocuk;
    public BSTDugum solCocuk;
    public void dugumGoster() 
    { 
        System.out.print(sayi+" "); 
    } 
}
class Agac
 {
    public BSTDugum kok;
    public Agac() 
    { 
        kok = null; 
    }
    public BSTDugum getkok() 
    {
        return kok;
    }
    public void ekleme(int yeniSayi)
    {
        BSTDugum dugum = new BSTDugum();
        dugum.sayi = yeniSayi;
        if(kok == null)
        {
            kok = dugum;
            System.out.println(yeniSayi + " - kök ");
        }
        else 
        {
            BSTDugum simdikiDugum = kok; 
            BSTDugum aile;
            while(true) 
            {
                aile = simdikiDugum;
                if(yeniSayi < simdikiDugum.sayi)
                {        
                    simdikiDugum = simdikiDugum.solCocuk;
                    if(simdikiDugum == null)
                    {
                        aile.solCocuk=dugum;
                        System.out.println(yeniSayi + " - sol ");
                        return;
                    }
                } 
                else
                {
                    simdikiDugum = simdikiDugum.sagCocuk;
                    if(simdikiDugum==null)
                    { 
                        aile.sagCocuk=dugum;
                        System.out.println(yeniSayi + " - sağ ");
                        return; 
                    } 
                }
            }
        }
    }
    public void Silme(int veri)
    {
        BSTDugum simdikiDugum = kok;
        BSTDugum aile = kok;
        boolean solCocukbool = false;
        if (simdikiDugum == null)
        {
            return;
        }
        while(simdikiDugum != null && simdikiDugum.sayi != veri)
        {
            aile = simdikiDugum;
            if (veri < simdikiDugum.sayi)
            {
                simdikiDugum = simdikiDugum.solCocuk;
                solCocukbool = true;
                System.out.println(veri + " Silindi.. ");
                
            }
            else
            {
                simdikiDugum = simdikiDugum.sagCocuk;               
                solCocukbool = false;
                System.out.println(veri + " Silindi.. ");
            }
        }
        if (simdikiDugum == null)
        {
            return;
        }
        if (simdikiDugum.sagCocuk == null && simdikiDugum.solCocuk == null)
        {
            if (simdikiDugum == kok)
            {
                kok = null;
            }
            else
            {
                if (solCocukbool)
                {                 
                    aile.solCocuk = null;
                }
                else
                {   
                    aile.sagCocuk = null;
                }
            }
        }
        else if (simdikiDugum.sagCocuk == null)
        {
            if (simdikiDugum == kok)
            {
                kok = simdikiDugum.solCocuk;                
            }
            else
            {
                if (solCocukbool)
                {
                    aile.solCocuk = simdikiDugum.solCocuk;
                }
                else
                {   
                    aile.sagCocuk = simdikiDugum.solCocuk;
                }
            }
        }
        else if(simdikiDugum.solCocuk == null) 
        {
            if (simdikiDugum == kok)
            {
                kok = simdikiDugum.sagCocuk;
            }
            else
            {
                if (solCocukbool)
                {   
                    aile.solCocuk = simdikiDugum.sagCocuk;
                }
                else
                {  
                    aile.sagCocuk = simdikiDugum.sagCocuk;
                }
            }
        }
        else
        {        
            BSTDugum varisYeri = gidilecekDugum(simdikiDugum);
            if (simdikiDugum == kok)
            {
                kok = varisYeri;
            }
            else if (solCocukbool)
            {
                aile.solCocuk = varisYeri;              
            }
            else
            {
                aile.sagCocuk = varisYeri;
            }
        }
    }
    private BSTDugum gidilecekDugum(BSTDugum kok)
    {
        BSTDugum aileVarisYeri = kok;
        BSTDugum varisYeri = kok;
        BSTDugum simdikiDugum = kok.sagCocuk;
        while (simdikiDugum != null)
        {
            aileVarisYeri = varisYeri;
            varisYeri = simdikiDugum;
            simdikiDugum = simdikiDugum.solCocuk;
        }
        if (varisYeri != kok.sagCocuk)
        {
            aileVarisYeri.solCocuk = varisYeri.sagCocuk;
            varisYeri.sagCocuk = kok.sagCocuk;
        }
        varisYeri.solCocuk = kok.solCocuk;
        return varisYeri;
    }
    public BSTDugum Bul(BSTDugum kok , int deger)
    {    
        int duzey = duzeyBul(kok , deger);
        if (kok == null) 
        {
            return null;
        }
        else if (deger == kok.sayi) 
        {
            System.out.println("Değer kökte bulundu , düzeyi : " + duzey);
        }
        else if (deger < kok.sayi) 
        {
            System.out.println("Aranan değer sol çocukta bulundu , düzeyi : " + duzey);
            return Bul(kok.solCocuk, deger);   
        }
        else if(deger > kok.sayi)
        {    
            System.out.println("Aranan değer sağ çocukta bulundu , düzeyi : " + duzey);
            return Bul(kok.sagCocuk, deger);
        }
        else
        {
            System.out.println("Düğüm bulunamadı..");           
        }
        return null;
    }
    public void preOrderDizilis(BSTDugum kok)
    {
        if(kok!=null) 
        {
            kok.dugumGoster();
            preOrderDizilis(kok.solCocuk);
            preOrderDizilis(kok.sagCocuk);
        }
    }
    public void inOrderDizilis(BSTDugum kok)
    {
        if(kok!=null)
        {
            inOrderDizilis(kok.solCocuk);
            kok.dugumGoster();
            inOrderDizilis(kok.sagCocuk);
        }
    }
    public void postOrderDizilis(BSTDugum kok)
    {
        if(kok != null)
        {
            postOrderDizilis(kok.solCocuk);
            postOrderDizilis(kok.sagCocuk);
            kok.dugumGoster();
        }
    }
    public int dugumSayisi(BSTDugum kok)
    {
        if (kok == null) 
        {
            return 0;
        }
        else
        {
            return  1 + dugumSayisi(kok.solCocuk) + dugumSayisi(kok.sagCocuk);
        }
    }
    public int derinlikBul(BSTDugum kok)
    {
        if (kok == null) 
        {
            return 0;
        }
        else
        {
            int solDerinlik = derinlikBul(kok.solCocuk);
            int sagDerinlik = derinlikBul(kok.sagCocuk);
            if (solDerinlik > sagDerinlik) 
            {
                return solDerinlik + 1;
            }
            else
            {
                return sagDerinlik + 1;
            }
        }
    }
    public void yaprakDugum(BSTDugum kok)
    {
        if (kok == null) 
        {
            return;
        }
        if (kok.solCocuk == null && kok.sagCocuk == null) 
        {
            System.out.println("Yapraklar : " + kok.sayi);
            return;
        }
        if (kok.solCocuk != null) 
        {    
            yaprakDugum(kok.solCocuk);
        }
        if (kok.sagCocuk != null) 
        {
            yaprakDugum(kok.sagCocuk);
        }
    }
    static int duzeyBelirle(BSTDugum kok , int data, int duzey)
    {
        if (kok == null)
            return 0;
 
        if (kok.sayi == data)
            return duzey;
 
        int altDuzey = duzeyBelirle(kok.solCocuk, data, duzey + 1);
        if (altDuzey != 0)
            return altDuzey;
        
        altDuzey = duzeyBelirle(kok.sagCocuk, data, duzey + 1);
        return altDuzey;
    }  
    int duzeyBul(BSTDugum kok, int data)
    {
        return duzeyBelirle(kok, data, 1);
    }
}
public class VeriYapilariAgacProje2 {

    public static void main(String[] args) 
    {
        Agac agac = new Agac();
        Scanner sc = new Scanner(System.in);  
        int sayi = 0 ;     
        String devam ="e";
        boolean flag = true;
        try 
        {
            System.out.println("(1)Ekleme Yap (2)Ağaç Bilgilerini Göster \n(3)Arama Yap (4)Silme Yap (5)Menü");
            while (flag) 
            {   
                System.out.print("Hangi işlemi seçmek istiyorsunuz : ");
                int islem = sc.nextInt();
                if (flag == true) 
                {
                    switch(islem)
                    {
                        case 1 :
                            System.out.print("Ağacın Elemanları : " );
                            sayi = sc.nextInt();
                            agac.ekleme(sayi);
                            break;
                        case 2 :
                            System.out.println("\n- - - - - -AĞAÇ BİLGİLERİ- - - - - - \n");
                            System.out.print("PreOrder Dolaşma :");
                            agac.preOrderDizilis(agac.getkok());
                            System.out.println("");
                            System.out.print("InOrder Dolaşma : ");
                            agac.inOrderDizilis(agac.getkok());
                            System.out.println("");
                            System.out.print("PostOrder Dolaşma : ");
                            agac.postOrderDizilis(agac.getkok());
                            System.out.println("");
                            System.out.println("Düğümün Yüksekliği : " + agac.derinlikBul(agac.kok));
                            System.out.println("Düğüm Sayısı : " + agac.dugumSayisi(agac.kok));
                            agac.yaprakDugum(agac.kok);
                            System.out.println("");
                            break;
                        case 3 :
                            System.out.print("Aranan Düğüm : ");
                            int aranan = sc.nextInt();
                            agac.Bul(agac.kok , aranan);
                            break;
                        case 4 :
                            System.out.print("Silinecek Düğüm : ");
                            int silinecek = sc.nextInt();
                            agac.Silme(silinecek);
                            break;
                        case 5 :
                            System.out.println("Devam etmek için e ,durdurmak için h giriniz");
                            devam = sc.next();
                            if (devam.equals("h")) 
                            {
                                flag = false;
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
        }
        catch (InputMismatchException e) 
        {
            System.out.println("Geçersiz İfade Girdiniz : " + e);   
        }
    }
}
