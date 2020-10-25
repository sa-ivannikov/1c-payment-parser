/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ili4
 */
public class TestApp {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		ArrayList<Payment> payments = Read1C.readFromFile(new File("\"D:\\My Documents\\develogic\\Выписка ГПБ за 20.10.20.txt\""));

		//ArrayList<Payment> payments = Read1C.readFromFile(new File("Выписка ГПБ за 20.10.20.txt"));
		for (Payment p : payments) {
			System.out.println(p);
			System.out.println(p.payer.bank);
		}
	}

}
