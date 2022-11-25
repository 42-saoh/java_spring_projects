package basicio;

import java.util.Scanner;

public class IsAdult {
    public static void main(String[] args) {
        Scanner ageScanner = new Scanner(System.in);

        do {
            System.out.println("나이를 입력해주세요.");
            int age = Integer.parseInt(ageScanner.nextLine());
            System.out.println("입력한 나이는 " + age + "입니다.");
            if (age > 19)
                System.out.println("성인입니다.");
            else
                System.out.println("학생입니다.");
            System.out.println("계속 하시겠습니까? (y or any key)");
        } while (ageScanner.nextLine().equals("y"));
    }
}
