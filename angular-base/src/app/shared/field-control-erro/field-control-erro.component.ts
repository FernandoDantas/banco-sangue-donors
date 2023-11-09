import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'field-control-erro',
  templateUrl: './field-control-erro.component.html',
  styleUrls: ['./field-control-erro.component.css']
})
export class FieldControlErroComponent implements OnInit {

  @Input() msgErro: string;
  @Input() showErro?: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
