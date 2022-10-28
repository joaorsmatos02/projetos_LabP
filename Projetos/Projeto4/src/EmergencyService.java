import java.util.LinkedList;
import java.util.List;

/**
 * 
 * The EmergencyService class makes use of both Doctor and Patient classes to
 * simulate an hospital's urgency service
 * 
 * @author João Matos nº 56292
 *
 */

public class EmergencyService {

	private Doctor[] avaliableDoctors;
	private LinkedList<Patient> currentPatients = new LinkedList<Patient>();
	private int lastPatientIndex;

	/**
	 * The constructor creates a new object of the EmergencyService class using the
	 * provided information
	 * 
	 * @param m - The number of doctors in the service
	 */

	public EmergencyService(int m) {
		avaliableDoctors = new Doctor[m];
		for (int i = 0; i < m; i++) {
			avaliableDoctors[i] = new Doctor(i + 1);
		}
	}

	/**
	 * Checks if a doctor with the given ID exists in the service
	 * 
	 * @param doctorID - The ID to the checked
	 * @return true if such doctor exists, false if otherwise
	 */

	public boolean hasDoctor(int doctorID) {
		return doctorID <= getNumberOfDoctors() && doctorID >= 1;
	}

	/**
	 * Calculates the current number of doctors in service
	 * 
	 * @return the current number of doctors
	 */

	public int getNumberOfDoctors() {
		return avaliableDoctors.length;
	}

	/**
	 * Calculates the number of patients currently in the service
	 * 
	 * @return the current number of patients
	 */

	public int totalPatients() {
		return currentPatients.size();
	}

	/**
	 * Registers a new patient in the service
	 * 
	 * @param urgency          - The severity of the patient's case
	 * @param consultationTime - The time it will take to attend the patient
	 * @return the new patient's ID
	 */

	public int checkIn(UrgencyStatus urgency, int consultationTime) {
		lastPatientIndex++;
		currentPatients.add(new Patient(lastPatientIndex, consultationTime, urgency));
		return lastPatientIndex;
	}

	/**
	 * Verfies if a patient with the given patient ID has checked in
	 * 
	 * @param patientID - The ID to be checked
	 * @return true if such a patient has checked in, false if otherwise
	 */

	public boolean hasCheckedIn(int patientID) {
		return lastPatientIndex >= patientID;
	}

	/**
	 * Chooses one of the avaliable doctors to treat the patient. Doctors are chosen
	 * using the following criteria: 1 - attendance time, 2 - number of patients
	 * assigned, 3 - number of urgent patients assinged
	 * 
	 * @return - The ID of the chosen doctor
	 */

	public int chooseDoctor() {
		int current = 0;
		int time = avaliableDoctors[0].attendanceTime();
		boolean free = false;
		if (time != 0) {
			for (int i = 1; i < getNumberOfDoctors() && !free; i++) {
				if (avaliableDoctors[i].isFree()) { // se encontrar um medico sem pacientes pode parar
					current = i;
					free = true;
				} else if (avaliableDoctors[i].attendanceTime() < time) { // atribuir ao que tiver menor tempo alocado
					time = avaliableDoctors[i].attendanceTime();
					current = i;
				} else if (avaliableDoctors[i].attendanceTime() == time) { // caso dois tenham o mesmo tempo
					if (avaliableDoctors[current].getNumberOfPatients() > avaliableDoctors[i].getNumberOfPatients()) {
						current = i;
					} else if (avaliableDoctors[current].getNumberOfPatients() == avaliableDoctors[i]
							.getNumberOfPatients()) { // caso tenham o mesmo nº de pacientes
						if (avaliableDoctors[current].numberPatients(UrgencyStatus.URGENT) > avaliableDoctors[i]
								.numberPatients(UrgencyStatus.URGENT))
							current = i;
					}
				}
			}
		}
		return avaliableDoctors[current].getID();
	}

	/**
	 * Assigns the patient to the doctor, adding him to the corresponding queues
	 * 
	 * @param doctorID  - The ID of the chosen doctor
	 * @param patientID - The ID of the chosen patient
	 * @requires hasDoctor(doctorID) && hasCheckedIn(patientID)
	 */

	public void assignPatient(int doctorID, int patientID) {
		int index = 0;
		while (currentPatients.get(index).getID() != patientID) {
			index++;
		}
		avaliableDoctors[doctorID - 1].addPatient(patientID, currentPatients.get(index).getStatus(),
				currentPatients.get(index).getTime());
	}

	/**
	 * Checks which of the avaliable doctors have an attendance time above the
	 * chosen value
	 * 
	 * @param bound - The value to compare the attendace time
	 * @return a list containig the ID's of the doctors whose attendace time is
	 *         greater than bound
	 */

	public List<Integer> attendanceTimeAbove(int bound) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i = 0; i < getNumberOfDoctors(); i++) {
			if (avaliableDoctors[i].attendanceTime() > bound)
				result.add(avaliableDoctors[i].getID());
		}
		return result;
	}

	/**
	 * Calculates minimum waiting time for a patient currently arriving at the
	 * hospital
	 * 
	 * @return the minimum waiting time for a new patient
	 */

	public int minWaitingTime() {
		int min = avaliableDoctors[0].attendanceTime();
		for (int i = 1; i < getNumberOfDoctors() && min != 0; i++) {
			if (avaliableDoctors[i].attendanceTime() < min)
				min = avaliableDoctors[i].attendanceTime();
		}
		return min;
	}

	/**
	 * Calculates the patient with the least time needed that is next in a doctor's
	 * queue, removes him from the queue and returns his ID. In case there's more
	 * than one patient with the same time, the following tie breaking criteria is
	 * applied: lower ID number
	 * 
	 * @return the chosen patient's ID
	 * @requires totalPatients() > 0
	 */
	
	public int dischargePatient() {
		int currentID = avaliableDoctors[0].nextToAttend();
		int time = 0;
		for (int i = 0; i < currentPatients.size() && time == 0; i++) {
			if (currentPatients.get(i).getID() == currentID)
				time = currentPatients.get(i).getTime();
		}
		int nextID = 0;
		int docIndex = 0;
		int IDIndex = 0;
		for (int i = 1; i < getNumberOfDoctors(); i++) {
			nextID = avaliableDoctors[i].nextToAttend();
			int j = 0;
			while (currentPatients.get(j).getID() != nextID) { // procurar o paciente com este ID
				j++;
			}
			if (currentPatients.get(j).getTime() < time) { // se o tempo for menor
				time = currentPatients.get(j).getTime();
				currentID = nextID;
				docIndex = i;
				IDIndex = j;
			} else if (currentPatients.get(j).getTime() == time) { // se o tempo for igual fica o ID menor
				currentID = Math.min(currentID, nextID);
				if (currentID == nextID) {
					docIndex = i;
					IDIndex = j;
				}
			}
		}
		avaliableDoctors[docIndex].dischargePatient();
		currentPatients.remove(IDIndex);
		return currentID;
	}

	/**
	 * Constructs a string representing the patients that have not yet been attended
	 * assigned to all avaliable doctors, in the order they arrived at the hospital
	 * 
	 * @return a string with all of the unattended patients' information
	 */

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getNumberOfDoctors(); i++) {
			sb.append("for Doctor " + (i + 1) + ":\r\n" + avaliableDoctors[i].forAttendance() + "\r\n");
		}
		return sb.toString();
	}

}
