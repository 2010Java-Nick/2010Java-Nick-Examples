
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    const url = "guest";
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {

            case 0:
                console.log("nothing, initalized not sent");
                break;
            case 1:
                console.log("connection established")
                break;
            case 2:
                console.log("request sent");
                break;
            case 3:
                console.log("waiting response");
                break;
            case 4:
                console.log("FINISHED!!!!!!!!!!!");
                //logic to add guest to table
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                    let guestList = JSON.parse(xhr.responseText);
                    console.log(guestList);
                    console.log(guestList[0]);
                    guestList.forEach(element => {
                        addRow(element);
                    });
                }
                break;

        }
    }

    //opens up the request
    xhr.open("GET", url, true);
    //sends request
    xhr.send();

}

let addRow = function (myGuest) {
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