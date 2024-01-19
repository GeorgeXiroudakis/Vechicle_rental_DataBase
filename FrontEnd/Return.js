document.addEventListener('DOMContentLoaded', () => {
    initializeDateTimeInputs();
    document.getElementById('submitReturnForm').addEventListener('click', handleSubmit);
});

function initializeDateTimeInputs() {
    const currentDate = new Date();
    document.getElementById('returnDate').value = currentDate.toISOString().split('T')[0];
    document.getElementById('returnTime').value = currentDate.toTimeString().split(' ')[0].substring(0, 5);
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('submitReturnForm').addEventListener('click', handleSubmit);
});

function handleSubmit() {
    // Retrieve form values
    const rentNumber = document.getElementById('rentNumber').value;
    const returnReason = document.getElementById('Returnreason').value;
    const returnDate = document.getElementById('returnDate').value;
    const returnTime = document.getElementById('returnTime').value;

    // Check if all fields are filled
    if (!rentNumber || !returnReason || !returnDate || !returnTime) {
        alert('Please fill in all fields.');
        return;
    }

    // Store the data (example using localStorage)
    localStorage.setItem('rentNumber', rentNumber);
    localStorage.setItem('returnReason', returnReason);
    localStorage.setItem('returnDate', returnDate);
    localStorage.setItem('returnTime', returnTime);

    // Display a confirmation message
    alert('Return details submitted successfully.');

    // Redirect to Home.html
    window.location.href = 'Home.html';
}

fuction

function ReturnPOST() {
    document.addEventListener('DOMContentLoaded', () => {
        initializeDateTimeInputs();
        document.getElementById('submitReturnForm').addEventListener('click', handleSubmit);
    });

    function initializeDateTimeInputs() {
        const currentDate = new Date();
        document.getElementById('returnDate').value = currentDate.toISOString().split('T')[0];
        document.getElementById('returnTime').value = currentDate.toTimeString().split(' ')[0].substring(0, 5);
    }

    document.addEventListener('DOMContentLoaded', () => {
        document.getElementById('submitReturnForm').addEventListener('click', handleSubmit);
    });

    function handleSubmit() {
        // Retrieve form values
        const rentNumber = document.getElementById('rentNumber').value;
        const returnReason = document.getElementById('Returnreason').value;
        const returnDate = document.getElementById('returnDate').value;
        const returnTime = document.getElementById('returnTime').value;

        // Check if all fields are filled
        if (!rentNumber || !returnReason || !returnDate || !returnTime) {
            alert('Please fill in all fields.');
            return;
        }

        // Store the data (example using localStorage)
        localStorage.setItem('rentNumber', rentNumber);
        localStorage.setItem('returnReason', returnReason);
        localStorage.setItem('returnDate', returnDate);
        localStorage.setItem('returnTime', returnTime);

        // Display a confirmation message
        alert('Return details submitted successfully.');

        // Redirect to Home.html
        window.location.href = 'Home.html';
    }
}

ReturnPOST()
{

    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("returnForm").addEventListener("submit", function(event){
            event.preventDefault();

            var formData = {
                rentNumber: document.getElementById('rentNumber').value,
                returnReason: document.getElementById('returnReason').value,
                returnDate: document.getElementById('returnDate').value,
                returnTime: document.getElementById('returnTime').value
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
                    window.location.href = '../Home/Home.html';
                })
                .catch((error) => {
                    console.error('Error:', error);
                    ageErrorElement.textContent = "Registration failed: " + error;
                    ageErrorElement.style.display = "block";
                });
        });
    });
}

