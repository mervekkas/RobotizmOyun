package com.yilandene;

public class Robot1 {

    int YUKARİ = 0;
    int ASAGİ = 1;
    int SOL = 2;
    int SAG = 3;
    int BASLANGİC = 4;

    int [][] saha;

    int bos_kare = 0;
    int kare_duvar = 1;
    int puan_kare = 2;

    int x,y;
    int mod = BASLANGİC;

    public Robot1(int x, int y, int [][] saha){

        this.saha = saha;
        this.x = x;
        this.y = y;

    }//klavyeden bastığımız tuşlara göre hareket etmesini sağlıyoruz
    public void yuru(){

            if(mod == YUKARİ){
                y++;
            }
            else if(mod == ASAGİ){
                y--;
            }
            else if(mod == SOL){
                x--;
            }
            else if(mod == SAG){
                x++;
            }
            else if(mod == BASLANGİC){
                x=0;
                y=0;
            }


        //if(saha [x][y] == bos_kare){ } robot iç zeminde mi kontrol edecek

    }
    public  void modDegistir(int i){
        if( i<5 && i>=0){
            mod = i;
        }
    }
}
