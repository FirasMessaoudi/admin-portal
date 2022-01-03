import {
  AfterViewInit,
  Component,
  EventEmitter, forwardRef, Injector,
  Input,
  OnInit,
  Output,
  ViewChild,
  ViewEncapsulation
} from '@angular/core';
import {
  NgbCalendar,
  NgbCalendarIslamicUmalqura,
  NgbDatepickerI18n,
  NgbDateStruct,
  NgbTimeStruct
} from '@ng-bootstrap/ng-bootstrap';
import {IslamicI18n} from '../IslamicI18n';
import {noop} from "rxjs";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, NgControl} from "@angular/forms";
import * as momentjs from 'moment';
import {DatePipe} from "@angular/common";

const moment = momentjs;

@Component({
  selector: 'hijri-datetime-picker',
  templateUrl: './hijri-datetime-picker.component.html',
  providers: [
    {provide: NgbCalendar, useClass: NgbCalendarIslamicUmalqura},
    {provide: NgbDatepickerI18n, useClass: IslamicI18n},
    DatePipe,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => HijriDatetimePickerComponent),
      multi: true
    }
  ],
  styleUrls: [
    './hijri-datetime-picker.component.scss'
  ],
  encapsulation: ViewEncapsulation.None
})

export class HijriDatetimePickerComponent implements ControlValueAccessor, OnInit, AfterViewInit {

  @Input()
  inputDatetimeFormat = 'dd/MM/yyyy HH:mm';

  @ViewChild('d') datePicker: any;

  @Input() selectedDate: NgbDateStruct;

  @Output() selectedDateChange: EventEmitter<NgbDateStruct> = new EventEmitter();

  @Input() readonly = false;
  @Input() isRequired = false;
  @Input() disabled = false;
  @Input() min: NgbDateStruct;
  @Input() max: NgbDateStruct;
  @Input() name: string;

  @Input() placeHolder: string;

  private onChange: (_: any) => void = noop;
  private onTouched: () => void = noop;

  public ngControl: NgControl;

  timeStruct: NgbTimeStruct;
  date: Date;

  constructor(private inj: Injector) {
  }

  ngOnInit() {
    // tslint:disable-next-line: deprecation
    this.ngControl = this.inj.get(NgControl);
  }

  ngAfterViewInit(): void {
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

  onDateChange(event: NgbDateStruct) {
    this.setDateStringModel();
  }

  onTimeChange(event: NgbTimeStruct) {
    this.setDateStringModel();
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
      this.selectedDateChange.emit(this.selectedDate);
    }
  }

  onBlur() {
    if (!this.selectedDate) {
      this.selectedDateChange.emit(null);
    }
  }

  clear() {
    this.selectedDate = undefined;
    this.datePicker.close();
    this.selectedDateChange.emit(null);
  }

  toggle() {
    this.datePicker.toggle();
  }

  inputBlur($event) {
    this.onTouched();
  }

  onInputChange(event: any) {
  }

}
