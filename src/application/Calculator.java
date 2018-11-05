package application;

/**
 * Berechnet das Formelrad
 * 
 * @author Peter Rutschmann, Joel Wäger, Sara Ott
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

	/**
	 * calculate u from i and r
	 * @param i
	 * @param r
	 * @return
	 */
	private double uFromIAndR(double i, double r) {
		return i*r;
	}
	
	/**
	 * calculate u from p and i
	 * @param p
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	private double uFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i==0.0){
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p/i;
	}
	
	/**
	 * calculate u from p and r
	 * @param p
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	private double uFromPAndR(double p, double r) throws IllegalArgumentException {
		if ((p*r)<0){
			throw new IllegalArgumentException("Argument 'Power * Resistance' is < 0");
		}
		return Math.sqrt(p*r);
	}
	
	
}