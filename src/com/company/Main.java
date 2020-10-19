package com.company;

public class Main {

    public static void main(String[] args) {
	 Percolation percolation = new Percolation(5);

        System.out.println(percolation.isOpen(2, 3));
        System.out.println(percolation.isOpen(2, 4));

        percolation.open(2, 3);
        percolation.open(2, 4);

        System.out.println(percolation.isOpen(2, 3));
        System.out.println(percolation.isOpen(2, 4));
    }
}
