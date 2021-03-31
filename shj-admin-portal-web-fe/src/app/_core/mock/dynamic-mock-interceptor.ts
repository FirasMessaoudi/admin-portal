/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */

import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {delay, dematerialize, materialize, mergeMap} from 'rxjs/operators';
import {environment} from "@env/environment";


@Injectable({
  providedIn: 'root'
})
export class DynamicMockInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.debug('################## @@@@@@@@@@@@@@@@Dynamic Mock is enabled');
    // if mock is not enabled or we are not in dev environment, then do nothing
    if (!environment.mock || !environment.dev) {
      return next.handle(request);
    }
    console.debug('################## Dynamic Mock is enabled');

    // wrap in delayed observable to simulate server api call
    return of(null).pipe(mergeMap(() => {

      environment.mockData.forEach(mData => {
        // loop
        if (request.url.indexOf(mData.url) != -1) {
          console.debug('################## Mocking Data for URL : ' + mData.url);
          let body = JSON.parse(mData.data);
          return of(new HttpResponse({status: 200, body: body}));
        }
      });

      // pass through any requests not handled above
      return next.handle(request);

    }))

      // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
      .pipe(materialize())
      .pipe(delay(500))
      .pipe(dematerialize());
  }
}
