import {Procedure} from "../procedure/procedure";
import {Dentist} from "../dentist/dentist";
import {Patient} from "../patient/patient";
import {Diagnosis} from "../diagnosis/diagnosis";
import {Appointment} from "../appointment/appointment";

export class Visit {

  id: number;
  complaint: string;
  status: string;
  visitDate: Date;
  procedure: Procedure;
  diagnosis: Diagnosis
  dentist: Dentist
  patient: Patient;
  appointment: Appointment;

}
