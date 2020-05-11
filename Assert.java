package geometry;

public class Assert {
		/**
		 * @param nrErrors Anzahl der Fehler
		 * @return nrErrors Gibt die Anzahl der Fehler wieder
		 */
		private static int nrErrors;
		
		public static int getNrErrors(){
			return nrErrors;
		}
		/**
		 * Vergleicht den erwarteten mit dem tatsaechlichen Wert und gibt ein Fehlermeldung aus
		 * @param expected Der erwartete Wert
		 * @param actual Der tatsaechliche Wert
		 * @param epsilon Abweichung
		 * @param errMsg Fehlernachricht
		 */
		public static void assertEquals(double expected, double actual, double epsilon, String errMsg) {
			if(expected > actual + epsilon || expected < actual - epsilon) {
				System.out.println("Fehler in" + errMsg + " Erwartet: " + expected + " aber wurde: " + actual);
				nrErrors++;
			}	
		}
		/**
		 * ueberprueft einen Wert auf Richtigkeit
		 * @param actual der Tatsaechliche Wert
		 * @param errMsg Fehlermeldung
		 */
		public static void assertTrue(boolean actual, String errMsg) {
			if(!actual) {
				System.out.println("Fehler in " + errMsg);
				nrErrors++;
			}
		}
		public static void assertEquals(String[] expected,String[] actual,String errMsg) {
			if(expected.length != actual.length) { //Ueberprueft die Laenge des Array
			System.out.println("Fehler in " + errMsg + " Erwartet: " + expected.length + " aber wurde: " + actual.length);
			nrErrors++; 
			}else{
				for(int i = 0; i < expected.length;i++) {
					if(!expected[i].equals(actual[i])) { //Ueberprueft jeden String einzelnd
						System.out.println("Fehler in "+ errMsg + "Erwartet: " + expected.length + " aber wurde: " + actual.length);
						nrErrors++;
					}
				}
			}
		}

		public static void assertEquals(int expected, double actual, double epsilon,String errMsg) {
			if(expected > actual + epsilon || expected < actual - epsilon) {
				System.out.println("Fehler in" + errMsg + " Erwartet: " + expected + " aber wurde: " + actual);
				nrErrors++;
			}	
		}
		public static void assertEquals(Rectangle rectangle, Geometry encapsulate, String errMsg) {
			
		}
	}