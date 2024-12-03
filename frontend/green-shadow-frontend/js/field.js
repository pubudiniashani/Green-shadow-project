$('#add-btn').on('click',()=> {
    console.log("add button clicked")

    var name = $('#name').val()
    var location = $('#location').val()
    var extentSize = $('#extentSize').val()
    var image1 = $('#image1')[0].files[0]
    var image2 = $('#image2')[0].files[0]

    console.log(name, location, extentSize)

    const formData = new FormData();
    formData.append("name",name)
    formData.append("location",location)
    formData.append("extentSize",extentSize)

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
            localStorage.setItem('token', response.token);
            alert("Successfully saved the field")
        },
        error: function () {
            alert("Error while saving a field")
        }
    });

});

