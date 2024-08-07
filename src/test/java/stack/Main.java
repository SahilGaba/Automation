package stack;

import dll.DoublyLinkedList;
import ll.LinkedList;
import queue.Queue;

public class Main {

    public static void main(String[] args) {

        LinkedList myLinkedList = new LinkedList(1);
        DoublyLinkedList myDoublyLinkedList = new DoublyLinkedList(1);
        Stack stack = new Stack(1);
        Queue myQueue = new Queue (7);

        ///////////////////////////////////////////////
//        myLinkedList.append(2);
//        myLinkedList.printList();
//        myLinkedList.removeLast();
//        myLinkedList.prepend(0);
//        myLinkedList.prepend(-1);
//        myLinkedList.removeFirst();
//        myLinkedList.printList();
//        //System.out.println("Index value: "+myLinkedList.get(1).value);
//        //System.out.println("Value set: "+myLinkedList.set(1,2));
//        System.out.println("Value inserted: " + myLinkedList.insert(0, -1));
//        System.out.println("Value inserted: " + myLinkedList.insert(1, 99));
//        System.out.println("Value inserted: " + myLinkedList.insert(4, 2));
//        myLinkedList.printList();
//        //System.out.println("Value removed: " + myLinkedList.remove(1).value);
//        System.out.println("Value removed: "+myLinkedList.remove(3).value);
//        System.out.println("Value removed: "+myLinkedList.remove(1).value);
//        //myLinkedList.printList();
//        myLinkedList.reverse();
//        myLinkedList.printList();

        ///////////////////////////////////////////////

//        myDoublyLinkedList.prepend(0);
//        myDoublyLinkedList.printList();
//
//        System.out.println("\nFirst: "+myDoublyLinkedList.removeFirst().value);
//        myDoublyLinkedList.printList();

        ///////////////////////////////////////////////

//        stack.printStack();
//        stack.push(0);
//        stack.printStack();
//        stack.pop();
//        stack.printStack();
        ///////////////////////////////////////////////

        myQueue.printStack();
        myQueue.enQueue(2);
        myQueue.printStack();
        myQueue.deQueue();
        myQueue.printStack();

    }
}
