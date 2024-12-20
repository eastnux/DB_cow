package io.mobile.customer;

import java.util.List;
import java.util.Scanner;

public class CustomerMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 제거

            switch (choice) {
                case 1:
                    listAllCustomers();
                    break;
                case 2:
                    getCustomerById();
                    break;
                case 3:
                    insertNewCustomer();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    deleteCustomer();
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
        System.out.println("\n--- 고객 관리 시스템 ---");
        System.out.println("1. 모든 고객 목록 조회");
        System.out.println("2. ID로 고객 조회");
        System.out.println("3. 새로운 고객 추가");
        System.out.println("4. 고객 정보 업데이트");
        System.out.println("5. 고객 삭제");
        System.out.println("6. 종료");
        System.out.print("실행할 번호 입력 : ");
    }

    private static void listAllCustomers() {
        List<Customer> customers = CustomerService.selectAll();
        if (customers == null || customers.isEmpty()) {
            System.out.println("고객이 없습니다.");
        } else {
            System.out.println("고객 목록:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private static void getCustomerById() {
        System.out.print("고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        Customer customer = CustomerService.selectById(customerId);
        if (customer != null) {
            System.out.println("고객 정보: " + customer);
        } else {
            System.out.println("고객을 찾을 수 없습니다.");
        }
    }

    private static void insertNewCustomer() {
        System.out.print("고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        System.out.print("고객 이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("고객 주소를 입력하세요: ");
        String address = scanner.nextLine();

        int result = CustomerService.insert(customerId, name, address);
        if (result > 0) {
            System.out.println("새로운 고객이 성공적으로 추가되었습니다.");
        } else {
            System.out.println("고객 추가에 실패했습니다.");
        }
    }

    private static void updateCustomer() {
        System.out.print("업데이트할 고객의 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        System.out.print("새로운 이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("새로운 주소를 입력하세요: ");
        String address = scanner.nextLine();

        int result = CustomerService.update(customerId, name, address);
        if (result > 0) {
            System.out.println("고객 정보가 성공적으로 업데이트되었습니다.");
        } else {
            System.out.println("고객 정보 업데이트에 실패했습니다.");
        }
    }

    private static void deleteCustomer() {
        System.out.print("삭제할 고객의 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        int result = CustomerService.delete(customerId);
        if (result > 0) {
            System.out.println("고객이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("고객 삭제에 실패했습니다.");
        }
    }
}
