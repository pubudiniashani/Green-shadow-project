$(document).ready(function () {
   // alert("ok")

    loadTable()

    const $fieldSelect = $("#fieldId");

    const token = localStorage.getItem('token');
    console.log(token)

    $.ajax({
        url: "http://localhost:8080/greenshadow/api/v1/field",
        method: "GET",
        headers: {
            Authorization: "Bearer " + token
        },
        success: function (data) {
            console.log("Field API Response:", data);
            data.forEach(function (field) {
                $("#fieldId").append(`<option value="${field.fieldId}">${field.name}</option>`);

            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading field IDs:", error);
        }
    });
});

$('#add-staff-btn').on('click',()=> {
    console.log("add button clicked")

    var firstName = $('#firstName').val()
    var lastName = $('#lastName').val()
    var designation = $('#designation').val()
    var gender = $('#gender').val()
    var jointedDate = $('#jointedDate').val()
    var dob = $('#dob').val()
    var address = $('#address').val()
    var contactNumber = $('#contactNumber').val()
    var email = $('#email').val()
    var role = $("#role").val()
    var fieldId = $('#fieldId').val()

    console.log(firstName,lastName,designation,gender,jointedDate,dob,address,contactNumber,email,role,fieldId)


    const token = localStorage.getItem('token');
    console.log(token)

    const field_id = [];
    $("#fieldId").each(function () {
        const fieldId = $(this).val();
        if (fieldId) {
            field_id.push(fieldId);
            console.log(field_id)
        }
    });

    const formData = {
        firstName: firstName,
        lastName: lastName,
        designation: designation,
        gender: gender,
        jointedDate: jointedDate,
        dob: dob,
        address: address,
        contactNumber: contactNumber,
        email: email,
        role: role,
        fieldId: field_id

    };

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/staff',
        type: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        headers: {
            Authorization: "Bearer " + token
        },
        success: function (response) {
            // localStorage.setItem('token', response.token);
            alert("Successfully saved a staff member")
        },
        error: function () {
            alert("Error while saving a staff member")
        }
    });

});

function loadTable() {

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/staff',
        method: 'GET',
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            console.log(data);
            $("#staff-tbl-body").empty();
            data.forEach(staff => {

                const record = `
                    <tr>
                        <th scope="row">${staff.firstName}</th>
                        <td>${staff.lastName}</td>
                        <td>${staff.designation}</td>
                        <td>${staff.gender}</td>
                        <td>${staff.jointedDate}</td>
                        <td>${staff.dob}</td>
                        <td>${staff.address}</td>
                        <td>${staff.contactNumber}</td>
                        <td>${staff.email}</td>
                        <td>${staff.role}</td>
                    </tr>
                `;

                $("#staff-tbl-body").append(record);
            });
        },
        error: function (xhr, status, error) {
            console.error('Failed to load fields:', error);
        }
    });
}

