import{ HttpEvent,HttpHandler,HttpInterceptor,HttpRequest,HTTP_INTERCEPTORS,} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginserviceService } from './loginservice.service';


@Injectable({
  providedIn: 'root'
})
export class TockenInterceptorService implements HttpInterceptor {

  constructor(private login:LoginserviceService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    //add the jwt token (localStorage) request
    let authReq = req;
    const token = this.login.getToken();
    console.log('inside interceptor');

    if (token != null) {
      authReq = authReq.clone({
        setHeaders: { Authorization: `Bearer ${token}` },
      });
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TockenInterceptorService,
    multi: true,
  },
];

