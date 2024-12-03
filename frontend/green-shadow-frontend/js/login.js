$('#login-btn').on('click',()=>{
    var email = $('#email').val()
    var password = $('#password').val()

    console.log(email,password)

    $.ajax({
        url: 'http://localhost:8080/greenshadow/api/v1/auth/signin',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ email, password }),
        success: function (response) {
            // Store the token from the response
            localStorage.setItem('token', response.token);
            localStorage.setItem('email',email)
            window.location.href = "dashboard.html";
        },
        error: function () {
           alert("Error while login")
        }
    });
});

