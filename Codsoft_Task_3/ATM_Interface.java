package Codsoft_Task_3;

import java.util.Scanner;

//BankAccount class - handles account balance
class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
     balance = initialBalance;
 }

 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Amount deposited successfully.");
     } else {
         System.out.println("Invalid deposit amount.");
     }
 }

 public void withdraw(double amount) {
     if (amount <= 0) {
         System.out.println("Invalid withdrawal amount.");
     } else if (amount > balance) {
         System.out.println("Insufficient balance.");
     } else {
         balance -= amount;
         System.out.println("Amount withdrawn successfully.");
     }
 }

 public double getBalance() {
     return balance;
 }
}

//ATM class - handles user interaction
public class ATM_Interface {
 private static Scanner scanner = new Scanner(System.in);

 public static void main(String[] args) {
     BankAccount account = new BankAccount(1000.00); // Initial balance

     System.out.println("Welcome to the ATM!");

     boolean exit = false;

     while (!exit) {
         System.out.println("\n--- ATM Menu ---");
         System.out.println("1. Check Balance");
         System.out.println("2. Deposit Money");
         System.out.println("3. Withdraw Money");
         System.out.println("4. Exit");
         System.out.print("Choose an option (1-4): ");
         int choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.printf("Current Balance: ₹%.2f\n", account.getBalance());
                 break;

             case 2:
                 System.out.print("Enter amount to deposit: ₹");
                 double depositAmount = scanner.nextDouble();
                 account.deposit(depositAmount);
                 break;

             case 3:
                 System.out.print("Enter amount to withdraw: ₹");
                 double withdrawAmount = scanner.nextDouble();
                 account.withdraw(withdrawAmount);
                 break;

             case 4:
                 exit = true;
                 System.out.println("Thank you for using the ATM. Goodbye!");
                 break;

             default:
                 System.out.println("Invalid option. Please try again.");
         }
     }

     scanner.close();
 }
}