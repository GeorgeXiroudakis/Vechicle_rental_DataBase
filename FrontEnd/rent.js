document.getElementById("rentalForm").addEventListener("submit", function(event){
    event.preventDefault();

    const birthdate = new Date(document.getElementById("birthdate").value);
    const age = calculateAge(birthdate);
    const vehicleType = document.getElementById("vehicleSelection").value;

    if (age < 18 && (vehicleType === "car" || vehicleType === "motorcycle")) {
        alert("Only adults over 18 can reserve a car or motorcycle.");
        return;
    }

    submitForm();
});




document.getElementById("birthdate").addEventListener("change", function() {
    const birthdate = new Date(this.value);
    const age = calculateAge(birthdate);
    const vehicleSelect = document.getElementById("vehicleSelection");
    updateVehicleOptions(vehicleSelect, age);
});
/*--------------------------------------------------podilato----------------------------------------------------------------*/
document.getElementById("vehicleSelection").addEventListener("change", function() {
    const selectedVehicle = this.value;
    if (selectedVehicle === "bicycle") {
        displayBicycles();
    } else {
        document.getElementById("bicycleDetails").style.display = "none";
    }
});

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

function updateVehicleOptions(selectElement, age) {
    if (age >= 18) {
        selectElement.innerHTML = `
            <option value="bicycle">Bicycle</option>
            <option value="scooter">Scooter</option>
            <option value="car">Car</option>
            <option value="motorcycle">Motorcycle</option>
        `;
    } else {
        selectElement.innerHTML = `
            <option value="bicycle">Bicycle</option>
            <option value="scooter">Scooter</option>
        `;
    }
}

function displayBicycles() {
    const bicycles = [
        { brand: "Trek", model: "Emonda", color: "Red", autonomy: 120, registrationNumber: "TRK123", rentalCost: 15, insuranceCost: 5 ,
            },
        { brand: "Giant", model: "Defy", color: "Blue", autonomy: 150, registrationNumber: "GNT456", rentalCost: 20, insuranceCost: 6,
             },
        { brand: "Specialized", model: "Tarmac", color: "Black", autonomy: 110, registrationNumber: "SPZ789", rentalCost: 18, insuranceCost: 7,
            },
        { brand: "Cannondale", model: "Synapse", color: "Green", autonomy: 130, registrationNumber: "CND012", rentalCost: 17, insuranceCost: 8,
            },
        { brand: "Bianchi", model: "Infinito", color: "White", autonomy: 140, registrationNumber: "BNC345", rentalCost: 16, insuranceCost: 9 ,
            }
    ];

    const bicycleDetailsContainer = document.getElementById("bicycleDetails");
    bicycleDetailsContainer.innerHTML = '';
    bicycles.forEach((bicycle, index) => {
        bicycleDetailsContainer.innerHTML += generateBicycleHTML(bicycle, index + 1);
    });
    bicycleDetailsContainer.style.display = "block";
}

function generateBicycleHTML(bicycle, index) {
    return `
        <fieldset>
            <legend>Bicycle ${index}</legend>
            <label>Brand: ${bicycle.brand}</label><br>
            <label>Model: ${bicycle.model}</label><br>
            <label>Color: ${bicycle.color}</label><br>
            <label>Autonomy (km): ${bicycle.autonomy}</label><br>
            <label>Registration Number: ${bicycle.registrationNumber}</label><br>
            <label>Daily Rental Cost: ${bicycle.rentalCost}</label><br>
            <label>Insurance Cost: ${bicycle.insuranceCost}</label><br>
            
            
            <button type="button" onclick="selectBicycle(${index})">SELECT${index}</button>
        </fieldset>
    `;
}

function selectBicycle(index) {
    console.log(`Bicycle ${index} selected`);
    // Add logic for handling bicycle selection
}
/*--------------------------------------------------scooters----------------------------------------------------------------*/
document.getElementById("vehicleSelection").addEventListener("change", function() {
    const selectedVehicle = this.value;
    if (selectedVehicle === "scooter") {
        displayScooters();
    } else {
        document.getElementById("scooterDisplay").style.display = "none";
    }
});

function displayScooters() {
    const scooters = [
        { brand: "Yamaha", model: "NMAX", color: "Red", autonomy: 200, registrationNumber: "YM123", rentalCost: 10, insuranceCost: 4 ,
            },
        { brand: "Honda", model: "PCX", color: "Blue", autonomy: 220, registrationNumber: "HN456", rentalCost: 12, insuranceCost: 5 ,
            },
        { brand: "Vespa", model: "GTS", color: "Green", autonomy: 180, registrationNumber: "VP789", rentalCost: 15, insuranceCost: 6 ,
            },
        { brand: "Piaggio", model: "Liberty", color: "Black", autonomy: 210, registrationNumber: "PG012", rentalCost: 11, insuranceCost: 4,
           },
        { brand: "Aprilia", model: "SRV", color: "White", autonomy: 190, registrationNumber: "AP345", rentalCost: 13, insuranceCost: 5,
           }
    ];

    const scooterDisplayContainer = document.getElementById("scooterDisplay");
    scooterDisplayContainer.innerHTML = '';
    scooters.forEach((scooter, index) => {
        scooterDisplayContainer.innerHTML += generateScooterHTML(scooter, index + 1);
    });
    scooterDisplayContainer.style.display = "block";
}

