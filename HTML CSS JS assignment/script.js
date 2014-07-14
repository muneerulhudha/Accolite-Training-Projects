key = 1;
function select(){
		
		key =parseInt(document.getElementById("select").value);
		var obj1 = document.getElementById("firstValue");
		var obj2 = document.getElementById("secondValue");
		clearFields();
		if(key===1)
			{
				
				obj1.placeholder = "KM";
				obj2.placeholder = "Miles";
			}
		else if(key===2)
			{
				obj1.placeholder = "KG";
				obj2.placeholder = "LBS";
			}

		else if(key===3)
			{
				obj1.placeholder = "Temperature in C";
				obj2.placeholder = "Temperature in F";
			}

	}
function clearFields(){
		var obj1= document.getElementById("firstValue");
		obj1.value = null;
		var obj2= document.getElementById("secondValue");
		obj2.value = null;
	}

function convert1(){
		var obj1= document.getElementById("firstValue");
		var obj2= document.getElementById("secondValue");      			           

		if (key===1)
		{
			if(!isNaN(obj1.value))
				obj2.value = obj1.value * 0.624;
			else
				alert("Not a number");
		}
		else if (key===2)
		{
			if(!isNaN(obj1.value))
				obj2.value = obj1.value * 2.26;
			else
				alert("Not a number");
		}
		else if (key===3)
		{
			if(!isNaN(obj1.value))
				obj2.value = obj1.value * (9/5) + 32;
			else
				alert("Not a number");
		}
	}

function convert2(){
		var obj1= document.getElementById("firstValue");
		var obj2= document.getElementById("secondValue"); 			           

		if (key===1)
		{
			if(!isNaN(obj2.value))
				obj1.value = obj2.value / 0.624;
			else
				alert("Not a number");
		}
		else if (key===2)
		{
			if(!isNaN(obj2.value))
				obj1.value = obj2.value / 2.26;
			else
				alert("Not a number");
		}
		else if (key===3)
		{
			if(!isNaN(obj2.value))
				obj1.value=(obj2.value-32)*5/9;
			else
				alert("Not a number");
		}
	}

function selectInput(){
			var dis1 = document.getElementById("firstValue");
			dis1.onchange = function () {
			if (this.value != "" || this.value.length > 0) {
		   		document.getElementById("secondValue").disabled = true;
		  	}
		  	else
		  		document.getElementById("secondValue").disabled = false;
			}
			var dis2 = document.getElementById("secondValue");
			dis2.onchange = function () {
			if (this.value != "" || this.value.length > 0) {
   				document.getElementById("firstValue").disabled = true;
  	 		}
			else
				document.getElementById("firstValue").disabled = false;
			}	
	}