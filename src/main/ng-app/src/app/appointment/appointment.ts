import {Patient} from "../patient/patient";
import {Time} from "@angular/common";

export class Appointment {

  id: number;
  appointmentTime: Time;
  patient: Patient;

}
