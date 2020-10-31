import {Patient} from "../patient/patient";
import {Time} from "@angular/common";
import {Dentist} from "../dentist/dentist";

export class Appointment {

  id: number;
  appointmentTime: Time;
  patient: Patient;
  dentist: Dentist;

}
