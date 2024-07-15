package hashtable;

public class Main {
    public static void main(String[] args) {
        HashTable myHashTable = new HashTable();
        myHashTable. set("nails", 100);
        myHashTable. set ("tile", 50);
        myHashTable. set("lumber", 80);
        myHashTable.printTable();
        System.out.println(myHashTable.get("nails"));
    }
}
