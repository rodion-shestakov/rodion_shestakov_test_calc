import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Input:");
        String strInput = input.nextLine();
        String sinput = "";
        String strOutput = "";
        String ca = "";
        String cb = "";
        String cd = "";
        Boolean ar = false;
        Boolean br = false;
        String[] rimnum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int aa = 0;
        int bb = 0;
        int result = 0;
        int rrr;


        // очистить ввод от пробелов
        for(int i=0; i< strInput.length();i++){
            if(strInput.charAt(i)!=' ') {
                sinput = sinput+strInput.charAt(i);
            }
        }

        // разбить на числа и действие
        for(int i=0; i< sinput.length();i++){
            if(sinput.charAt(i)=='+' || sinput.charAt(i)=='-' || sinput.charAt(i)=='/' || sinput.charAt(i)=='*') {
                ca=sinput.substring(0,i);
                cb=sinput.substring(i+1,sinput.length());
                cd=cd+sinput.charAt(i);
            }
        }

        // определить римские/арабские
        if (ca.contains("I") || ca.contains("V") || ca.contains("X")){
            ar=true;
        }
        if (cb.contains("I") || cb.contains("V") || cb.contains("X")){
            br=true;
        }
        if (ar != br){
            System.out.println("Римляне с Арабами не складывают свои числа в одну корзину!");
            System.exit(0);
        }

        // проверка на целое число
        if (ca.contains(",") || ca.contains(".") || cb.contains(",") || cb.contains(".")){
            System.out.println("Дроби я еще не изучал :-(");
            System.exit(0);
        }

        // преобразовать в арабские
        if (ar){
            List<String> rimList = new ArrayList<>(Arrays.asList(rimnum));
            if (rimList.contains(ca)){
                aa = rimList.indexOf(ca);
                aa++;
            } else {
                System.out.println("Число " + ca +" я еще не проходил :-(");
                System.exit(0);
            }
            if (rimList.contains(cb)){
                bb = rimList.indexOf(cb);
                bb++;
            } else {
                System.out.println("Число " + cb +" я еще не проходил :-(");
                System.exit(0);
            }

        } else {
            aa = Integer.valueOf(ca);
            bb = Integer.valueOf(cb);
        }

        // проверить на ограничение 1..10
        if (aa<1 || aa>10 || bb<1 || bb>10) {
            System.out.println("Я считаю на пальцах, а их у меня десять. С другими числами не работаю.");
            System.exit(0);
        }

        // рассчитать
        switch (cd) {
            case "+":
                //strOutput = Integer.toString(aa+bb);
                result = aa+bb;
                break;
            case "-":
                result = aa-bb;
                break;
            case "*":
                result = aa*bb;
                break;
            case "/":
                result = Math.round(aa/bb);
                break;
        }

        //если были римские, перевести в них. если меньше 1- вывалиться.
        if (ar) {
            if (result<1) {
                System.out.println("У римлян меньше I не бывает!!!");
                System.exit(0);
            } else if (result<11) {
                strOutput = rimnum[result-1];
            } else if (result<40) {
                rrr = result;
                for(int j=0; j<Math.floor(result/10);j++){
                    rrr=rrr-10;
                    strOutput=strOutput+"X";
                }
                if (rrr>0){
                    strOutput = strOutput+rimnum[rrr-1];
                }
            } else if (result<50) {
                strOutput = "XL";
                rrr = result-40;
                if (rrr>0){
                    strOutput = strOutput+rimnum[rrr-1];
                }
            } else if (result<90) {
                strOutput = "L";
                rrr = result-50;
                for(int j=5; j<Math.floor(result/10);j++){
                    rrr=rrr-10;
                    strOutput=strOutput+"X";
                }
                if (rrr>0){
                    strOutput = strOutput+rimnum[rrr-1];
                }
            } else if (result<100) {
                strOutput = "XC";
                rrr = result-90;
                if (rrr>0){
                    strOutput = strOutput+rimnum[rrr-1];
                }
            } else {
                strOutput = "C";
            }
        } else {
            strOutput = Integer.toString(result);
        }

        //выдать результат
        System.out.println("Output: "+strOutput);
    }
}
