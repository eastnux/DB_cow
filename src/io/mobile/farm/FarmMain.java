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
            scanner.nextLine(); // Consume newline

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
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Farm Management System ---");
        System.out.println("1. List all farms");
        System.out.println("2. Get farm by ID");
        System.out.println("3. Insert new farm");
        System.out.println("4. Update farm");
        System.out.println("5. Delete farm");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void listAllFarms() {
        List<Farm> farms = farmService.selectAll();
        if (farms.isEmpty()) {
            System.out.println("No farms found.");
        } else {
            for (Farm farm : farms) {
                System.out.println(farm);
            }
        }
    }

    private static void getFarmById() {
        System.out.print("Enter farm ID: ");
        String farmId = scanner.nextLine();
        Farm farm = farmService.selectById(farmId);
        if (farm != null) {
            System.out.println(farm);
        } else {
            System.out.println("Farm not found.");
        }
    }

    private static void insertNewFarm() {
        System.out.print("Enter farm ID: ");
        String farmId = scanner.nextLine();
        System.out.print("Enter farm name: ");
        String farmName = scanner.nextLine();
        System.out.print("Enter farm location: ");
        String farmLocation = scanner.nextLine();

        Farm newFarm = new Farm(farmId, farmName, farmLocation);
        farmService.insert(newFarm);
        System.out.println("New farm inserted successfully.");
    }

    private static void updateFarm() {
        System.out.print("Enter farm ID to update: ");
        String farmId = scanner.nextLine();
        Farm existingFarm = farmService.selectById(farmId);
        if (existingFarm == null) {
            System.out.println("Farm not found.");
            return;
        }

        System.out.print("Enter new farm name (press enter to keep current): ");
        String farmName = scanner.nextLine();
        System.out.print("Enter new farm location (press enter to keep current): ");
        String farmLocation = scanner.nextLine();

        Farm updatedFarm = new Farm(
                farmId,
                farmName.isEmpty() ? existingFarm.getFarm_name() : farmName,
                farmLocation.isEmpty() ? existingFarm.getFarm_location() : farmLocation
        );

        farmService.updateById(farmId, updatedFarm);
        System.out.println("Farm updated successfully.");
    }

    private static void deleteFarm() {
        System.out.print("Enter farm ID to delete: ");
        String farmId = scanner.nextLine();
        farmService.deleteById(farmId);
        System.out.println("Farm deleted successfully.");
    }
}
