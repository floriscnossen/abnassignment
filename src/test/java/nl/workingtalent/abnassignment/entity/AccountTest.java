package nl.workingtalent.abnassignment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
	/*
	 * Check whether the IBAN is valid. We first put country code and two check digits at the end of the IBAN 
	 * and convert all letters into numbers via the mapping A -> 10, B -> 11, ..., Z -> 35. 
	 * Then we calculate the remainder of this number modulo 97. This remainder should equal 1.
	 */
	public static boolean checkIban(String iban) {
		String string = iban.substring(4) + iban.substring(0, 4);
		
		int remainder = 0;
		for (char c : string.toCharArray()) {
			if (Character.isLetter(c)) {
				remainder = (remainder*3 + (c - 'A' + 10)) % 97;
			}
			else {
				remainder = (remainder*10 + (c - '0')) % 97;
			}
		}
		return remainder == 1;
	}
	
	@Test
	public void testAccount() {
		Account account = new Account(1234567890, AccountType.CHECKING, 40, "Euro");
		assertEquals(checkIban(account.getIban()), true);
		assertEquals(account.getAccountType(), AccountType.CHECKING);
		assertEquals(account.getBalance(), 40);
		assertEquals(account.getCurrency(), "Euro");
	}
}
