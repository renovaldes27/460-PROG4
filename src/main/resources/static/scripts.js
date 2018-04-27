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

    $('#leaseTable').DataTable({
        ajax: {
            url: '/lease',
            dataSrc: ''
        },
        columns: [
            { data: 'id', title: 'ID' },
            { data: 'rID', title: 'Room ID' },
            { data: 'sID', title: 'Student ID' },
            { data: 'duration', title: 'Duration' },
            { data: 'cost', title: 'Monthly Rent' },
            { data: 'startDate', title: 'Start Date' },
        ]
    });

    var staffTable = $('#staffTable').DataTable();
    var studentTable = $('#studentTable').DataTable();
    var leaseTable = $('#leaseTable').DataTable();

    var studentModal = document.getElementById('studentModal');
    var staffModal = document.getElementById('staffModal');


    $('#staffTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            staffTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#studentTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            studentTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#leaseTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            leaseTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#editStudentBtn').click( function () {
        studentModal.style.display= "block";
        var selectedData = studentTable.row('.selected').data()
        if(selectedData !== null){
            $('#studID').attr('value',selectedData.id);
            $('#studName').attr('value',selectedData.name);
            $('#studPhone').attr('value',selectedData.phone);
            $('#studEmail').attr('value',selectedData.email);
            $('#studDob').attr('value', selectedData.dob);
            $('#studGender').attr('value',selectedData.gender);
            $('#studCategory').attr('value',selectedData.category);
            $('#studMajor').attr('value',selectedData.major);
            $('#studMinor').attr('value',selectedData.minor);
            $('#studAddress').attr('value',selectedData.address);
            $('#aid').attr('value',selectedData.advisorID);
        }
    });

    $('#editStaffBtn').click( function () {
        staffModal.style.display= "block";
        var selectedData = staffTable.row('.selected').data()
        if(selectedData !== null){
            $('#staffID').attr('value',selectedData.id);
            $('#staffName').attr('value',selectedData.name);
            $('#staffEmail').attr('value',selectedData.email);
            $('#staffDob').attr('value', selectedData.dob);
            $('#staffGender').attr('value',selectedData.gender);
            $('#staffTitle').attr('value',selectedData.jobTitle);
            $('#staffLocation').attr('value',selectedData.location);
            $('#staffAddress').attr('value',selectedData.address);
            $('#aid').attr('value',selectedData.advisorID);
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
    $("#studentForm").find("input[type=text]").attr('value',"");
    studentModal.style.display = "block";
}

newStaffBtn.onclick = function () {
    $("#staffForm").find("input[type=text]").attr('value',"");
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