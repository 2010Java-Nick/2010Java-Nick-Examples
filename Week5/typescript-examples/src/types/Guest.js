"use strict";
exports.__esModule = true;
exports.Guest = void 0;
var Guest = /** @class */ (function () {
    function Guest(name, phoneNumber, payment) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.payment = payment;
    }
    Guest.prototype.getName = function () {
        return this.name;
    };
    Guest.prototype.setName = function (name) {
        this.name = name;
    };
    Guest.prototype.getPhoneNumber = function () {
        return this.phoneNumber;
    };
    Guest.prototype.setPhoneNumber = function (phoneNumber) {
        this.phoneNumber = phoneNumber;
    };
    Guest.prototype.getPayment = function () {
        return this.payment;
    };
    Guest.prototype.setPayment = function (payment) {
        this.payment = payment;
        var myNum = 75; //inferes type
        //myNum = "75";
        var myOtherNum; //does not infer type
        //The same as let myOtherNum: any; (bad practice)
        myOtherNum = 75;
        myOtherNum = "75";
    };
    return Guest;
}());
exports.Guest = Guest;
