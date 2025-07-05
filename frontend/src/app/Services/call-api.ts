import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CallAPI {
  private baseUrl = "http://localhost:8080"



  constructor(private http: HttpClient) { }

  GET(apiName: String): Observable<any> {
    console.log("calling API: " + this.baseUrl + apiName, {headers: this.getHeaders()});
    return this.http.get(`${this.baseUrl}/${apiName}`,  {headers: this.getHeaders()})
  }
  POST(apiName: String, data: any): Observable<any>{
    console.log("calling API: " + this.baseUrl + apiName, {headers: this.getHeaders()}, data);
    return this.http.post(`${this.baseUrl}/${apiName}`,data, {headers: this.getHeaders()})
  }

  getHeaders(): HttpHeaders{

    if (localStorage.getItem("token") != null) {
    return new HttpHeaders({
        "Content-Type": "application/json",
        'Authorization': "Bearer " + localStorage.getItem("token")
      })
    } else return new HttpHeaders({
      "Content-Type": "application/json"
    })
  }
}
