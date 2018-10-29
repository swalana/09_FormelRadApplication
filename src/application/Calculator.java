package application;

/**
 * Berechnet das Formelrad
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
		return "Calculator [leistung=" + leistung + 
				", spannung=" + spannung + 
				", strom=" + strom + 
				", widerstand="	+ widerstand + "]";
	}

	public void calculate() {
		/* Hier auf Grund der vorhanden Werte entscheiden
		 * welche Methode unten aufgerufen werden muss.
		 */
	}
	/* Hier die Methoden mit den Formlen hinzufügen
	 */
	/**
	 * Calculate R=U/I
	 * @param u Power
	 * @param i Current
	 * @return  Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromUAndI(double u, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return u/i;
	}

	/**
	 * Calculate R=P/I^2
	 * @param p Power
	 * @param i Current
	 * @return  Resitance
	 * @throws IllegalArgumentException
	 */
	private double rFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p/i/i;
	}

	/**
	 * Calculate R=U*U/P
	 * @param p Power
	 * @param u Tension
	 * @return  Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromPAndU(double p, double u) throws IllegalArgumentException {
		if (p==0.0){
			throw new IllegalArgumentException("Argument 'Power' is 0");
		}
		return u*u/p;
	}
	

	
}
