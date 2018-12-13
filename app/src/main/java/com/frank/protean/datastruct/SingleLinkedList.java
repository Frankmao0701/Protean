package com.frank.protean.datastruct;

public class SingleLinkedList<E> {
    public Node headNode;
    public int size;

    public SingleLinkedList() {
        headNode = null;
        size = 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            return;
        }
        if (size == 0) {
            headNode = new Node(e);
            size++;
            return;
        } else {
            if (index == 0) {
                Node currentNode = new Node(e);
                currentNode.nextNode = headNode;
                headNode = currentNode;
                size++;
            }else{
                Node preNode = headNode;
                Node currentNode = headNode;
                for (int i = 0;i<index;i++){
                    preNode = currentNode;
                    currentNode = currentNode.nextNode;
                }
                if (index == size) {
                    currentNode = null;
                }
                Node newNode = new Node(e);
                preNode.nextNode = newNode;
                newNode.nextNode = currentNode;
                size++;
            }
        }

    }
}
