import { Component, OnInit } from '@angular/core';
import { Employee, HttpClientService } from '../service/http-client.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employees: Employee[] = [];

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit(): void {
    this.httpClientService.getEmployee().subscribe(
      response => this.employees = response
    )
  }

  deleteEmployee(employee: Employee) : void{
    this.httpClientService.deleteEmployee(employee)
    .subscribe( data =>{
      this.employees = this.employees.filter(u => u !== employee)
    })
  };

}
