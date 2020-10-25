/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

import java.math.BigDecimal;
import java.util.Date;

/** 
 * Платежное поручение
 * @author ili4
 */
public class Payment {

	/** Дата платежа */
	public Date date;
	
	/** Сумма */
	public BigDecimal sum;
	
	/** Номер платежки */
	public String number;
	
	/** Назначение платежа */
	public String description;
	
	/** Плательщик */
	public Counterparty payer;
	
	/** Получатель */
	public Counterparty payee;

	public Payment(Date paymentDate, BigDecimal paymentSum, String paymentNumber, String paymentDescription, Counterparty paymentPayer, Counterparty paymentPayee) {
		date = paymentDate;
		sum = paymentSum;
		number = paymentNumber;
		description = paymentDescription;
		payer = paymentPayer;
		payee = paymentPayee;
	}



	@Override
	public String toString() {
		return String.format("{Number: %s, date: %tD, sum: %s, description: %s, payer: %s, payee: %s }", this.number, this.date, this.sum, this.description, this.payer, this.payee);
	}

}
