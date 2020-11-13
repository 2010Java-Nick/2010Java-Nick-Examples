//some logic, perferably JavaScript
//let a, myVar, myFunction, addRow;
let a = 5;

function myFunction(input) {
    console.log('my value is: ' + input);
}

myFunction(5);

console.log(myVar); //Hoisting

{
    var myVar = 6;
    let myLet = 7;
}

console.log(myVar);

//DOM Manipulation
window.onload = function () {
    document.cookie = "token=secure-token";
    console.log(document.cookie);
    let nameLabel = document.getElementById("nameLabel");
    nameLabel.innerHTML = "Guest Label";
    grabGuest();

    //console.log(myGuest);

    /* let myGuest = {
        name: "Turkey Turkson",
        phoneNumber: "5555555555",
        payment: 500
    };

    addRow(myGuest); */
    
}

let grabGuest = function() {

    let name = document.getElementById("nameInput");
    let phoneNumber = document.getElementById("phoneNumberInput");
    let payment = document.getElementById("paymentInput");
    let btn = document.getElementById("createGuestButton");

    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        console.log("inside on click");
        console.log(event.target);
        let newGuest = {name: name.value, phoneNumber: phoneNumber.value, payment: payment.value};
        addRow(newGuest);
    }, true);

}

let addRow = function(myGuest) {
    let table = document.getElementById("guest-table");
    let tableRow = document.createElement("tr");
    let nameCol = document.createElement("td");
    let phoneCol = document.createElement("td");
    let paymentCol = document.createElement("td");
    
    tableRow.appendChild(nameCol);
    tableRow.appendChild(phoneCol);
    tableRow.appendChild(paymentCol);
    table.appendChild(tableRow);

    nameCol.innerHTML = myGuest.name;
    phoneCol.innerHTML = myGuest.phoneNumber;
    paymentCol.innerHTML = myGuest.payment;

    nameCol.className = "table-style";
    phoneCol.className = "table-style";
    paymentCol.className = "table-style";
    tableRow.className = "table-style";
}

