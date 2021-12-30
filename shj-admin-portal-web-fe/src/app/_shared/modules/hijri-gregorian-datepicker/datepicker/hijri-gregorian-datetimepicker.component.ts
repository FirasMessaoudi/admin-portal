import {
  AfterViewInit,
  Component,
  EventEmitter,
  forwardRef,
  Injector,
  Input,
  OnInit,
  Output,
  ViewChild,
  ViewEncapsulation
} from '@angular/core';
import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";
import {DateType} from './consts';
import * as momentjs from 'moment';
import * as moment_ from 'moment-hijri';
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/datepicker/date-formatter.service";
import {noop} from "rxjs";
import {DatePipe} from "@angular/common";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, NgControl} from '@angular/forms';

const moment = momentjs;

const momentHijri = moment_;

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-hijri-gregorian-datetimepicker',
  templateUrl: './hijri-gregorian-datetimepicker.component.html',
  styleUrls: ['./hijri-gregorian-datetimepicker.component.scss'],
  providers: [
    DatePipe,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => HijriGregorianDatetimepickerComponent),
      multi: true
    }
    ]
})
export class HijriGregorianDatetimepickerComponent implements ControlValueAccessor, OnInit, AfterViewInit {

  @Input()
  inputDatetimeFormat = 'dd/MM/yyyy HH:mm';

  @ViewChild('d')
  private datePicker: any;

  @Input() selectedDateType: DateType;
  @Input() selectedDate: NgbDateStruct;
  @Output() selectedDateChange: EventEmitter<Date> = new EventEmitter();

  @Input() name: string;

  @Input() label: string;
  @Input() showLabel = true;

  @Input() buttonClass: string = 'dcc-primary';

  @Input() readonly = false;
  @Input() isRequired = false;
  @Input() disabled = false;
  @Input() grayed = false;

  @Input() minHijri: NgbDateStruct;
  @Input() maxHijri: NgbDateStruct;
  @Input() minGreg: NgbDateStruct;
  @Input() maxGreg: NgbDateStruct;

  @Input() hijriLabel: string;
  @Input() GregLabel: string;

  @Input() placeHolder: string;

  @Input()
  seconds = false;

  private onChange: (_: any) => void = noop;
  private onTouched: () => void = noop;

  public ngControl: NgControl;

  timeStruct: NgbTimeStruct;
  date: Date;

  get DateType() {
    return DateType;
  }

  constructor(private inj: Injector,
              private dateFormatterService: DateFormatterService) {
  }

  ngOnInit() {
    if (!this.selectedDateType) {
      this.selectedDateType = DateType.Hijri;
    }
    // tslint:disable-next-line: deprecation
    this.ngControl = this.inj.get(NgControl);
  }

  ngAfterViewInit(): void {
  }

  close() {
    this.datePicker.close();
  }

  clear() {
    this.selectedDate = undefined;
    this.timeStruct = undefined;
    this.date = undefined;
    this.close();
    this.selectedDateChange.emit(null);
  }

  getSelectedDate(): string {

    let formattedDate = this.dateFormatterService.toString(this.selectedDate);

    if (this.selectedDateType == DateType.Hijri) {
      return momentHijri(formattedDate, 'iDD/iMM/iYYYY').locale('en').format();
    }

    if (this.selectedDateType == DateType.Gregorian) {
      return moment(formattedDate, 'DD/MM/YYYY').locale('en').format();
    }
  }

  hijriClick() {
    if (this.selectedDateType == DateType.Hijri) {
      return;
    }
    this.selectedDateType = DateType.Hijri;
    //to hijri
    this.selectedDate = this.dateFormatterService.toHijri(this.selectedDate);
    this.selectedDateChange.emit(this.date);
  }

  gregClick() {
    if (this.selectedDateType == DateType.Gregorian) {
      return;
    }
    this.selectedDateType = DateType.Gregorian;
    //to Gregorian
    this.selectedDate = this.dateFormatterService.toGregorian(this.selectedDate);
    this.selectedDateChange.emit(this.date);
  }

  onDateChange(event: NgbDateStruct) {
    this.setDateStringModel();
  }

  onTimeChange(event: NgbTimeStruct) {
    this.setDateStringModel();
  }

  onInputChange($event: any) {
  }

  setDateStringModel() {
    if (!this.timeStruct) {
      const dateA = new Date();
      this.timeStruct = {
        hour: dateA.getHours(),
        minute: dateA.getMinutes(),
        second: 0
      };
    }

    if (this.selectedDate) {
      this.date = new Date(
        this.selectedDate.year,
        this.selectedDate.month - 1,
        this.selectedDate.day,
        this.timeStruct.hour,
        this.timeStruct.minute,
        this.timeStruct.second
      );

      this.onChange(this.date);
    }
  }

  writeValue(newModel: string) {
    if (newModel) {
      const myDate = moment(newModel).toDate();

      this.selectedDate = {
        year: myDate.getFullYear(),
        month: myDate.getMonth() + 1,
        day: myDate.getDate()
      };

      this.timeStruct = {
        hour: myDate.getHours(),
        minute: myDate.getMinutes(),
        second: myDate.getSeconds()
      };

      this.setDateStringModel();
    }
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  toggle() {
    this.datePicker.toggle();
  }


  onBlur() {
    if (!this.selectedDate) {
      this.selectedDateChange.emit(null);
    }
  }
}
