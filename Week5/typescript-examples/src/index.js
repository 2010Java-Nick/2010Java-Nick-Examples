"use strict";
exports.__esModule = true;
var GuestServiceImpl_1 = require("./services/GuestServiceImpl");
window.onload = function () {
    var viewGuests = new ViewGuests();
    viewGuests.setUpButton();
};
var ViewGuests = /** @class */ (function () {
    function ViewGuests() {
        this.guestService = new GuestServiceImpl_1.GuestServiceImpl();
    }
    ViewGuests.prototype.setUpButton = function () {
        var _this = this;
        var btn = document.getElementById("createGuestButton");
        btn.addEventListener("click", function (event) {
            event.preventDefault();
            event.stopPropagation();
            console.log("inside on click");
            console.log(event.target);
            var newGuest = _this.guestService.getGuest();
            _this.updateTable(newGuest);
        }, true);
    };
    ViewGuests.prototype.updateTable = function (guest) {
        var table = document.getElementById("guest-table");
        var tableRow = document.createElement("tr");
        var nameCol = document.createElement("td");
        var phoneCol = document.createElement("td");
        var paymentCol = document.createElement("td");
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
    };
    return ViewGuests;
}());
