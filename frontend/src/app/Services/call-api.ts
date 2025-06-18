import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CallAPI {
  private baseUrl = "http://localhost:8080"

  private username = 'user'
  private password = '123'
  private basicAuth = btoa(`${this.username}:${this.password}`)

  private headers = {
    'content-type': 'application/json',
    'Authorization': `Basic ${this.basicAuth}`
  }

  constructor(private http: HttpClient) { }

  GET(apiName: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/${apiName}`)
  }
  POST(apiName: String, data: any): void{
    console.log("calling API: " + this.baseUrl + apiName, {headers: this.headers}, data);
    this.http.post(`${this.baseUrl}/${apiName}`,data, {headers: this.headers}).subscribe(response => {
      console.log(response)
    })
  }
}
