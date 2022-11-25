package basicio;

import java.util.Arrays;
import java.util.Scanner;

public class SumArray {
    public static void main(String[] args) {
        Scanner sumScanner = new Scanner(System.in);


        do {
            System.out.println("숫자 목록을 입력해주세요. 구분은 콤마(,)로 합니다.");
            try {
                int sum = Arrays
                        .stream(sumScanner.nextLine().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum);
                System.out.println("합계는 " + sum + "입니다.");
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage().substring(18) + " 숫자가 아닙니다.");
            }
            System.out.println("계속 하시겠습니까? (y or any key)");
        } while (sumScanner.nextLine().equals("y"));
    }
}