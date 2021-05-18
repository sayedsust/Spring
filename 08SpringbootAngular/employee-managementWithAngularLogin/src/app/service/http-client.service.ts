import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export class Employee{
  constructor( public empId: string, public name: string, public designation: string, public salary: string){

  }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) { }

  public getEmployee(){
    console.log("test client");
    return this.httpClient.get<Employee[]>('http://localhost:8080/employees');
  }

  public deleteEmployee(employee : Employee) {
    return this.httpClient.delete<Employee>("http://localhost:8080/employees" + "/"+ employee.empId);
  }

  public createEmployee(employee: Employee) {
    return this.httpClient.post<Employee>("http://localhost:8080/employees", employee);
  }
}
