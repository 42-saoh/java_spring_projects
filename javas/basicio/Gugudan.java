package basicio;

import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args) {
        Scanner danScanner = new Scanner(System.in);

        do {
            System.out.println("구구단 중 출력할 단은? ");
            int dan = Integer.parseInt(danScanner.nextLine());
            while (dan < 2 || dan > 9) {
                System.out.println("2에서 9 사이의 숫자를 입력해주세요.");
                dan = Integer.parseInt(danScanner.nextLine());
            }
            System.out.println(dan + "단을 출력합니다.");
            for (int i = 1; i < 10; i++) {
                System.out.println(dan + " * " + i + " = " + dan * i);
            }
            System.out.println("계속 하시겠습니까? (y or any key)");
        } while (danScanner.nextLine().equals("y"));
    }
}