function generateScooterHTML(scooter, index) {
    return `
        <fieldset>
            <legend>Scooter ${index}</legend>
            <label>Brand: ${scooter.brand}</label><br>
            <label>Model: ${scooter.model}</label><br>
            <label>Color: ${scooter.color}</label><br>
            <label>Autonomy (km): ${scooter.autonomy}</label><br>
            <label>Registration Number: ${scooter.registrationNumber}</label><br>
            <label>Daily Rental Cost: ${scooter.rentalCost}</label><br>
            <label>Insurance Cost: ${scooter.insuranceCost}</label><br>
            
            
            <button type="button" onclick="selectScooter(${index})">SELECT${index}</button>
        </fieldset>
    `;
}

function selectScooter(index) {
    console.log(`Scooter ${index} selected`);
    // Add logic for handling scooter selection
}
/*--------------------------------------------------car----------------------------------------------------------------*/
document.getElementById("vehicleSelection").addEventListener("change", function() {
    const selectedVehicle = this.value;
    if (selectedVehicle === "car") {
        displayCars();
    } else {
        document.getElementById("carDisplay").style.display = "none";
    }
});

function displayCars() {
    const cars = [
        { brand: "Toyota", model: "Corolla", color: "Red", autonomy: 500, registrationNumber: "TY123",
            rentalCost: 40, insuranceCost: 10 , passengerNumber: 4,type: "SUV",},
        { brand: "Honda", model: "Civic", color: "Blue", autonomy: 550, registrationNumber: "HN456",
            rentalCost: 45, insuranceCost: 12 , passengerNumber: 5,type: "VAN",},
        { brand: "Volkswagen", model: "Golf", color: "Green", autonomy: 450, registrationNumber: "VW789",
            rentalCost: 50, insuranceCost: 15, passengerNumber: 6, type: "CityCar",},
        { brand: "Mercedes", model: "A-Class", color: "Black", autonomy: 480, registrationNumber: "MC012",
            rentalCost: 35, insuranceCost: 8, passengerNumber: 3 , type: "CABRIO",}
            ,
        { brand: "BMW", model: "1-Series", color: "White", autonomy: 520, registrationNumber: "BM345",
            rentalCost: 30, insuranceCost: 7, passengerNumber: 4 , type: "TRACK",}

    ];

    const carDisplayContainer = document.getElementById("carDisplay");
    carDisplayContainer.innerHTML = '';
    cars.forEach((car, index) => {
        carDisplayContainer.innerHTML += generateCarHTML(car, index + 1);
    });
    carDisplayContainer.style.display = "block";
}

function generateCarHTML(car, index) {
    return `
        <fieldset>
            <legend>Car ${index}</legend>
            <label>Brand: ${car.brand}</label><br>
            <label>Model: ${car.model}</label><br>
            <label>Color: ${car.color}</label><br>
            <label>Autonomy (km): ${car.autonomy}</label><br>
            <label>Registration Number: ${car.registrationNumber}</label><br>
            <label>Daily Rental Cost: ${car.rentalCost}</label><br>
            <label>Insurance Cost: ${car.insuranceCost}</label><br>
            <label>Number of Passengers: ${car.passengerNumber}</label><br>
            <label>Type: ${car.type}</label><br>
            
            
            <button type="button" onclick="selectCar(${index})">SELECT${index}</button>
        </fieldset>
    `;
}

function selectCar(index) {
    console.log(`Car ${index} selected`);
    // Add logic for handling car selection
}

/*--------------------------------------------------motorcycle----------------------------------------------------------------*/
document.getElementById("vehicleSelection").addEventListener("change", function() {
    const selectedVehicle = this.value;
    if (selectedVehicle === "motorcycle") {
        displayMotorcycles();
    } else {
        document.getElementById("motorcycleDisplay").style.display = "none";
    }
});

function displayMotorcycles() {
    const motorcycles = [
        { brand: "Yamaha", model: "MT-07", color: "Red", autonomy: 300, registrationNumber: "YM123", rentalCost: 20, insuranceCost: 5 ,},
        { brand: "Honda", model: "CBR", color: "Blue", autonomy: 350, registrationNumber: "HN456", rentalCost: 25, insuranceCost: 6 ,},
        { brand: "BMW", model: "S1000RR", color: "Black", autonomy: 330, registrationNumber: "BM012", rentalCost: 32, insuranceCost: 8 ,},
        { brand: "Kawasaki", model: "Ninja", color: "Green", autonomy: 280, registrationNumber: "KW345", rentalCost: 30, insuranceCost: 7 ,},
        { brand: "Ducati", model: "Monster", color: "White", autonomy: 290, registrationNumber: "DC345", rentalCost: 21, insuranceCost: 6}
    ];

    const motorcycleDisplayContainer = document.getElementById("motorcycleDisplay");
    motorcycleDisplayContainer.innerHTML = '';
    motorcycles.forEach((motorcycle, index) => {
        motorcycleDisplayContainer.innerHTML += generateMotorcycleHTML(motorcycle, index + 1);
    });
    motorcycleDisplayContainer.style.display = "block";
}

