package BehaviourPattern;

import java.util.*;

public class MementoDesignPattern {
    public static void main(String[] args) {
        Database db = new Database();
        TransactionManager manager = new TransactionManager();

        manager.beginTransaction(db);
        db.insert("User1", "Yash");
        db.insert("User2", "Sahil");
        manager.commitTransaction();

        db.displayRecords();

        manager.beginTransaction(db);
        db.insert("User3", "Jack");
        db.insert("User4", "Daniel");

        db.displayRecords();

        System.out.println("ERROR SOmething went wrong during transaction");
        manager.rollbackTransaction(db);
        db.displayRecords();
    }
}

class DatabaseMemento {
    Map<String, String> data = new HashMap<>();

    DatabaseMemento(Map<String, String> data) {
        this.data = new HashMap<>(data);
    }

    Map<String, String> getState() {
        return data;
    }
}

class Database {
    Map<String, String> records = new HashMap<>();

    void insert(String key, String value) {
        records.put(key, value);
        System.out.println("Record Inserted key " + key + " value" + value);
    }

    void update(String key, String value) {
        if (records.containsKey(key)) {
            records.put(key, value);
            System.out.println("Updated Key " + key + " Value " + value);
        } else {
            System.out.println("Key not Found in Records");
        }
    }

    void remove(String key) {
        if (records.containsKey(key)) {
            records.remove(key);
            System.out.println("Deleted Record" + key);
        } else {
            System.out.println("Key Not Found" + key);
        }
    }

    DatabaseMemento createMemento() {
        System.out.println("Creating DB Backup");
        return new DatabaseMemento(new HashMap<>(records));
    }

    void restoreMemento(DatabaseMemento memento) {
        records = new HashMap<>(memento.getState());
        System.out.println("Database restore from backup");
    }

    void displayRecords() {
        System.out.println("==== Current Database State ====");
        if (records.isEmpty()) {
            System.out.println("Records is Empty");
        } else {
            for (Map.Entry<String, String> map : records.entrySet()) {
                System.out.println("Key is " + map.getKey() + " value is" + map.getValue());
            }
            System.out.println();
        }
    }
}

class TransactionManager {
    DatabaseMemento backup;

    TransactionManager() {
        backup = null;
    }

    void beginTransaction(Database db) {
        System.out.println("===== BEGIN TRANSACTION =====");
        if (backup != null) {
            backup = null;
        }
        backup = db.createMemento();
    }

    void commitTransaction() {
        System.out.println("  ======== COMMIT TRANSACTION ===");
        if (backup != null) {
            backup = null;
        }
        System.out.println("Transaction Commit Successfully");
    }

    void rollbackTransaction(Database db) {
        System.out.println("====== ROLLBACK TRANSACTION ======");
        if (backup != null) {
            db.restoreMemento(backup);
            backup = null;
            System.out.println("Transaction Rollback ");
        } else {
            System.out.println("No Backup Available for rollback");
        }
    }
}