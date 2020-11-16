import {Patient} from "../patient/patient";
import {Dentist} from "../dentist/dentist";

export class Appointment {

  id: number;
  appointmentDate: string;
  appointmentTime: string;
  patient: Patient;
  dentist: Dentist;

}
