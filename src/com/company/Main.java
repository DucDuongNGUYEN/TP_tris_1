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
        int n = sequence.size();

        LinkedList<Integer> listLeft = new LinkedList<>();
        LinkedList<Integer> listRight = new LinkedList<>();
        if (n > 1) {
            // division en 2 sous listes
            int mid = n / 2;

            // sous liste de gauche
            for (int i = 0; i < mid; i++) {
                listLeft.add(sequence.get(i));
            }
            // sous liste de droite
            for (int j = mid; j < n; j++) {
                listRight.add(sequence.get(j));
            }
            mergeSort(listLeft);
            mergeSort(listRight);

            merge(sequence,listLeft, listRight,mid,n-mid);
        } else {
            return sequence;
        }
        return sequence;

    }
    public static void merge(LinkedList<Integer> sequence,LinkedList<Integer> listLeft,LinkedList<Integer> listRight, int left, int right) {
        int i = 0, j = 0, k = 0;
        while(i < left && j < right){
            if (listLeft.get(i) <= listRight.get(j)){
                sequence.set(k,listLeft.get(i));
                k++;
                i++;
            }
            else {
                sequence.set(k,listRight.get(j));
                k++;
                j++;
            }
        }
        while (i < left){
            sequence.set(k,listLeft.get(i));
            k++;
            i++;
        }
        while (j < right){
            sequence.set(k, listRight.get(j));
            k++;
            j++;
        }
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

    public static void heapify(ArrayList<Integer> sequence, int n ,int node){
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
            heapify(sequence,n,largest);
        }
    }
    public static ArrayList<Integer> heapSort(ArrayList<Integer> sequence){
        int n = sequence.size();
        for (int i = n/2 -1; i >= 0; i--){
            heapify(sequence,n,i);
        }
        for (int i = n-1;i >= 0 ; i--){
            permuter(sequence,0,i);
            heapify(sequence,i,0);
        }
        return sequence;
    }

}

