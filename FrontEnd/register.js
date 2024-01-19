document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("registrationForm").addEventListener("submit", function(event){
        event.preventDefault();

        var age = calculateAge();
        var ageErrorElement = document.getElementById("ageError");

        if (age >= 16) {
            ageErrorElement.style.display = "none";

            var formData = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                addressNum: document.getElementById('addressNum').value,
                street: document.getElementById('street').value,
                PostalCode: document.getElementById('PostalCode').value,
                birthdate: document.getElementById('birthdate').value,
                licenseNumber: document.getElementById('licenseNumber').value,
                creditCardDetails: document.getElementById('creditCardDetails').value
            };

            fetch('/api/submit-form', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                    window.location.href = '../Rent/rent.html';
                })
                .catch((error) => {
                    console.error('Error:', error);
                    ageErrorElement.textContent = "Registration failed: " + error;
                    ageErrorElement.style.display = "block";
                });

        } else {
            ageErrorElement.style.display = "block";
        }
    });

    Post

    function calculateAge() {
        var birthdate = new Date(document.getElementById("birthdate").value);
        var today = new Date();
        var age = today.getFullYear() - birthdate.getFullYear();
        var m = today.getMonth() - birthdate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthdate.getDate())) {
            age--;
        }
        return age;
    }
});


function RegisterPOST() {
    var formData = new FormData(document.getElementById("registrationForm"));
    var jsonData = {};

    formData.forEach(function(value, key){
        jsonData[key] = value;
    });

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/RegisterServlet", true); // Ensure this matches your server endpoint
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Registration successful!");
            // Redirect or update UI as needed
        } else if (xhr.readyState === 4) {
            console.error("Registration failed: ", xhr.status);
            // Handle error case
        }
    };
    xhr.send(JSON.stringify(jsonData));
}










