$(document).ready(() => {
    loadTable()

});


$('#add-btn').on('click',()=> {
    console.log("add button clicked")

    var name = $('#name').val()
    var location = $('#location').val()
    var extentSize = $('#extentSize').val()
    var image1 = $('#image1')[0].files[0]
    var image2 = $('#image2')[0].files[0]

    console.log(name, location, extentSize)

    const formData = new FormData();
    formData.append("name", name)
    formData.append("location", location)
    formData.append("extentSize", extentSize)

    if (image1) {
        formData.append("image1", image1);
    }

    if (image2) {
        formData.append("image2", image2);
    }

    const token = localStorage.getItem('token');
    console.log(token)

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/field',
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


var recordIndex;

function loadTable() {

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/field',
        method: 'GET',
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            console.log(data);
            $("#field-tbl-body").empty();
            data.forEach(item => {

                const image1 = item.image1
                    ? `<img src="data:image/jpeg;base64,${item.image1}" alt="Image 1" style="width: 100px; height: auto;">`
                    : 'No Image';
                const image2 = item.image2
                    ? `<img src="data:image/jpeg;base64,${item.image2}" alt="Image 2" style="width: 100px; height: auto;">`
                    : 'No Image';

                var record = `
                      <tr data-field-id="${item.fieldId}">
                        <th scope="row" class="field-name-value">${item.name}</th>
                        <td class="field-location-value">${item.location}</td>
                         <td class="field-extentSize-value">${item.extentSize}</td>
                         <td>${image1}</td>
                        <td>${image2}</td>
                        
                    </tr>
                `;
                $("#field-tbl-body").append(record);
            });
        },
        error: function (xhr, status, error) {
            console.error('Failed to load fields:', error);
        }
    });
}

/*
$("#field-tbl-body").on("click", "tr", function () {
    const fieldId = $(this).data("field-id");
    console.log("Selected Field ID:", fieldId);
});
*/

$("#field-tbl-body").on("click", "tr", function () {
    const fieldId = $(this).data("field-id");
    console.log("Selected Field ID:", fieldId);

    if (fieldId) {
        const token = localStorage.getItem('token');

        $.ajax({
            url: `http://localhost:8080/greenshadow/api/v1/field/${fieldId}`,
            method: "GET",
            headers: {
                Authorization: "Bearer " + token,
            },
            success: function (data) {
                console.log("Field Details:", data);
                $("#name").val(data.name);
                $("#location").val(data.location);
                $("#extentSize").val(data.extentSize);


                $("#image1").attr("src", `data:image/png;base64,${data.image1}`);
                $("#image2").attr("src", `data:image/png;base64,${data.image2}`);

            },
            error: function (xhr, status, error) {
                console.error("Error fetching field details:", error);
                console.error("Response text:", xhr.responseText);
            }
        });
    } else {
        console.error("Field ID is undefined or invalid.");
    }
});



