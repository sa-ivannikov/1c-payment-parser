/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

/**
 * Контрагент
 * @author ili4
 */
public class Counterparty {
	
	/** Наименование */
	public String name;
	
	/** ИНН */
	public String inn;
	
	/** КПП */
	public String kpp;
	
	/** Банк */
	public String bank;
	
	/** Расчетный счет */
	public String rs;

	public Counterparty(String counterpartyName, String counterpartyInn, String counterpartyKpp, String counterpartyBank, String counterpartyRs) {
		name = counterpartyName;
		inn = counterpartyInn;
		kpp = counterpartyKpp;
		bank = counterpartyBank;
		rs = counterpartyRs;
	}

	@Override
	public String toString() {
		return String.format("{Name: %s, inn: %s, kpp: %s, bank: %s, rs: %s}", this.name, this.inn, this.kpp, this.bank, this.rs);
	}
	
}
