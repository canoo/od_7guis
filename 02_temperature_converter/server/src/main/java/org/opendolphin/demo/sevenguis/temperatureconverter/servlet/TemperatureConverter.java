package org.opendolphin.demo.sevenguis.temperatureconverter.servlet;

public class TemperatureConverter {

	public static Double fahrenheitFromCelsius(Double c) {
		// F = C * (9/5) + 32
		return c * (9 / 5D) + 32D;

	}
	public static Double celsiusFromFahrenheit(Double f) {
		// C = (F - 32) * (5/9)
		return (f - 32D) * (5/9D);

	}
}
