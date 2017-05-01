package com.randomizer;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

import com.primerandamizor.PrimeRandomizer;
import com.dto.Result;

public class Randomizer extends Thread {

	public static PriorityQueue printQueue = new PriorityQueue();
	private String purpose;
	private static final int SIZE = 5;

	public static void startRandomizer() {

		Randomizer print = new Randomizer();
		print.purpose = "print";
		Thread printer = new Thread(print);
		printer.start();
		
		Randomizer send = new Randomizer();
		send.purpose = "send";
		Thread sender = new Thread(send);
		sender.start();

	}

	public void run() {
		if ("send".equals(this.purpose)) {
			outer:while (true) {
				Random random = new Random();
				for (int i = 0; i < SIZE; i++) {
					int randomNumber = random.nextInt(10000);
					if (randomNumber < 0) {
						randomNumber = randomNumber * -1;

					}
					
					PrimeRandomizer.numberQueue.add(Integer
							.valueOf(randomNumber));
					// break;
				}
				// break;
				while(true){
					if (printQueue.isEmpty()
							&& PrimeRandomizer.numberQueue.isEmpty()) {
						Scanner sc = new Scanner(System.in);
						while (true) {
							System.out
									.println("Enter X to stop or enter Y to send next set of random numbers.");
							String input = sc.next();
							if (null == input || input.isEmpty()) {
								continue;
							} else if ("Y".equalsIgnoreCase(input)) {
								continue outer;
							} else if ("X".equalsIgnoreCase(input)) {
								System.exit(0);
							}
						}
	
					}
				}
			}
		} else {
			while (true) {
				Object obj = printQueue.poll();
				
				if (null != obj) {
					Result result = (Result) obj;
					System.out.print("");
					if (result.isPrime()) {
						System.out.println(result.getNumber()
								+ " is a prime number.");
					} else {
						System.out.println(result.getNumber()
								+ " is not a prime number.");
					}
				}
			}
		}
	}

}