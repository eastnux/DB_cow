package io.mobile.farm;

import java.util.List;
import java.util.Scanner;

public class FarmMain {
    private static FarmService farmService = new FarmService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllFarms();
                    break;
                case 2:
                    getFarmById();
                    break;
                case 3:
                    insertNewFarm();
                    break;
                case 4:
                    updateFarm();
                    break;
                case 5:
                    deleteFarm();
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
        System.out.println("\n--- 농장 관리 시스템 ---");
        System.out.println("1. 모든 농장 목록 조회");
        System.out.println("2. ID로 농장 조회");
        System.out.println("3. 새로운 농장 추가");
        System.out.println("4. 농장 정보 업데이트");
        System.out.println("5. 농장 삭제");
        System.out.println("6. 종료");
        System.out.print("실행할 번호 입력 : ");
    }

    private static void listAllFarms() {
        List<Farm> farms = farmService.selectAll();
        if (farms.isEmpty()) {
            System.out.println("농장이 없습니다.");
        } else {
            for (Farm farm : farms) {
                System.out.println(farm);
            }
        }
    }

    private static void getFarmById() {
        System.out.print("농장 ID를 입력하세요: ");
        String farmId = scanner.nextLine();
        Farm farm = farmService.selectById(farmId);
        if (farm != null) {
            System.out.println(farm);
        } else {
            System.out.println("농장을 찾을 수 없습니다.");
        }
    }

    private static void insertNewFarm() {
        System.out.print("농장 ID를 입력하세요: ");
        String farmId = scanner.nextLine();
        System.out.print("농장 이름을 입력하세요: ");
        String farmName = scanner.nextLine();
        System.out.print("농장 위치를 입력하세요: ");
        String farmLocation = scanner.nextLine();

        Farm newFarm = new Farm(farmId, farmName, farmLocation);
        farmService.insert(newFarm);
        System.out.println("새로운 농장이 성공적으로 추가되었습니다.");
    }

    private static void updateFarm() {
        System.out.print("업데이트할 농장의 ID를 입력하세요: ");
        String farmId = scanner.nextLine();
        Farm existingFarm = farmService.selectById(farmId);
        if (existingFarm == null) {
            System.out.println("농장을 찾을 수 없습니다.");
            return;
        }

        System.out.print("새로운 농장 이름을 입력하세요 (변경하지 않으려면 엔터): ");
        String farmName = scanner.nextLine();
        System.out.print("새로운 농장 위치를 입력하세요 (변경하지 않으려면 엔터): ");
        String farmLocation = scanner.nextLine();

        Farm updatedFarm = new Farm(
                farmId,
                farmName.isEmpty() ? existingFarm.getFarm_name() : farmName,
                farmLocation.isEmpty() ? existingFarm.getFarm_location() : farmLocation
        );

        farmService.updateById(farmId, updatedFarm);
        System.out.println("농장 정보가 성공적으로 업데이트되었습니다.");
    }

    private static void deleteFarm() {
        System.out.print("삭제할 농장의 ID를 입력하세요: ");
        String farmId = scanner.nextLine();
        farmService.deleteById(farmId);
        System.out.println("농장이 성공적으로 삭제되었습니다.");
    }
}
