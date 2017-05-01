package com.primerandamizor;

import java.util.PriorityQueue;

import com.dto.Result;
import com.randomizer.Randomizer;

public class PrimeRandomizer extends Thread{

	public static PriorityQueue numberQueue = new PriorityQueue();

	/**
	 * @param args
	 */
	//public static void main(String[] args) {
	public static void startPrimeRandomizer() {

		PrimeRandomizer prime = new PrimeRandomizer();
		Thread primeThread = new Thread(prime);
		primeThread.start();
	}
	
	public void run() {
		while (true) {
			Object obj = numberQueue.poll();
			//System.out.println(obj);
			if (null != obj) {
				Integer number = (Integer) obj;
				//System.out.println("Received " + number.intValue());
				boolean flag = true;
				for (int i = 2; i < number.intValue(); i++) {
					if (number.intValue() % i == 0) {
						flag = false;
						break;
					}
				}
				Result result = new Result();
				result.setNumber(number.intValue());
				result.setPrime(flag);
				Randomizer.printQueue.add(result);
			}

		}
	}
	

}