function generateMotorcycleHTML(motorcycle, index) {
    return `
        <fieldset>
            <legend>Motorcycle ${index}</legend>
            <label>Brand: ${motorcycle.brand}</label><br>
            <label>Model: ${motorcycle.model}</label><br>
            <label>Color: ${motorcycle.color}</label><br>
            <label>Autonomy (km): ${motorcycle.autonomy}</label><br>
            <label>Registration Number: ${motorcycle.registrationNumber}</label><br>
            <label>Daily Rental Cost: ${motorcycle.rentalCost}</label><br>
            <label>Insurance Cost: ${motorcycle.insuranceCost}</label><br>
         
            <button type="button" onclick="selectMotorcycle(${index})">SELECT${index}</button>
        </fieldset>
    `;
}

function selectMotorcycle(index) {
    console.log(`Motorcycle ${index} selected`);
    // Add logic for handling motorcycle selection
}

/*--------------------------------------------------submit----------------------------------------------------------------*/
document.addEventListener("DOMContentLoaded", function() {
    // Add event listener for the rental form submission
    document.getElementById("rentalForm").addEventListener("submit", function(event){
        event.preventDefault();

        if (isAgeValid()) {
            alert("Thank you for your submission!");
            window.location.href = 'Home.html';
        } else {
            document.getElementById("ageError").style.display = "block";
        }
    });

    // Event listener for the birthdate change
    document.getElementById("birthdate").addEventListener("change", function(event){
        updateAgeValidationDisplay();
    });

    // Add event listeners to select buttons
    var selectButtons = document.getElementsByClassName("select-button");
    for (var i = 0; i < selectButtons.length; i++) {
        selectButtons[i].addEventListener("click", function(event){
            this.classList.toggle("selected-button");
        });
    }

    // Function to check if age is valid
    function isAgeValid() {
        var age = calculateAge();
        return age >= 16;
    }

    // Function to update the age validation display
    function updateAgeValidationDisplay() {
        var ageErrorElement = document.getElementById("ageError");
        if (!isAgeValid()) {
            ageErrorElement.style.display = "block";
        } else {
            ageErrorElement.style.display = "none";
        }
    }
});
//-----------------------------------MARKED-------------------------------------------------------------------------------------//


document.addEventListener('DOMContentLoaded', function() {
    // Function to get query parameters
    function getQueryParams() {
        const queryParams = new URLSearchParams(window.location.search);
        return {
            rentCost: queryParams.get('RentCost'),
            rentNum: queryParams.get('RentNum')
        };
    }

    // Function to submit the form
    function submitForm() {
        console.log("Form submitted successfully");

        // Retrieve RentCost and RentNum values
        const { rentCost, rentNum } = getQueryParams();

        // Display an alert with RentCost and RentNum
        alert(`Thank you for your submission! Rental Cost: ${rentCost}, Rental Number: ${rentNum}`);


    }
    window.submitForm = submitForm;
});
function RentPOST() {
    var formData = new FormData(document.getElementById("rentalForm"));
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
/*

document.addEventListener('DOMContentLoaded', function() {
    // Function to get query parameters
    function getQueryParams() {
        const queryParams = new URLSearchParams(window.location.search);
        return {
            rentCost: queryParams.get('RentCost'),
            rentNum: queryParams.get('RentNum')
        };
    }

    // Function to submit the form
    function submitForm() {
        console.log("Form submitted successfully");

        // Retrieve RentCost and RentNum values
        const { rentCost, rentNum } = getQueryParams();

        // Display an alert with RentCost and RentNum
        alert(`Thank you for your submission! Rental Cost: ${rentCost}, Rental Number: ${rentNum}`);
    }
    window.submitForm = submitForm;
, function() {
    // Function to get query parameters
    function getQueryParams() {
        const queryParams = new URLSearchParams(window.location.search);
        return {
            rentCost: queryParams.get('RentCost'),
            rentNum: queryParams.get('RentNum')
        };
    }

    // Function to submit the form
    function submitForm() {
        console.log("Form submitted successfully");

        // Retrieve RentCost and RentNum values
        const { rentCost, rentNum } = getQueryParams();

        // Display an alert with RentCost and RentNum
        alert(`Thank you for your submission! Rental Cost: ${rentCost}, Rental Number: ${rentNum}`);
    }
    window.submitForm = submitForm;
}

*/
