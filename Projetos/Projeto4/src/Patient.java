
/**
 * 
 * The Patient class provides the necessary methods to represent a patient at
 * the hospital
 * 
 * @author João Matos nº 56292
 *
 */

public class Patient {

	private int patientID;
	private int time;
	private UrgencyStatus status;

	/**
	 * The constructor creates a new object of the Patient class with the provided
	 * information
	 * 
	 * @param patientID - The identification of the patient
	 * @param time     - The time it is going to take to treat the patient
	 * @param status   - The severity of the patient's case
	 * @requires time >= 1 && status == UrgencyStatus.URGENT || status ==
	 *           UrgencyStatus.NONURGENT
	 */

	public Patient(int patientID, int time, UrgencyStatus status) {
		this.patientID = patientID;
		this.time = time;
		this.status = status;
	}

	/**
	 * Creates a String containing all of the patient's information
	 * 
	 * @return a String containing all of the patient's information
	 */

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Patient " + patientID + ": " + status + " " + time + "; ");
		return sb.toString();
	}

	/**
	 * Fetches the patient's identification
	 * 
	 * @return the patient's ID
	 */

	public int getID() {
		return patientID;
	}

	/**
	 * Fetches the time it will take to treat the patient
	 * 
	 * @return the time it will take to treat the patient
	 */

	public int getTime() {
		return time;
	}

	/**
	 * Fetches the status of the patient
	 * 
	 * @return the status of the patient
	 */

	public UrgencyStatus getStatus() {
		return status;
	}
}
