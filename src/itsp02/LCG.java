/**
 * 
 */
package itsp02;

/**
 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
class LCG {
	
	/*
	 * +++++++++++++++++++
	 * 		CONSTANTS
	 * +++++++++++++++++++
	 */
	
	/*
	 * Parameterkombinationen fuer das lineare Kongruenzverfahren
	 * nach
	 * Berkeley UNIX Pascal
	 */
	
	private static final long A = 62605;
	private static final long B = 113218009;
	private static final long N = 536870912; // 2^29
	
	
	/*
	 * +++++++++++++++++++
	 * 		Variables
	 * +++++++++++++++++++
	 */
	
	private int x;
	
	/*
	 * +++++++++++++++++++
	 * 		Construction
	 * +++++++++++++++++++
	 */
	
	public LCG(int x){
		this.x = x;
	}
	
	/*
	 * +++++++++++++++++++
	 * 		Methods
	 * +++++++++++++++++++
	 */
	
	/*
	 * Erstellt eine Zufallszahl nach der linearen Kongruenzmethode
	 * 
	 */
	public int nextInt(){
		this.x = (int) ((A * this.x + B) % N);
		return this.x;
	}
}
