"use strict";
exports.__esModule = true;
exports.GuestServiceImpl = void 0;
var Guest_1 = require("../types/Guest");
var GuestServiceImpl = /** @class */ (function () {
    function GuestServiceImpl() {
    }
    GuestServiceImpl.prototype.getGuest = function () {
        var name = document.getElementById("nameInput").value;
        var phoneNumber = document.getElementById("phoneNumberInput").value;
        var payment = document.getElementById("paymentInput").value;
        return new Guest_1.Guest(name, phoneNumber, payment);
    };
    return GuestServiceImpl;
}());
exports.GuestServiceImpl = GuestServiceImpl;
