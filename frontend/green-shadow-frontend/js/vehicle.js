$(document).ready(function () {
    alert("ok")

    loadTable()

    const $staffSelect = $("#staffId");

    const token = localStorage.getItem('token');
    console.log(token);

    $.ajax({
        url: "http://localhost:8080/greenshadow/api/v1/staff",
        method: "GET",
        headers: {
            Authorization: "Bearer " + token
        },
        success: function (data) {
            console.log("Staff API Response:", data);
            // Populate the select dropdown with staff options
            data.forEach(function (staff) {
                $staffSelect.append(`<option value="${staff.staffId}">${staff.firstName}</option>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading staff data:", error);
        }
    });
});


$('#add-vehicle-btn').on('click',()=> {

    console.log("add button clicked")

    var licenseNumber =  $('#licenseNumber').val()
    var vehicleCategory =  $('#vehicleCategory').val()
    var fuelType =  $('#fuelType').val()
    var status =  $('#status').val()
    var remarks =  $('#remarks').val()
    var staffId =  $('#staffId').val()

    console.log(licenseNumber,vehicleCategory,fuelType,status,remarks,staffId)

    const token = localStorage.getItem('token');
    console.log(token)

    const staff_id = [];
    $("#staffId option").each(function () {
        const staffId = $(this).val();
        if (staffId) {
            staff_id.push(staffId);
            console.log(staffId);
        }
    });

    const staffIdString = staff_id.join(',');

    const formData = {
        licenseNumber: licenseNumber,
        vehicleCategory: vehicleCategory,
        fuelType: fuelType,
        status: status,
        remarks: remarks,
        staffId:staffIdString

    };

    console.log(formData)

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/staff',
        type: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        headers: {
            Authorization: "Bearer " + token
        },
        success: function (response) {
            alert("Successfully saved a vehicle");
        },
        error: function (xhr, status, error) {
            console.error("Error in AJAX request:", error);
            alert("Error while saving a vehicle");
        }
    });

});


function loadTable() {

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/vehicle',
        method: 'GET',
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            console.log(data);
            $("#vehicle-tbl-body").empty();
            data.forEach(item => {

                var record = `
                      <tr data-field-id="${item.vehicleId}">
                        <td>${item.vehicleCategory}</td>
                        <td>${item.fuelType}</td>
                        <td>${staff.gender}</td>
                        <td>${staff.status}</td>
                        <td>${staff.remarks}</td>
                        
                    </tr>
                `;
                $("#vehicle-tbl-body").append(record);
            });
        },
        error: function (xhr, status, error) {
            console.error('Failed to load vehicles:', error);
        }
    });
}

