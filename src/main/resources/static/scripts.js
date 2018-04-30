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

    function createAdvisors() {
        $('#advisor-table').DataTable({
            ajax: {
                url: '/advisors',
                dataSrc: ''
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'name', title: 'Name' },
                { data: 'position', title: 'Position' },
                { data: 'departmentID', title: 'Dept. ID' },
                { data: 'telephoneNumber', title: 'Phone' },
                { data: 'email', title: 'Email' },
            ]
        });
    }
    createAdvisors();

    function createRooms() {
        $('#room-table').DataTable({
            ajax: {
                url: '/room',
                dataSrc: ''
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'roomNumber', title: 'Room #' },
                { data: 'buildingId', title: 'Building ID' },
                { data: 'studentID', title: 'Student ID' },
                { data: 'monthlyRent', title: 'Monthly Rent' }
            ]
        });
    }
    createRooms();

    function createBuildings() {
        $('#building-table').DataTable({
            ajax: {
                url: '/buildings',
                dataSrc: ''
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'name', title: 'Name' },
                { data: 'address', title: 'Address' },
                { data: 'isApartment', title: 'Apartment?' },
                { data: 'telephoneNumber', title: 'Phone' },
                { data: 'managerID', title: 'Manager ID' },
                { data: 'numStudents', title: 'Student Count' },
            ]
        });
    }
    createBuildings();

    function createInspections() {
        $('#inspection-table').DataTable({
            ajax: {
                url: '/inspections',
                dataSrc: ''
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'inspectionDate', title: 'Inspection Date' },
                { data: 'staffName', title: 'Staff' },
                { data: 'roomString', title: 'Room Info' },
                { data: 'condition', title: 'Condition' },
                { data: 'action', title: 'Action' }
            ]
        });
    }
    createInspections();

    function createInvoices() {
        $('#invoice-table').DataTable({
            ajax: {
                url: '/invoices',
                dataSrc: ''
            },
            columns: [
                { data: 'id', title: 'ID' },
                { data: 'leaseID', title: 'Lease ID' },
                { data: 'semester', title: 'Semester' },
                { data: 'paymentDueDate', title: 'Payment Due' },
                { data: 'DatePaid', title: 'Date Paid' }
            ]
        });
    }
    createInvoices();


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
    var advisorTable = $('#advisor-table').DataTable();
    var roomTable = $('#room-table').DataTable();
    var buildingTable = $('#building-table').DataTable();
    var invoiceTable = $('#invoice-table').DataTable();
    var inspectionTable = $('#inspect-table').DataTable();

    var studentModal = document.getElementById('studentModal');
    var staffModal = document.getElementById('staffModal');
    var leaseModal = document.getElementById('lease-modal');
    var advisorModal = document.getElementById('advisor-modal');
    var roomModal = document.getElementById('room-modal');
    var buildingModal = document.getElementById('building-modal');
    var invoiceModal = document.getElementById('invoice-modal');
    var inspectionModal = document.getElementById('inspect-modal');



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

    $('#advisor-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            advisorTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#room-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            roomTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#building-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            buildingTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#invoice-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            invoiceTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#inspect-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            inspectionTable.$('tr.selected').removeClass('selected');
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

    $('#edit-advisor').click( function () {
        advisorModal.style.display= "block";
        var selectedData = advisorTable.row('.selected').data()
        if(selectedData !== null){
            $('#advisor-id').attr('value',selectedData.id);
            $('#advisor-name').attr('value',selectedData.name);
            $('#advisor-position').attr('value',selectedData.position);
            $('#advisor-department').attr('value',selectedData.departmentID);
            $('#advisor-phone').attr('value', selectedData.telephoneNumber);
            $('#advisor-email').attr('value',selectedData.email);
        }
    });

    $('#edit-room').click( function () {
        roomModal.style.display= "block";
        var selectedData = roomTable.row('.selected').data()
        if(selectedData !== null){
            $('#room-id').attr('value',selectedData.id);
            $('#room-num').attr('value',selectedData.roomNumber);
            $('#room-building').attr('value',selectedData.buildingId);
            $('#room-student').attr('value',selectedData.studentID);
            $('#room-rent').attr('value', selectedData.monthlyRent);
        }
    });

    $('#edit-building').click( function () {
        buildingModal.style.display= "block";
        var selectedData = buildingTable.row('.selected').data()
        if(selectedData !== null){
            $('#building-id').attr('value',selectedData.id);
            $('#building-name').attr('value',selectedData.name);
            $('#building-apt').attr('value',selectedData.isApartment);
            $('#building-phone').attr('value',selectedData.telephoneNumber);
            $('#building-manager').attr('value', selectedData.managerID);
            $('#building-count').attr('value',selectedData.numStudents);
            $('#building-address').attr('value',selectedData.address);
        }
    });

    $('#edit-invoice').click( function () {
        invoiceModal.style.display= "block";
        var selectedData = invoiceTable.row('.selected').data()
        if(selectedData !== null){
            $('#invoice-id').attr('value',selectedData.id);
            $('#invoice-lease').attr('value',selectedData.leaseID);
            $('#invoice-semester').attr('value',selectedData.semester);
            $('#invoice-due').attr('value',selectedData.paymentDueDate);
            $('#invoice-paid').attr('value', selectedData.DatePaid);

        }
    });

    $('#edit-inspect').click( function () {
        inspectionModal.style.display= "block";
        var selectedData = inspectionTable.row('.selected').data()
        if(selectedData !== null){
            $('#inspect-id').attr('value',selectedData.id);
            $('#inspect-date').attr('value',selectedData.inspectionDate);
            $('#inspect-staff').attr('value',selectedData.staffName);
            $('#inspect-room').attr('value',selectedData.roomString);
            $('#inspect-condition').attr('value', selectedData.condtion);
            $('#inspect-action').attr('value',selectedData.action);
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

    $('#new-advisor').click( function() {
        advisorModal.style.display = "block";
    });

    $('#new-room').click( function() {
        roomModal.style.display = "block";
    });

    $('#new-building').click( function() {
        buildingModal.style.display = "block";
    });

    $('#new-inspect').click( function() {
        inspectionModal.style.display = "block";
    });

    $('#new-invoice').click( function() {
        invoiceModal.style.display = "block";
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

    $('#advisor-close').click( function() {
        advisorModal.style.display = "none";
    });

    $('#room-close').click( function() {
        roomModal.style.display = "none";
    });

    $('#building-close').click( function() {
        buildingModal.style.display = "none";
    });

    $('#inspect-close').click( function() {
        inspectionModal.style.display = "none";
    });

    $('#invoice-close').click( function() {
        invoiceModal.style.display = "none";
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

    $('#advisor-cancel').click( function() {
        advisorModal.style.display = "none";
    });

    $('#room-cancel').click( function() {
        roomModal.style.display = "none";
    });

    $('#building-cancel').click( function() {
        buildingModal.style.display = "none";
    });

    $('#inspect-cancel').click( function() {
        inspectionModal.style.display = "none";
    });

    $('#invoice-cancel').click( function() {
        invoiceModal.style.display = "none";
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

    $('#advisor-submit').click( function() {
        var formData = {
            id: $('#advisor-id').val(),
            name:$('#advisor-name').val(),
            position:$('#advisor-position').val(),
            departmentID:$('#advisor-department').val(),
            telephoneNumber:$('#advisor-phone').val(),
            email:$('#advisor-email').val(),
        };

        advisorModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/advisors",
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

    $('#room-submit').click( function() {
        var formData = {
            id: $('#room-id').val(),
            roomNumber:$('#room-num').val(),
            buildingId:$('#room-building').val(),
            studentID:$('#room-student').val(),
            monthlyRent:$('#room-rent').val(),
        };
            
        roomModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/room",
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

    $('#building-submit').click( function() {
        var formData = {
            id: $('#building-id').val(),
            name:$('#building-name').val(),
            address:$('#building-address').val(),
            isApartment:$('#building-apt').val(),
            telephoneNumber:$('#building-phone').val(),
            managerID:$('#building-manager').val(),
            numStudents:$('#building-count').val()
        };
            
        buildingModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/buildings",
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

    $('#inspect-submit').click( function() {
        var formData = {
            id: $('#inspect-id').val(),
            inspectionDate:$('#inspect-date').val(),
            staffName:$('#inspect-staff').val(),
            roomString:$('#inspect-room').val(),
            condtion:$('#inspect-condition').val(),
            action:$('#inspect-action').val(),
        };
            
        inspectionModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/inspections",
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

    $('#invoice-submit').click( function() {
        var formData = {
            id: $('#invoice-id').val(),
            leaseID:$('#invoice-lease').val(),
            semester:$('#invoice-semester').val(),
            paymentDueDate:$('#invoice-due').val(),
            DatePaid:$('#invoice-paid').val(),
        };
            
        invoiceModal.style.display = "none";

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/invoices",
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

    $('#delete-student').click( function() {

        var selectedData = studentTable.row('.selected').data()

        if(selectedData.id !== null){
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/studremove",
                data : JSON.stringify(selectedData),
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
        }
    });

    $.getJSON( "/staff", function( data ) {
        var buildingOpt = $("#building-manager");
        var inspectOpt = $("#inspect-staff");
        data.forEach(element => {
            buildingOpt.append(new Option(element.name, element.id));
            inspectOpt.append(new Option(element.name, element.id));
        });
      });

      $.getJSON( "/students", function( data ) {
        var options = $("#room-student");
        data.forEach(element => {
            options.append(new Option(element.name, element.id));
        });
      });

      $.getJSON( "/buildings", function( data ) {
        var options = $("#room-building");
        data.forEach(element => {
            options.append(new Option(element.name, element.id));
        });
      });

      $.getJSON( "/leases", function( data ) {
        var options = $("#invoice-lease");
        data.forEach(element => {
            options.append(new Option(element.leaseID, element.leaseID));
        });
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