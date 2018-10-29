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
	
	/* Calculate I=P/U
	 * @param p Power
	 * @param u Tension
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandU(double p, double u) throws IllegalArgumentException {
		if (u==0.0){
			throw new IllegalArgumentException("Argument 'Tension' is 0");
		}
		return p/u;
	}

	/**
	 * Calcluate I=SQRT(P/R)
	 * @param p Power
	 * @param r Resistance
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandR(double p, double r) throws IllegalArgumentException {
		if (r==0.0){
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		} else if ((p/r)<0.0){
			throw new IllegalArgumentException("Argument 'Power/Resistance' is < 0");
		}
		return Math.sqrt(p/r);
	}

	/**
	 * Calculate I=U/R
	 * @param u Tension
	 * @param r Resistance
	 * @return  Current
	 * @throws IllegalArgumentException
	 */
	private double iFromUandR(double u, double r) throws IllegalArgumentException {
		if (r==0.0){
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		}
		return u/r;
	}
		
}
