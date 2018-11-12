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

		int choosenFields = 0;

		if (leistung != 0.0) {
			choosenFields++;
		}
		if (spannung != 0.0) {
			choosenFields++;
		}
		if (widerstand != 0.0) {
			choosenFields++;
		}
		if (strom != 0.0) {
			choosenFields++;
		}

		// select the right method
		if (choosenFields == 2) {
			
			if (leistung != 0.0 && spannung != 0.0) {
				strom = iFromPandU(leistung, spannung);
				widerstand = rFromPAndU(leistung, spannung);
			}

			if (leistung != 0.0 && strom != 0.0) {
				spannung = uFromPAndI(leistung, strom);
				widerstand = rFromPAndI(leistung, strom);
			}

			if (leistung != 0.0 && widerstand != 0.0) {
				spannung = uFromPAndR(leistung, widerstand);
				strom = iFromPandR(leistung, widerstand);
			}

			if (spannung != 0.0 && strom != 0.0) {
				leistung = pFromUAndI(spannung, strom);
				widerstand = rFromUAndI(spannung, strom);
			}

			if (spannung != 0.0 && widerstand != 0.0) {
				leistung = pFromUAndR(spannung, widerstand);
				strom = iFromUandR(spannung, widerstand);
			}

			if (strom != 0.0 && widerstand != 0.0) {
				leistung = pFromIAndR(strom, widerstand);
				spannung = uFromIAndR(strom, widerstand);
			}
		} else {
			System.out.println("Bitte genau 2 Felder selektieren");
		}
//		
//		if (leistung != 0.0) {
//			if (spannung != 0.0) {
//				strom = iFromPandU(leistung, spannung);
//				widerstand = rFromPAndU(leistung, spannung);
//			} else if (strom != 0.0) {
//				spannung = uFromPAndI(leistung, strom);
//				widerstand = rFromPAndI(leistung, strom);
//			} else if (widerstand != 0.0) {
//				spannung = uFromPAndR(leistung, widerstand);
//				strom = iFromPandR(leistung, widerstand);
//			}
//		} else if (spannung != 0.0) {
//			if (strom != 0.0) {
//				leistung = pFromUAndI(spannung, strom);
//				widerstand = rFromUAndI(spannung, strom);
//			} else if (widerstand != 0.0) {
//				leistung = pFromUAndR(spannung, widerstand);
//				strom = iFromUandR(spannung, widerstand);
//			}
//		} else if (strom != 0.0) {
//			if (widerstand != 0.0) {
//				leistung = pFromIAndR(strom, widerstand);
//				spannung = uFromIAndR(strom, widerstand);
	}

	// }}

	/**
	 * calculate p from i and u
	 * 
	 * @param u
	 * @param i
	 * @return
	 */
	public double pFromUAndI(double u, double i) {
		return i * u;
	}

	/**
	 * calculate p from u and r
	 * 
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
	 * 
	 * @param i
	 * @param r
	 * @return
	 */
	public double pFromIAndR(double i, double r) {
		return r * i * i;
	}

	/**
	 * calculate u from i and r
	 * 
	 * @param i
	 * @param r
	 * @return
	 */
	private double uFromIAndR(double i, double r) {
		return i * r;
	}

	/**
	 * calculate u from p and i
	 * 
	 * @param p
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	private double uFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p / i;
	}

	/**
	 * calculate u from p and r
	 * 
	 * @param p
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	private double uFromPAndR(double p, double r) throws IllegalArgumentException {
		if ((p * r) < 0) {
			throw new IllegalArgumentException("Argument 'Power * Resistance' is < 0");
		}
		return Math.sqrt(p * r);
	}

	/**
	 * Calculate R=U/I
	 * 
	 * @param u Power
	 * @param i Current
	 * @return Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromUAndI(double u, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return u / i;
	}

	/**
	 * Calculate R=P/I^2
	 * 
	 * @param p Power
	 * @param i Current
	 * @return Resitance
	 * @throws IllegalArgumentException
	 */
	private double rFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		return p / i / i;
	}

	/**
	 * Calculate R=U*U/P
	 * 
	 * @param p Power
	 * @param u Tension
	 * @return Resistance
	 * @throws IllegalArgumentException
	 */
	private double rFromPAndU(double p, double u) throws IllegalArgumentException {
		if (p == 0.0) {
			throw new IllegalArgumentException("Argument 'Power' is 0");
		}
		return u * u / p;
	}

	/**
	 * Calculate I=P/U
	 * 
	 * @param p Power
	 * @param u Tension
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandU(double p, double u) throws IllegalArgumentException {
		if (u == 0.0) {
			throw new IllegalArgumentException("Argument 'Tension' is 0");
		}
		return p / u;
	}

	/**
	 * Calcluate I=SQRT(P/R)
	 * 
	 * @param p Power
	 * @param r Resistance
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private double iFromPandR(double p, double r) throws IllegalArgumentException {
		if (r == 0.0) {
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		} else if ((p / r) < 0.0) {
			throw new IllegalArgumentException("Argument 'Power/Resistance' is < 0");
		}
		return Math.sqrt(p / r);
	}

	/**
	 * Calculate I=U/R
	 * 
	 * @param u Tension
	 * @param r Resistance
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private double iFromUandR(double u, double r) throws IllegalArgumentException {
		if (r == 0.0) {
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		}
		return u / r;
	}

}
