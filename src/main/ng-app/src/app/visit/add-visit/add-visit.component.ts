import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Appointment} from "../../appointment/appointment";
import {AppointmentService} from "../../appointment/appointment.service";
import {Visit} from "../visit";
import {Procedure} from "../../procedure/procedure";
import {ProcedureService} from "../../procedure/procedure.service";
import {Diagnosis} from "../../diagnosis/diagnosis";
import {DiagnosisService} from "../../diagnosis/diagnosis.service";
import {VisitService} from "../visit.service";
import {PatientService} from "../../patient/patient.service";
import {Patient} from "../../patient/patient";
import {Dentist} from "../../dentist/dentist";
import {DentistService} from "../../dentist/dentist.service";

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.css']
})
export class AddVisitComponent implements OnInit {

  appointment: Appointment;
  patient: Patient;
  visit: Visit = new Visit();
  procedures: Procedure[];
  diagnosisArray: Diagnosis[];
  procedureId: number;
  diagnosisId: number;
  dentist: Dentist;

  constructor(private appointmentService: AppointmentService,
              private procedureService: ProcedureService,
              private diagnosisService: DiagnosisService,
              private patientService: PatientService,
              private visitService: VisitService,
              private dentistService: DentistService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params.apptmtId) {
        this.fillAppointment(params.apptmtId);
      }
      if (params.patient) {
        this.fillPatient(params.patient);
        this.fillDentist();
      }
    });
    this.fillProcedures()
    this.fillDiagnosisArray();
  }

  private fillPatient(id: string): void {
    this.patientService.getPatient(id).subscribe(data => this.patient = data,
      error => {
        console.log(error);
        switch (error.status) {
          case 404:
            alert("Пациента с идентификатором " + id + " не существует");
            this.router.navigate(["/appointments"]);
            break;
        }
      });
  }

  private fillProcedures(): void {
    this.procedureService.getProcedures().subscribe(data => this.procedures = data);
  }

  private fillDiagnosisArray(): void {
    this.diagnosisService.getDiagnosisAll().subscribe(data => this.diagnosisArray = data);
  }

  private fillAppointment(id: string): void {
    this.appointmentService.getAppointment(id).subscribe(data => {
      this.appointment = data;
      this.patient = this.appointment.patient;
    }, error => {
      console.log(error);
      switch (error.status) {
        case 404:
          alert("Записи с идентификатором " + id + " не существует");
          this.router.navigate(["/appointments"]);
          break;
      }
    });
  }

  private fillDentist(): void {
    this.dentistService.getCurrentDentist().subscribe(data => this.dentist = data);
  }

  public onSubmit() {
    debugger;
    this.visit.procedure = this.findProcedureById(this.procedureId);
    this.visit.diagnosis = this.findDiagnosisById(this.diagnosisId);
    this.visit.patient = this.patient;
    this.visit.dentist = this.appointment ? this.appointment.dentist : this.dentist;
    this.visit.appointment = this.appointment;
    console.log(this.visit);
    this.saveVisit(this.visit);
  }

  private saveVisit(visit: Visit): void {
    this.visitService.createVisit(visit).subscribe(
      data => {
        console.log(data);
        this.navigateToList();
      },
      error => console.log(error)
    );

  }

  private navigateToList(): void {
    this.router.navigate(["/appointments"]);
  }

  private findProcedureById(id: number): Procedure {
    return this.procedures.filter(procedure => {
      return procedure.id == id;
    })[0];
  }

  private findDiagnosisById(id: number): Diagnosis {
    return this.diagnosisArray.filter(diagnosis => {
      return diagnosis.id == id;
    })[0];
  }


}
