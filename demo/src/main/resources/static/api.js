function getPatient() {
	var id = encodeURIComponent(document.getElementById("patientId").value.trim());
	var tablels = document.getElementById("content");

	fetch('http://localhost:8080/api/patients/'+id)
	  .then(function(response) {
	  	console.log(response);
	    return response.json();
	  })
	  .then(function(res) {
	    // clean table
	    while (tablels.firstChild) {
		    tablels.removeChild(tablels.firstChild);
		}
	    // populate table
	    var header = document.createElement('tr');
	    header.innerHTML = '<td>Patient Id</td><td>Sex</td><td>Vital Status</td>'
	    	+ '<td>Smoking History</td><td>Overall Months</td><td>Overall Status</td>';
	    tablels.appendChild(header);

	    var tablebody = document.createElement('tbody');
	    for (var i=0; i <res.length; i++) {
	    	var row = document.createElement('tr');
	    	var td1 = document.createElement('td');
	    	td1.innerHTML = res[i].patientId;
	    	var td2 = document.createElement('td');
	    	td2.innerHTML = res[i].sex;
	    	var td3 = document.createElement('td');
	    	td3.innerHTML = res[i].vitalStatus;
	    	var td4 = document.createElement('td');
	    	td4.innerHTML = res[i].smokingHistory;
	    	var td5 = document.createElement('td');
	    	td5.innerHTML = res[i].osMonths;
	    	var td6 = document.createElement('td');
	    	td6.innerHTML = res[i].osStatus;

	    	row.appendChild(td1);
	    	row.appendChild(td2);
	    	row.appendChild(td3);
	    	row.appendChild(td4);
	    	row.appendChild(td5);
	    	row.appendChild(td6);

			tablebody.appendChild(row);
		}
		tablels.appendChild(tablebody);
	  });	 
}