package com.problems.ecoins;

import java.util.ArrayList;
import java.util.Scanner;

public class ECoins {

	/* add checks for the number range limit */
	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int numberOfCoins;
		int num;
		/* Set the no of problems and put a check for the value of the emodulus */
		do {
			while (!sc.hasNextInt()) {
				sc.next();
			}
			num = sc.nextInt();

		} while (num <= 0 || num > 101);

		/* The first loop for types and mod */
		for (int i = 0; i < num; i++) {
			ArrayList<Integer> conventionalValueList = new ArrayList<Integer>();
			ArrayList<Integer> infoTechValueList = new ArrayList<Integer>();
			int typesOfCoin;
			int eModulusvalue;
			// /Take in the number of coins
			do {
				while (!sc.hasNextInt()) {
					sc.next();
				}
				typesOfCoin = sc.nextInt();

			} while (typesOfCoin <= 0 || typesOfCoin > 41);

			do {
				while (!sc.hasNextInt()) {
					sc.next();
				}
				eModulusvalue = sc.nextInt();

			} while (eModulusvalue <= 0 || eModulusvalue > 301);
			

			for (int j = 0; j < typesOfCoin; j++) {

				int nextnum = sc.nextInt();
				conventionalValueList.add(nextnum);
				nextnum = sc.nextInt();
				infoTechValueList.add(nextnum);

			}

			numberOfCoins = checkforNumberofcoins(conventionalValueList,
					infoTechValueList, eModulusvalue, typesOfCoin);
			if (numberOfCoins == 301)
				System.out.println("Not Possible");
			else {
				System.out.println(numberOfCoins);
			}

		}

	}

	/* the number of coins can be a maximum of emv */
	private static int checkforNumberofcoins(ArrayList<Integer> cVL,
			ArrayList<Integer> iTVL, int eMV, int tOC) {
		int i, j, minimum = 301;
		int a[][] = new int[301][301];
		int emvsq=(int) Math.pow(eMV, 2);
		for (i = 0; i <= eMV; i++) {
			for (j = 0; j <= eMV; j++) {
				a[i][j] = 301;
			}
		}
		
		a[0][0] = 0;
		

		for (i = 0; i < tOC; i++) {
			int conv = cVL.get(i);
			int techinfo = iTVL.get(i);
			
			for (int x = conv; x * x + techinfo * techinfo <=emvsq;  x++) {
				for (int y = techinfo; y * y + x * x <= emvsq; y++) {
					a[x][y] = Math.min(a[x][y], a[x - conv][y - techinfo] + 1);
				}

			}
		}

		for (i = 0; i <= eMV; i++) {
			for (j = 0; j <= eMV; j++) {
				if (i * i + j * j == emvsq) {
					if (minimum > a[i][j]) {
						minimum = a[i][j];
					}
				}
			}
		}
		return minimum;


	}
}
