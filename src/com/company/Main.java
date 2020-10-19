package com.company;

public class Main {

    public static void main(String[] args) {
	 Percolation percolation = new Percolation(5);

        percolation.open(5, 3);
        percolation.open(3, 3);
        percolation.open(1, 1);
        percolation.open(1, 4);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 3);
//
        System.out.println(percolation.percolates());
    }
}
