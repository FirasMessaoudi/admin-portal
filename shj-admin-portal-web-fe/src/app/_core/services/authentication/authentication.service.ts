import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {BehaviorSubject, Observable, of, throwError} from 'rxjs';
import {EAuthority} from "@model/enum/authority.enum";


const CURRENT_USER_KEY = 'currentUser';


/**
 * Provides a base for authentication workflow.
 * The Credentials interface as well as login/logout methods should be replaced with proper implementation.
 */
@Injectable()
export class AuthenticationService {

  // Sprint 6
  private authenticatedUserSubject: BehaviorSubject<any>;
  public authenticatedUser: Observable<any>;

  constructor(private http: HttpClient) {
    const savedUser = sessionStorage.getItem(CURRENT_USER_KEY) || localStorage.getItem(CURRENT_USER_KEY);
    if (savedUser) {
      this._currentUser = JSON.parse(savedUser);

      //
      this.authenticatedUserSubject = new BehaviorSubject<any>(JSON.parse(savedUser));
      this.authenticatedUser = this.authenticatedUserSubject.asObservable();
    }
  }

  public get authenticatedUserValue(): any {
    return this.authenticatedUserSubject.value;
  }

  private _currentUser: any | null;

  /**
   * Gets the user credentials.
   * @return {User} The user credentials or null if the user is not authenticated.
   */
  get currentUser(): any | null {
    return JSON.parse(localStorage.getItem(CURRENT_USER_KEY));
  }

  /**
   * Authenticates the user.
   * @param {LoginContext} context The login parameters.
   * @return {Observable<Credentials>} The user credentials.
   */
  login(username: string, password: string, recaptchaToken: string): Observable<any> {

    let params: String = recaptchaToken ? '?grt=' + recaptchaToken : '';

    return this.http.post<any>('/core/api/auth/login' + params, {'idNumber': username, 'password': password})
      .pipe(map(authentication => {
        console.log(JSON.stringify(authentication));
        let user: any = authentication;

        // login successful if there's a jwt token in the response
        if (user && user.principal) {
          this.setCurrentUser(user);
          //
          this.authenticatedUserSubject.next(user);
          //
        } else {
          this.setCurrentUser();
        }
        return this.currentUser;
      }), catchError((err: HttpErrorResponse) => {
        return throwError(err);
      }));
  }

  /**
   * Logs out the user and clear credentials.
   * @return {Observable<boolean>} True if the user was logged out successfully.
   */
  logout(): Observable<any> {
    localStorage.clear();
    this.http.post<any>('/core/api/auth/logout', null).subscribe(result => {
      if (result) {
        this.setCurrentUser();
        //
        this.authenticatedUserSubject.next(null);
      }
      return of(result);
    });
    return of(true);
  }

  /**
   * Checks is the user is authenticated.
   * @return {boolean} True if the user is authenticated.
   */
  isAuthenticated(): boolean {
    return !!localStorage.getItem(CURRENT_USER_KEY);
  }

  /**
   * Checks is the user is authenticated.
   * @return {boolean} True if the user is authenticated.
   */
  userHasExpiredPassword(): boolean {
    return this.currentUser && this.currentUser.passwordExpired;
  }

  /**
   * Sets the user credentials.
   * The credentials are only persisted for the current session.
   * @param {User=} currentUser The user credentials.
   */
  private setCurrentUser(currentUser?: any) {
    this._currentUser = currentUser || null;
    if (currentUser) {
      localStorage.setItem(CURRENT_USER_KEY, JSON.stringify(currentUser));
      //
      /** Ahmed **/
      const savedUser = sessionStorage.getItem(CURRENT_USER_KEY) || localStorage.getItem(CURRENT_USER_KEY);
      if (savedUser) {
        this._currentUser = JSON.parse(savedUser);

        //
        this.authenticatedUserSubject = new BehaviorSubject<any>(JSON.parse(savedUser));
        this.authenticatedUser = this.authenticatedUserSubject.asObservable();
      }

      //
    } else {
      localStorage.removeItem(CURRENT_USER_KEY);
    }
  }

  get preferredLanguage() {
    return this.currentUser.preferredLanguage;
  }

  updatePreferredLanguage(language: string) {
    this.currentUser.preferredLanguage = language;
  }

  /**
   * Check if logged in user has the passed authority enum in his role authorities.
   * @param authority
   */
  hasAuthority(authority: EAuthority) : boolean {
    return this.currentUser && this.currentUser.authorities && this.currentUser.authorities.filter(authorityObj => authorityObj.authority === authority.toString()).length > 0;
  }

}
