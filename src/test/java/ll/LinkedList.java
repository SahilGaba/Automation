package ll;

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
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    //the slow and fast pointer technique (also known as Floyd's Tortoise and Hare algorithm)
    //we aren't using length var
    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            /*fast=fast.next;
            if(fast.next!=null){
                fast=fast.next;
            }
            else{
                return slow;
            }*/
        }
        return slow;
    }

    //the slow and fast pointer technique (also known as Floyd's Tortoise and Hare algorithm)
    //we aren't using length var
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

//This algorithm uses the two-pointer technique to efficiently find the k-th node from the end of the linked list.
// Explained another way:
//The algorithm uses two pointers, called 'slow' and 'fast'. Both of these pointers start at the head of the list (the beginning of the chain).
//First, 'fast' is moved 'k' steps along the list. If 'fast' encounters the end of the list (represented by 'null') before it has taken 'k' steps, the function returns 'null' because the list is shorter than 'k' elements.
//If 'fast' successfully takes 'k' steps without reaching the end of the list, then the game changes. Now, both 'slow' and 'fast' start moving along the list at the same pace, one step at a time.
//Here's the clever bit: by the time 'fast' hits the end of the list, 'slow' will be 'k' steps behind it - and therefore 'k' steps from the end of the list. So the function returns 'slow'.
//This is a common technique in computer science known as the 'fast-pointer / slow-pointer' or 'runner' technique, and it's a neat way of finding a position relative to the end of a list in a single pass.

    //Explained yet another way:
//This code is kind of like a game of tag, where you and your friend are running along a straight line of stones (these stones are like the "nodes" of our linked list). You're "slow" and your friend is "fast".
//The rule of the game is your friend gets a head start and runs "k" stones ahead first.
//If your friend runs out of stones before counting to "k" (when fast equals null), then you know the line of stones is not long enough (return null).
//But if there are enough stones, after your friend's head start, you both start running together. If your friend hits the end of the line (when fast equals null again), the stone you are standing on is "k" stones from the end.
//And that's the stone this code is trying to find!
    public Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //Given a value x you need to rearrange the linked list such that all nodes with a value less than x come before all nodes with a value greater than or equal to x.
    //Additionally, the relative order of nodes in both partitions should remain unchanged
    //This algorithm uses two dummy nodes and two pointers to maintain two separate partitions of the original list, one containing nodes with values less than x, and the other containing nodes with values greater than or equal to x. It then concatenates the two partitions and updates the head of the list accordingly.
    public void partitionList(int x) {
        // Step 1: Check for an empty list.
        // If the list is empty, there is nothing
        // to partition, so we exit the method.
        if (head == null) return;

        // Step 2: Create two dummy nodes.
        // These dummy nodes act as placeholders
        // to simplify list manipulation.
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // Step 3: Initialize pointers for new lists.
        // 'prev1' and 'prev2' will track the end nodes of
        // the two lists that are being created.
        Node prev1 = dummy1;
        Node prev2 = dummy2;

        // Step 4: Start with the head of the original list.
        Node current = head;

        // Step 5: Iterate through the original list.
        while (current != null) {

            // Step 6: Compare current node value with 'x'.
            // Nodes are partitioned based on their value
            // being less than or greater than/equal to 'x'.

            // Step 6.1: If value is less than 'x',
            // add node to the first list.
            if (current.value < x) {
                prev1.next = current;  // Link node to the end of the first list.
                prev1 = current;       // Update the end pointer of the first list.
            } else {
                // Step 6.2: If value is greater or equal,
                // add node to the second list.
                prev2.next = current;  // Link node to the end of the second list.
                prev2 = current;       // Update the end pointer of the second list.
            }

            // Move to the next node in the original list.
            current = current.next;
        }

        // Step 7: Terminate the second list.
        // This prevents cycles in the list.
        prev2.next = null;

        // Step 8: Connect the two lists.
        // The first list is followed by the second list.
        prev1.next = dummy2.next;

        // Step 9: Update the head of the original list.
        // The head now points to the start of the first list.
        head = dummy1.next;
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

