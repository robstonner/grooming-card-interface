var clients = [];
var clientMap = new Map();

function success() {
    clients = JSON.parse(this.responseText);
    for (i = 0; i < clients.length; i++) {
        clientMap[clients[i].id.toString()] = clients[i];
    }
}

function error(err) {
    console.log('Error Occurred :', err);
}

function getAllClients() {
    var request = new XMLHttpRequest();
    request.onload = success;
    request.onerror = error;
    request.open("GET", "http://localhost:8080/api/getallclients", false);
    request.send();
}

function addClientsToTable(clients) {
    document.getElementById("column-headers").innerHTML =
        '<th>ID</th><th>Name</th><th>Phone Numbers</th><th>Address</th><th>Pets</th><th>Appointments</th><th>Card</th>';
    for (i = 0; i < clients.length; i++) {
            document.getElementById("main-table").innerHTML += (
                '<tr>'
                    + '<td>' + clients[i].id + '</td>'
                    + '<td>' + clients[i].name + '</td>'
                    + '<td>' 
                        + clients[i].phoneNumber 
                        + (clients[i].phoneNumberAlt == '' || clients[i].phoneNumberAlt == null ? '' : '<br />' + clients[i].phoneNumberAlt) 
                    + '</td>'
                    + '<td>' 
                        + clients[i].address.line1 + '<br />' 
                        + (clients[i].line2 == '' || clients[i].line2 == null ? '' : clients[i].line2 + '<br />')
                        + clients[i].address.city + ', ' + clients[i].address.state + ' ' + clients[i].address.zipCode 
                    + '</td>'
                    + '<td>' 
                        + '<button id="client-pets-' + clients[i].id + '"class="client-card btn btn-light"><img src="pets.png" width="30" height="30" /></button>'
                    + '</td>' 
                    + '<td>' 
                        + '<button id="client-appt-' + clients[i].id + '" class="client-card btn btn-light"><img src="appt.png" width="30" height="30" /></button>'
                    + '</td>' 
                    + '<td>'
                        + '<button id="client-card-' + clients[i].id + '" type="button" class="client-card btn btn-info" data-toggle="modal" data-target="#grooming-card"><img src="card.png" width="30" height="30" /></button>'
                    + '</td>'
                + '</tr>'
            );
    }
}

window.addEventListener('DOMContentLoaded', () => {
    getAllClients();
    addClientsToTable(clients);
    var cardBtns = document.getElementsByClassName("client-card");
    for (i = 0; i < cardBtns.length; i++) {
        cardBtns[i].addEventListener('click', (e) => {
            var el = (e.target.localName == 'img' ? e.target.parentNode : e.target);
            var id = +el.id.split("-")[2];
            var client = clientMap[id.toString()];
            document.getElementById("card-appt-table").innerHTML = "";
            for (i = 0; i < client.appointments.length; i++) {
                document.getElementById("card-appt-table").innerHTML += '<td>' + client.appointments[i].date + '</td>'
                    + '<td>' + client.appointments[i].time + '</td>'
                    + '<td>' + client.appointments[i].description + '</td>'
            }
        });
    }
});

    
    