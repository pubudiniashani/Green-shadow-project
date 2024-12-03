$('#signup-btn').on('click',()=>{
    var email = $('#email-signup').val()
    var password = $('#signup-password').val()
    var role = $('#roleSelect').val()

    console.log(email,password,role)

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/auth/signup',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ email, password, role}),
        success: function (response) {
            // Store the token from the response
            localStorage.setItem('token', response.token);
            localStorage.setItem('email',email)
            window.location.href = "login.html";
        },
        error: function () {
            alert("Error while signup")
        }
    });
});
