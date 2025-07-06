import {HttpInterceptorFn} from '@angular/common/http';

import {UserData} from "./user-data";
import {inject} from "@angular/core";

export const interceptor: HttpInterceptorFn = (req, next) => {
  console.log('interceptor activated');
  const token = localStorage.getItem("token");
  let newReq = null;
  if (token != null) {
    newReq = req.clone({
      setHeaders: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      }
    })
  } else {
    newReq = req.clone({
      setHeaders: {
        "Content-Type": "application/json"
      }
    })
  }
  console.log(`request sent with headers: ${newReq.headers.keys()} URL: ${req.url}`);
  return next(newReq);
}
