package org.example;

public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) {
            return null;
        }
        Node temp = head;
        Node removedNode = head;
        while (removedNode.next != null) {
            temp = removedNode;
            removedNode = removedNode.next;
        }
        tail = temp;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return removedNode;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) {
            return null;
        }
        Node removedNode = head;
        head = head.next;
        removedNode.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return removedNode;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) {
            return false;
        }
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }
        Node temp = get(index - 1);
        Node removedNode = get(index); //Node removedNode = temp.next;
        temp.next = removedNode.next;
        removedNode.next = null;
        length--;
        return removedNode;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node before = null;
        Node after = temp.next;
        for (int i=0; i<length;i++){
            after = temp.next;
            temp.next=before;
            before=temp;
            temp=after;
        }
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value /*+" = "+ temp.next +" = "+ temp*/);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("\nHead: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("\nHead: " + head.value /*+" = "+ head.next +" = "+ head*/);
            System.out.println("Tail: " + tail.value /*+" = "+ tail.next +" = "+ tail*/);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }
}

