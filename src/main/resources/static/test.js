$(document).ready(function () {
    console.log("Ready!")
    $.get('/get', function (data) {


        let table = "<table class=\"table table-striped table-hover\">\n" +
            "                                        <thead>\n" +
            "                                        <tr>\n" +
            "                                            <td><h5>ID</h5></td>\n" +
            "                                            <td><h5>First Name</h5></td>\n" +
            "                                            <td><h5>Last Name</h5></td>\n" +
            "                                            <td><h5>Age</h5></td>\n" +
            "                                            <td><h5>Email</h5></td>\n" +
            "                                            <td><h5>Role</h5></td>\n" +
            "                                            <td><h5>Edit</h5></td>\n" +
            "                                            <td><h5>Delete</h5></td>\n" +
            "                                        </tr>\n" +
            "                                        </thead>\n" +
            "                                        <tbody>"

        for (let i = 0; i < data.length; i++) {
            table = table + "<tr><td>" +
                data[i].id + "</td><td>" +
                data[i].firstName + "</td><td>" +
                data[i].lastName + "</td><td>" +
                data[i].age + "</td><td>" +
                data[i].email + "</td><td>" +
                data[i].role + "<td>\n" +
                "  <button class=\"btn btn-info\" role=\"button\" data-toggle=\"modal\"\n" +
                "   data-target=\"#exampleModal\">\n" +
                "   Edit\n" +
                "  </button>\n" +
                " </td>\n" +
                " <td>\n" +
                "  <button class=\"btn btn-danger\" role=\"button\" data-toggle=\"modal\"\n" +
                "    data-target=\"#exampleModal\">\n" +
                "   Delete\n" +
                "  </button>\n" +
                " </td>\n" +
                "</tr>"
        }

        table = table + "</tbody>";

        $("allUsersTable").html(table);
    })
})

// data.id = $("userId").val()
// data.firstName = $("userFirstName");
// data.lastName = $("userLastName");
// data.age = $("userAge");
// data.email = $("userEmail");
// data.role = $("userRole");


// $("createNewUser").on("click", function () {
//     const firstName = $("newUserFirstName").val().trim();
//     const lastName = $("newUserLastName").val().trim();
//     const age = $("newUserAge").val().trim();
//     const email = $("newUserEmail").val().trim();
//     const password = $("newUserPassword").val().trim();
//     const role = $("newUserRole").val().trim();
//
//     if (firstName === "") {
//         $("errorMessage").text("Введите имя")
//         return false;
//     }
//     if (lastName === "") {
//         $("errorMessage").text("Введите фамилию")
//         return false;
//     }
//     if (age === "") {
//         $("errorMessage").text("Введите возраст")
//         return false;
//     }
//     if (email === "") {
//         $("errorMessage").text("Введите email")
//         return false;
//     }
//     if (password === "") {
//         $("errorMessage").text("Введите пароль")
//         return false;
//     }
//     if (role === "") {
//         $("errorMessage").text("Выберите роль")
//         return false;
//     }
//
//     $.ajax({
//         url: 'api/create',
//         type: 'POST',
//         data: {
//             "email": email,
//             "password": password,
//             "firstName": firstName,
//             "lastName": lastName,
//             "age": age,
//             "enabled": true,
//             "authorities": [{"role": "ROLE_" + role, "authority": "ROLE_" + role}],
//             "accountNonLocked": true,
//             "credentialsNonExpired": true,
//             "accountNonExpired": true
//         },
//         dataType: 'html'
//     })
// });