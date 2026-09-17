import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu
        String[] items = {
            "Burger",
            "Pizza",
            "Iced Coffee",
            "Spaghetti",
            "French Fries"
        };

        double[] prices = {
            80.00,
            95.00,
            120.00,
            90.00,
            60.00
        };

        int totalQuantity = 0;
        double totalAmount = 0.00;

        boolean orderAgain = true;

        while (orderAgain) {
            System.out.println("\n-------------- MENU -------------");

            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %-20s $%.2f%n",
                        i + 1, items[i], prices[i]);
            }

            System.out.println("---------------------------------");

            System.out.print("Enter item number (1-5): ");
            int itemNumber = scanner.nextInt();

            System.out.print("Enter quantity (1-10): ");
            int quantity = scanner.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            char studentStatus = scanner.next().toUpperCase().charAt(0);

            // Validate order
            if (itemNumber < 1 || itemNumber > items.length) {
                System.out.println("Invalid item number. Order was not added.");
            } else if (quantity < 1 || quantity > 10) {
                System.out.println("Invalid quantity. Please enter 1-10. "
                        + "Order was not added.");
            } else if (studentStatus != 'Y' && studentStatus != 'N') {
                System.out.println("Invalid student status. Please enter Y or N. "
                        + "Order was not added.");
            } else {
                // Valid order
                double orderAmount = prices[itemNumber - 1] * quantity;

                totalQuantity += quantity;
                totalAmount += orderAmount;

                System.out.printf("Order added: %d x %s = $%.2f%n",
                        quantity, items[itemNumber - 1], orderAmount);
            }

            // Ask whether to continue
            System.out.print("\nDo you want to order again? (Y/N): ");
            char choice = scanner.next().toUpperCase().charAt(0);

            if (choice == 'N') {
                orderAgain = false;
            } else if (choice != 'Y') {
                System.out.println("Invalid choice. Ordering will stop.");
                orderAgain = false;
            }
        }

        // Final deduction computation
        double deductionRate;

        // Note: The student status from the final order is used here.
        // To correctly apply student discounts across the whole transaction,
        // track whether the customer is a student separately.
        //
        // Since the requirement asks for the customer's status,
        // ask for it once more for the final transaction if needed.

        System.out.print("\nAre you a student for this transaction? (Y/N): ");
        char finalStudentStatus = scanner.next().toUpperCase().charAt(0);

        if (finalStudentStatus == 'Y' && totalAmount >= 500) {
            deductionRate = 0.15;
        } else if (finalStudentStatus == 'Y') {
            deductionRate = 0.10;
        } else if (totalAmount >= 500) {
            deductionRate = 0.05;
        } else {
            deductionRate = 0.00;
        }

        double totalDeduction = totalAmount * deductionRate;
        double finalAmount = totalAmount - totalDeduction;

        // Final receipt
        System.out.println("\n---------------------------------");
        System.out.println("          ORDER RECEIPT          ");
        System.out.println("---------------------------------");
        System.out.println("Total quantity:             " + totalQuantity);
        System.out.printf("Total before discount:      $%.2f%n", totalAmount);
        System.out.printf("Total deduction:            $%.2f%n", totalDeduction);
        System.out.printf("Final amount to pay:        $%.2f%n", finalAmount);
        
        System.out.println("     THANKYOU FOR ORDERING!");

        scanner.close();
    }
}
