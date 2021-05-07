var clients = [];
var clientMap = new Map();
var currentClient = {};

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
                        + (clients[i].address.line2 == '' || clients[i].address.line2 == null ? '' : clients[i].address.line2 + '<br />')
                        + clients[i].address.city + ', ' + clients[i].address.state + ' ' + clients[i].address.zipCode 
                    + '</td>'
                    + '<td>' 
                        + '<button id="client-pets-' + clients[i].id + '" data-toggle="modal" data-target="#pet-modal" class="pet-card btn btn-light"><img src="pets.png" width="30" height="30" /></button>'
                    + '</td>' 
                    + '<td>' 
                        + '<button id="client-appt-' + clients[i].id + '" data-toggle="modal" data-target="#appt-modal" class="appt-card btn btn-light"><img src="appt.png" width="30" height="30" /></button>'
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
            currentClient = client;
            document.getElementById("card-client-name").innerHTML = client.name;
            document.getElementById("card-client-phone").innerHTML = client.phoneNumber;
            document.getElementById("card-client-phone-alt").innerHTML = client.phoneNumberAlt;
            document.getElementById("card-client-address").innerHTML = client.address.line1 + '<br />' 
                + (client.address.line2 == '' || client.address.line2 == null ? '' : client.address.line2 + '<br />')
                + client.address.city + ', ' + client.address.state + ' ' + client.address.zipCode ;
            document.getElementById("card-appt-table").innerHTML 
                = '<tr class="thead-dark position-sticky"><th>Date</th><th>Time</th><th>Description</th></tr>';
            for (i = 0; i < client.appointments.length; i++) {
                document.getElementById("card-appt-table").innerHTML += '<tr><td>' + client.appointments[i].date + '</td>'
                    + '<td>' + client.appointments[i].time + '</td>'
                    + '<td>' + client.appointments[i].description + '</td></tr>'
            }
            document.getElementById("card-pet-table").innerHTML
                = '<tr class="thead-dark position-sticky"><th>Name</th><th>Breed</th><th>Age</th><th>Vax</th><th>Medical Info</th></tr>';
            for (i = 0; i < client.pets.length; i++) {
                document.getElementById("card-pet-table").innerHTML += '<tr><td>' + client.pets[i].name + '</td>'
                    + '<td>' + client.pets[i].breed + '</td>'
                    + '<td>' + client.pets[i].age + '</td>'
                    + '<td>' + (client.pets[i].vaccinated ? '&#10004;' : '') + '</td>'
                    + '<td>' + client.pets[i].medicalNotes + '</td></tr>'
            }
        });
    }
    var apptBtns = document.getElementsByClassName("appt-card");
    for (i = 0; i < apptBtns.length; i++) {
        apptBtns[i].addEventListener('click', (e) => {
            var el = (e.target.localName == 'img' ? e.target.parentNode : e.target);
            var id = +el.id.split("-")[2];
            var client = clientMap[id.toString()];
            currentClient = client;
            document.getElementById("appt-modal-title").innerHTML = "Appointments for " + currentClient.name;
            document.getElementById("appt-table").innerHTML 
                = '<tr class="thead-dark position-sticky"><th>Date</th><th>Time</th><th>Description</th></tr>';
            for (i = 0; i < client.appointments.length; i++) {
                document.getElementById("appt-table").innerHTML += '<tr><td>' + client.appointments[i].date + '</td>'
                    + '<td>' + client.appointments[i].time + '</td>'
                    + '<td>' + client.appointments[i].description + '</td></tr>'
            }
        });
    }
    var petBtns = document.getElementsByClassName("pet-card");
    for (i = 0; i < apptBtns.length; i++) {
        petBtns[i].addEventListener('click', (e) => {
            var el = (e.target.localName == 'img' ? e.target.parentNode : e.target);
            var id = +el.id.split("-")[2];
            var client = clientMap[id.toString()];
            currentClient = client;
            document.getElementById("pet-modal-title").innerHTML = "Pets for " + currentClient.name;
            document.getElementById("pet-table").innerHTML 
                = '<tr class="thead-dark position-sticky"><th>Name</th><th>Breed</th><th>Age</th><th>Vax</th><th>Medical Info</th></tr>';
            for (i = 0; i < client.pets.length; i++) {
                document.getElementById("pet-table").innerHTML += '<tr><td>' + client.pets[i].name + '</td>'
                    + '<td>' + client.pets[i].breed + '</td>'
                    + '<td>' + client.pets[i].age + '</td>'
                    + '<td>' + (client.pets[i].vaccinated ? '&#10004;' : '') + '</td>'
                    + '<td>' + client.pets[i].medicalNotes + '</td></tr>'
            }
        });
    }
    document.getElementById("new-client-submit").addEventListener('click', (e) => {
        var newClient = {};
        newClient.address = {};
        newClient.name = document.newClientForm.name.value;
        newClient.phoneNumber = document.newClientForm.phoneNumber.value;
        newClient.phoneNumberAlt = document.newClientForm.phoneNumberAlt.value;
        newClient.address.line1 = document.newClientForm.line1.value;
        newClient.address.line2 = document.newClientForm.line2.value;
        newClient.address.city = document.newClientForm.city.value;
        newClient.address.state = document.newClientForm.state.value;
        newClient.address.zipCode = document.newClientForm.zipCode.value;

        var request = new XMLHttpRequest();
        request.onload = () => { window.location.reload(); };
        request.onerror = error;
        request.open("POST", "http://localhost:8080/api/addclient", false);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        request.send(JSON.stringify(newClient));
    })
    document.getElementById("new-appt-submit").addEventListener('click', (e) => {
        var newAppt = {};
        newAppt.clientId = currentClient.id;
        newAppt.date = document.newApptForm.date.value;
        newAppt.time = document.newApptForm.time.value;
        newAppt.description = document.newApptForm.description.value;

        var request = new XMLHttpRequest();
        request.onload = () => { window.location.reload(); };
        request.onerror = error;
        request.open("POST", "http://localhost:8080/api/addappointment", false);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        request.send(JSON.stringify(newAppt));
    })
    document.getElementById("new-pet-submit").addEventListener('click', (e) => {
        var newPet = {};
        newPet.clientId = currentClient.id;
        newPet.name = document.newPetForm.name.value;
        newPet.breed = document.newPetForm.breed.value;
        newPet.age = document.newPetForm.age.value;
        newPet.vaccinated = document.newPetForm.vaccinated.checked;
        newPet.medicalNotes = document.newPetForm.medicalNotes.value;

        var request = new XMLHttpRequest();
        request.onload = () => { window.location.reload(); };
        request.onerror = error;
        request.open("POST", "http://localhost:8080/api/addpet", false);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        request.send(JSON.stringify(newPet));
    })
});

    
    