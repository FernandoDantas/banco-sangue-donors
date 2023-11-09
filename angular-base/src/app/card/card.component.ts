import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() textPrimaryColor: string;
  @Input() boderPrimaryColor: string;
  @Input() header: string;
  @Input() subHeader: any;
  @Input() icon: string;


  constructor() { }

  ngOnInit(): void {
  }

}
