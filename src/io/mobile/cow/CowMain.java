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
            scanner.nextLine(); // Consume newline

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
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Cow Management System ---");
        System.out.println("1. List all cows");
        System.out.println("2. Get cow by ID");
        System.out.println("3. Insert new cow");
        System.out.println("4. Update cow");
        System.out.println("5. Delete cow");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void listAllCows() {
        List<Cow> cows = cowService.selectAll();
        if (cows.isEmpty()) {
            System.out.println("No cows found.");
        } else {
            for (Cow cow : cows) {
                System.out.println(cow);
            }
        }
    }

    private static void getCowById() {
        System.out.print("Enter cow ID: ");
        String cowId = scanner.nextLine();
        Cow cow = cowService.selectById(cowId);
        if (cow != null) {
            System.out.println(cow);
        } else {
            System.out.println("Cow not found.");
        }
    }

    private static void insertNewCow() {
        System.out.print("Enter cow ID: ");
        String cowId = scanner.nextLine();
        System.out.print("Enter cow age: ");
        Integer age = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter cow health status: ");
        String healthStatus = scanner.nextLine();
        System.out.print("Enter cow gender: ");
        String gender = scanner.nextLine();

        Cow newCow = new Cow(cowId, age, healthStatus, gender);
        cowService.insert(newCow);
        System.out.println("New cow inserted successfully.");
    }

    private static void updateCow() {
        System.out.print("Enter cow ID to update: ");
        String cowId = scanner.nextLine();
        Cow existingCow = cowService.selectById(cowId);
        if (existingCow == null) {
            System.out.println("Cow not found.");
            return;
        }

        System.out.print("Enter new age (press enter to keep current): ");
        String age = scanner.nextLine();
        System.out.print("Enter new health status (press enter to keep current): ");
        String healthStatus = scanner.nextLine();
        System.out.print("Enter new gender (press enter to keep current): ");
        String gender = scanner.nextLine();

        Cow updatedCow = new Cow(
                cowId,
                age.isEmpty() ? existingCow.getAge() : Integer.parseInt(age),
                healthStatus.isEmpty() ? existingCow.getHealth_status() : healthStatus,
                gender.isEmpty() ? existingCow.getGender() : gender
        );

        cowService.updateById(cowId, updatedCow);
        System.out.println("Cow updated successfully.");
    }

    private static void deleteCow() {
        System.out.print("Enter cow ID to delete: ");
        String cowId = scanner.nextLine();
        cowService.deleteById(cowId);
        System.out.println("Cow deleted successfully.");
    }
}
