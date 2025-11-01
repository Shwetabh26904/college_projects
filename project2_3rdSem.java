import java.util.*;

class BAccount {
    private String accountNumber;
    private String accountHolderName;
    private String password;
    private double balance;

    public BAccount(String accNo, String name, String pass, double initialBalance) {
        this.accountNumber = accNo;
        this.accountHolderName = name;
        this.password = pass;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return accountHolderName;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }
    public void displayInfo() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: ₹" + balance);
    }
}

class MultiUserSystem {
    private static HashMap<String, BAccount> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== WELCOME TO BANK SYSTEM ===");
            System.out.println("1. Create New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginAccount();
                    break;
                case 3:
                    System.out.println("Thank you for using the Bank System!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
    private static void createAccount() {       // Create a new account
        sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        if (accounts.containsKey(accNo)) {
            System.out.println("Account number already exists!");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Set Password: ");
        String pass = sc.nextLine();
        System.out.print("Enter Initial Deposit: ₹");
        double initial = sc.nextDouble();

        BAccount newAcc = new BAccount(accNo, name, pass, initial);
        accounts.put(accNo, newAcc);

        System.out.println("Account created successfully!");
    }
    private static void loginAccount() {        // Login to account
        sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        BAccount acc = accounts.get(accNo);
        if (!acc.getPassword().equals(pass)) {
            System.out.println("Incorrect password!");
            return;
        }

        System.out.println("\nWelcome, " + acc.getName() + "!");
        accountMenu(acc);
    }
    private static void accountMenu(BAccount acc) {         // Menu for logged-in user
        int choice;
        do {
            System.out.println("\n=== ACCOUNT MENU ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Account Info");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    acc.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    acc.withdraw(sc.nextDouble());
                    break;
                case 3:
                    acc.checkBalance();
                    break;
                case 4:
                    acc.displayInfo();
                    break;
                case 5:
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }
}
