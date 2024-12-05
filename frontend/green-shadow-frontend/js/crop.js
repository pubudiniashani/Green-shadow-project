$(document).ready(function () {
    alert("ok")
    const $fieldSelect = $("#field");

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
                $("#field").append(`<option value="${field.fieldId}">${field.name}</option>`);

            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading field IDs:", error);
        }
    });
});






$('#crop-add-btn').on('click',()=> {
    console.log("add button clicked")

    var commonName = $('#commonName').val()
    var scientificName = $('#scientificName').val()
    var fuelType = $('#fuelType').val()
    var category = $('#category').val()
    var season = $('#season').val()
    var cropImage = $('#cropImage')[0].files[0]
    var field = $('#field').val()


    console.log(commonName,scientificName,fuelType,category,season,cropImage,field)

    const formData = new FormData();
    formData.append("commonName", commonName)
    formData.append("scientificName", scientificName)
    formData.append("fuelType", fuelType)
    formData.append("category",category)
    formData.append("season", season)

    if (cropImage) {
        formData.append("cropImage", cropImage);
    }

    const field_id = [];
    $("#field").each(function () {
        const fieldId = $(this).val();
        if (fieldId) {
            field_id.push(fieldId);
        }
    });

    const token = localStorage.getItem('token');
    console.log(token)

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/crops',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        headers: {
            Authorization: "Bearer " + token
        },
        success: function (response) {
            // localStorage.setItem('token', response.token);
            alert("Successfully saved the field")
        },
        error: function () {
            alert("Error while saving a field")
        }
    });



});