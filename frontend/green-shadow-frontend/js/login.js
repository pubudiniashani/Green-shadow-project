$('#login-btn').on('click',()=>{
    var email = $('#email').val()
    var password = $('#password').val()

    console.log(email,password)

    $.ajax({
        url: 'http://localhost:8080/greenshadowbackend/api/v1/auth/signin',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ email, password }),
        success: function (response) {
            // Store the token from the response
            localStorage.setItem('token', response.token);
            localStorage.setItem('email',email)
        },
        error: function () {
           alert("Error while login")
        }
    });
});

