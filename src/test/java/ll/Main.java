package ll;

import dll.DoublyLinkedList;
import queue.Queue;
import stack.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.append(5);


        System.out.println("1 -> 2 -> 3 -> 4 -> 5");
        System.out.println( "Middle Node: "+ myLinkedList.findMiddleNode().value);
        myLinkedList.append(6);
        System.out.println("===========================");
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println( "Middle Node: "+ myLinkedList.findMiddleNode().value);

        myLinkedList.getTail().next = myLinkedList.getHead().next;
        System.out.println("Has Loop:");
        System.out.println( myLinkedList.hasLoop());
    }
}
