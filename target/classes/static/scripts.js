$(document).ready(function () {
    function createStudents() {
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

    }
    createStudents();

    function createStaff() {
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
    }
    createStaff();


    function createLease() {
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
    }
    createLease();


    $('#hallmanagers-table').DataTable( {
        ajax: {
            url: '/getHallManagerInfo',
            dataSrc: ''
        },
        columns: [
            { data: 'ManagerName', title: 'Manager Name' },
            { data: 'TelephoneNumber', title: 'Telephone Number' },
            { data: 'BuildingName', title: 'Building Name' },
        ]
    } );

    var staffTable = $('#staffTable').DataTable();
    var studentTable = $('#studentTable').DataTable();
    var leaseTable = $('#leaseTable').DataTable();

    var studentModal = document.getElementById('studentModal');
    var staffModal = document.getElementById('staffModal');
    var leaseModal = document.getElementById('lease-modal')


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

    $('#editLeaseBtn').click( function () {
        leaseModal.style.display= "block";
        var selectedData = leaseTable.row('.selected').data()
        if(selectedData !== null){
            $('#lease-id').attr('value',selectedData.id);
            $('#lease-rid').attr('value',selectedData.rID);
            $('#lease-sid').attr('value',selectedData.sID);
            $('#lease-cost').attr('value', selectedData.cost);
            $('#lease-duration').attr('value',selectedData.duration);
            $('#lease-start').attr('value',selectedData.startDate);

        }
    });

    $('#newStudentBtn').click( function() {
        studentModal.style.display = "block";
    });

    $('#newStaffBtn').click( function() {
        staffModal.style.display = "block";
    });

    $('#newLeaseBtn').click( function() {
        leaseModal.style.display = "block";
    });

    $('#studentClose').click( function() {
        studentModal.style.display = "none";
    });

    $('#staffClose').click( function() {
        staffModal.style.display = "none";
    });

    $('#lease-close').click( function() {
        leaseModal.style.display = "none";
    });

    $('#studentCancel').click( function() {
        studentModal.style.display = "none";
    });

    $('#staff-cancel').click( function() {
        staffModal.style.display = "none";
    });

    $('#lease-cancel').click( function() {
        leaseModal.style.display = "none";
    });

    $('#studentSubmit').click( function() {
        var formData = {
            id: $('#studID').val(),
            name:$('#studName').val(),
            address:$('#studAddress').val(),
            phone:$('#studPhone').val(),
            email:$('#studEmail').val(),
            gender:$('#studGender').val(),
            dob:$('#studDob').val(),
            category:$('#studCategory').val(),
            major: $('#studMajor').val(),
            minor: $('#studMinor').val(),
            advisorID: $('#aid').val()

        };
        studentModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/students",
			data : JSON.stringify(formData),
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
                console.log("SUCCESS: ", data);
                location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
        });

        location.reload();
    });

    $('#staff-submit').click( function() {
        var formData = {
            id: $('#staffID').val(),
            name:$('#staffName').val(),
            address:$('#staffAddress').val(),
            email:$('#staffEmail').val(),
            gender:$('#staffGender').val(),
            dob:$('#staffDob').val(),
            jobTitle:$('#staffTitle').val(),
            location: $('#staffLocation').val(),
        };
        staffModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/staff",
			data : JSON.stringify(formData),
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
                console.log("SUCCESS: ", data);
                location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
        });

        location.reload();
    });

    $('#lease-submit').click( function() {
        var formData = {
            id: $('#lease-id').val(),
            rID:$('#lease-rid').val(),
            sID:$('#lease-sid').val(),
            duration:$('#lease-duration').val(),
            cost:$('#lease-cost').val(),
            startDate:$('#lease-start').val(),
        };

        leaseModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/lease",
			data : JSON.stringify(formData),
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
                console.log("SUCCESS: ", data);
                location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
        });

        location.reload();
    });

});

$("#nav-hallmanagers").on("click", function populateHallManagersTable() {
    table = $('#hallmanagers-table').DataTable();

    table.destroy();

    $('#hallmanagers-table').DataTable( {
        ajax: {
            url: '/getHallManagerInfo',
            dataSrc: ''
        },
        columns: [
            { data: 'ManagerName', title: 'Manager Name' },
            { data: 'TelephoneNumber', title: 'Telephone Number' },
            { data: 'BuildingName', title: 'Building Name' },
        ]
    } );
});

function toggle(elId) {
    var tables = document.getElementsByClassName('dt');

    for (var i = 0; i < tables.length; i++) {
        tables[i].style.display = 'none';
    }

    document.getElementById(elId).style.display = 'block';
}