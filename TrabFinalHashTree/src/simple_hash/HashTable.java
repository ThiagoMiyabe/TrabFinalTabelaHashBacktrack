package simple_hash;

import tree.Node;
import tree.Tree;

public class HashTable {

    private Tree<Integer, String>[] buckets;

    public HashTable() {
        this.buckets = new Tree[3];
        for (int i = 0; i < this.buckets.length; i++) {
            this.buckets[i] = new Tree<>();
        }
    }

    private int hashFunction(int key) {
        return key % this.buckets.length;
    }

    public void put(int key, String value) {
        int b = this.hashFunction(key);
        Tree<Integer, String> bucket = this.buckets[b];
        bucket.insert(key, value);
    }

    public String get(int key) {
        int b = this.hashFunction(key);
        Tree<Integer, String> bucket = this.buckets[b];
        Object value = bucket.get(key);
        return value != null ? value.toString() : "null";
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("{\n");
        for (int i = 0; i < this.buckets.length; i++) {
            out.append("\t").append(this.buckets[i].toString()).append("\n");
        }
        return out + "}";
    }

    public static void main(String[] args) {

        HashTable ht = new HashTable();
        System.out.println(ht.toString());
        ht.put(0, "a");
        System.out.println(ht.toString());
        ht.put(1, "b");
        System.out.println(ht.toString());
        ht.put(2, "c");
        System.out.println(ht.toString());
        ht.put(3, "d");
        System.out.println(ht.toString());
        ht.put(4, "e");
        System.out.println(ht.toString());
        ht.put(5, "f");
        System.out.println(ht.toString());

        System.out.println(ht.get(2));
        System.out.println(ht.get(10));

    }

}