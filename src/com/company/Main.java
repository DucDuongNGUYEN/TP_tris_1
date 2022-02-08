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
         */
        LinkedList<Integer> sequence = linkedListGenerated(10);
        System.out.println(sequence);
        System.out.println(mergeSort(sequence));

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
    /*
    public static ArrayList<Integer> generation(int maxValue, int size ){
        Random rd = new Random();
        ArrayList<Integer> myList = new ArrayList<Integer>(size);
        for(int i = 0; i < size;i++){
            myList.add(rd.nextInt(maxValue));
        }
        return myList;
    }
     */
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
            System.out.println(listLeft);
            System.out.println(listRight);
            //listMerge = merge(listLeft, listRight);
            listLeft = mergeSort(listLeft);
            listRight = mergeSort(listRight);
            listMerge = merge(listLeft, listRight);
        } else {
            return sequence;
        }
        return listMerge;
    }
    public static LinkedList<Integer> merge(LinkedList<Integer> listLeft, LinkedList<Integer> listRight) {
        // index des listes
        int l = 0, r = 0;
        while (l < listLeft.size() && r < listRight.size()) {
            if (listLeft.get(l) < listRight.get(r) || listLeft.get(l) == listRight.get(r)) {
                listLeft.add(l, listLeft.get(l));
                if ((r + 1) > listRight.size()) {
                    break;
                }
                r = r + 1;
                l = 0;
            } else {
                if ((l + 1) == listRight.size()) {
                    listLeft.add(listRight.get(r));
                }
                r = r;
                l = l + 1;
            }
            return listLeft;
        }
        return listLeft;
    }

}

