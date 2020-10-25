/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

import javax.management.monitor.CounterMonitorMBean;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * @author ili4
 */
public class Read1C {

	public static ArrayList<Payment> readFromFile(File file) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		try {
			//Create full string of file contents
			String fullString = fullStringFromFile(file);
			// Split it by sections
			String[] sections = splitBySection(fullString);
			for (var section :sections) {
				HashMap<String, String> sectionMap = mapSection(section);
				// Skip first section - it does not contain payment document. Identify first section by Кодировка.
				if (sectionMap.get("Кодировка") != null) {
					continue;
				}
				Payment payment = makePayment(sectionMap);
				payments.add(payment);
			}
		} catch (IOException e) {
			System.out.println("Could not parse text from file");
		}
		return payments;
	}

	static String[] splitBySection(String string) {
		// Takes full string and spits it by Documents
		String[] parts = string.split("СекцияДокумент=Платежное поручение");
		return parts;
	}

	static String fullStringFromFile(File file) throws IOException {
		// Takes file path and makes string of all content of the file

		FileInputStream stream = new FileInputStream(file);
		Scanner sc = new Scanner(stream, "Windows-1251");


		StringBuilder builder = new StringBuilder();
		while(sc.hasNextLine()) {
			builder.append(sc.nextLine() + System.lineSeparator());
		}
		String fullString = builder.toString();
		return fullString;

	}

	static HashMap<String, String> mapSection(String documentString) throws IOException {
		// Create map from key-value pairs
		HashMap<String, String> paymentMap = new HashMap<String, String>();
		BufferedReader stringReader = new BufferedReader(new StringReader(documentString));
		String line;

		while ((line = stringReader.readLine()) != null) {
			String[] parts = line.split("=", 2);
			if (parts.length >= 2) {
				String key = parts[0];
				String value = parts[1];
				paymentMap.put(key, value);
			}
		}
		for (String key: paymentMap.keySet()) {
		}
		return paymentMap;
	}

	static Payment makePayment(HashMap<String, String> map) {
		// Instantiate payment and counterparty objects from mapping
		Date date = new Date();
		Counterparty payer = new Counterparty(map.get("Плательщик1"), map.get("ПлательщикИНН"), map.get("ПлательщикКПП"), map.get("ПлательщикБанк1"), map.get("ПлательщикСчет"));
		Counterparty payee = new Counterparty(map.get("Получатель1"), map.get("ПолучательИНН"), map.get("ПолучательКПП"), map.get("ПолучательБанк1"), map.get("ПолучательСчет"));
		try {
			date = new SimpleDateFormat("dd.mm.yyyy").parse(map.get("Дата"));
		} catch (ParseException e) {
			System.err.println("Could not parse payment. Set up current date instead.");
			e.printStackTrace();
		}

		Payment payment = new Payment(date, new BigDecimal(map.get("Сумма")), map.get("Номер"), map.get("НазначениеПлатежа"), payer, payee);
		return payment;


	}
}
