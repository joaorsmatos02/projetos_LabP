import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * The Doctor class provides the necessary methods to represent a doctor at the
 * hospital
 * 
 * @author João Matos nº 56292
 *
 */

public class Doctor {

	private int doctorID;
	private int totalTime;
	private Queue<Integer> patientQueue = new LinkedList<Integer>();
	private Queue<Integer> urgentPatientQueue = new LinkedList<Integer>();
	private LinkedList<Patient> allPatients = new LinkedList<Patient>();

	/**
	 * The constructor creates a new object of the Doctor class with the provided
	 * information
	 * 
	 * @param doctorID - the new doctor's identification
	 */

	public Doctor(int doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * Calculates the total number of patients assigned to the doctor
	 * 
	 * @return the total number of patients assigned to the doctor
	 */

	public int getNumberOfPatients() {
		return allPatients.size();
	}

	/**
	 * Fetches the doctor's identification
	 * 
	 * @return the doctor's ID
	 */

	public int getID() {
		return doctorID;
	}

	/**
	 * Fetches the time it is going to take for the doctor to attend all patients
	 * assigned to him
	 * 
	 * @return the total time assigned to a doctor
	 */

	public int attendanceTime() {
		return totalTime;
	}

	/**
	 * Checks if the doctor has any patients assigned to him
	 * 
	 * @return true if the doctor has no patients assigned, false if otherwise
	 */

	public boolean isFree() {
		return allPatients.size() == 0;
	}

	/**
	 * Adds a patient to the appropriate queue and adds a corresponing Patient
	 * object to the list with all patients assigned to the doctor
	 * 
	 * @param patientID        - The identification of the patient
	 * @param urgency          - The severity of the patient's case
	 * @param consultationTime - The time it will take to attend the patient
	 * @requires time >= 1 && status == UrgencyStatus.URGENT || status ==
	 *           UrgencyStatus.NONURGENT
	 */

	public void addPatient(int patientID, UrgencyStatus urgency, int consultationTime) {
		totalTime += consultationTime;
		if (urgency == UrgencyStatus.NONURGENT)
			patientQueue.add(patientID);
		else
			urgentPatientQueue.add(patientID);
		allPatients.add(new Patient(patientID, consultationTime, urgency));
	}

	/**
	 * Calculates which patient the doctor is going to attend next
	 * 
	 * @return the next patient's id
	 * @requires !isFree()
	 */

	public int nextToAttend() {
		int next = 0;
		if (urgentPatientQueue.size() == 0)
			next = patientQueue.peek();
		else
			next = urgentPatientQueue.peek();
		return next;
	}

	/**
	 * Removes the next patient to be attended from the doctor's queue
	 * 
	 * @return the patient's identification
	 * @requires !isFree()
	 */

	public int dischargePatient() {
		int ID = nextToAttend();
		if (urgentPatientQueue.size() != 0 && urgentPatientQueue.peek() == ID) {
			urgentPatientQueue.remove();
		} else {
			patientQueue.remove();
		}
		int index = 0;
		while (allPatients.get(index).getID() != ID) {
			index++;
		}
		totalTime -= allPatients.get(index).getTime();
		allPatients.remove(index);
		return ID;
	}

	/**
	 * Calculates the number of patients in the doctor's queue whose status is equal
	 * urgency
	 * 
	 * @param urgency - The status to be considered
	 * @return the number of patients whose status is equal urgency
	 */

	public int numberPatients(UrgencyStatus urgency) {
		int count = 0;
		if (urgency == UrgencyStatus.URGENT)
			count = urgentPatientQueue.size();
		else
			count = patientQueue.size();
		return count;
	}

	/**
	 * Creates a string containing the information of all patients assigned to the
	 * doctor, in the order they arrived at the hospital
	 * 
	 * @return the information of all patients assingned to the doctor
	 */

	public String forAttendance() {
		StringBuilder sb = new StringBuilder();
		for (Patient p : allPatients) {
			sb.append(p.toString());
		}
		return sb.toString();
	}
}
