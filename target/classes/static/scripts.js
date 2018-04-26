$(document).ready(function () {
    $('#studentTable').DataTable({
        ajax: {
            url: '/students',
            dataSrc: ''
        },
        columns: [
            { data: 'id', title: 'ID' },
            { data: 'name', title: 'Name' },
            { data: 'address', title: 'Address' },
            { data: 'phone', title: 'Phone' },
            { data: 'email', title: 'Email' },
            { data: 'gender', title: 'Gender' },
            { data: 'dob', title: 'DOB' },
            { data: 'category', title: 'Category' },
            { data: 'major', title: 'Major' },
            { data: 'minor', title: 'Minor' },
            { data: 'advisorID', title: 'AID' }
        ]
    });

    $('#staffTable').DataTable({
        ajax: {
            url: '/staff',
            dataSrc: ''
        },
        columns: [
            { data: 'id', title: 'ID' },
            { data: 'name', title: 'Name' },
            { data: 'email', title: 'Email' },
            { data: 'address', title: 'Address' },
            { data: 'gender', title: 'Gender' },
            { data: 'dob', title: 'DOB' },
            { data: 'jobTitle', title: 'Title' },
            { data: 'location', title: 'Location' },
        ]
    });

    $('#staffTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            var table = $('#staffTable').DataTable();
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#studentTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            var table = $('#studentTable').DataTable();
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
});

function toggle(elId) {
    var tables = document.getElementsByClassName('dt');

    for (var i = 0; i < tables.length; i++) {
        tables[i].style.display = 'none';
    }
    document.getElementById(elId).style.display = 'block';
}

// Get the modal
var studentModal = document.getElementById('studentModal');

var staffModal = document.getElementById('staffModal');

// Get the buttons that opens the modal
var newStudentBtn = document.getElementById("newStudentBtn");
var newStaffBtn = document.getElementById("newStaffBtn");

var editStudentBtn = document.getElementById("editStudentBtn");
var editStaffBtn = document.getElementById("editStaffBtn");

// Get the <span> element that closes the modal
var studentClose = document.getElementById("studentClose")
var staffClose = document.getElementById("staffClose")

// When the user clicks on the button, open the modal 
newStudentBtn.onclick = function () {
    studentModal.style.display = "block";
}

newStaffBtn.onclick = function () {
    staffModal.style.display = "block";
}

editStudentBtn.onclick = function () {
    studentModal.style.display = "block";
}

editStaffBtn.onclick = function () {
    staffModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
studentClose.onclick = function () {
    studentModal.style.display = "none";
}

// When the user clicks on <span> (x), close the modal
staffClose.onclick = function () {
    staffModal.style.display = "none";
}