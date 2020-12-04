import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { By } from 'protractor';

import { SearchBarComponent } from './search-bar.component';

describe('SearchBarComponent', () => {
  let component: SearchBarComponent;
  let fixture: ComponentFixture<SearchBarComponent>;
  let searchInputBox: HTMLInputElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchBarComponent],
      imports: [FormsModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBarComponent);
    component = fixture.componentInstance;
    searchInputBox = fixture.nativeElement.querySelector('#search-field');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should raise searchValue event when input is typed in', () => {
    let searchText = '123'; //search value to set in text box
    let emittedSearchText: string = ''; // search value being emitted to parent on event
    component.searchValue.subscribe((text: string) => { emittedSearchText = text }); //subscribe to event to test it has triggered correctly
    searchInputBox.value = searchText; //put search text in search box
    fixture.detectChanges();
    fixture.whenStable().then(() => { //because of 2 way data binding
      searchInputBox.dispatchEvent(new Event('keyup')); //trigger synthetic event
      fixture.detectChanges(); //make sure event occured
      tick(); // used to wait for all fakeAsync events
      expect(emittedSearchText).toBe(searchText); //expect correct search term was emitted
    })

  })
});
