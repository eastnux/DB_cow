package io.mobile.cow;

import java.util.List;
import java.util.Scanner;

public class CowMain {
    private static CowService cowService = new CowService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllCows();
                    break;
                case 2:
                    getCowById();
                    break;
                case 3:
                    insertNewCow();
                    break;
                case 4:
                    updateCow();
                    break;
                case 5:
                    deleteCow();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- 소 관리 시스템 ---");
        System.out.println("1. 모든 소 목록 조회");
        System.out.println("2. ID로 소 조회");
        System.out.println("3. 새로운 소 추가");
        System.out.println("4. 소 정보 업데이트");
        System.out.println("5. 소 삭제");
        System.out.println("6. 종료");
        System.out.print("실행할 번호 입력 : ");
    }

    private static void listAllCows() {
        List<Cow> cows = cowService.selectAll();
        if (cows.isEmpty()) {
            System.out.println("소가 없습니다.");
        } else {
            for (Cow cow : cows) {
                System.out.println(cow);
            }
        }
    }

    private static void getCowById() {
        System.out.print("소 ID를 입력하세요: ");
        String cowId = scanner.nextLine();
        Cow cow = cowService.selectById(cowId);
        if (cow != null) {
            System.out.println(cow);
        } else {
            System.out.println("소를 찾을 수 없습니다.");
        }
    }

    private static void insertNewCow() {
        System.out.print("소 ID를 입력하세요: ");
        String cowId = scanner.nextLine();
        System.out.print("소의 나이를 입력하세요: ");
        Integer age = Integer.valueOf(scanner.nextLine());
        System.out.print("소의 건강 상태를 입력하세요: ");
        String healthStatus = scanner.nextLine();
        System.out.print("소의 성별을 입력하세요: ");
        String gender = scanner.nextLine();

        Cow newCow = new Cow(cowId, age, healthStatus, gender);
        cowService.insert(newCow);
        System.out.println("새로운 소가 성공적으로 추가되었습니다.");
    }

    private static void updateCow() {
        System.out.print("업데이트할 소의 ID를 입력하세요: ");
        String cowId = scanner.nextLine();
        Cow existingCow = cowService.selectById(cowId);
        if (existingCow == null) {
            System.out.println("소를 찾을 수 없습니다.");
            return;
        }

        System.out.print("새로운 나이를 입력하세요 (변경하지 않으려면 엔터): ");
        String age = scanner.nextLine();
        System.out.print("새로운 건강 상태를 입력하세요 (변경하지 않으려면 엔터): ");
        String healthStatus = scanner.nextLine();
        System.out.print("새로운 성별을 입력하세요 (변경하지 않으려면 엔터): ");
        String gender = scanner.nextLine();

        Cow updatedCow = new Cow(
                cowId,
                age.isEmpty() ? existingCow.getAge() : Integer.parseInt(age),
                healthStatus.isEmpty() ? existingCow.getHealth_status() : healthStatus,
                gender.isEmpty() ? existingCow.getGender() : gender
        );

        cowService.updateById(cowId, updatedCow);
        System.out.println("소 정보가 성공적으로 업데이트되었습니다.");
    }

    private static void deleteCow() {
        System.out.print("삭제할 소의 ID를 입력하세요: ");
        String cowId = scanner.nextLine();
        cowService.deleteById(cowId);
        System.out.println("소가 성공적으로 삭제되었습니다.");
    }
}
