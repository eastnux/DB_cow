package io.mobile.purchase;

import java.util.List;
import java.util.Scanner;

public class PurchaseMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllPurchases();
                    break;
                case 2:
                    getPurchaseByOrderNumber();
                    break;
                case 3:
                    insertNewPurchase();
                    break;
                case 4:
                    updatePurchase();
                    break;
                case 5:
                    deletePurchase();
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
        System.out.println("\n--- 구매 관리 시스템 ---");
        System.out.println("1. 모든 구매 목록 조회");
        System.out.println("2. 주문 번호로 특정 구매 조회");
        System.out.println("3. 새로운 구매 추가");
        System.out.println("4. 기존 구매 정보 업데이트");
        System.out.println("5. 구매 삭제");
        System.out.println("6. 종료");
        System.out.print("실행할 번호 입력 : ");
    }

    private static void listAllPurchases() {
        List<Purchase> purchases = PurchaseService.selectAll();
        if (purchases == null || purchases.isEmpty()) {
            System.out.println("구매 내역이 없습니다.");
        } else {
            for (Purchase purchase : purchases) {
                System.out.println(purchase);
            }
        }
    }

    private static void getPurchaseByOrderNumber() {
        System.out.print("주문 번호 입력: ");
        int orderNub = scanner.nextInt();
        scanner.nextLine();
        Purchase purchase = PurchaseService.selectByOrderNumber(orderNub);
        if (purchase != null) {
            System.out.println(purchase);
        } else {
            System.out.println("해당 주문을 찾을 수 없습니다.");
        }
    }

    private static void insertNewPurchase() {
        System.out.print("주문 번호 입력: ");
        int orderNub = scanner.nextInt();
        scanner.nextLine();
        System.out.print("주문 날짜 입력: ");
        String orderDate = scanner.nextLine();
        System.out.print("주문 수량 입력: ");
        int orderQuantity = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 제거
        System.out.print("배송 주소 입력: ");
        String shippingAddress = scanner.nextLine();

        int result = PurchaseService.insert(orderNub, orderDate, orderQuantity, shippingAddress);
        if (result > 0) {
            System.out.println("새 주문이 성공적으로 추가되었습니다.");
        } else {
            System.out.println("주문 추가에 실패했습니다.");
        }
    }

    private static void updatePurchase() {
        System.out.print("업데이트할 주문 번호 입력: ");
        int orderNub = scanner.nextInt();
        scanner.nextLine();
        Purchase existingPurchase = PurchaseService.selectByOrderNumber(orderNub);
        if (existingPurchase == null) {
            System.out.println("해당 주문을 찾을 수 없습니다.");
            return;
        }

        System.out.print("새 주문 날짜 입력 (변경하지 않으려면 엔터): ");
        String orderDate = scanner.nextLine();
        if (orderDate.isEmpty()) {
            orderDate = existingPurchase.getOrder_date();
        }

        System.out.print("새 주문 수량 입력 (변경하지 않으려면 엔터): ");
        String orderQuantityStr = scanner.nextLine();
        int orderQuantity = orderQuantityStr.isEmpty() ? existingPurchase.getOrder_quantity() : Integer.parseInt(orderQuantityStr);

        System.out.print("새 배송 주소 입력 (변경하지 않으려면 엔터): ");
        String shippingAddress = scanner.nextLine();
        if (shippingAddress.isEmpty()) {
            shippingAddress = existingPurchase.getShipping_address();
        }

        int result = PurchaseService.update(orderNub, orderDate, orderQuantity, shippingAddress);
        if (result > 0) {
            System.out.println("주문이 성공적으로 업데이트되었습니다.");
        } else {
            System.out.println("주문 업데이트에 실패했습니다.");
        }
    }

    private static void deletePurchase() {
        System.out.print("삭제할 주문 번호 입력: ");
        int orderNub = scanner.nextInt();
        scanner.nextLine();
        int result = PurchaseService.delete(orderNub);
        if (result > 0) {
            System.out.println("주문이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("주문 삭제에 실패했습니다. 해당 주문이 존재하지 않을 수 있습니다.");
        }
    }
}
