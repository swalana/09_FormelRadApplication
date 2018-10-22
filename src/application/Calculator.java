package application;

/**
 * Berechnet das Formelrad
 * 
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Calculator {
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;

	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}

	public double getLeistung() {
		return leistung;
	}

	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + ", spannung=" + spannung + ", strom=" + strom + ", widerstand="
				+ widerstand + "]";
	}

	public void calculate() {
		/*
		 * Hier auf Grund der vorhanden Werte entscheiden welche Methode unten
		 * aufgerufen werden muss.
		 */
	}

	/**
	 * calculate p from i and u
	 * @param u
	 * @param i
	 * @return
	 */
	public double pFromUAndI(double u, double i) {
		return i * u;
	}

	/**
	 * calculate p from u and r
	 * @param u
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	public double pFromUAndR(double u, double r) throws IllegalArgumentException {
		if (r == 0) {
			throw new IllegalArgumentException("Resistence is 0.");
		}
		return u * u / r;
	}

	/**
	 * calculate p from i and r
	 * @param i
	 * @param r
	 * @return
	 */
	public double pFromIAndR(double i, double r) {
		return r * i * i;
	}

}
