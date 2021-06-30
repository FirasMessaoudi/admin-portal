import {NavigationEnd, Router} from "@angular/router";
import {Location} from '@angular/common'
import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable({providedIn: 'root'})
export class NavigationService {
  private history: string[] = []
  private behaviorSubject = new BehaviorSubject<boolean>(false);
  canGoBack = this.behaviorSubject.asObservable();

  constructor(private router: Router, private location: Location) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.history.push(event.urlAfterRedirects)
      }
    })
  }

  public getHistory(): string[] {
    return this.history;
  }

  public getPreviousUrl(): string {
    return this.history[this.history.length - 2] || '/';
  }

  showGoBackLink(value: boolean): void {
    this.behaviorSubject.next(value);
  }

  back(): void {
    this.history.pop()
    if (this.history.length > 0) {
      this.location.back();
    } else {
      this.router.navigateByUrl('/');
    }
  }
}
