package com.company;

import java.util.*;
import java.util.Random;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        /*
        ArrayList<Integer> myList = sequenceGenerated(10);
        System.out.println(myList);
        bubbleSort(myList);
        System.out.println(myList);

        LinkedList<Integer> sequence = linkedListGenerated(10);
        System.out.println(sequence);
        System.out.println(mergeSort(sequence));

         */
        ArrayList<Integer> sequence = sequenceGenerated(10);
        System.out.println(sequence);
        //System.out.println(quickSort(sequence));
        System.out.println(heapSort(sequence));

    }
    public static void permuter(ArrayList<Integer> myList, int num1,int num2){
        int temp = myList.get(num2);
        myList.set(num2,myList.get(num1));
        myList.set(num1, temp);
    }
    public static void bubbleSort(ArrayList<Integer> myList){
        int n = myList.size();
        for(int i = 0; i < n;i++){
            for(int j = 0; j < n-i-1;j++){
                if (myList.get(j) > myList.get(j+1)) {
                    permuter(myList,j,j+1);
                }
            }
        }
    }
    public static ArrayList<Integer> sequenceGenerated(int n){
        Random rd = new Random();
        ArrayList<Integer> suite = new ArrayList<>();
        int k = 0;
        while (k<n){
            suite.add(rd.nextInt(n));
            k++;
        }
        return suite;
    }
    public static LinkedList<Integer> linkedListGenerated(int n){
        Random rd = new Random();
        LinkedList<Integer> list = new LinkedList<>();
        int k = 0;
        while (k<n){
            list.add(rd.nextInt(n));
            k++;
        }
        return list;
    }
    public static LinkedList<Integer>  mergeSort(LinkedList<Integer> sequence) {
        LinkedList<Integer> listLeft = new LinkedList<>();
        LinkedList<Integer> listRight = new LinkedList<>();
        LinkedList<Integer> listMerge;
        if (sequence.size() > 1) {
            // division en 2 sous listes
            int middle = sequence.size() / 2;

            // sous liste de gauche
            for (int i = 0; i < middle; i++) {
                listLeft.add(sequence.get(i));
            }

            // sous liste de droite
            for (int j = middle; j < sequence.size(); j++) {
                listRight.add(sequence.get(j));
            }
            listLeft = mergeSort(listLeft);
            listRight = mergeSort(listRight);

            listMerge = merge(listLeft, listRight);
        } else {
            return sequence;
        }
        return listMerge;
    }
    public static LinkedList<Integer> merge(LinkedList<Integer> listLeft, LinkedList<Integer> listRight) {
        LinkedList<Integer> list = new LinkedList<>();
        ListIterator<Integer> it1 = listLeft.listIterator(), it2 = listRight.listIterator();
        int n1 = it1.hasNext() ? it1.next() : 0, n2 = it2.hasNext() ? it2.next() : 0;
        for (int i = 0, i1 = 0, i2 = 0, sz1 = listLeft.size(), sz2 = listRight.size(), sz = sz1 + sz2; i < sz; i++) {
            if (i1 < sz1 && (i2 == sz2 || n1 < n2)) {
                list.add(n1);
                i1++;
                n1 = it1.hasNext() ? it1.next() : 0;
            } else {
                list.add(n2);
                i2++;
                n2 = it2.hasNext() ? it2.next() : 0;
            }
        }
        return list;
    }
    public static ArrayList<Integer> quickSortPartition(ArrayList<Integer> sequence, int first, int last){
        if (first < last){
            int left = first, right = last;
            int pivot = sequence.get((left+right)/2);
            while (left <= right){
                while (sequence.get(left) < pivot){
                    left++;
                }
                while (sequence.get(right) > pivot){
                    right--;
                }
                if (left <= right){
                    permuter(sequence,left,right);
                    left++;
                    right--;
                }
            }
            quickSortPartition(sequence,first,right);
            quickSortPartition(sequence,left,last);
        }
        return sequence;
    }
    public static ArrayList<Integer> quickSort(ArrayList<Integer> sequence){
        return quickSortPartition(sequence,0,sequence.size()-1);
    }

    public static ArrayList<Integer> tamiser(ArrayList<Integer> sequence, int node ,int n){
        // n is size of heap
        int largest = node; // Initialize largest as root
        int left = 2*node + 1;
        int right = 2*node + 2;

        if (left < n && sequence.get(left) > sequence.get(largest)){
            largest = left;
        }
        if (right < n && sequence.get(right) > sequence.get(largest)){
            largest = right;
        }
        if (largest != node){
            permuter(sequence,node,largest);
            tamiser(sequence,n,largest);
        }
        return sequence;
    }
    public static ArrayList<Integer> heapSort(ArrayList<Integer> sequence){
        int n = sequence.size();
        for (int i = n/2 -1; i >= 0; i--){
            tamiser(sequence,n,i);
        }
        for (int i = n-1;i >= 0 ; i--){
            permuter(sequence,0,i);
            tamiser(sequence,i,0);
        }
        return sequence;
    }

}

