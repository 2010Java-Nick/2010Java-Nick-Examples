import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
  styleUrls: ['./title.component.css']
  //styles: [``]
})
export class TitleComponent implements OnInit {

  public title: string = `Guest List View`;

  constructor() { }

  ngOnInit(): void {
  }

}
