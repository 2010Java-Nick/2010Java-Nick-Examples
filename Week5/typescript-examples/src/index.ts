import { GuestServiceImpl } from './services/GuestServiceImpl';
import { GuestService } from './services/GuestService';
import { Guest } from './types/Guest';


window.onload = function() {
    let viewGuests = new ViewGuests();
    viewGuests.setUpButton();
}

class ViewGuests {

    guestService = new GuestServiceImpl();

    setUpButton(): void {
        let btn = document.getElementById("createGuestButton");
        btn.addEventListener("click", (event) => {
            event.preventDefault();
            event.stopPropagation();
            console.log("inside on click");
            console.log(event.target);
            let newGuest = this.guestService.getGuest();
            this.updateTable(newGuest);
        }, true);
    }

    public updateTable(guest: Guest): void {

        let table = document.getElementById("guest-table");
        let tableRow = document.createElement("tr");
        let nameCol = document.createElement("td");
        let phoneCol = document.createElement("td");
        let paymentCol = document.createElement("td");

        tableRow.appendChild(nameCol);
        tableRow.appendChild(phoneCol);
        tableRow.appendChild(paymentCol);
        table.appendChild(tableRow);

        nameCol.innerHTML = guest.getName();
        phoneCol.innerHTML = guest.getPhoneNumber();
        paymentCol.innerHTML = guest.getPayment() + "";

        nameCol.className = "table-style";
        phoneCol.className = "table-style";
        paymentCol.className = "table-style";
        tableRow.className = "table-style";

    }
}
