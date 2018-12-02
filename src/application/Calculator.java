package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
				iFromPandU(leistung, spannung);
				rFromPAndU(leistung, spannung);
			} else if (leistung != 0.0 && strom != 0.0) {
				uFromPAndI(leistung, strom);
				rFromPAndI(leistung, strom);
			} else if (leistung != 0.0 && widerstand != 0.0) {
				uFromPAndR(leistung, widerstand);
				iFromPandR(leistung, widerstand);
			} else if (spannung != 0.0 && strom != 0.0) {
				pFromUAndI(spannung, strom);
				rFromUAndI(spannung, strom);
			} else if (spannung != 0.0 && widerstand != 0.0) {
				pFromUAndR(spannung, widerstand);
				iFromUandR(spannung, widerstand);
			} else if (strom != 0.0 && widerstand != 0.0) {
				pFromIAndR(strom, widerstand);
				uFromIAndR(strom, widerstand);
			}
		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Falsche Feldselektion");
			alert.setContentText("Bitte genau 2 Felder selektieren!");
			alert.showAndWait();
		}
	}

	/**
	 * calculate p from i and u
	 * 
	 * @param u
	 * @param i
	 * @return
	 */
	public void pFromUAndI(double u, double i) {
		System.out.println("calculate p from i and u");
		this.leistung =  i * u;
	}

	/**
	 * calculate p from u and r
	 * 
	 * @param u
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void pFromUAndR(double u, double r) throws IllegalArgumentException {
		if (r == 0) {
			throw new IllegalArgumentException("Resistence is 0.");
		}
		System.out.println("calculate p from u and r");
		this.leistung = u * u / r;
	}

	/**
	 * calculate p from i and r
	 * 
	 * @param i
	 * @param r
	 * @return
	 */
	public void pFromIAndR(double i, double r) {
		System.out.println("calculate p from i and r");
		this.leistung = r * i * i;
	}

	/**
	 * calculate u from i and r
	 * 
	 * @param i
	 * @param r
	 * @return
	 */
	private void uFromIAndR(double i, double r) {
		System.out.println("calculate u from i and r");
		this.spannung = i * r;
	}

	/**
	 * calculate u from p and i
	 * 
	 * @param p
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	private void uFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		System.out.println("calculate u from p and i");
		this.spannung = p / i;
	}

	/**
	 * calculate u from p and r
	 * 
	 * @param p
	 * @param r
	 * @return
	 * @throws IllegalArgumentException
	 */
	private void uFromPAndR(double p, double r) throws IllegalArgumentException {
		if ((p * r) < 0) {
			throw new IllegalArgumentException("Argument 'Power * Resistance' is < 0");
		}
		System.out.println("calculate u from p and r");
		this.spannung = Math.sqrt(p * r);
	}

	/**
	 * Calculate R from U and I
	 * 
	 * @param u Power
	 * @param i Current
	 * @return Resistance
	 * @throws IllegalArgumentException
	 */
	private void rFromUAndI(double u, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		System.out.println("Calculate R from U and I");
		this.widerstand = u / i;
	}

	/**
	 * Calculate R from P and I
	 * 
	 * @param p Power
	 * @param i Current
	 * @return Resitance
	 * @throws IllegalArgumentException
	 */
	private void rFromPAndI(double p, double i) throws IllegalArgumentException {
		if (i == 0.0) {
			throw new IllegalArgumentException("Argument 'Current' is 0");
		}
		System.out.println("Calculate R from P and I");
		this.widerstand = p / i / i;
	}

	/**
	 * Calculate R from U and P
	 * 
	 * @param p Power
	 * @param u Tension
	 * @return Resistance
	 * @throws IllegalArgumentException
	 */
	private void rFromPAndU(double p, double u) throws IllegalArgumentException {
		if (p == 0.0) {
			throw new IllegalArgumentException("Argument 'Power' is 0");
		}
		System.out.println("Calculate R from U and P");
		this.widerstand = u * u / p;
	}

	/**
	 * Calculate I=P/U
	 * 
	 * @param p Power
	 * @param u Tension
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private void iFromPandU(double p, double u) throws IllegalArgumentException {
		if (u == 0.0) {
			throw new IllegalArgumentException("Argument 'Tension' is 0");
		}
		System.out.println("Calculate I from P and U");
		this.strom = p / u;
	}

	/**
	 * Calcluate I=SQRT(P/R)
	 * 
	 * @param p Power
	 * @param r Resistance
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private void iFromPandR(double p, double r) throws IllegalArgumentException {
		if (r == 0.0) {
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		} else if ((p / r) < 0.0) {
			throw new IllegalArgumentException("Argument 'Power/Resistance' is < 0");
		}
		System.out.println("Calcluate I from P and R");
		this.strom = Math.sqrt(p / r);
	}

	/**
	 * Calculate I=U/R
	 * 
	 * @param u Tension
	 * @param r Resistance
	 * @return Current
	 * @throws IllegalArgumentException
	 */
	private void iFromUandR(double u, double r) throws IllegalArgumentException {
		if (r == 0.0) {
			throw new IllegalArgumentException("Argument 'Resistance' is 0");
		}
		System.out.println("calculate i from u and r");
		this.strom = u / r;
	}

}
