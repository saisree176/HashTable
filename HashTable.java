import java.util.Scanner;
import java.util.LinkedList;

class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTable() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % table.length);
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> list = table[index];
        if (list != null) {
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> list = table[index];
        if (list != null) {
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    list.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public void displayHashTable() {
        System.out.println("Hash Table:");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    System.out.println("[" + entry.key + ", " + entry.value + "]");
                }
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable<String, Integer> hashTable = new HashTable<>();

        do {
            System.out.println("1. Insert Key-Value Pair");
            System.out.println("2. Retrieve Value by Key");
            System.out.println("3. Remove Key-Value Pair");
            System.out.println("4. Display Hash Table");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = scanner.next();
                    System.out.print("Enter value: ");
                    int value = scanner.nextInt();
                    hashTable.put(key, value);
                    break;
                case 2:
                    System.out.print("Enter key to retrieve value: ");
                    key = scanner.next();
                    Integer retrievedValue = hashTable.get(key);
                    if (retrievedValue != null) {
                        System.out.println("Value for key '" + key + "': " + retrievedValue);
                    } else {
                        System.out.println("Key '" + key + "' not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter key to remove: ");
                    key = scanner.next();
                    hashTable.remove(key);
                    System.out.println("Key '" + key + "' removed.");
                    break;
                case 4:
                    hashTable.displayHashTable();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.print("Do you want to continue? (yes/no): ");
        } while (scanner.next().equalsIgnoreCase("yes"));
        
        scanner.close();
    }
}
