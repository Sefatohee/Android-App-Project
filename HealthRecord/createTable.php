<?php

	class CreateTableUser_details{


		function TableUser_details($conn){
			$sqlCreateTable = "Create Table User_details(
			Id INT(10) AUTO_INCREMENT PRIMARY KEY,
		    Email char(30),
		    Year Char(10),
		    Month char(30),
		    Date_  char(30) UNIQUE,
		    Sugar_level double(10,2),
		    Consumed_Calory double(10,2),
		    Systolic_Rate double(10,2),
		    Diastolic_Rate double(10,2),
		    Weight double(10,2)

		);";
		    $conn->query($sqlCreateTable);
		}
	}

?>