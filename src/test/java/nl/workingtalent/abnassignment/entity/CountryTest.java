package nl.workingtalent.abnassignment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountryTest {
	@Test
	public void testCountry() {
		Country country = new Country("USA", "United States", true);
		assertEquals(country.getCountryCode(), "USA");
		assertEquals(country.getName(), "United States");
		assertEquals(country.isRegisterAllowed(), true);
	}
}
