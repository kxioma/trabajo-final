// Bank.java
// Sistema bancario con ArrayList, HashMap y Árbol BST.

import java.util.*;

public class Bank {

    private ArrayList<Account> accounts;
    private HashMap<Integer, Account> idMap;
    private int nextId;
    private Scanner sc;

    // ------------------- ÁRBOL BST -------------------
    private class Node {
        Account acc;
        Node left, right;

        Node(Account acc) {
            this.acc = acc;
        }
    }

    private Node root;

    private Node insert(Node node, Account acc) {
        if (node == null) return new Node(acc);
        if (acc.getId() < node.acc.getId()) node.left = insert(node.left, acc);
        else if (acc.getId() > node.acc.getId()) node.right = insert(node.right, acc);
        return node;
    }

    private Account searchBST(Node node, int id) {
        if (node == null) return null;
        if (id == node.acc.getId()) return node.acc;
        if (id < node.acc.getId()) return searchBST(node.left, id);
        return searchBST(node.right, id);
    }

    private void inorder(Node node, List<Account> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.acc);
        inorder(node.right, list);
    }
    // ------------------- FIN ÁRBOL -------------------

    public Bank() {
        accounts = new ArrayList<>();
        idMap = new HashMap<>();
        nextId = 1000;
        sc = new Scanner(System.in);
    }

    public void run() {
        int option;

        do {
            printMenu();
            option = readInt("Opción: ");

            switch (option) {
                case 1: createAccountUI(); break;
                case 2: updateHolderNameUI(); break;
                case 3: deleteAccountUI(); break;
                case 4: depositUI(); break;
                case 5: withdrawUI(); break;
                case 6: transferUI(); break;
                case 7: searchByIdUI(); break;
                case 8: reportByBalanceUI(); break;
                case 9: reportByNameUI(); break;
                case 10: reportByIdTreeUI(); break;
                case 0: System.out.println("Saliendo gracias por preferirnos..."); break;
                default: System.out.println("opción Inválida");
            }

        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n===== SISTEMA BANCARIO RC (REBUBLICA'SCALDERON)=====");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Actualizar nombre");
        System.out.println("3. Eliminar cuenta");
        System.out.println("4. Depositar");
        System.out.println("5. Retirar");
        System.out.println("6. Transferir");
        System.out.println("7. Buscar por ID");
        System.out.println("8. Reporte por saldo");
        System.out.println("9. Reporte por nombre");
        System.out.println("10. Reporte por ID )");
        System.out.println("0. Salir");
    }

    private void createAccountUI() {
        String name = readLine("Nombre: ");
        double initial = readDouble("Saldo inicial: ");
        int id = ++nextId;

        Account acc = new Account(id, name, initial);

        accounts.add(acc);
        idMap.put(id, acc);
        root = insert(root, acc);

        System.out.println("Cuenta creada: " + acc);
    }

    private void updateHolderNameUI() {
        int id = readInt("ID: ");
        Account acc = idMap.get(id);

        if (acc == null) {
            System.out.println("No existe.");
            return;
        }

        acc.setHolderName(readLine("Nuevo nombre: "));
        System.out.println("Actualizado.");
    }

    private void deleteAccountUI() {
        int id = readInt("ID: ");

        Account acc = idMap.remove(id);
        if (acc == null) {
            System.out.println("No existe.");
            return;
        }

        accounts.remove(acc);
        System.out.println("Su cuenta a sido Eliminada exitosamente .");
    }

    private void depositUI() {
        Account acc = idMap.get(readInt("ID: "));
        if (acc == null) { System.out.println("No existe"); return; }

        acc.deposit(readDouble("Monto: "));
        System.out.println("Depósito correcto.");
    }

    private void withdrawUI() {
        Account acc = idMap.get(readInt("ID: "));
        if (acc == null) { System.out.println("No existe"); return; }

        if (acc.withdraw(readDouble("Monto: "))) System.out.println("Retiro ok.");
        else System.out.println("Sin fondos.");
    }

    private void transferUI() {
        Account a = idMap.get(readInt("ID origen: "));
        Account b = idMap.get(readInt("ID destino: "));

        if (a == null || b == null) { System.out.println("Cuenta no existe."); return; }

        double amount = readDouble("Monto: ");

        if (!a.withdraw(amount)) {
            System.out.println("Fondos insuficientes.");
            return;
        }

        b.deposit(amount);
        System.out.println("Transferencia ok.");
    }

    private void searchByIdUI() {
        int id = readInt("ID: ");
        System.out.println("1. HashMap");
        System.out.println("2. BST");

        int op = readInt("Opción: ");

        if (op == 1) System.out.println(idMap.get(id));
        else System.out.println(searchBST(root, id));
    }

    private void reportByBalanceUI() {
        for (Account a : Sorter.mergeSortByBalance(accounts)) System.out.println(a);
    }

    private void reportByNameUI() {
        for (Account a : Sorter.quickSortByName(accounts)) System.out.println(a);
    }

    private void reportByIdTreeUI() {
        List<Account> list = new ArrayList<>();
        inorder(root, list);
        for (Account a : list) System.out.println(a);
    }

    private int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine().trim());
    }

    private String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}

